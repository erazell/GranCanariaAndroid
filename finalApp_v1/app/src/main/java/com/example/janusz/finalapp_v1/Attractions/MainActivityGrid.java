package com.example.janusz.finalapp_v1.Attractions;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.janusz.finalapp_v1.R;

public class MainActivityGrid extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        setSingleEvent(mainGrid);
    }

        private void setSingleEvent(GridLayout mainGrid) {
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(finalI == 0){
                        Intent intent = new Intent(MainActivityGrid.this,Aguimes.class);
                        startActivity(intent);
                    }
                    else if(finalI == 1){
                        Intent intent = new Intent(MainActivityGrid.this,Arucas.class);
                        startActivity(intent);
                    }
                    else if(finalI == 2){
                        Intent intent = new Intent(MainActivityGrid.this,DunasDeMaspalomas.class);
                        startActivity(intent);
                    }
                   else if(finalI == 3){
                        Intent intent = new Intent(MainActivityGrid.this,Galdar.class);
                        startActivity(intent);
                    }
                    else if(finalI == 4){
                        Intent intent = new Intent(MainActivityGrid.this,LasPalmas.class);
                        startActivity(intent);
                    }
                    else if(finalI == 5){
                        Intent intent = new Intent(MainActivityGrid.this,Palmitos.class);
                        startActivity(intent);
                    }
                    else if(finalI == 6){
                        Intent intent = new Intent(MainActivityGrid.this,PlayaDeMogan.class);
                        startActivity(intent);
                    }
                    else if(finalI == 7){
                        Intent intent = new Intent(MainActivityGrid.this,PuertoRico.class);
                        startActivity(intent);
                    }
                    else if(finalI == 8){
                        Intent intent = new Intent(MainActivityGrid.this,Roque.class);
                        startActivity(intent);
                    }
                    else if(finalI == 9){
                        Intent intent = new Intent(MainActivityGrid.this,Teror.class);
                        startActivity(intent);
                    }



                }
            });
        }
    }

}
