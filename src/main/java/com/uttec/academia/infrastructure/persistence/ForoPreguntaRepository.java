package com.uttec.academia.infrastructure.persistence;

import com.uttec.academia.domain.ForoPregunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForoPreguntaRepository extends JpaRepository<ForoPregunta, Long> {}