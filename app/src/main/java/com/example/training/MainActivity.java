package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewListeners();
    }

    private void initializeViewListeners() {
        mNameEditText = findViewById(R.id.editTextName);
        Button loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener(view -> {
            if(validateFields()) {
                String fullName = mNameEditText.getText().toString().trim();
                doLogin(fullName);
            }
        });
    }

    private void doLogin(String name) {
        Intent loginIntent = HomeActivity.getStartIntent(this, name);
        startActivity(loginIntent);

    }

    private boolean validateFields() {
        if (TextUtils.isEmpty(mNameEditText.getText().toString().trim())) {
            mNameEditText.setError("Please Enter Name");
            return false;
        }

        return true;
    }
}