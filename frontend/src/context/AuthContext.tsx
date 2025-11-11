import React, { createContext, useContext, useState } from 'react';
import api from '../api/axios';

type AuthCtx = {
  user: { username: string; role: string; fullName: string } | null;
  login: (username: string, password: string) => Promise<void>;
  signup: (fullName: string, username: string, password: string) => Promise<void>;
  logout: () => void;
};

const Ctx = createContext<AuthCtx>(null!);
export const useAuth = () => useContext(Ctx);

export const AuthProvider: React.FC<{children: React.ReactNode}> = ({children}) => {
  const [user, setUser] = useState<AuthCtx['user']>(null);

  const login = async (username: string, password: string) => {
    const { data } = await api.post('/api/auth/login', { username, password });
    localStorage.setItem('token', data.token);
    setUser({ username: data.username, role: data.role, fullName: data.fullName });
  };

  const signup = async (fullName: string, username: string, password: string) => {
    const { data } = await api.post('/api/auth/signup', { fullName, username, password });
    localStorage.setItem('token', data.token);
    setUser({ username: data.username, role: data.role, fullName: data.fullName });
  };

  const logout = () => { localStorage.removeItem('token'); setUser(null); };

  return <Ctx.Provider value={{user, login, signup, logout}}>{children}</Ctx.Provider>;
};
