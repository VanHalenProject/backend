package com.vanhalen.messaging;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;

import static org.eclipse.paho.client.mqttv3.MqttException.REASON_CODE_CLIENT_NOT_CONNECTED;

public class MqttService implements MqttServiceInterface{

    private final String publisherId = UUID.randomUUID().toString();
    private final IMqttClient _mqttClient;

    public MqttService() throws MqttException {
        _mqttClient = new MqttClient("tcp://localhost:8080", publisherId);
        _mqttClient.connect();
    }

    public void publishMessage(String topic, byte[] payload) throws MqttException {
        if (!_mqttClient.isConnected()) throw new MqttException(REASON_CODE_CLIENT_NOT_CONNECTED);
        var mqttMessage = new MqttMessage(payload);
        mqttMessage.setRetained(true);
        _mqttClient.publish(topic, mqttMessage);
    }
}
