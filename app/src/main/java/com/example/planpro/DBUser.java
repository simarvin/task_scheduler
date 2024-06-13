package com.example.planpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBUser extends SQLiteOpenHelper {
    public DBUser(@Nullable Context context) {
        super(context, "user", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  user (id INTEGER PRIMARY KEY, username TEXT, password TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");

    }

    public Boolean userRegister(String username, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contValues = new ContentValues();

        contValues.put("username", username);
        contValues.put("password", password);

        long result = db.insert("user", null, contValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean userExist(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ?", new String[]{username});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Boolean userLogin(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});

        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<ModalUser> fetchUser(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
        ArrayList<ModalUser> arrayList = new ArrayList<>();

        while (cursor.moveToNext()){
            ModalUser modalUser = new ModalUser();
            modalUser.id = cursor.getInt(0);
            modalUser.username = cursor.getString(1);
            modalUser.password = cursor.getString(2);

            arrayList.add(modalUser);
        }
        return arrayList;
    }
}
