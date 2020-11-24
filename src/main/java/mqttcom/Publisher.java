package mqttcom;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {


    public static void main(String[] args) throws MqttException {

        String messageString = "Hello World from Java!";

        if (args.length == 2) {
            messageString = args[1];git
        }


        System.out.println("== START PUBLISHER ==");


        MqttClient client = new MqttClient("tcp://test.mosquitto.org:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(messageString.getBytes());
        client.publish("roadinfoe2a", message);

        System.out.println("\tMessage '" + messageString + "' to roadinformation");

        client.disconnect();

        System.out.println("== END PUBLISHER ==");

    }

}
