package com.example.janusz.finalapp_v1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.janusz.finalapp_v1.Attractions.MainActivityGrid;
import com.example.janusz.finalapp_v1.Weather.MainActivityWeather;
import com.example.janusz.finalapp_v1.GoogleMap.MapActivity;
import com.example.janusz.finalapp_v1.SQLiteNotes.MainListActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tutorial";
    private static final String TAG2 = "MainActivityMap";
    private static final int Error_DIALOG_REQUEST = 9001;

    Button buttonZapiszShared, buttonNotes,button4, buttonMap;
    EditText editTextShared;
    TextView textViewShared;
    ImageButton btn1, btn2, btn3;
    Intent intentCallService4;


    ScrollView scrollView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNav);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        intentCallService4 = new Intent(this, MyService4.class);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                        case R.id.nav_menu:

                            break;

                        case R.id.nav_note:
                            Intent intent1 = new Intent(MainActivity.this, MainListActivity.class);
                            startActivity(intent1);
                            break;
                        case R.id.nav_map:
                            Intent intent2 = new Intent(MainActivity.this, MapActivity.class);
                            startActivity(intent2);
                            break;

                        case R.id.nav_attraction:
                            Intent intent3 = new Intent(MainActivity.this, MainActivityGrid.class);
                            startActivity(intent3);
                            break;
                        case R.id.nav_weather:
                            Intent intent4 = new Intent(MainActivity.this, MainActivityWeather.class);
                            startActivity(intent4);
                            break;


                    }
                return false;
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "790686607";
                Uri call = Uri.parse("tel:" + number);
                Intent intent = new Intent(Intent.ACTION_DIAL, call);

                startActivity(intent);
            }

        });

        editTextShared = findViewById(R.id.editTextShared);
        textViewShared = findViewById(R.id.textViewShared);

        buttonZapiszShared = findViewById(R.id.buttonZapiszShared);

    }

    public void saveMethod(View view) {

        SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userTarget", editTextShared.getText().toString());
        editor.apply();

        Toast.makeText(this, "Zapisano!", Toast.LENGTH_LONG).show();

        String temp1 = sharedPref.getString("userTarget", "");
        textViewShared.setText("Cel na dzisiaj: \n" + temp1);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: app restart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: app start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: app resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: app pasue");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: app stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: app destroy");
    }
    public boolean isServiceOk() {
        Log.d(TAG, "isServiceOk: checking google service version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            Log.d(TAG2, "isServiceOk: Google Play");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Log.d(TAG2, "isServiceOk: Error occured, but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, Error_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "we can't make maps requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


public void onClick2(View v) {
    if (v.getId() == R.id.btn2) {
        Log.e("MAIN", "onClick: starting service4");
        startService(intentCallService4);
    } else if (v.getId() == R.id.btn3) {
        Log.e("MAIN", "onClick: stopping service4");
        stopService(intentCallService4);
    }
    }
}


