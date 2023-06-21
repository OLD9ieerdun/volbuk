package com.example.myapplication.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.screens.event.EventActivity;
import com.example.myapplication.screens.organizer.OrganizerActivity;
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

    private ImageView imView, imageView2;
    private ConstraintLayout layoutStudent, layoutCounselor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        tvSecName = findViewById(R.id.tvSecName);
        tvPoint = findViewById(R.id.tvPoint);
        tvPointClass = findViewById(R.id.tvPointClass);
        layoutStudent = findViewById(R.id.layoutStudent);
        layoutCounselor = findViewById(R.id.layoutCounselor);
        imageView2 = findViewById(R.id.imageView2);

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
                    point = 100;
                }

                if(point <= 201){
                    imageView2.setImageResource(R.drawable.copper);
                    tvPointClass.setText("Медный уровень");
                } else if (point <= 401) {
                    imageView2.setImageResource(R.drawable.bronze);
                    tvPointClass.setText("Бронзовый уровень");
                } else if (point <= 601) {
                    imageView2.setImageResource(R.drawable.silver);
                    tvPointClass.setText("Серебряный уровень");
                } else if (point <= 801) {
                    imageView2.setImageResource(R.drawable.gold);
                    tvPointClass.setText("Золотой уровень");
                } else if (point <= 1000) {
                    imageView2.setImageResource(R.drawable.brilliant);
                    tvPointClass.setText("Бриллиантовый уровень");
                }

                String photoUrl = dataSnapshot.child("URLimage").getValue(String.class);
                Glide.with(MainActivity.this).load(photoUrl).into(imView);
                Toast.makeText(getApplicationContext(), photoUrl, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }

    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String post = dataSnapshot.child("post").getValue(String.class);


                Toast.makeText(getApplicationContext(), post, Toast.LENGTH_SHORT).show();


                if(post.equals("Вожатый")){
                    layoutStudent.setVisibility(View.GONE);

                    layoutCounselor.setVisibility(View.VISIBLE);
                }
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

    public void onClickRequest (View view){
        Intent intent = new Intent(this, RequestActivity.class);
        startActivity(intent);
        finish();
    }
}