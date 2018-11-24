package com.arcsoft.sdk_demo.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "face_cabinet_1114.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
        //main Box
//        db.execSQL("INSERT INTO box VALUES(?)", new Object[]{1});

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:

            case 2:

            case 3://需要新增加一张称重的表，试剂记录需要多一列试剂名
        }
    }


    public void createTable(SQLiteDatabase db){
        db.execSQL("CREATE TABLE IF NOT EXISTS faceregister" +
                "(_id INTEGER PRIMARY KEY,userName VARCHAR,boxId INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS user" +
                "(_id INTEGER PRIMARY KEY,userName VARCHAR)");
    }


}
