package com.example.training;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.training.db.dao.TaskDao;
import com.example.training.db.database.TaskDatabase;
import com.example.training.db.entity.Task;
import com.example.training.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity implements AppConstants {

    private List<Task> mTaskList = new ArrayList<>();
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        handleIntent();
        initializeViewListeners();
    }

    public static Intent getStartIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    private void handleIntent() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
    }

    private void initializeViewListeners() {
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        TaskDatabase taskDatabase = TaskDatabase.getInstance(this);
        TaskDao taskDao = taskDatabase.taskDao();
        compositeDisposable.add(taskDao.getAllTask().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws Throwable {
                        mTaskList = tasks;
                        if(mTaskList != null && !mTaskList.isEmpty()) {
                            initializeRecyclerView();
                        } else {
                            Toast.makeText(HomeActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        Toast.makeText(HomeActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                    }
                })
        );
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
    }

    private void initializeRecyclerView() {
        RecyclerView countriesRecyclerView = findViewById(R.id.recyclerView);
        CountriesAdapter adapter = new CountriesAdapter(this, mTaskList);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        countriesRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}