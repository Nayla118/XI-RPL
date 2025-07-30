package com.example.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText edtAngka1, edtAngka2;
    TextView txtHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtAngka1 = findViewById(R.id.edtAngka1);
        edtAngka2 = findViewById(R.id.edtAngka2);
        txtHasil = findViewById(R.id.txtHasil);

        Button btnTambah = findViewById(R.id.btnTambah);
        Button btnKurang = findViewById(R.id.btnKurang);
        Button btnKali = findViewById(R.id.btnKali);
        Button btnBagi = findViewById(R.id.btnBagi);

        btnTambah.setOnClickListener(v -> hitung("+"));
        btnKurang.setOnClickListener(v -> hitung("-"));
        btnKali.setOnClickListener(v -> hitung("*"));
        btnBagi.setOnClickListener(v -> hitung("/"));
    }

    private void hitung(String operator) {
        String input1 = edtAngka1.getText().toString();
        String input2 = edtAngka2.getText().toString();

        // Validasi kosong pakai Toast
        if (input1.isEmpty() && input2.isEmpty()) {
            Toast.makeText(this, "Masukkan kedua angka terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        } else if (input1.isEmpty()) {
            Toast.makeText(this, "Angka pertama belum diisi", Toast.LENGTH_SHORT).show();
            return;
        } else if (input2.isEmpty()) {
            Toast.makeText(this, "Angka kedua belum diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        double angka1 = Double.parseDouble(input1);
        double angka2 = Double.parseDouble(input2);
        double hasil = 0;

        switch (operator) {
            case "+":
                hasil = angka1 + angka2;
                break;
            case "-":
                hasil = angka1 - angka2;
                break;
            case "*":
                hasil = angka1 * angka2;
                break;
            case "/":
                if (angka2 != 0) {
                    hasil = angka1 / angka2;
                } else {
                    Toast.makeText(this, "Tidak bisa dibagi 0", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }

        if (hasil == (int) hasil) {
            txtHasil.setText(String.valueOf((int) hasil));
        } else {
            txtHasil.setText(String.format("%.2f", hasil));
        }
    }
}
