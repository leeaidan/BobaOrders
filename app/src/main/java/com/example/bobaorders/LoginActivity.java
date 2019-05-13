package com.example.bobaorders;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private FloatingActionButton fab;
    private TextView attempts;
    private int countTries = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() !=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if((validate(username.getText().toString(), password.getText().toString()))) {
                    Intent goToRetrieval = new Intent(LoginActivity.this
                            , RetrievalActivity.class);
                    startActivity(goToRetrieval);

                }

            }
        });

        username = (EditText) findViewById(R.id.txt_Username);
        password = (EditText) findViewById(R.id.txt_Password);
        attempts = (TextView) findViewById(R.id.txt_LoginAttempts);

        attempts.setText("No of Attempts Remaining: 5");

    }

    private boolean validate(String userName, String userPassword){
        if ((userName.equals("Admin") )&& (userPassword.equals("999999"))){
            return true;
        } else {
            countTries--;

            attempts.setText("No of Attempts Remaining: " + String.valueOf(countTries));

            if(countTries == 0){
                fab.setEnabled(false);
            }
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
