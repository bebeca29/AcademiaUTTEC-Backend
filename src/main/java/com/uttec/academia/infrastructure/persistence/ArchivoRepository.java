package com.uttec.academia.infrastructure.persistence;

import com.uttec.academia.domain.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivoRepository extends JpaRepository<Archivo, Long> {}