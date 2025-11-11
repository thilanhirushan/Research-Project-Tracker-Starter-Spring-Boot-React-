package lk.ijse.cmjd.researchtracker.auth;

import lk.ijse.cmjd.researchtracker.auth.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service){ this.service = service; }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody @Validated SignupRequest req){
        return ResponseEntity.ok(service.signup(req));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Validated LoginRequest req){
        return ResponseEntity.ok(service.login(req));
    }
}
