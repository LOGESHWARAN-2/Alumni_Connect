import { Client } from "@stomp/stompjs";
import SockJS from "sockjs-client";

let stompClient = null;

// Connect to WebSocket
export const connectSocket = (userId, onMessageReceived) => {
  // Create SockJS connection
  const socket = new SockJS("http://localhost:8080/ws");

  stompClient = new Client({
    webSocketFactory: () => socket,

    debug: (str) => {
      console.log("STOMP:", str);
    },

    onConnect: () => {
      console.log("✅ WebSocket Connected for user:", userId);

      // Subscribe to receive messages for this user
      stompClient.subscribe(`/topic/messages/${userId}`, (message) => {
        const receivedMessage = JSON.parse(message.body);
        console.log("📩 New message received:", receivedMessage);

        // Call the callback function to handle the message
        if (onMessageReceived) {
          onMessageReceived(receivedMessage);
        }
      });

      // Notify server that user is online (optional)
      stompClient.publish({
        destination: "/app/chat.online",
        body: JSON.stringify({ userId, online: true }),
      });
    },

    onStompError: (frame) => {
      console.error("❌ STOMP error:", frame.headers["message"]);
      console.error("Error details:", frame.body);
    },

    onWebSocketClose: () => {
      console.log("WebSocket connection closed");
    },
  });

  // Activate the connection
  stompClient.activate();
};

// Send a message
export const sendMessage = (senderId, receiverId, messageText, isAlumni) => {
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
    console.error("❌ STOMP client not connected. Cannot send message.");
  }
};

// Disconnect socket
export const disconnectSocket = () => {
  if (stompClient) {
    stompClient.deactivate();
    console.log("WebSocket disconnected");
  }
};

export default { connectSocket, sendMessage, disconnectSocket };
