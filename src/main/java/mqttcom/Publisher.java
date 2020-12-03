package mqttcom;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Publisher {

    MqttClient client;

    public Publisher(String IP,String port)  {



        try {
            client = new MqttClient("tcp://" + IP + ":" + port , MqttClient.generateClientId(), new MemoryPersistence());
            client.connect();
        } catch (MqttException e) {
            System.out.println("Cannot connect to tcp://" + IP + ":" + port);
            e.printStackTrace();
        }

    }
    public void publishto(String topic, String messageString){

        System.out.println("PUBLISHER to " + topic);

        MqttMessage message = new MqttMessage();
        message.setPayload(messageString.getBytes());

        try {
            client.publish("roadinfoe2a", message);
        } catch (MqttException e) {
            System.out.println("Error publishing to " + topic);
            e.printStackTrace();
        }


    }

    public void Disconnect(){
        try {
            client.disconnect();
        } catch (MqttException e) {
            System.out.println("Error disconnecting");
            e.printStackTrace();
        }
        System.out.println("END PUBLISHER");
    }

}
