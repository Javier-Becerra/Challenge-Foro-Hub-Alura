package com.javier.forohub.seguridad;

import com.javier.forohub.repositorio.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Filtro extends OncePerRequestFilter {

    private final Token token;
    private final RepositorioUsuario repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var token = recuperarToken(request);
        if(token!=null){
            var subject = this.token.getSubject(token);
            var usuario = repository.findByLogin(subject);

            var auth = new UsernamePasswordAuthenticationToken(
                    usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader==null) return null;
        return authHeader.replace("Bearer ","");
    }
}