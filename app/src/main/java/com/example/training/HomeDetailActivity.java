package com.example.training;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;

public class HomeDetailActivity extends AppCompatActivity {
    private static final String COUNTRY_NAME = "countryName";
    public static String USER_FULL_NAME = "userName";
    private String mFullName;
    private String mCountryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);

        handleIntent();
        initializeViewListeners();
    }

    public static Intent getStartIntent(Context context, String countryName, String userName) {
        Intent homeDetailIntent = new Intent(context, HomeDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(COUNTRY_NAME, countryName);
        bundle.putString(USER_FULL_NAME, userName);
        homeDetailIntent.putExtras(bundle);
        return homeDetailIntent;
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        } else {
            Bundle extras = intent.getExtras();
            mCountryName = extras.getString(COUNTRY_NAME);
            mFullName = extras.getString(USER_FULL_NAME);
        }
    }

    private void initializeViewListeners() {
        TextView userName = findViewById(R.id.textViewName);
        String fullName = "Hello, " + mFullName;
        userName.setText(fullName);

        TextView countryName = findViewById(R.id.textViewCountry);
        countryName.setText(mCountryName);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}