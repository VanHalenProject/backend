package com.vanhalen.messaging;

import com.vanhalen.interfaces.MqttServiceInterface;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.eclipse.paho.client.mqttv3.MqttException.REASON_CODE_CLIENT_NOT_CONNECTED;

@Service
public class MqttService implements MqttServiceInterface {

    private final String publisherId = UUID.randomUUID().toString();
    private final IMqttClient _mqttClient;

    public MqttService() throws MqttException {
        _mqttClient = new MqttClient("tcp://localhost:1883", publisherId);
        _mqttClient.connect();
    }

    public void publishMessage(String topic, byte[] payload) throws MqttException {
        if (!_mqttClient.isConnected()) throw new MqttException(REASON_CODE_CLIENT_NOT_CONNECTED);
        var mqttMessage = new MqttMessage(payload);
        mqttMessage.setRetained(true);
        mqttMessage.setQos(2);
        _mqttClient.publish(topic, mqttMessage);
    }
}
