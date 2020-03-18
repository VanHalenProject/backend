package com.vanhalen.repositories;

import com.vanhalen.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public abstract class UserRepository implements JpaRepository<User, UUID> {
}
