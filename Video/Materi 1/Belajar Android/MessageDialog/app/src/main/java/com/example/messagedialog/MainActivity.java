package com.example.messagedialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnToast(View view) {
        showToast("Selamat Belajar");
    }

    public void btnAlert(View view) {
        showAlert("Selamat Belajar");
    }

    public void btnAlertDialogButton(View view) {
        showAlertButton("Yakin Akan Menghapus?");
    }

    private void showToast(String pesan) {
        Toast.makeText(this, pesan, Toast.LENGTH_SHORT).show();
    }

    private void showAlert(String pesan) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(pesan);
        alert.show();
    }

    private void showAlertButton(String pesan) {
        AlertDialog.Builder showAlert = new AlertDialog.Builder(this);
        showAlert.setMessage(pesan);

        showAlert.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                showToast("Data Sudah dihapus");
            }
        });

        showAlert.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                showToast("Data tidak dihapus");
            }
        });

        showAlert.show();
    }
}
