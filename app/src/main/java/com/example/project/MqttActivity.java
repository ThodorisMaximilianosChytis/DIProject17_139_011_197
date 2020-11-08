package com.example.project;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttActivity extends AppCompatActivity {
    private static final String TAG = "Mqtt Activity";
    String IP, Port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mqtt_activity);
        try {
            MqttClient client = new MqttClient("tcp://" + IP + ":" + Port, "AndroidThingSub", new MemoryPersistence());
            client.setCallback((MqttCallback) this);
            client.connect();
            String topic = "topic/ayushgemini";
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void connectionLost(Throwable cause) {
        Log.d(TAG, "connectionLost");
    }

    //@Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
        Log.d(TAG, payload);
    }

    //@Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        Log.d(TAG, "deliveryComplete");
    }
}
