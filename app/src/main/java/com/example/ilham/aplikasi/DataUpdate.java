package com.example.ilham.aplikasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;

public class DataUpdate extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText edit_user, editID;

    int selectedID;
    String selectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_update);

        myDb = new DatabaseHelper(this);
        editID = (EditText) findViewById(R.id.id_user);
        edit_user = (EditText) findViewById(R.id.username);

        Intent recIntent = getIntent();
        selectedID = recIntent.getIntExtra("id", -1);
        selectedName = recIntent.getStringExtra("name");


        editID.setText(Integer.toString(selectedID));
        edit_user.setText(selectedName);
    }

    public void update_data(View view) {
        String ambil_ID = editID.getText().toString();
        String ambil_user = edit_user.getText().toString();

        if (!ambil_user.equals("")) {
            myDb.updateData(ambil_ID, ambil_user);
            tampil_toast("Data berhasil di update");
            newWindow();
        } else {
            tampil_toast("Data tidak boleh kosong");
        }
    }

    public void tampil_toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void newWindow() {
        Intent intent = new Intent(this, DataTampilListView.class);
        startActivity(intent);
    }

    public void hapus(View view) {
        String ambil_id = editID.getText().toString();
        if (ambil_id.isEmpty()) {
            tampil_toast("Data tidak boleh kosong");
        } else {
            Boolean result = myDb.deleteData(ambil_id);
            if (result == true) {
                tampil_toast("Data berhasil dihapus");
                newWindow();
            } else {
                tampil_toast("Data tidak ditemukan");
            }
        }
    }
}
