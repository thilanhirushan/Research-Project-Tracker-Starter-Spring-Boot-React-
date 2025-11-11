import { useEffect, useState } from 'react';
export default function Admin(){
  const [users, setUsers] = useState<any[]>([]);
  useEffect(()=>{ /* Extend backend with /api/users if required */ },[]);
  return (
    <div>
      <h3>Admin Panel</h3>
      <p>Add user management endpoints if required for your submission.</p>
    </div>
  );
}
