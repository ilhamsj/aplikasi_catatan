package com.example.ilham.aplikasi;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DataTampilListView extends AppCompatActivity {

    DatabaseHelper myDb;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tampil_list_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.myList);
        myDb = new DatabaseHelper(this);
        tampil_data();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    public void tampil_data() {
        Cursor res = myDb.readData();
        ArrayList<String> listData = new ArrayList<>();

        while (res.moveToNext()) {
            listData.add(res.getString(1));
        }
        final ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Cursor data = myDb.getItemID(name);

                int itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }

                if (itemID > -1) {
                    tampil_toast(name);
                    Intent editData = new Intent(DataTampilListView.this, DataUpdate.class);
                    editData.putExtra("id", itemID);
                    editData.putExtra("name", name);
                    startActivity(editData);
                } else {
                    tampil_toast("Object Not Found");
                }
            }
        });
    }

    public void tampil_toast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void halaman_awal(View view) {
        Intent intent = new Intent(this, DataTambah.class);
        startActivity(intent);
    }
}
