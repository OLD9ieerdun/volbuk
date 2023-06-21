package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ShowEventActivity extends AppCompatActivity {

    private TextView tvName, tvData, tvResponsible, tvQuantity, tvPlace, tvDirection, tvDescription;
    private Button checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event);

        init();
        getIntentMain();
    }

    private void init(){
        tvName = findViewById(R.id.tvEventName);
        tvData = findViewById(R.id.tvEventData);
        tvResponsible = findViewById(R.id.tvEventResponsible);
        tvDirection = findViewById(R.id.tvEventDirection);
        tvQuantity = findViewById(R.id.tvEventQuantity);
        tvPlace = findViewById(R.id.tvEventPlace);
        tvDescription = findViewById(R.id.tvEventDescription);
        checkBox = findViewById(R.id.checkBox);
    }

    private void getIntentMain(){
        Intent i = getIntent();
        if(i != null){
            tvName.setText(i.getStringExtra(Constant.EVENT_NAME));
            tvData.setText("Дата проведения мероприятия:\n" + i.getStringExtra(Constant.EVENT_DATA));
            tvQuantity.setText("Количество участников:\n" + i.getStringExtra(Constant.EVENT_QUANTITY));
            tvPlace.setText("Место проведения мероприятия:\n" + i.getStringExtra(Constant.EVENT_PLACE));
            tvDirection.setText("Направление мероприятия:\n" + i.getStringExtra(Constant.EVENT_DIRECTION));
            tvDescription.setText("Описание мероприятия:\n" + i.getStringExtra(Constant.EVENT_DESCRIPTION));

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(i.getStringExtra(Constant.EVENT_RESPONSIBLE));
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = snapshot.child("name").getValue(String.class);
                    String secName = snapshot.child("secName").getValue(String.class);
                    tvResponsible.setText("Руководитель мероприятия:\n" + secName + " " + name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void onClickReturn (View view){
        Intent intent = new Intent(this, EventActivity.class);
        startActivity(intent);
    }
}