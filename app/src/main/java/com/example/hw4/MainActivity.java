package com.example.hw4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextNameBird, editTextZipcode, editTextNamePerson;
    Button buttonSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNameBird = findViewById(R.id.editTextNameBird);
        editTextZipcode = findViewById(R.id.editTextZipcode);
        editTextNamePerson = findViewById(R.id.editTextNamePerson);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Bird Obs");



        if (view == buttonSubmit) {

        }


    }
}
