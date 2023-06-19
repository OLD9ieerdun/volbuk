package com.example.myapplication.screens.organizer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowOrganizerActivity extends AppCompatActivity {

    private TextView tvName, tvData, tvResponsible, tvQuantity, tvPlace, tvDirection, tvDescription;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_organizer);

        init();
        getIntentMain();
    }

    private void init(){
        tvName = findViewById(R.id.tvEventNameOrg);
        tvData = findViewById(R.id.tvEventDataOrg);
        tvResponsible = findViewById(R.id.tvEventResponsibleOrg);
        tvDirection = findViewById(R.id.tvEventDirectionOrg);
        tvQuantity = findViewById(R.id.tvEventQuantityOrg);
        tvPlace = findViewById(R.id.tvEventPlaceOrg);
        tvDescription = findViewById(R.id.tvEventDescriptionOrg);
        mDataBase = FirebaseDatabase.getInstance().getReference();
    }

    private void getIntentMain(){
        Intent i = getIntent();
        if(i != null){
            tvName.setText(i.getStringExtra(Constant.EVENT_NAME));
            tvData.setText("Дата проведения мероприятия:\n" + i.getStringExtra(Constant.EVENT_DATA));
            tvQuantity.setText("Количество участников:\n" + i.getIntExtra(Constant.EVENT_QUANTITY, 0));
            tvPlace.setText("Место проведения мероприятия:\n" + i.getStringExtra(Constant.EVENT_PLACE));
            tvDirection.setText("Направление мероприятия:\n" + i.getStringExtra(Constant.EVENT_DIRECTION));
            tvDescription.setText("Описание мероприятия:\n" + i.getStringExtra(Constant.EVENT_DESCRIPTION));

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(i.getStringExtra(Constant.EVENT_RESPONSIBLE));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String respName = snapshot.child("name").getValue(String.class);
                    String respSecName = snapshot.child("secName").getValue(String.class);
                    tvResponsible.setText("Руководитель мероприятия:\n" + respSecName + " " + respName);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void onClickShowExit (View view){
        Intent intent = new Intent(this, OrganizerActivity.class);
        startActivity(intent);
        finish();
    }
}