package com.example.ilham.aplikasi;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DataTambah extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tambah);

        editText = findViewById(R.id.username);
        button = findViewById(R.id.button_simpan);

        myDb = new DatabaseHelper(this);
    }

    public void tambah_data(View view) {
        String nama_user = editText.getText().toString();
        Boolean result = myDb.insertData(nama_user);

        if (result == true) {
            tampil_toast("Data Berhasil ditambahkan");
            newWindow();
        } else {
            tampil_toast("Ada masalah saat menambahkan data");
        }
    }

    public void tampil_toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void newWindow() {
        Intent intent = new Intent(this, DataTampilListView.class);
        startActivity(intent);
    }
}
