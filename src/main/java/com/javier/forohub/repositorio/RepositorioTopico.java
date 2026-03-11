package com.javier.forohub.repositorio;

import com.javier.forohub.dominio.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioTopico extends JpaRepository<Topico, Long> {}
