import { Routes, Route, Navigate } from 'react-router-dom';
import NavBar from './components/NavBar';
import Login from './pages/Login';
import Register from './pages/Register';
import Projects from './pages/Projects';
import ProjectDetail from './pages/ProjectDetail';
import Milestones from './pages/Milestones';
import Documents from './pages/Documents';
import Admin from './pages/Admin';
import PrivateRoute from './routes/PrivateRoute';

export default function App(){
  return (
    <div>
      <NavBar />
      <div className="container py-3">
        <Routes>
          <Route path="/" element={<Navigate to="/projects" />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />

          <Route path="/projects" element={<PrivateRoute><Projects/></PrivateRoute>} />
          <Route path="/projects/:id" element={<PrivateRoute><ProjectDetail/></PrivateRoute>} />
          <Route path="/milestones" element={<PrivateRoute><Milestones/></PrivateRoute>} />
          <Route path="/documents" element={<PrivateRoute><Documents/></PrivateRoute>} />
          <Route path="/admin" element={<PrivateRoute><Admin/></PrivateRoute>} />
        </Routes>
      </div>
    </div>
  );
}
