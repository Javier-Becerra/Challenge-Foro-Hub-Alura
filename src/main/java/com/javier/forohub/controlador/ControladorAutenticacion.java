package com.javier.forohub.controlador;

import com.javier.forohub.dominio.usuario.Usuario;
import com.javier.forohub.dto.*;
import com.javier.forohub.seguridad.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class ControladorAutenticacion {

    private final AuthenticationManager manager;
    private final Token token;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DatosAutenticacionUsuario datos){
        var token = new UsernamePasswordAuthenticationToken(datos.login(), datos.password());
        var auth = manager.authenticate(token);
        var jwt = this.token.generarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(jwt));
    }
}