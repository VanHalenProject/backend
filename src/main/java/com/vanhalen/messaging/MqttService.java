package com.vanhalen.messaging;

import com.vanhalen.interfaces.MqttServiceInterface;
import com.vanhalen.repositories.SkittleRepository;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.eclipse.paho.client.mqttv3.MqttException.REASON_CODE_CLIENT_NOT_CONNECTED;

@Service
public class MqttService implements MqttServiceInterface, MqttCallback {


    private IMqttClient _mqttClient;
    private SkittleRepository _skittleRepository;

    @Autowired
    public MqttService(@Value("${mqtt.client.host-url}") String mqttHostUrl, @Value("${mqtt.client.publisher-id}") String publisherId,
                       @Value("${mqtt.client.topic.sorting}") String sortingTopic, SkittleRepository skittleRepository) throws MqttException {
        _mqttClient = new MqttClient(mqttHostUrl, publisherId);
        _mqttClient.connect();
        _mqttClient.setCallback(this);
        _mqttClient.subscribe(sortingTopic, 2);
        _skittleRepository = skittleRepository;
    }

    public void publishMessage(String topic, byte[] payload) throws MqttException {
        if (!_mqttClient.isConnected()) throw new MqttException(REASON_CODE_CLIENT_NOT_CONNECTED);
        var mqttMessage = new MqttMessage(payload);
        mqttMessage.setQos(2);
        _mqttClient.publish(topic, mqttMessage);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Lost connection to mqtt broker");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        var colors = mqttMessage.toString().split(";");
        var skittle = _skittleRepository.findAll().get(0);
        skittle.setRed(skittle.getRed() + Integer.parseInt(colors[0]));
        skittle.setOrange((skittle.getOrange() + Integer.parseInt(colors[1])));
        skittle.setYellow((skittle.getYellow() + Integer.parseInt(colors[2])));
        skittle.setGreen((skittle.getGreen() + Integer.parseInt(colors[3])));
        skittle.setPurple((skittle.getPurple() + Integer.parseInt(colors[4])));
        _skittleRepository.save(skittle);
        System.out.println(mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
