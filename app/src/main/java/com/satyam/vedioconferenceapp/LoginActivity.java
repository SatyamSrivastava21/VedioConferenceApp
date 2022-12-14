package com.satyam.vedioconferenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
public class LoginActivity extends AppCompatActivity {
    EditText emailBox,passwordBox;
    Button signupBtn,loginBtn;
    FirebaseAuth auth;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database= FirebaseFirestore.getInstance();
        auth= FirebaseAuth.getInstance();
        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
         progressDialog.setTitle("Sabar Karo Login U In");
        progressDialog.setMessage(" Ho raha hai tumhara internet slow hai, Log In");
        emailBox=findViewById(R.id.emailBox);
        passwordBox=findViewById(R.id.passwordBox);
       loginBtn=findViewById(R.id.loginBtn);
       signupBtn=findViewById(R.id.createBtn);
       loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String email,pass;
                email=emailBox.getText().toString();
                pass=passwordBox.getText().toString();
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                        }else
                        {
                            Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });





    }
}