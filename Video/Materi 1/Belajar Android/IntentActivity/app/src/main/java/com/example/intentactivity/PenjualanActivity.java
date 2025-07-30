package com.example.intentactivity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PenjualanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);

        String data = getIntent().getStringExtra("ISI");
        TextView tvHasil = findViewById(R.id.tvHasil);
        tvHasil.setText(data);
    }
}
