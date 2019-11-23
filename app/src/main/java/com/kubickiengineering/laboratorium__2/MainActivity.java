package com.kubickiengineering.laboratorium__2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.przyciskLogowania);

        button.setOnClickListener(v -> {
                Intent intent = new Intent(this, AbsenceActivity.class);
                startActivity(intent);
        });
    }
}
