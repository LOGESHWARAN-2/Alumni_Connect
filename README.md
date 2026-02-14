# 🎓 AlumniConnect

A full-stack alumni networking platform built with **React** (frontend) and **Spring Boot** (backend). Connect students with alumni, facilitate mentorship, host events, and enable real-time messaging.


## 🛠️ Tech Stack

**Frontend:** React + Vite + TailwindCSS + WebSocket  
**Backend:** Spring Boot + MongoDB + JWT + Cloudinary  
**Database:** MongoDB Atlas

## ⚡ Quick Start

### Backend
```bash
cd backend
# Configure application.properties with MongoDB URI, JWT secret, Cloudinary keys
mvn spring-boot:run
# Runs on http://localhost:8080
```

### Frontend
```bash
cd frontend
npm install
# Create .env with VITE_API_BASE_URL=http://localhost:8080
npm run dev
# Runs on http://localhost:5173
```

## 🔑 Key Features

- Dual registration (Students & Alumni)
- JWT authentication
- Real-time chat (WebSocket)
- Post/comment/like system
- Event scheduling
- Profile management
- Admin verification for alumni

## 📝 Requirements

- Java 17+
- Node.js 16+
- MongoDB Atlas account
- Cloudinary account


---

⭐ Star this repo if you found it helpful!
