package com.example.project;

import android.content.Intent;
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



    Intent intent = getIntent();
    String IP = intent.getStringExtra(select_ip_port.EXTRA_TEXT1);

    Intent intent2 = getIntent();
    String Port = intent2.getStringExtra(select_ip_port.EXTRA_TEXT2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);
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
