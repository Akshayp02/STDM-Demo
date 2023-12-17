package com.example.studentmanagement;

import android.app.Dialog;
import android.os.Bundle;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentmanagement.Models.StudentModel;

import com.example.studentmanagement.databinding.ActivityMainBinding;
import com.example.studentmanagement.databinding.AddstudentBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = FirebaseAuth.getInstance().getCurrentUser();

        binding.addstudent.setOnClickListener(v -> {
            Dialog dialog = new Dialog(MainActivity.this);
            // Assuming AddstudentBinding is generated from addstudent.xml
            AddstudentBinding dialogBinding = AddstudentBinding.inflate(getLayoutInflater());
            dialog.setContentView(dialogBinding.getRoot());
            dialog.getWindow().setLayout(700, 1100);
            dialog.getWindow().setBackgroundDrawableResource(R.color.white);
            dialog.show();

            String name = dialogBinding.name.getText().toString();
            String email = dialogBinding.email.getText().toString();
            String phone = dialogBinding.phone.getText().toString();
            String id = dialogBinding.id.getText().toString();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || id.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            } else {
                // Constructing the correct DocumentReference
                DocumentReference documentReference = db.collection("students").document(user.getUid()).collection("user_students").document(id);

                documentReference.set(new StudentModel(name, email, phone, id))
                        .addOnSuccessListener(
                                command -> {
                                    Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }
                        )
                        .addOnFailureListener(
                                command -> {
                                    Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
                                }
                        );
            }


        });


    }
}