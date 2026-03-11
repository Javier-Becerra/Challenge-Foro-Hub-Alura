package com.javier.forohub.servicio;

import com.javier.forohub.dominio.usuario.Usuario;
import com.javier.forohub.repositorio.RepositorioUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Autenticacion {

    private final AuthenticationManager authenticationManager;
    private final RepositorioUsuario repositorioUsuario;
    private final PasswordEncoder passwordEncoder;

    public Usuario autenticar(String login, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );
        return (Usuario) auth.getPrincipal();
    }

    public Usuario registrar(String login, String password) {
        // Verificar si el login ya existe
        if (repositorioUsuario.findByLogin(login) != null) {
            throw new RuntimeException("El login ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setPassword(passwordEncoder.encode(password));
        return repositorioUsuario.save(usuario);
    }
}