package com.uttec.academia.infrastructure.persistence;

import com.uttec.academia.domain.Cuatrimestre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuatrimestreRepository extends JpaRepository<Cuatrimestre, Long> {}