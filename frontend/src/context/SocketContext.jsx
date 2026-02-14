import React, {
  createContext,
  useContext,
  useEffect,
  useState,
  useRef,
} from "react";
import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import { useAuth } from "./AuthContext";

// Create a context for socket
const SocketContext = createContext();

// Custom hook to access the socket context
export const useSocket = () => {
  return useContext(SocketContext);
};

export const useSocketContext = () => {
  const context = useContext(SocketContext);
  if (!context) {
    throw new Error("useSocketContext must be used within a SocketProvider");
  }
  return context;
};

// SocketProvider component which will wrap the app and provide the socket connection
export const SocketProvider = ({ children }) => {
  const [socket, setSocket] = useState(null);
  const [connected, setConnected] = useState(false);
  const { user } = useAuth();
  const stompClientRef = useRef(null);

  useEffect(() => {
    if (!user?._id) {
      console.log("No user found, skipping socket initialization");
      return;
    }

    console.log("Initializing WebSocket connection for user:", user._id);

    // Create SockJS connection
    const sockJS = new SockJS("http://localhost:8080/ws");

    const stompClient = new Client({
      webSocketFactory: () => sockJS,

      debug: (str) => {
        console.log("STOMP:", str);
      },

      onConnect: () => {
        console.log("✅ WebSocket Connected for user:", user._id);
        setConnected(true);

        // Subscribe to receive messages for this user
        stompClient.subscribe(`/topic/messages/${user._id}`, (message) => {
          const receivedMessage = JSON.parse(message.body);
          console.log("📩 New message received:", receivedMessage);

          // Emit custom event for message listeners
          const event = new CustomEvent("newMessage", {
            detail: receivedMessage,
          });
          window.dispatchEvent(event);
        });

        // Notify server that user is online
        stompClient.publish({
          destination: "/app/chat.online",
          body: JSON.stringify({ userId: user._id, online: true }),
        });
      },

      onStompError: (frame) => {
        console.error("❌ STOMP error:", frame.headers["message"]);
        setConnected(false);
      },

      onWebSocketClose: () => {
        console.log("WebSocket connection closed");
        setConnected(false);
      },
    });

    stompClientRef.current = stompClient;
    stompClient.activate();

    // Create a custom socket object that mimics Socket.IO API
    const customSocket = {
      connected,
      on: (event, callback) => {
        if (event === "receiveMessage") {
          window.addEventListener("newMessage", (e) => callback(e.detail));
        }
      },
      off: (event, callback) => {
        if (event === "receiveMessage") {
          window.removeEventListener("newMessage", (e) => callback(e.detail));
        }
      },
      emit: (event, data) => {
        if (event === "registerUser") {
          console.log("User registered:", data);
        }
      },
      sendMessage: (senderId, receiverId, messageText, isAlumni) => {
        if (stompClient && stompClient.connected) {
          const messageData = {
            senderId,
            receiverId,
            message: messageText,
            isAlumni: isAlumni || false,
          };

          console.log("📤 Sending message:", messageData);

          stompClient.publish({
            destination: "/app/chat.send",
            body: JSON.stringify(messageData),
          });
        } else {
          console.error("❌ STOMP client not connected");
        }
      },
    };

    setSocket(customSocket);

    return () => {
      console.log("Cleaning up WebSocket connection");
      if (stompClientRef.current) {
        stompClientRef.current.deactivate();
      }
    };
  }, [user]);

  return (
    <SocketContext.Provider value={{ socket, connected }}>
      {children}
    </SocketContext.Provider>
  );
};

export default SocketContext;
