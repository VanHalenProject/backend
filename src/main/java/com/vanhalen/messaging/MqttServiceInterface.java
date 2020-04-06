package com.vanhalen.messaging;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface MqttServiceInterface {
    void publishMessage(String topic, byte[] payload) throws MqttException;
}
