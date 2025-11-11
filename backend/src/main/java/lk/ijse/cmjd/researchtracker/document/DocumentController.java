package lk.ijse.cmjd.researchtracker.document;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class DocumentController {
    private final DocumentService service;
    public DocumentController(DocumentService service){ this.service = service; }

    @GetMapping("/api/projects/{id}/documents")
    public List<Document> list(@PathVariable("id") String projectId){ return service.list(projectId); }

    @PostMapping("/api/projects/{id}/documents")
    public Document add(@PathVariable("id") String projectId, @RequestBody Document d, Authentication auth){
        return service.add(projectId, d, auth);
    }

    @DeleteMapping("/api/documents/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id, Authentication auth){
        service.delete(id, auth); return ResponseEntity.noContent().build();
    }
}
