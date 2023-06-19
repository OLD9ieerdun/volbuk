package com.example.myapplication.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.screens.event.EventActivity;
import com.example.myapplication.screens.organizer.OrganizerActivity;
import com.example.myapplication.screens.organizer.OrganizerCreateActivity;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
    }

    public void onClickMain (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickEvent (View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickOrganizer (View view){
        Intent intent = new Intent(this, OrganizerActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickRequest (View view){
        Intent intent = new Intent(this, RequestActivity.class);
        startActivity(intent);
        finish();
    }
}