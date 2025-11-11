import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../api/axios';

type Project = { id: string; title: string; summary: string; status: string };

export default function ProjectDetail(){
  const { id } = useParams();
  const [p, setP] = useState<Project | null>(null);

  useEffect(() => { (async()=>{ const { data } = await api.get(`/api/projects/${id}`); setP(data); })(); }, [id]);

  if(!p) return <div>Loading...</div>;
  return (
    <div>
      <h3>{p.title}</h3>
      <p className="text-muted">{p.summary}</p>
      <p><b>Status:</b> {p.status}</p>
    </div>
  );
}
