package com.vanhalen.repositories;

import com.vanhalen.domain.Skittle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SkittleRepository extends JpaRepository<Skittle, UUID> {
}
