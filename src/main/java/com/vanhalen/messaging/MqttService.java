package com.vanhalen.messaging;

import com.vanhalen.interfaces.MqttServiceInterface;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.eclipse.paho.client.mqttv3.MqttException.REASON_CODE_CLIENT_NOT_CONNECTED;

@Service
public class MqttService implements MqttServiceInterface, MqttCallback {


    private IMqttClient _mqttClient;

    public MqttService(@Value("${mqtt.client.host-url}") String mqttHostUrl, @Value("${mqtt.client.publisher-id}") String publisherId,
                       @Value("${mqtt.client.topic.sorting}") String sortingTopic) throws MqttException {
        if (!_mqttClient.isConnected()) {
            _mqttClient = new MqttClient(mqttHostUrl, publisherId);
            _mqttClient.connect();
            _mqttClient.setCallback(this);
            _mqttClient.subscribe(sortingTopic);
        }
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
        System.out.println(mqttMessage);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        
    }
}
