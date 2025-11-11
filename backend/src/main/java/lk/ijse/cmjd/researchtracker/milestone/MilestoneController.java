package lk.ijse.cmjd.researchtracker.milestone;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class MilestoneController {
    private final MilestoneService service;
    public MilestoneController(MilestoneService service){ this.service = service; }

    @GetMapping("/api/projects/{id}/milestones")
    public List<Milestone> list(@PathVariable("id") String projectId){ return service.listByProject(projectId); }

    @PostMapping("/api/projects/{id}/milestones")
    public Milestone add(@PathVariable("id") String projectId, @RequestBody Milestone m, Authentication auth){
        return service.add(projectId, m, auth);
    }

    @PutMapping("/api/milestones/{id}")
    public Milestone update(@PathVariable String id, @RequestBody Milestone m, Authentication auth){ return service.update(id, m, auth); }

    @DeleteMapping("/api/milestones/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication auth){
        service.delete(id, auth); return ResponseEntity.noContent().build();
    }
}
