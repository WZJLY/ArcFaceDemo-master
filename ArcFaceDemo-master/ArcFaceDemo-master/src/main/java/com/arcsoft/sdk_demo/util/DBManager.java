package com.arcsoft.sdk_demo.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;
    private String DBOPERATION = "DBManager";

    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void tableUpgrade(){
        helper.createTable(db);
    }


    //----------------------------------user manage begin----------------------------//

    public boolean isFaceRegisterExist(String strUserName)
    {
        Cursor cursor = db.query("faceregister",new String[] {"userName","boxId"},"userName=?",new String[]{strUserName},null,null,null);
        if(cursor.moveToNext()){
            Log.i(DBOPERATION,"用户已经存在");
            return true;

        }
        return false;

    }



    public void addRegister(FaceRegister register){
        if(!isFaceRegisterExist(register.getUserName())){
            db.execSQL("INSERT INTO faceregister VALUES(null,?,?)",new Object[]{register.getUserName(),register.getBoxId()});
            Log.i(DBOPERATION, "Insert Success");

        }
        else
        {
            Log.i(DBOPERATION, "already exist!");
        }

    }
    public boolean isUserExist(String strUser)
    {
        Cursor cursor = db.query("user",new String[] {"userName"},"userName=?",new String[]{strUser},null,null,null);
        if(cursor.moveToNext()){
            Log.i(DBOPERATION,"该用户已经存在");
            return true;

        }
        return false;

    }

    public void addUser(User user){

        if(!isUserExist(user.getUserName())){
            db.execSQL("INSERT INTO user VALUES(null,?)",new Object[]{user.getUserName()});
            Log.i(DBOPERATION, "Insert Success");

        }
        else
        {
            Log.i(DBOPERATION, "already exist!");
        }


    }
    public void deletUser(String strUserName)
    {
        db.delete("user","_id=",new  String[]{strUserName});
    }



    public void updateRegisterByUserName(String strUserName, int ibodID){
        ContentValues cv = new ContentValues();
        cv.put("userName",strUserName);
        cv.put("boxId",ibodID);
        db.update("faceregister",cv,"userName=?",new String[]{strUserName});
    }

    public void deletFaceregister(String strUserName)
    {
        db.delete("faceregister","userName=?",new  String[]{strUserName});
    }
    public boolean isFaceRegisterExistByboxID(int boxID)
    {
        Cursor cursor = db.query("faceregister",new String[] {"userName","boxId"},"boxId=?",new String[]{boxID+""},null,null,null);
        if(cursor.moveToNext()){
            Log.i(DBOPERATION,"该柜子已经存在");
            return true;

        }
        return false;

    }

    public FaceRegister getRegisterByUser(String strUserName) {
        Cursor cursor = db.rawQuery("select * from faceregister where userName = '" + strUserName + "'",null);
        FaceRegister register = null;
        ArrayList<FaceRegister> arrListRegister = new ArrayList<>();
        if (cursor.moveToFirst()) {
            if (!cursor.isAfterLast()) {
                register = new FaceRegister(cursor.getString(cursor.getColumnIndex("userName")),
                        cursor.getInt(cursor.getColumnIndex("boxId")));
                return register;
            }
        }
        return register;
    }


}
