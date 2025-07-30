package com.example.intentactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etBarang = findViewById(R.id.etBarang);
    }

    public void btnBarang(View view) {
        sendData(BarangActivity.class);
    }

    public void btnPenjualan(View view) {
        sendData(PenjualanActivity.class);
    }

    public void btnPembelian(View view) {
        sendData(PembelianActivity.class);
    }

    private void sendData(Class<?> targetActivity) {
        String data = etBarang.getText().toString();
        Intent intent = new Intent(this, targetActivity);
        intent.putExtra("ISI", data);
        startActivity(intent);
    }
}
