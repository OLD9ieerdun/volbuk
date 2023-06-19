package com.example.myapplication.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.models.ApplicationEvent;
import com.example.myapplication.models.Event;
import com.example.myapplication.models.User;
import com.example.myapplication.screens.event.EventActivity;
import com.example.myapplication.screens.organizer.OrganizerActivity;
import com.example.myapplication.screens.organizer.OrganizerCreateActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestActivity extends AppCompatActivity {

    private RadioGroup toggle;
    private RadioButton rbUser, rbEvent;
    private ListView lvUser, lvEvent;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<User> listTemp1;
    private List<ApplicationEvent> listTemp2;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        init();
        getEventDataFromDB();
//        getUserDataFromDB();

        toggle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(rbEvent.isChecked()){
                    lvUser.setVisibility(View.GONE);
                    lvEvent.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "Event", Toast.LENGTH_SHORT).show();
                } else if (rbUser.isChecked()) {
                    lvEvent.setVisibility(View.GONE);
                    lvUser.setVisibility(View.VISIBLE);

                    Toast.makeText(getApplicationContext(), "User", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init(){
        toggle = findViewById(R.id.toggle);
        rbUser = findViewById(R.id.rbUser);
        rbEvent = findViewById(R.id.rbEvent);
        lvUser = findViewById(R.id.lvUser);
        lvEvent = findViewById(R.id.lvEvent);

        listData = new ArrayList<>();
        listTemp1 = new ArrayList<>();
        listTemp2 = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        //listView.setAdapter(adapter);
        //mDataBase = FirebaseDatabase.getInstance().getReference();
    }

    private void getEventDataFromDB(){
        mDataBase = FirebaseDatabase.getInstance().getReference("ApplicationEvent").child("34sefun898");
        lvEvent.setAdapter(adapter);
        ValueEventListener vListener = new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(listData.size() > 0)listData.clear();
                if(listTemp2.size() > 0)listTemp2.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    ApplicationEvent apEvent = ds.getValue(ApplicationEvent.class);
                    assert apEvent != null;
                    listData.add(apEvent.userName);
                    //Log.d("listTemp", event.toString());
                    listTemp2.add(apEvent);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(vListener);
    }

//    private void getUserDataFromDB(){
//        mDataBase = FirebaseDatabase.getInstance().getReference("Request");
//        lvUser.setAdapter(adapter);
//        ValueEventListener vListener = new ValueEventListener(){
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(listData.size() > 0)listData.clear();
//                if(listTemp1.size() > 0)listTemp1.clear();
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    User user = ds.getValue(User.class);
//                    assert user != null;
//                    listData.add(user.name);
//                    //Log.d("listTemp", event.toString());
//                    listTemp1.add(user);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        mDataBase.addValueEventListener(vListener);
//    }

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