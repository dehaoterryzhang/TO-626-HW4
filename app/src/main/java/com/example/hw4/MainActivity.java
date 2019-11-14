package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemReport) {
            Toast.makeText(this, "You are already in report page!", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.itemSearch) {
            Intent SearchIntent = new Intent(this, SearchActivity.class);
            startActivity(SearchIntent);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("BirdObs");

        if (view == buttonSubmit) {

            String createNameBird = editTextNameBird.getText().toString();
            String createZipcode = editTextZipcode.getText().toString();
            String createNamePerson = editTextNamePerson.getText().toString();

            Bird createBird = new Bird(createNameBird, createZipcode, createNamePerson);

            myRef.push().setValue(createBird);
        }


    }
}
