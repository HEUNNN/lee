package com.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Scanner;

public class MqttClient2 {
    public static void main(String[] args) {
        String subTopic = "test/publish";
        String broker = "tcp://127.0.0.1:1883";
        String clientId = "emqx_client2";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);

            // MQTT connection option
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName("lee");
            connOpts.setPassword("lee@1234".toCharArray());
            // retain session
            connOpts.setCleanSession(true);

            client.setCallback(new PushCallback(client));
            client.connect(connOpts);
            client.subscribeWithResponse(subTopic);


            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("waiting");
                String str = sc.nextLine(); // String ????
                if (str.equalsIgnoreCase("close")) {
                    break;
                }
            }

            client.disconnect();
            System.out.println("Disconnected");
            client.close();
            System.exit(0);

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    }
}
