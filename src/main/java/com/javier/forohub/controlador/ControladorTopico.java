package com.javier.forohub.controlador;

import com.javier.forohub.dominio.topico.Topico;
import com.javier.forohub.dto.DatosRegistroTopico;
import com.javier.forohub.repositorio.RepositorioTopico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class ControladorTopico {

    private final RepositorioTopico repository;

    @PostMapping
    public ResponseEntity crear(@RequestBody DatosRegistroTopico datos){
        Topico topico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                LocalDateTime.now(),
                datos.autor(),
                datos.curso()
        );
        repository.save(topico);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public List<Topico> listar(){ return repository.findAll(); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id){ repository.deleteById(id); }
}