package com.uttec.academia.infrastructure.persistence;

import com.uttec.academia.domain.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {}