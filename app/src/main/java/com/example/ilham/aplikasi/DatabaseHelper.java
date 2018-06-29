package com.example.ilham.aplikasi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "teman_nonton";
    public static final String TABLE_NAME = "misi";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "user";

    SQLiteDatabase db = this.getWritableDatabase();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);

        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor readData() {
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);

        int result = db.update(TABLE_NAME, contentValues, "ID=?", new String[]{id});
        return cek_data(result);
    }

    public boolean deleteData(String id) {
        int i = db.delete(TABLE_NAME, "ID=?", new String[]{id});
        return cek_data(i);
    }

    public boolean cek_data(int result) {
        if (result>0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor getItemID(String name) {
        String query = "SELECT " + COL_1 + " FROM " + TABLE_NAME + " WHERE " + COL_2 + " = '"+name+"' ";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}

