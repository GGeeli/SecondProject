package com.example.secondproject.SQLite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

public class LocalDatabase  extends SQLiteOpenHelper {


    public LocalDatabase(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(username text  primary key,email text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins = db.insert("user",null,contentValues);
        if(ins == -1 ) return false;
        else return true;
    }

    public boolean checkusername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?",new String[]{username});
        if(cursor.getCount() > 0 ) return false;
        else return true;
    }

    public boolean checkemail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=?",new String[]{email});
        if(cursor.getCount() > 0 ) return false;
        else return true;
    }

    public boolean usernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email=? and password=?",new String[]{username,password});
        if (cursor.getCount() > 0 ) return true;
        else return false;
    }

}
