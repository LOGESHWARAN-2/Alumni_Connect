import { useEffect } from "react";
import { useSocketContext } from "../context/SocketContext";

const useListenMessages = ({ onMessageReceived } = {}) => {
  const { socket } = useSocketContext();

  useEffect(() => {
    if (!socket) return;

    const handleMessage = (message) => {
      console.log("Message received in hook:", message);
      if (onMessageReceived) {
        onMessageReceived(message);
      }
    };

    // Listen for receiveMessage event
    socket.on("receiveMessage", handleMessage);

    // Cleanup the event listener on unmount
    return () => {
      socket.off("receiveMessage", handleMessage);
    };
  }, [socket, onMessageReceived]);
};

export default useListenMessages;
