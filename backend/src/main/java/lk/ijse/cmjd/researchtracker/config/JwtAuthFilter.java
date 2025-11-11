package lk.ijse.cmjd.researchtracker.config;

import javax.servlet.*;
import javax.servlet.http.*;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import lk.ijse.cmjd.researchtracker.user.CustomUserDetailsService;
import java.io.IOException;

@Component
public class JwtAuthFilter extends GenericFilter {
    private final JwtUtil jwt;
    private final CustomUserDetailsService users;
    public JwtAuthFilter(JwtUtil jwt, CustomUserDetailsService users){ this.jwt = jwt; this.users = users; }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String header = req.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            try {
                Claims claims = jwt.parse(token).getBody();
                String username = claims.getSubject();
                UserDetails ud = users.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        ud, null, ud.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception ignored) { }
        }
        chain.doFilter(request, response);
    }
}
