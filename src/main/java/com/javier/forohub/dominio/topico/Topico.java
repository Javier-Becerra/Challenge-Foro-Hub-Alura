package com.javier.forohub.dominio.topico;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="topicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private String autor;
    private String curso;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NO_RESPONDIDO;

    public Topico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, String autor, String curso) {
        this.id = id; // puede ser null
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.curso = curso;
        this.status = Status.NO_RESPONDIDO;
    }

    public enum Status {
        NO_RESPONDIDO, RESPONDIDO, CERRADO
    }
}