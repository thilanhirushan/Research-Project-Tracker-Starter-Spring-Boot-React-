import { useEffect, useState } from 'react';
import api from '../api/axios';
import { Link } from 'react-router-dom';

type Project = { id: string; title: string; summary: string; status: string };

export default function Projects(){
  const [items, setItems] = useState<Project[]>([]);
  const [title, setTitle] = useState('');
  const [summary, setSummary] = useState('');

  const load = async () => {
    const { data } = await api.get('/api/projects');
    setItems(data);
  };
  useEffect(() => { load(); }, []);

  const create = async () => {
    await api.post('/api/projects', { title, summary, id: crypto.randomUUID(), pi: { id: 'TODO-SET-PI' } });
    setTitle(''); setSummary(''); load();
  };

  return (
    <div>
      <div className="d-flex align-items-end gap-2 mb-3">
        <div className="flex-grow-1">
          <label className="form-label">Title</label>
          <input className="form-control" value={title} onChange={e=>setTitle(e.target.value)} />
        </div>
        <div className="flex-grow-1">
          <label className="form-label">Summary</label>
          <input className="form-control" value={summary} onChange={e=>setSummary(e.target.value)} />
        </div>
        <button className="btn btn-primary" onClick={create}>Create</button>
      </div>

      <table className="table">
        <thead><tr><th>Title</th><th>Status</th><th></th></tr></thead>
        <tbody>
          {items.map(p => (
            <tr key={p.id}>
              <td>{p.title}<div className="text-muted small">{p.summary}</div></td>
              <td>{p.status}</td>
              <td className="text-end"><Link className="btn btn-sm btn-outline-secondary" to={`/projects/${p.id}`}>Open</Link></td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
