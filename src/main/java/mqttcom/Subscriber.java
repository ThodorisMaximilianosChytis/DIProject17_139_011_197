package mqttcom;


import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Subscriber {

    MqttClient client;
    HandleMqttMessages handlemess;


    IMqttMessageListener messageListener = new IMqttMessageListener() {
        @Override
        public void messageArrived(String _topic, MqttMessage mqttMessage) throws Exception {
            System.out.println("Message received:\t"+ new String(mqttMessage.getPayload()) );

            _topic = _topic.substring(0, _topic.length() - 4);          //removing /a2e to keep clean topic root
            System.out.println(_topic);


            handlemess.handleMessage(new String(mqttMessage.getPayload()),_topic);
        }
    };


    public Subscriber(String IP,String port,HandleMqttMessages _handlemess){
        handlemess =_handlemess;
        try {
            client = new MqttClient("tcp://" + IP + ":" + port , MqttClient.generateClientId(), new MemoryPersistence());
            client.setCallback( new MqttCallBackOV() );
            client.connect();
        } catch (MqttException e) {
            System.out.println("Cannot connect to tcp://" + IP + ":" + port);
            e.printStackTrace();
        }

    }

    public void subscribeto(String topic) {
//        handlemess.setTopic(topic);
        try {
            topic = topic + "/a2e";
            System.out.println("SUBSCRIBE to " + topic);

            this.client.subscribe(topic, messageListener);
        } catch (MqttException var3) {
            System.out.println("Cannot subscribe to " + topic);
            var3.printStackTrace();
        }

    }



}