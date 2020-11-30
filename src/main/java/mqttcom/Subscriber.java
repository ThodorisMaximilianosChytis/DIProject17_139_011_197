package mqttcom;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber {

    MqttClient client;

    public Subscriber(String IP,String port) throws MqttException {

        client = new MqttClient("tcp://" + IP + ":" + port , MqttClient.generateClientId(), new MemoryPersistence());

        client.setCallback( new MqttCallBackOV() );
        try {
            client.connect();
        } catch (MqttException e) {
            System.out.println("Cannot connect to tcp://" + IP + ":" + port);
            e.printStackTrace();
        }

    }

    public void subscribeto(String topic){
        System.out.println("SUBSCRIBE to " + topic);
        try {
            client.subscribe(topic);
        } catch (MqttException e) {
            System.out.println("Cannot subscribe to " + topic);
            e.printStackTrace();
        }

    }



}