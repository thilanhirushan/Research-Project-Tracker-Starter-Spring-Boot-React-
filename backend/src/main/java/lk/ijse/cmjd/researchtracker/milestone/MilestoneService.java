package lk.ijse.cmjd.researchtracker.milestone;

import lk.ijse.cmjd.researchtracker.project.*;
import lk.ijse.cmjd.researchtracker.user.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MilestoneService {
    private final MilestoneRepository repo; private final ProjectRepository projects; private final UserRepository users;
    public MilestoneService(MilestoneRepository repo, ProjectRepository projects, UserRepository users){
        this.repo=repo; this.projects=projects; this.users=users;
    }

    public List<Milestone> listByProject(String projectId){ return repo.findByProject_Id(projectId); }

    public Milestone add(String projectId, Milestone m, Authentication auth){
        Project p = projects.findById(projectId).orElseThrow();
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(actor.getRole()==UserRole.VIEWER) throw new RuntimeException("VIEWER cannot add milestones");
        if(m.getId()==null) m.setId(UUID.randomUUID().toString());
        m.setProject(p); m.setCreatedBy(actor);
        return repo.save(m);
    }

    public Milestone update(String id, Milestone in, Authentication auth){
        Milestone m = repo.findById(id).orElseThrow();
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(actor.getRole()==UserRole.VIEWER) throw new RuntimeException("Not allowed");
        m.setTitle(in.getTitle()); m.setDescription(in.getDescription()); m.setDueDate(in.getDueDate()); m.setIsCompleted(in.getIsCompleted());
        return repo.save(m);
    }

    public void delete(String id, Authentication auth){
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(actor.getRole()==UserRole.VIEWER) throw new RuntimeException("Not allowed");
        repo.deleteById(id);
    }
}
