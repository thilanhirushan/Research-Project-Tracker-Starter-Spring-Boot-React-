import { Link, NavLink } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

export default function NavBar(){
  const { user, logout } = useAuth();
  return (
    <nav className="navbar navbar-expand navbar-light bg-light px-3">
      <Link className="navbar-brand" to="/projects">ResearchTracker</Link>
      <div className="navbar-nav">
        <NavLink className="nav-link" to="/projects">Projects</NavLink>
        <NavLink className="nav-link" to="/milestones">Milestones</NavLink>
        <NavLink className="nav-link" to="/documents">Documents</NavLink>
        {user?.role === 'ADMIN' && <NavLink className="nav-link" to="/admin">Admin</NavLink>}
      </div>
      <div className="ms-auto">
        {user ? (
          <>
            <span className="me-3">{user.fullName} ({user.role})</span>
            <button className="btn btn-outline-secondary btn-sm" onClick={logout}>Logout</button>
          </>
        ) : (
          <>
            <NavLink className="btn btn-outline-primary btn-sm me-2" to="/login">Login</NavLink>
            <NavLink className="btn btn-primary btn-sm" to="/register">Sign Up</NavLink>
          </>
        )}
      </div>
    </nav>
  );
}
