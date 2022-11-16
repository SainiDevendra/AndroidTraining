package com.example.training;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.training.utils.AppConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements AppConstants {

    private String mUserName;
    private final List<String> mCountriesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        handleIntent();
        initializeViewListeners();
        addListData();
        initializeRecyclerView();
    }

    public static Intent getStartIntent(Context context, String name) {
        Intent loginIntent = new Intent(context, HomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(AppConstants.USER_NAME, name);
        loginIntent.putExtras(bundle);
        return loginIntent;
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        } else {
            Bundle extras = intent.getExtras();
            mUserName = extras.getString(AppConstants.USER_NAME);
        }
    }

    private void initializeViewListeners() {
        TextView name = findViewById(R.id.textViewFullName);
        String fullName = "Welcome, " + mUserName;
        name.setText(fullName);

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

    private void addListData() {
        String[] arr = {"England", "Pakistan", "India", "New Zealand", "Australia", "South Africa",
                "Sri Lanka", "Netherlands", "Ireland", "Zimbabwe", "Bangladesh", "Afghanistan"};
        mCountriesList.addAll(Arrays.asList(arr));
    }

    private void initializeRecyclerView() {
        RecyclerView countriesRecyclerView = findViewById(R.id.recyclerView);
        CountriesAdapter adapter = new CountriesAdapter(this, mCountriesList, mUserName);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countriesRecyclerView.setAdapter(adapter);
    }
}