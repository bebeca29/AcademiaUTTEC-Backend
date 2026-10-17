package com.uttec.academia.infrastructure.persistence;

import com.uttec.academia.domain.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Long> {}