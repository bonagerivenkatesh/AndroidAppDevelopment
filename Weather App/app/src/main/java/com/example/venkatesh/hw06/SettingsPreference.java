package com.example.venkatesh.hw06;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by Venkatesh on 10/20/2016.
 */
public class SettingsPreference extends PreferenceActivity {

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.pref_data_sync);

        ListPreference listPreference = (ListPreference) findPreference("tempPreference");

        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {

                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String tempType = pref.getString("tempPreference","");

                String caller=getIntent().getExtras().getString("caller");

                if(tempType.equals("F"))
                {
                    Toast.makeText(getApplicationContext(),"temperature format changed to Celsius",Toast.LENGTH_LONG).show();
                    Intent i;
                    if(caller.equals("MainActivity.class"))
                        i = new Intent(getApplicationContext(),MainActivity.class);
                    else
                    {
                        i = new Intent(getApplicationContext(),CityWeather.class);
                        String city,country;

                        city = getIntent().getExtras().getString("city");
                        country = getIntent().getExtras().getString("country");

                        i.putExtra("city",city);
                        i.putExtra("country",country);

                    }


                    finish();
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"temperature format changed to Fahreneit",Toast.LENGTH_LONG).show();

                    Intent i;
                    if(caller.equals("MainActivity.class"))
                        i = new Intent(getApplicationContext(),MainActivity.class);
                    else
                    {
                        i = new Intent(getApplicationContext(),CityWeather.class);
                        String city,country;

                        city = getIntent().getExtras().getString("city");
                        country = getIntent().getExtras().getString("country");

                        i.putExtra("city",city);
                        i.putExtra("country",country);
                    }


                    finish();
                    startActivity(i);

                    finish();
                    //startActivity(i);
                }

                return true;
            }
        });

    }

    public void onBackPressed()
    {

        String caller=getIntent().getExtras().getString("caller");

        Intent i;
        if(caller.equals("MainActivity.class"))
            i = new Intent(getApplicationContext(),MainActivity.class);
        else
        {
            i = new Intent(getApplicationContext(),CityWeather.class);
            String city,country;

            city = getIntent().getExtras().getString("city");
            country = getIntent().getExtras().getString("country");

            i.putExtra("city",city);
            i.putExtra("country",country);

        }


        finish();
        startActivity(i);

        return;
    }



}