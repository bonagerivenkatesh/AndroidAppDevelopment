package com.example.venkatesh.hw06;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Venkatesh on 10/20/2016.
 */
public class WeatherTable {

    //cityname, country, temperature, favorite

    static final String TABLENAME = "Cities";
    static final String COLUMN_CITY ="city";
    static final String COLUMN_COUNTRY = "country";
    static final String COLUMN_TEMPERATURE = "temperature";
    static final String COLUMN_FAVORITE = "favorite";
    static final String COLUMN_UPDATEDDATE = "updatedDate";

    static public void onCreate(SQLiteDatabase db)
    {
        StringBuilder sb =new StringBuilder();

        sb.append("Create table if not exists "+TABLENAME+" (");
        sb.append(COLUMN_CITY+" text not null primary key, ");
        sb.append(COLUMN_COUNTRY+" text not null, ");
        sb.append(COLUMN_TEMPERATURE+" text not null, ");
        sb.append(COLUMN_FAVORITE+" integer not null, ");
        sb.append(COLUMN_UPDATEDDATE+" text not null);");
        try{
            db.execSQL(sb.toString());
        }catch (SQLException ex)
        {
            Log.d("debugdb", ex.getMessage());
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersiom)
    {
        db.execSQL("drop table if exists "+TABLENAME);
        WeatherTable.onCreate(db);
    }
}
