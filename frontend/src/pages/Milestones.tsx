import { useState } from 'react';
import api from '../api/axios';

export default function Milestones(){
  const [projectId, setProjectId] = useState('');
  const [items, setItems] = useState<any[]>([]);
  const [title, setTitle] = useState('');

  const load = async () => { const { data } = await api.get(`/api/projects/${projectId}/milestones`); setItems(data); };
  const add = async () => { await api.post(`/api/projects/${projectId}/milestones`, { id: crypto.randomUUID(), title }); setTitle(''); load(); };

  return (
    <div>
      <div className="d-flex gap-2 mb-3">
        <input className="form-control" placeholder="Project ID" value={projectId} onChange={e=>setProjectId(e.target.value)} />
        <button className="btn btn-outline-secondary" onClick={load}>Load</button>
      </div>
      <div className="d-flex gap-2 mb-3">
        <input className="form-control" placeholder="Milestone title" value={title} onChange={e=>setTitle(e.target.value)} />
        <button className="btn btn-primary" onClick={add}>Add</button>
      </div>
      <ul className="list-group">
        {items.map(m => <li key={m.id} className="list-group-item d-flex justify-content-between">{m.title}<span>{m.isCompleted? '✅':'⏳'}</span></li>)}
      </ul>
    </div>
  );
}
