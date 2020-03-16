package com.vanhalen.repositories;

import com.vanhalen.domain.Skittle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkittleRepository extends JpaRepository<Skittle, Integer> {
}
