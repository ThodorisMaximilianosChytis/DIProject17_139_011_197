package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class select_ip_port extends AppCompatActivity {

    public static final String EXTRA_TEXT1 = "com.example.project.EXTRA_TEXT1";
    public static final String EXTRA_TEXT2 = "com.example.project.EXTRA_TEXT2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_ip_port);

        @SuppressLint("WrongViewCast") Button ok = (Button) findViewById(R.id.receivedata);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receive_data();
            }
        });
    }

    public void receive_data() {
        EditText ip = (EditText) findViewById(R.id.ip);
        String t = ip.getText().toString();

        EditText port = (EditText) findViewById(R.id.port);
        String t2 = port.getText().toString();

        Intent intent = new Intent(this, select_time.class);
        intent.putExtra(EXTRA_TEXT1, t);

        Intent intent2 = new Intent(this, select_time.class);
        intent.putExtra(EXTRA_TEXT2, t2);

        startActivity(intent);
    }
}