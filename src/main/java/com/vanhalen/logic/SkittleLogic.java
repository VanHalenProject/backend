package com.vanhalen.logic;

import com.vanhalen.interfaces.SkittleLogicInterface;
import com.vanhalen.repositories.SkittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkittleLogic implements SkittleLogicInterface {
    @Autowired
    private SkittleRepository repository;
}
