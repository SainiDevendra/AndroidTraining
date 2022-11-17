package com.example.training;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.training.db.database.TaskDatabase;
import com.example.training.db.entity.Task;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText, mDescriptionEditText;
    private TaskDatabase mTaskDatabase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViewListeners();
    }

    private void initializeViewListeners() {

        mTaskDatabase = TaskDatabase.getInstance(this);

        mNameEditText = findViewById(R.id.editTextName);
        mDescriptionEditText = findViewById(R.id.editTextDescription);
        Button submitButton = findViewById(R.id.button);

        submitButton.setOnClickListener(view -> {
            if(validateFields()) {
                String taskName = mNameEditText.getText().toString().trim();
                String taskDescription = mDescriptionEditText.getText().toString().trim();
                Task task = new Task(taskName, taskDescription);
                compositeDisposable.add(mTaskDatabase.taskDao().insert(task)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action() {
                            @Override
                            public void run() throws Throwable {
                                showTaskData();
                            }
                        }));

            }
        });
    }

    private void showTaskData() {
        Intent homeIntent = HomeActivity.getStartIntent(this);
        startActivity(homeIntent);

    }

    private boolean validateFields() {
        if (TextUtils.isEmpty(mNameEditText.getText().toString().trim())) {
            mNameEditText.setError("Please Enter Name");
            return false;
        } else if(TextUtils.isEmpty(mDescriptionEditText.getText().toString().trim())) {
            mDescriptionEditText.setError("Please Enter Description");
            return false;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}