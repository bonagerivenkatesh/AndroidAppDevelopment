package com.example.venkatesh.hw06;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapterSaved.ItemClickCallback{

    TextView tvmessage;

    DatabaseDataManager dm;
    List<WeatherDB> weatherDBInserted;

    private RecyclerView recViewSaved;
    private RecyclerAdapterSaved adapterSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etcity = (EditText) findViewById(R.id.mainetcity);
        final EditText etcountry = (EditText) findViewById(R.id.mainetstate);
        Button btnsubmit = (Button) findViewById(R.id.mainbtnsubmit);

        tvmessage = (TextView) findViewById(R.id.maintvmessage);

        dm = new DatabaseDataManager(this);

        weatherDBInserted = new ArrayList<WeatherDB>();

        weatherDBInserted = dm.getAllWeather();

        if(weatherDBInserted.size()==0){
            tvmessage.setText("There are no cities to display. Search the\n" +
                    "city from the search box and save.");
        }
        else {
            tvmessage.setText("Saved Cities");

            recViewSaved = (RecyclerView)findViewById(R.id.saved_recycler);

            recViewSaved.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

            adapterSaved = new RecyclerAdapterSaved(weatherDBInserted, this);
            recViewSaved.setAdapter(adapterSaved);
            adapterSaved.setItemClickCallback(this);
            adapterSaved.notifyDataSetChanged();

        }


        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);



        Log.d("debugApp",(pref.getString("tempPreference","")));


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = etcity.getText().toString().trim();
                String country = etcountry.getText().toString().trim();

                if(city.length()>0 && country.length()>0)
                {
                    Intent i = new Intent(getApplicationContext(),CityWeather.class);

                    i.putExtra("city",city);
                    i.putExtra("country",country);

                    finish();

                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter the proper details", Toast.LENGTH_LONG).show();
                }

            }
        });






    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menumain, menu);
        return true;
    }

    @Override
    public MenuInflater getMenuInflater() {


        return super.getMenuInflater();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        try{
            int id = item.getItemId();

            if(id == R.id.settings){

                Intent i = new Intent(this, SettingsPreference.class);
                i.putExtra("caller", "MainActivity.class");
                finish();
                startActivity(i);
            }
        }catch (Exception e)
        {
            Log.d("debug2",e.getMessage());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(int p) {

        try
        {
            WeatherDB tempWeather = weatherDBInserted.get(p);

            if(dm.deleteWeather(tempWeather))
            {
                Toast.makeText(getApplicationContext(),"City Deleted Successfully",Toast.LENGTH_LONG).show();

                weatherDBInserted.remove(weatherDBInserted);

                weatherDBInserted = dm.getAllWeather();
                //adapterSaved.notifyDataSetChanged();

                if(weatherDBInserted.size()==0){
                    tvmessage.setText("There are no cities to display. Search the\n" +
                            "city from the search box and save.");
                }
                else {
                    tvmessage.setText("Saved Cities");
                }

                adapterSaved.setListData(weatherDBInserted);
                adapterSaved.notifyDataSetChanged();

            }
            else {
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
            }

        }catch (Exception e)
        {
            Log.d("debugApp","error is "+e.getMessage());
        }


    }

    @Override
    public void onStarClick(int p) {

        int flag = weatherDBInserted.get(p).getFavorite();
        WeatherDB tempWeather = weatherDBInserted.get(p);
        if(flag==1)
        {
            tempWeather.setFavorite(0);
        }
        else{
            tempWeather.setFavorite(1);
        }

        adapterSaved.setListData(weatherDBInserted);
        adapterSaved.notifyDataSetChanged();

        dm.deleteWeather(tempWeather);
        dm.saveWeather(tempWeather);
        weatherDBInserted = dm.getAllWeather();
        adapterSaved.setListData(weatherDBInserted);
        adapterSaved.notifyDataSetChanged();

        adapterSaved.setListData(weatherDBInserted);
        adapterSaved.notifyDataSetChanged();

    }
}
