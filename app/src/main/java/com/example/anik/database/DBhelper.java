package com.example.anik.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

/**
 * Created by Anik on 29-Sep-17.
 */

public class DBhelper extends SQLiteOpenHelper {

    public static final String TAG=DBhelper.class.getSimpleName();
    public static final String DB_name="myapp.db";
    public static final int DB_version =1;

    public static final String User_table="users";
    public static final String column_id="_id";
    public static final String column_email="email";
    public static final String column_pass="password";


    public static final String Create_table_user ="CREATE TABLE "+User_table+"("
            +column_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +column_email+" TEXT,"
            +column_pass+" TEXT);";

    public DBhelper(Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Create_table_user);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS" + User_table);
            onCreate(db);
    }

    public void addUser(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(column_email,email);
        values.put(column_pass,password);
        long id=db.insert(User_table,null,values);
        db.close();
        Log.d(TAG,"user inserted"+id);
    }

    public boolean getUser(String email,String password){
        String selectQuery="select * from " +User_table+ " where " +column_email+ " = " + "'"+email.toString()+"'" + " and " +column_pass+ " = "+"'"+password.toString()+"'";

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        cursor.moveToFirst();

        if(cursor.getCount()>0){
            return true;
        }

        cursor.close();
        db.close();
        return false;
    }
}
