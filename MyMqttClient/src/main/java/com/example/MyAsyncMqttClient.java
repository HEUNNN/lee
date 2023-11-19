package com.example;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Scanner;

public class MyAsyncMqttClient {

    public static void main(String[] args) {
        String subTopic = "test/publish";
        String broker = "tcp://127.0.0.1:1883";
        String clientId = "emqx_async_client1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttAsyncClient aClient = new MqttAsyncClient(broker, clientId, persistence);

            // MQTT connection option
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("lee");
            connOpts.setPassword("lee@1234".toCharArray());
            // retain session
            connOpts.setCleanSession(true);

            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("waiting");
                String str = sc.nextLine();
                if (str.equalsIgnoreCase("disconnect")) {
                    aClient.disconnect();
                    System.out.println("Disconnected");
                } else if (str.equalsIgnoreCase("connect")){ // connection
                    aClient.setCallback(new PushCallback());
                    aClient.connect(connOpts);
                    System.out.println("Connected");
                } else {
                    break;
                }
            }


            client.close();
            System.exit(0);

        } catch (Exception e) {

        }
    }
}
