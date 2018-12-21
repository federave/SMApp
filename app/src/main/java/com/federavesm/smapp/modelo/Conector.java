package com.federavesm.smapp.modelo;

/**
 * Created by Federico on 2/1/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/**
 * Created by Federico on 9/5/2017.
 */

public class Conector extends SQLiteOpenHelper
{

    public Conector(Context context)
    {
    super(context,"SMApp",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
    }


    public int getLastId(String tableName)
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM " + tableName,null);
        int x=-1;

        if(cursor.moveToLast())
            {
            x = cursor.getInt(0);
            }
        db.close();
        return x;
        }
    catch (Exception e)
        {
        return -1;
        }
    }





}
