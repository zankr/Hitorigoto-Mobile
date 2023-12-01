package com.example.hitorigoto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hitorigoto.databinding.ActivitySignupBinding;

public class SignUp extends AppCompatActivity {

    ActivitySignupBinding binding;
    dbHelper DbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DbHelper = new dbHelper(this);

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.tfFullName.getText().toString();
                String email = binding.tfEmail.getText().toString();
                String password = binding.tfPassword.getText().toString();

                if (username.equals("") || email.equals("") || password.equals("")){
                    Toast.makeText(SignUp.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();

                } else {
                    Boolean checkUsername = DbHelper.checkUsername(username);


                        if (checkUsername == false){
                            Boolean insert = DbHelper.insertData(username, email, password);

                            if ( insert == true){
                                Toast.makeText(SignUp.this, "Signup Succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignUp.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SignUp.this, "User already exist", Toast.LENGTH_SHORT).show();
                        }

                }
            }
        });


    }

    public void backGetStarted(View view) {
        startActivity(new Intent(this, GettingStarted.class));
        finish();
    }

    public void goMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void goSignIn(View view) {
        startActivity(new Intent(this, SignIn.class));
        finish();
    }
}