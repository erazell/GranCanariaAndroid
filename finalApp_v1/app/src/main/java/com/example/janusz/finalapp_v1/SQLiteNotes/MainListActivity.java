package com.example.janusz.finalapp_v1.SQLiteNotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.janusz.finalapp_v1.Attractions.MainActivityGrid;
import com.example.janusz.finalapp_v1.BottomNavigationViewHelper;
import com.example.janusz.finalapp_v1.Weather.MainActivityWeather;
import com.example.janusz.finalapp_v1.GoogleMap.MapActivity;
import com.example.janusz.finalapp_v1.MainActivity;
import com.example.janusz.finalapp_v1.R;

import java.util.ArrayList;
/**
 * Created by janusz on 11.04.2018.
 */

public class MainListActivity extends AppCompatActivity {
    ListView noteList;
    Button btnAdd;
    DbNotes db;
    NotesAdapter adapter = null;

    ArrayList<Notes> list;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);

        noteList = (ListView) findViewById(R.id.contactList);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        list = new ArrayList<>();

        db = new DbNotes(this);

        ArrayList<Notes> contacts = db.getAllContacts();
        adapter = new NotesAdapter(this, R.layout.item_contacts, contacts);

        noteList.setAdapter(adapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainListActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        adapter.notifyDataSetChanged();

        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Notes selected_contact = (Notes) parent.getItemAtPosition(position);

                Intent intent = new Intent(MainListActivity.this, UpdateNote.class);
                intent.putExtra("id", selected_contact.getId());
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        BottomNavigationViewHelper.removeShiftMode(bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_menu:

                        Intent intent0 = new Intent(MainListActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.nav_note:

                        break;
                    case R.id.nav_map:
                        Intent intent2 = new Intent(MainListActivity.this, MapActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.nav_attraction:
                        Intent intent3 = new Intent(MainListActivity.this, MainActivityGrid.class);
                        startActivity(intent3);
                        break;
                    case R.id.nav_weather:
                        Intent intent4 = new Intent(MainListActivity.this, MainActivityWeather.class);
                        startActivity(intent4);
                        break;




                }
                return false;
            }
        });


    }
    @Override
    protected void onResume() {

        super.onResume();
        ArrayList<Notes> contacts = db.getAllContacts();


        NotesAdapter contactAdapter = new NotesAdapter(this, R.layout.item_contacts, contacts);
        noteList.setAdapter(contactAdapter);

    }
}
