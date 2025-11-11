package lk.ijse.cmjd.researchtracker.document;

import lk.ijse.cmjd.researchtracker.project.*;
import lk.ijse.cmjd.researchtracker.user.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DocumentService {
    private final DocumentRepository repo; private final ProjectRepository projects; private final UserRepository users;
    public DocumentService(DocumentRepository repo, ProjectRepository projects, UserRepository users){
        this.repo=repo; this.projects=projects; this.users=users;
    }

    public List<Document> list(String projectId){ return repo.findByProject_Id(projectId); }

    public Document add(String projectId, Document d, Authentication auth){
        Project p = projects.findById(projectId).orElseThrow();
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(actor.getRole()==UserRole.VIEWER) throw new RuntimeException("Not allowed");
        if(d.getId()==null) d.setId(UUID.randomUUID().toString());
        d.setProject(p); d.setUploadedBy(actor);
        return repo.save(d);
    }

    public void delete(String id, Authentication auth){
        User actor = users.findByUsername(auth.getName()).orElseThrow();
        if(!(actor.getRole()==UserRole.ADMIN || actor.getRole()==UserRole.PI))
            throw new RuntimeException("Only ADMIN or PI can delete documents");
        repo.deleteById(id);
    }
}
