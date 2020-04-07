package com.vanhalen.messaging;

import com.vanhalen.interfaces.MqttServiceInterface;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.eclipse.paho.client.mqttv3.MqttException.REASON_CODE_CLIENT_NOT_CONNECTED;

@Service
public class MqttService implements MqttServiceInterface {


    private IMqttClient _mqttClient;

    public MqttService(@Value("${mqtt.client.host-url}") String mqttHostUrl, @Value("${mqtt.client.publisher-id}") String publisherId) throws MqttException {
        try {
            _mqttClient = new MqttClient(mqttHostUrl, publisherId);
            _mqttClient.connect();
        } catch (MqttException ex) {
            //TODO action when unable to connect to host URL
        }

    }

    public void publishMessage(String topic, byte[] payload) throws MqttException {
        if (!_mqttClient.isConnected()) throw new MqttException(REASON_CODE_CLIENT_NOT_CONNECTED);
        var mqttMessage = new MqttMessage(payload);
        mqttMessage.setQos(2);
        _mqttClient.publish(topic, mqttMessage);
    }
}
