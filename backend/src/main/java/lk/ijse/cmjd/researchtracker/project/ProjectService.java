package lk.ijse.cmjd.researchtracker.project;

import lk.ijse.cmjd.researchtracker.user.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projects;
    private final UserRepository users;
    public ProjectService(ProjectRepository projects, UserRepository users){ this.projects=projects; this.users=users; }

    public List<Project> getAll(){ return projects.findAll(); }
    public Optional<Project> get(String id){ return projects.findById(id); }

    public Project create(Project p, Authentication auth){
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(!(actor.getRole()==UserRole.ADMIN || actor.getRole()==UserRole.PI))
            throw new RuntimeException("Only ADMIN or PI can create projects");
        if(p.getId()==null) p.setId(UUID.randomUUID().toString());
        return projects.save(p);
    }

    public Project update(String id, Project incoming, Authentication auth){
        Project existing = projects.findById(id).orElseThrow();
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(!(actor.getRole()==UserRole.ADMIN || existing.getPi().getUsername().equals(actor.getUsername())))
            throw new RuntimeException("Not allowed");
        existing.setTitle(incoming.getTitle());
        existing.setSummary(incoming.getSummary());
        existing.setTags(incoming.getTags());
        existing.setStartDate(incoming.getStartDate());
        existing.setEndDate(incoming.getEndDate());
        existing.setStatus(incoming.getStatus());
        existing.setPi(incoming.getPi());
        return projects.save(existing);
    }

    public void updateStatus(String id, Status status, Authentication auth){
        Project p = projects.findById(id).orElseThrow();
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(!(actor.getRole()==UserRole.ADMIN || p.getPi().getUsername().equals(actor.getUsername())))
            throw new RuntimeException("Not allowed");
        p.setStatus(status);
        projects.save(p);
    }

    public void delete(String id, Authentication auth){
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(actor.getRole()!=UserRole.ADMIN) throw new RuntimeException("Only ADMIN can delete projects");
        projects.deleteById(id);
    }
}
