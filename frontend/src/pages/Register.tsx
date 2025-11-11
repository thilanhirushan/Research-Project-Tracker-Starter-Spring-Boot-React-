import { FormEvent, useState } from 'react';
import { useAuth } from '../context/AuthContext';

export default function Register(){
  const { signup } = useAuth();
  const [fullName, setN] = useState('');
  const [username, setU] = useState('');
  const [password, setP] = useState('');
  const [err, setErr] = useState('');

  const submit = async (e: FormEvent) => {
    e.preventDefault(); setErr('');
    try { await signup(fullName, username, password); window.location.href='/projects'; }
    catch (e:any){ setErr(e.response?.data?.error || 'Sign up failed'); }
  };

  return (
    <div className="col-12 col-md-5 mx-auto">
      <h3>Create Account</h3>
      {err && <div className="alert alert-danger">{err}</div>}
      <form onSubmit={submit}>
        <div className="mb-2">
          <label className="form-label">Full name</label>
          <input className="form-control" value={fullName} onChange={e=>setN(e.target.value)} required />
        </div>
        <div className="mb-2">
          <label className="form-label">Email</label>
          <input className="form-control" value={username} onChange={e=>setU(e.target.value)} required />
        </div>
        <div className="mb-3">
          <label className="form-label">Password</label>
          <input type="password" className="form-control" value={password} onChange={e=>setP(e.target.value)} required />
        </div>
        <button className="btn btn-primary w-100">Sign Up</button>
      </form>
    </div>
  );
}
