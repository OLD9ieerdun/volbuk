package com.example.myapplication.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.screens.event.EventActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private TextView tvName, tvSecName, tvPoint, tvPointClass;

    private ImageView imView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvSecName = findViewById(R.id.tvSecName);
        tvPoint = findViewById(R.id.tvPoint);
        tvPointClass = findViewById(R.id.tvPointClass);

        imView = findViewById(R.id.imView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue(String.class);
                String secName = dataSnapshot.child("secName").getValue(String.class);
                Integer point = dataSnapshot.child("point").getValue(Integer.class);

                tvName.setText(name);
                tvSecName.setText(secName);
                tvPoint.setText(String.valueOf(point));

                if(point > 1000){
                    point = 1000;
                }

                if(point <= 201){
                    tvPointClass.setText("Медный");
                } else if (point <= 401) {
                    tvPointClass.setText("Бронзовый");
                } else if (point <= 601) {
                    tvPointClass.setText("Серебряный");
                } else if (point <= 801) {
                    tvPointClass.setText("Золотой");
                } else if (point <= 1000) {
                    tvPointClass.setText("Бриллиантовый");
                }

                String photoUrl = dataSnapshot.child("imageUrl").getValue(String.class);
                Glide.with(MainActivity.this).load(photoUrl).into(imView);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    public void onClickExit(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

        FirebaseAuth.getInstance().signOut();
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
}