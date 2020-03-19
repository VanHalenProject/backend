package com.vanhalen.interfaces;

import com.vanhalen.domain.User;

import java.util.Optional;

public interface UserLogicInterface {
    Optional<User> findByUserName(String userName);
}
