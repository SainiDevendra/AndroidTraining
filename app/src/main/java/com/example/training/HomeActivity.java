package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    public static String USER_FULL_NAME = "userName";
    String mFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        handleIntent();
        initializeViewListeners();
        initializeRecyclerView();
    }

    public static Intent getStartIntent(Context context, String name) {
        Intent loginIntent = new Intent(context, HomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(USER_FULL_NAME, name);
        loginIntent.putExtras(bundle);
        return loginIntent;
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        } else {
            Bundle extras = intent.getExtras();
            mFullName = extras.getString(USER_FULL_NAME);
        }
    }

    private void initializeViewListeners() {
        TextView name = findViewById(R.id.textViewFullName);
        String fullName = "Welcome, " + mFullName;
        name.setText(fullName);
    }

    private void initializeRecyclerView() {

    }
}