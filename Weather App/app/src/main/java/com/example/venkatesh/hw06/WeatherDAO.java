package com.example.venkatesh.hw06;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Venkatesh on 10/20/2016.
 */
public class WeatherDAO {

    private SQLiteDatabase db;

    public WeatherDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(WeatherDB weather)
    {
        ContentValues values = new ContentValues();

        values.put(WeatherTable.COLUMN_CITY,weather.city);
        values.put(WeatherTable.COLUMN_COUNTRY,weather.country);
        values.put(WeatherTable.COLUMN_TEMPERATURE,weather.temperature);
        values.put(WeatherTable.COLUMN_FAVORITE,weather.favorite);
        values.put(WeatherTable.COLUMN_UPDATEDDATE,weather.getUpdatedDate());

        return db.insert(WeatherTable.TABLENAME,null, values);

    }

    public boolean update(WeatherDB weather){

        ContentValues values = new ContentValues();

        values.put(WeatherTable.COLUMN_CITY,weather.city);
        values.put(WeatherTable.COLUMN_COUNTRY,weather.country);
        values.put(WeatherTable.COLUMN_TEMPERATURE,weather.temperature);
        values.put(WeatherTable.COLUMN_UPDATEDDATE,weather.updatedDate);

        return db.update(WeatherTable.TABLENAME,values,WeatherTable.COLUMN_CITY + "=?",new String[]{weather.city}) >0;


    }

    public boolean delete(WeatherDB weather){


        return db.delete(WeatherTable.TABLENAME,WeatherTable.COLUMN_CITY + " = ?",new String[]{weather.city}) >0;

    }

    public List<WeatherDB> getAll(){

        List<WeatherDB> weatherDBList = new ArrayList<WeatherDB>();

        String orderBy =  WeatherTable.COLUMN_FAVORITE + " DESC";

        Cursor c = db.query(WeatherTable.TABLENAME, new String[]{WeatherTable.COLUMN_CITY,WeatherTable.COLUMN_COUNTRY,WeatherTable.COLUMN_TEMPERATURE,WeatherTable.COLUMN_FAVORITE,WeatherTable.COLUMN_UPDATEDDATE }, null,null,null,null,orderBy);

        if(c!=null && c.moveToFirst()){

            do{

                WeatherDB weatherDB = buildNoteFromCursor(c);

                if(weatherDB != null){
                    weatherDBList.add(weatherDB);
                }
            }while (c.moveToNext());



            if (!c.isClosed())
                c.close();
        }

        return weatherDBList;


    }

    private WeatherDB buildNoteFromCursor(Cursor c){

        WeatherDB weather=null;

        if(c!=null)
        {
            weather = new WeatherDB();

            weather.setCity(c.getString(0));
            weather.setCountry(c.getString(1));
            weather.setTemperature(c.getString(2));
            weather.setFavorite(c.getInt(3));
            weather.setUpdatedDate(c.getString(4));
        }

        return weather;
    }
}
