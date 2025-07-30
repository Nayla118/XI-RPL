package com.example.counter;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;
    private TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHasil = findViewById(R.id.tvHasil);
        updateCounterText();
    }

    public void btnUP(View view) {
        counter++;
        updateCounterText();
    }

    public void btnDown(View view) {
        counter--;
        updateCounterText();
    }

    private void updateCounterText() {
        tvHasil.setText(String.valueOf(counter));
    }
}
