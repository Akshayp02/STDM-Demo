package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.studentmanagement.databinding.ActivityCreateAccountBinding;
import com.example.studentmanagement.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    ActivityCreateAccountBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();


        binding.register.setOnClickListener(v -> {
            String email = binding.emails.getText().toString();
            String password = binding.pass.getText().toString();
            String confirmPassword = binding.cnpass.getText().toString();

            if (isPasswordValid(password, confirmPassword)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(CreateAccount.this, login.class);
                                startActivity(intent);
                            }
                        }

                );

            }else {
                Toast.makeText(this, "Password not match", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        if (password.toString().equals(confirmPassword.toString()) ) {
          return true;
        }
        return false;
    }
}