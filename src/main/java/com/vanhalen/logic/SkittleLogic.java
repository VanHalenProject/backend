package com.vanhalen.logic;

import com.vanhalen.domain.Skittle;
import com.vanhalen.interfaces.MqttServiceInterface;
import com.vanhalen.interfaces.SkittleLogicInterface;
import com.vanhalen.repositories.SkittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SkittleLogic implements SkittleLogicInterface {

    private SkittleRepository _skittleRepository;
    private MqttServiceInterface _mqttService;
    private String _vendorTopic;

    @Autowired
    public SkittleLogic(@Value("${mqtt.client.topic.sorting}") String vendorTopic, SkittleRepository skittleRepository, MqttServiceInterface mqttService) {
        _skittleRepository = skittleRepository;
        _mqttService = mqttService;
        _vendorTopic = vendorTopic;
    }

    public boolean sortSkittles(Skittle skittle) {
        try {
            _mqttService.publishMessage(_vendorTopic, skittle.toString().getBytes());
            var skittleToUpdate = getSkittles();
            if (skittleToUpdate != null) {
                skittleToUpdate.setRed(skittleToUpdate.getRed() - skittle.getRed());
                skittleToUpdate.setOrange(skittleToUpdate.getOrange() - skittle.getOrange());
                skittleToUpdate.setYellow(skittleToUpdate.getYellow() - skittle.getYellow());
                skittleToUpdate.setGreen(skittleToUpdate.getGreen() - skittle.getGreen());
                skittleToUpdate.setPurple(skittleToUpdate.getPurple() - skittle.getPurple());
                _skittleRepository.save(skittleToUpdate);
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Skittle getSkittles() {
        try {
            var skittles = _skittleRepository.findAll();
            return skittles.get(0);
        } catch (Exception ex) {
            return null;
        }
    }
}
