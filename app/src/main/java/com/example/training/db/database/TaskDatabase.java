package com.example.training.db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.training.db.dao.TaskDao;
import com.example.training.db.entity.Task;
import com.example.training.utils.AppConstants;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase implements AppConstants {

    private static volatile TaskDatabase INSTANCE;

    public abstract TaskDao taskDao();

    public static TaskDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TaskDatabase.class, AppConstants.TASK_DATABASE).allowMainThreadQueries().build();
        }

        return INSTANCE;
    }


}
