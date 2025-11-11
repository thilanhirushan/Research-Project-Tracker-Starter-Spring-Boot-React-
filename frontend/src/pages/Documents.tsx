import { useState } from 'react';
import api from '../api/axios';

export default function Documents(){
  const [projectId, setProjectId] = useState('');
  const [items, setItems] = useState<any[]>([]);
  const [title, setTitle] = useState('');
  const [url, setUrl] = useState('');

  const load = async () => { const { data } = await api.get(`/api/projects/${projectId}/documents`); setItems(data); };
  const add = async () => { await api.post(`/api/projects/${projectId}/documents`, { id: crypto.randomUUID(), title, urlOrPath: url }); setTitle(''); setUrl(''); load(); };

  return (
    <div>
      <div className="d-flex gap-2 mb-3">
        <input className="form-control" placeholder="Project ID" value={projectId} onChange={e=>setProjectId(e.target.value)} />
        <button className="btn btn-outline-secondary" onClick={load}>Load</button>
      </div>
      <div className="d-flex gap-2 mb-3">
        <input className="form-control" placeholder="Doc title" value={title} onChange={e=>setTitle(e.target.value)} />
        <input className="form-control" placeholder="URL or path" value={url} onChange={e=>setUrl(e.target.value)} />
        <button className="btn btn-primary" onClick={add}>Upload</button>
      </div>
      <ul className="list-group">
        {items.map(d => <li key={d.id} className="list-group-item">{d.title} – <a href={d.urlOrPath} target="_blank">open</a></li>)}
      </ul>
    </div>
  );
}
