package com.example.janusz.finalapp_v1.Weather;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.janusz.finalapp_v1.Attractions.MainActivityGrid;
import com.example.janusz.finalapp_v1.BottomNavigationViewHelper;
import com.example.janusz.finalapp_v1.GoogleMap.MapActivity;
import com.example.janusz.finalapp_v1.MainActivity;
import com.example.janusz.finalapp_v1.R;
import com.example.janusz.finalapp_v1.SQLiteNotes.MainListActivity;

/**
 * Created by janusz on 29.05.2018.
 */

public class MainActivityWeather extends AppCompatActivity {



    private static final String TAG = "WeatherActivity";

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;






    Typeface weatherFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();
        setContentView(R.layout.activity_main_weather);




        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        updatedField = (TextView)findViewById(R.id.updated_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.humidity_field);
        pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);


        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(weather_city);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Wilgotność powietrza: "+weather_humidity);
                pressure_field.setText("Ciśnienie: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));



            }

        });


        asyncTask.execute("28.125696","-15.440090" ); //  asyncTask.execute("Latitude", "Longitude")

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_menu:

                        Intent intent0 = new Intent(MainActivityWeather.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_note:
                        Intent intent1 = new Intent(MainActivityWeather.this, MainListActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.nav_map:
                        Intent intent2 = new Intent(MainActivityWeather.this, MapActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_attraction:
                        Intent intent3 = new Intent(MainActivityWeather.this, MainActivityGrid.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_weather:

                        break;




                }
                return false;
            }
        });


    }




}
