package com.example.sturent1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //Initialize and assign varialbles
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);

        //set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.studentProfile);

        //Perform ItemSelectorListener
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent1 = new Intent(StudentProfile.this, MapsActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.listView:
                        Intent intent2 = new Intent(StudentProfile.this, ListView.class);
                        startActivity(intent2);
                        break;

                    case R.id.addProperty:
                        Intent intent3 = new Intent(StudentProfile.this, AddProperty.class);
                        startActivity(intent3);
                        break;

                    case R.id.studentProfile:

                        break;
                }
            }
        });
    }
}