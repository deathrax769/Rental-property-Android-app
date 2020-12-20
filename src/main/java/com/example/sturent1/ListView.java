package com.example.sturent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class  ListView extends AppCompatActivity {
    CardView card1, card2, card3, card4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        card1 = findViewById(R.id.display1);
        card2 = findViewById(R.id.display2);
        card3 = findViewById(R.id.display3);
        card4 = findViewById(R.id.display4);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListView.this, Display1.class);
                startActivity(intent);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListView.this, Display2.class);
                startActivity(intent);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListView.this, Display3.class);
                startActivity(intent);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListView.this, Display4.class);
                startActivity(intent);
            }
        });



        //Initialize and assign varialbles
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.listView);

        //Perform ItemSelectorListener
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.map:
                        Intent intent1 = new Intent(ListView.this, MapsActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.listView:
                        break;

                    case R.id.addProperty:
                        Intent intent2 = new Intent(ListView.this, AddProperty.class);
                        startActivity(intent2);
                        break;
                    case R.id.studentProfile:
                        Intent intent3 = new Intent(ListView.this, StudentProfile.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }
}