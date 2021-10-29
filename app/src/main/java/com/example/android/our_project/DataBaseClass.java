package com.example.android.our_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseClass extends SQLiteOpenHelper {

    public DataBaseClass(Context context) {
        super(context,"resto.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table resto_table (meal text primary key,amount_medium int , amount_large int,total_price int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists resto_table");
        onCreate(sqLiteDatabase);
    }

    public  boolean insertDate (String meal , int medium_amount , int large_amount,int total_price )
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("meal",meal);
        values.put("amount_medium",medium_amount);
        values.put("amount_large",large_amount);
        values.put("total_price",total_price);

        long bool =db.insert("resto_table",null,values);
        if(bool ==-1)return false;
        else return true ;
    }

    public  Cursor getAllData ()
    {
        SQLiteDatabase db = this . getWritableDatabase();
        Cursor res = db.rawQuery("select * from resto_table",null);
        return res;
    }

    public  Integer deleteData(String meal)
    {
        SQLiteDatabase db = this .getWritableDatabase();
        return db.delete("resto_table","meal = ?",new String[]{meal});
    }

    public void deleteAll ()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("delete from resto_table");

    }
}
