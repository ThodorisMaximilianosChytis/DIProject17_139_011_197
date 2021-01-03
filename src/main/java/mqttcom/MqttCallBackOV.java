package mqttcom;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttCallBackOV implements MqttCallback {

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String str = new String(mqttMessage.getPayload());
          //System.out.println("Message received:\t"+ new String(mqttMessage.getPayload()) );
        System.out.println("Message received:\t"+ str );

        String[] arrOfStr = str.split("|", 8);
        //0 time, 1 id, 2 lat,3 long,4 angle,5 speed,6 RSSI,7 Throughput

        double time = Double.parseDouble(arrOfStr[0]);
        int id = Integer.parseInt(arrOfStr[1]);
        double lat = Double.parseDouble(arrOfStr[2]);
        double lon = Double.parseDouble(arrOfStr[3]);
        double angle = Double.parseDouble(arrOfStr[4]);
        double speed = Double.parseDouble(arrOfStr[5]);
        double RSSI = Double.parseDouble(arrOfStr[6]);
        double Throughput = Double.parseDouble(arrOfStr[7]);

        Formula f = new Formula(lat,lon,angle,speed);

        time = time + 1.0;

        //the message we send to android
        String messtosend = String.valueOf(f.late) + "|" + String.valueOf(f.longe) + "|" +  String.valueOf(RSSIpred) + "|" + String.valueOf(Throughputpred) + "|" + String.valueOf(time) + "|";
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
