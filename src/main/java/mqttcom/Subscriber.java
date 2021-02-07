package mqttcom;


import JDBC.JDBC;
import form.EndValues;
import heatmap.values.CreateHeatMapValues;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import sun.misc.Signal;

import java.util.HashMap;

public class Subscriber {

    private final JDBC mysqldb;
    private final Publisher pub;
    private final EndValues endValues;
    MqttClient client;
    HashMap<String,HandleMqttMessages> Topics;




    IMqttMessageListener messageListener = new IMqttMessageListener() {


//          MESSAGE HANDLER
        @Override
        public void messageArrived(String _topic, MqttMessage mqttMessage) throws Exception {
            System.out.println("Message received: " + _topic + "  -->  " +new String(mqttMessage.getPayload()) );

            _topic = _topic.substring(0, _topic.length() - 4);          //removing /a2e to keep clean topic root
            String stringMessage = new String(mqttMessage.getPayload());

            //EVERY TOPIC GET OWN MESSAGEHANDLE CLASS
            if (! Topics.containsKey(_topic)) {
                Topics.put(_topic, new HandleMqttMessages(mysqldb, pub, endValues, _topic));
//                System.out.println("Only one time please");
            }

            //COMMUNICATION STOPS
            if (!stringMessage.equals("@SendingStops@"))
                Topics.get(_topic).handleMessage(stringMessage);
            else {

                DisplayErrorDistance(_topic,Topics.get(_topic).getMeanDistanceError());
                Topics.remove(_topic);
            }



        }
    };


    public Subscriber(String IP, String port, JDBC _database, Publisher _pub, EndValues _endValues){
        mysqldb = _database;
        pub = _pub;
        endValues = _endValues;

        Topics = new HashMap<String, HandleMqttMessages>();

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

        try {
            topic = topic + "/a2e";
            System.out.println("SUBSCRIBE to " + topic);

            this.client.subscribe(topic, messageListener);
        } catch (MqttException var3) {
            System.out.println("Cannot subscribe to " + topic);
            var3.printStackTrace();
        }

    }

    private void DisplayErrorDistance(String _topic ,Double error){
        System.out.println("________________________________________________________________________________________\n" +
                "Mean Distance Error for topic:  " + _topic + " is -------->" + error + "m" +
                "\n________________________________________________________________________________________" );


    }

    public void Disconnect(){
        try {
            client.disconnect();
        } catch (MqttException e) {
            System.out.println("Error disconnecting");
            e.printStackTrace();
        }
        System.out.println("END SUBSCRIBER");
    }

}