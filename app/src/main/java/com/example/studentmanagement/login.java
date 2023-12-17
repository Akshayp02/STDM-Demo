package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.studentmanagement.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    ActivityLoginBinding binding;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        binding.register.setOnClickListener(v -> {
           Intent intent = new Intent(login.this, CreateAccount.class);
            startActivity(intent);
            finish();
        });


        binding.login.setOnClickListener(v -> {
            String email = binding.emails.getText().toString();
            String password = binding.passwords.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(login.this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
        });

    }
}