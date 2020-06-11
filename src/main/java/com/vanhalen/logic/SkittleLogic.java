package com.vanhalen.logic;

import com.vanhalen.domain.Skittle;
import com.vanhalen.interfaces.MqttServiceInterface;
import com.vanhalen.interfaces.SkittleLogicInterface;
import com.vanhalen.repositories.SkittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkittleLogic implements SkittleLogicInterface {

    private SkittleRepository _skittleRepository;
    private MqttServiceInterface _mqttService;

    @Autowired
    public SkittleLogic(SkittleRepository skittleRepository, MqttServiceInterface mqttService) {
        _skittleRepository = skittleRepository;
        _mqttService = mqttService;
    }

    public boolean sortSkittles(Skittle skittle) {
        try {
            _mqttService.publishMessage("VanHalen/Vending", skittle.toString().getBytes());
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
