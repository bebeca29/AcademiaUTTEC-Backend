package com.uttec.academia.infrastructure.persistence;

import com.uttec.academia.domain.OfertaAcademica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertaAcademicaRepository extends JpaRepository<OfertaAcademica, Long> {}