package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.screens.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edLogin, edPassword;
    private FirebaseAuth mAuth;
    private Button bReg, bStart, bExit, bSignIn;
    private TextView tvUserEmail, tvGreetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null){
            showSigned();

            String userName = "Вы вошли как:\n" + cUser.getEmail();
            tvUserEmail.setText(userName);

            Toast.makeText(this, "User not null " + cUser.getEmail(), Toast.LENGTH_SHORT).show();
        } else {
            notSigned();

            Toast.makeText(this, "User null", Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        edLogin = findViewById(R.id.edLogEmail);
        edPassword = findViewById(R.id.edLogPassword);
        tvGreetings = findViewById(R.id.tvGreetings);
        bSignIn  = findViewById(R.id.bSignIn);
        bReg  = findViewById(R.id.bReg);

        mAuth = FirebaseAuth.getInstance();

        tvUserEmail = findViewById(R.id.tvUserEmail);
        bStart = findViewById(R.id.bStart);
        bExit = findViewById(R.id.bExit);
    }


    public void onClickSignIn (View view){

        if(!TextUtils.isEmpty(edLogin.getText().toString()) && !TextUtils.isEmpty(edPassword.getText().toString())){

            mAuth.signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        //Toast.makeText(getApplicationContext(), "User SignIn Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    else{
                        Toast.makeText(getApplicationContext(), "User SignIn Failed", Toast.LENGTH_SHORT).show();
                    }

                }

            });

        }

    }

    public void onClickSignUp(View view){
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickStart(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickExitLog(View view){
        FirebaseAuth.getInstance().signOut();

        notSigned();
    }

    private void showSigned(){
        edLogin.setVisibility(View.GONE);
        edPassword.setVisibility(View.GONE);
        bSignIn.setVisibility(View.GONE);
        bReg.setVisibility(View.GONE);
        tvGreetings.setVisibility(View.GONE);

        bStart.setVisibility(View.VISIBLE);
        bExit.setVisibility(View.VISIBLE);
        tvUserEmail.setVisibility(View.VISIBLE);
    }

    private void notSigned(){
        bStart.setVisibility(View.GONE);
        bExit.setVisibility(View.GONE);
        tvUserEmail.setVisibility(View.GONE);

        edLogin.setVisibility(View.VISIBLE);
        edPassword.setVisibility(View.VISIBLE);
        bSignIn.setVisibility(View.VISIBLE);
        bReg.setVisibility(View.VISIBLE);
        tvGreetings.setVisibility(View.VISIBLE);
    }
}