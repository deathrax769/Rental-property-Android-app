package com.example.sturent1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    TextView markertxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        markertxt = findViewById(R.id.marker);
        String title = getIntent().getStringExtra("title");
        markertxt.setText(title);
    }
}