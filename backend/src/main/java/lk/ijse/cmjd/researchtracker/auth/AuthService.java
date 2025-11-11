package lk.ijse.cmjd.researchtracker.auth;

import lk.ijse.cmjd.researchtracker.auth.dto.*;
import lk.ijse.cmjd.researchtracker.config.JwtUtil;
import lk.ijse.cmjd.researchtracker.user.*;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class AuthService {
    private final AuthenticationManager authManager;
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public AuthService(AuthenticationManager authManager, UserRepository users, PasswordEncoder encoder, JwtUtil jwt){
        this.authManager = authManager; this.users=users; this.encoder=encoder; this.jwt=jwt;
    }

    public AuthResponse signup(SignupRequest req){
        if(users.existsByUsername(req.getUsername())) throw new RuntimeException("Username already exists");
        User u = new User(UUID.randomUUID().toString(), req.getUsername(), encoder.encode(req.getPassword()),
                req.getFullName(), UserRole.MEMBER);
        users.save(u);
        String token = jwt.generateToken(u.getUsername(), u.getRole().name());
        return new AuthResponse(token, u.getRole().name(), u.getUsername(), u.getFullName());
    }

    public AuthResponse login(LoginRequest req){
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        User u = users.findByUsername(req.getUsername()).orElseThrow();
        String token = jwt.generateToken(u.getUsername(), u.getRole().name());
        return new AuthResponse(token, u.getRole().name(), u.getUsername(), u.getFullName());
    }
}
