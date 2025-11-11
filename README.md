# Research Project Tracker – Starter (Spring Boot + React)

## Run Backend
1. Ensure MySQL is running and credentials match `backend/src/main/resources/application.yml` (default root/root).
2. From `backend/`: `mvn spring-boot:run`

## Run Frontend (Vite)
1. From `frontend/`: `npm install`
2. `npm start` (alias for `vite`) and open http://localhost:5173 (or shown port).

## Auth Quick Test
- POST `http://localhost:8080/api/auth/signup` with `{ "fullName":"Test", "username":"a@a.com", "password":"1234" }`
- Then login at `/api/auth/login` to get a token.
- Use the token as `Authorization: Bearer <token>` for protected endpoints.

> NOTE: When creating a project from the UI, set a real PI user id (placeholder is present). You can extend `/api/users` endpoints if your rubric requires.
