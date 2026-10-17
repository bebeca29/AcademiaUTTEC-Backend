package com.uttec.academia.infrastructure.persistence;

import com.uttec.academia.domain.TipoArchivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoArchivoRepository extends JpaRepository<TipoArchivo, Long> {}