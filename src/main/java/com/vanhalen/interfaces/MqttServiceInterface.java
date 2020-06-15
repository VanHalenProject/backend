package com.vanhalen.interfaces;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public interface MqttServiceInterface {
    void publishMessage(String topic, byte[] payload) throws MqttException;
}
