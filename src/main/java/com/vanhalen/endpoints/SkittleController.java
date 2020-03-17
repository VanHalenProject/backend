package com.vanhalen.endpoints;

import com.vanhalen.logic.SkittleLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("skittles/")
public class SkittleController {
    @Autowired
    private SkittleLogic logic;
}
