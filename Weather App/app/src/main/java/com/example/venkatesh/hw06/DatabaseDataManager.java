package com.example.venkatesh.hw06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Venkatesh on 10/20/2016.
 */
public class DatabaseDataManager {

    private Context mContext;
    private DatabaseOpenHandler dbOpenHelper;
    private SQLiteDatabase db;
    private WeatherDAO weatherDAO;

    public DatabaseDataManager(Context mContext) {
        this.mContext = mContext;

        dbOpenHelper = new DatabaseOpenHandler(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        weatherDAO = new WeatherDAO(db);
    }

    public  void close(){
        if(db!=null){
            db.close();
        }
    }

    public WeatherDAO getWeathereDAO(){
        return this.weatherDAO;
    }

    public long saveWeather(WeatherDB weather){
        return this.weatherDAO.save(weather);
    }

    public boolean updateWeather(WeatherDB weather){
        return this.weatherDAO.update(weather);
    }

    public boolean deleteWeather(WeatherDB weather){
        return this.weatherDAO.delete(weather);
    }

    public List<WeatherDB> getAllWeather(){
        return  this.getWeathereDAO().getAll();
    }
}
