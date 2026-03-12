package com.javier.forohub.controlador;

import com.javier.forohub.dominio.topico.Topico;
import com.javier.forohub.dto.DatosRegistroTopico;
import com.javier.forohub.repositorio.RepositorioTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class ControladorTopico {

    private final RepositorioTopico repository;

    @PostMapping
    @Transactional
    public ResponseEntity crear(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
        // REGLA DE NEGOCIO: Verificar duplicados
        var existe = repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (existe) {
            return ResponseEntity.badRequest().body("Error: Ya existe un tópico con el mismo título y mensaje.");
        }

        Topico topico = new Topico(null, datos.titulo(), datos.mensaje(), null, datos.autor(), datos.curso());
        repository.save(topico);
        
        URI url = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(topico);
    }

    @GetMapping
    public ResponseEntity<Page<Topico>> listar(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {
        return ResponseEntity.ok(repository.findAll(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalle(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@PathVariable Long id, @RequestBody @Valid DatosRegistroTopico datos) {
        // REGLA DE NEGOCIO: Al actualizar, también se podría validar que no choque con otro existente
        Topico topico = repository.getReferenceById(id);
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        // El @Transactional se encarga de disparar el update en la BD
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        // Es buena práctica verificar si existe antes de borrar para evitar el error 500
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
