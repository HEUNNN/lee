package com.example;

import org.eclipse.paho.client.mqttv3.*;

public class PushCallback implements MqttCallback {

    private MqttClient mqttClient;

    public PushCallback(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void connectionLost(Throwable cause) {
        // After the connection is lost, it usually reconnects here
        System.out.println("disconnect, you can reconnect");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // The messages obtained after subscribe will be executed here
        System.out.println("Received message topic:" + topic + "Received message content:" + new String(message.getPayload()));
//        mqttClient.messageArrivedComplete(message.getId(), message.getQos());
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
}
