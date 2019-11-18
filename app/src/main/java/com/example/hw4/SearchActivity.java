package com.example.hw4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.Integer.parseInt;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editTextTypeZip;
    Button buttonSearch;
    TextView textViewPrompt, staticBird, staticPerson, textViewBird, textViewSighter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextTypeZip = findViewById(R.id.editTextTypeZip);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewPrompt = findViewById(R.id.textViewPrompt);
        textViewBird = findViewById(R.id.textViewBird);
        textViewSighter = findViewById(R.id.textViewSighter);
        staticBird = findViewById(R.id.staticBird);
        staticPerson = findViewById(R.id.staticPerson);

        buttonSearch.setOnClickListener(this);
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
            Intent ReportIntent = new Intent(this, MainActivity.class);
            startActivity(ReportIntent);

        } else if (item.getItemId() == R.id.itemSearch) {
            Toast.makeText(this, "You are already in search page!", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("BirdObs");

        if (view == buttonSearch) {

            int findZip = parseInt(editTextTypeZip.getText().toString());

            myRef.orderByChild("zipcode").equalTo(findZip).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    Bird foundBird = dataSnapshot.getValue(Bird.class);
                    String findBirdName = foundBird.nameofbird;
                    String findSighter = foundBird.nameofperson;

                    textViewBird.setText(findBirdName);
                    textViewSighter.setText(findSighter);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }
}
