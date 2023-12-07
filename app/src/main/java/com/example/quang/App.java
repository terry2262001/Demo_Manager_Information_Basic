package com.example.quang;

import android.app.Application;

import com.example.quang.dbHelper.DBHelper;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper db;
        db = new DBHelper(this);
        db.createThongTin();
    }
}
