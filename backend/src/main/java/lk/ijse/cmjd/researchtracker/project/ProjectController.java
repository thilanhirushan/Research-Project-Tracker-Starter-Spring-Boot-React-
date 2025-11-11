package lk.ijse.cmjd.researchtracker.project;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;
    public ProjectController(ProjectService service){ this.service = service; }

    @GetMapping
    public List<Project> list(){ return service.getAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable String id){
        return service.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Project create(@RequestBody Project p, Authentication auth){ return service.create(p, auth); }

    @PutMapping("/{id}")
    public Project update(@PathVariable String id, @RequestBody Project p, Authentication auth){
        return service.update(id, p, auth);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> status(@PathVariable String id, @RequestParam Status status, Authentication auth){
        service.updateStatus(id, status, auth); return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication auth){
        service.delete(id, auth); return ResponseEntity.noContent().build();
    }
}
