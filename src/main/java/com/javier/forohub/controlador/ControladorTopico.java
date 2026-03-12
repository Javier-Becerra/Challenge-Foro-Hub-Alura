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
    public ResponseEntity<Topico> crear(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriBuilder) {
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
        Topico topico = repository.getReferenceById(id);
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        return ResponseEntity.ok(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
