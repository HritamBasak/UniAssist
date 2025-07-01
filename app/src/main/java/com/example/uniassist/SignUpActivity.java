package com.example.uniassist;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uniassist.databinding.ActivitySignInBinding;
import com.example.uniassist.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import Model.Users;

public class SignUpActivity extends AppCompatActivity {
    @NonNull
    ActivitySignUpBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We're creating your account");

        //On CLicking the SignUp Button
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creating User with Mail and Password
                String text=binding.etuserName.getText().toString();
                String text1=binding.etEmail.getText().toString();
                String text2=binding.etPass.getText().toString();
                if(text.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                else if(text1.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Please Enter Your Mail", Toast.LENGTH_SHORT).show();
                }
                else if(text2.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.show();
                    auth.createUserWithEmailAndPassword
                                    (binding.etEmail.getText().toString(), binding.etPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        Users users = new Users(binding.etuserName.getText().toString(), binding.etEmail.getText().toString(), binding.etPass.getText().toString());
                                        String id = task.getResult().getUser().getUid();//Getting the user from the Result of the Task by UID
                                        database.getReference().child("Users").child(id).setValue(users);//Making the child node "Users" by their id  and setting and saving the values(username,mail,pass) into RealTime DataBase
                                        Toast.makeText(SignUpActivity.this, "User Created Succesfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Uer Not Created", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
        binding.tvAlreadyhaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}