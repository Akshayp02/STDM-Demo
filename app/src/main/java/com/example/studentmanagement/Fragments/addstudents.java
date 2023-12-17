package com.example.studentmanagement.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentmanagement.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addstudents#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addstudents extends Fragment {

 
    public addstudents() {
        // Required empty public constructor
    }

 

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

  
        return inflater.inflate(R.layout.fragment_addstudents, container, false);
    }
}