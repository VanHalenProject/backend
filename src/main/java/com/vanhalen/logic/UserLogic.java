package com.vanhalen.logic;

import com.vanhalen.domain.User;
import com.vanhalen.interfaces.UserLogicInterface;
import com.vanhalen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLogic implements UserLogicInterface {


    private UserRepository _userRepository;

    @Autowired
    public UserLogic(UserRepository userRepository) {

        _userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return _userRepository.findByUserName(userName);
    }
}
