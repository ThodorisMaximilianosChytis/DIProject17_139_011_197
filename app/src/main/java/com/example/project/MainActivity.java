package com.example.project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;


public class MainActivity extends AppCompatActivity {

    private Button b1, b2, b3, b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button1); //select time i can send data
        b2 = (Button) findViewById(R.id.button2); //cancel sending data
        b3 = (Button) findViewById(R.id.button3); //select IP and Port
        b4 = (Button) findViewById(R.id.button4); //exit

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_time();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select_ip_and_port();
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public void select_ip_and_port() {
        Intent intent3 = new Intent(this, select_ip_port.class);
        startActivity(intent3);
    }

    public void select_time() {
        Intent intent1 = new Intent(this, select_time.class);
        startActivity(intent1);
    }

}

//    String clientId = MqttClient.generateClientId();
//    MqttAndroidClient client =
//            new MqttAndroidClient(MainActivity.this, "tcp://broker.hivemq.com:1883",
//                    clientId);
//
//                try {
//                        IMqttToken token = client.connect();
//                        token.setActionCallback(new IMqttActionListener() {
//@Override
//public void onSuccess(IMqttToken asyncActionToken) {
//        // We are connected
//        Toast.makeText(MainActivity.this, "connected", Toast.LENGTH_SHORT).show();
//        }
//
//@Override
//public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//        // Something went wrong e.g. connection timeout or firewall problems
//        Toast.makeText(MainActivity.this, "disconnected", Toast.LENGTH_SHORT).show();
//
//        }
//        });
//        } catch (MqttException e) {
//        e.printStackTrace();
//        }