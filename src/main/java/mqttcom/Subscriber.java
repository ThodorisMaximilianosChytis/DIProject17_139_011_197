package mqttcom;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

    public static void main(String[] args) throws MqttException {

        System.out.println("== START SUBSCRIBER ==");

        MqttClient client=new MqttClient("tcp://test.mosquitto.org:1883", MqttClient.generateClientId());
        client.setCallback( new MqttCallBackOV() );
        client.connect();

        client.subscribe("roadinformation");

    }

}