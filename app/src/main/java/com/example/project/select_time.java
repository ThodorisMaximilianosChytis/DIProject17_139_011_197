package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class select_time extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.project.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_time);

        @SuppressLint("WrongViewCast") Button ok = (Button) findViewById(R.id.time);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receive_time();
            }
        });
    }

    public void receive_time() {
        EditText time = (EditText) findViewById(R.id.time);
        int t = Integer.parseInt(time.getText().toString());

        Intent intent = new Intent(this, select_time.class);
        intent.putExtra(EXTRA_NUMBER, t);
        startActivity(intent);
    }
}