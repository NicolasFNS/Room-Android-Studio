package com.example.crudroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.crudroom.dao.MedicationDao;
import com.example.crudroom.entity.Medication;

//constrói o database

@Database(entities = {Medication.class}, version = 1, exportSchema = false)
public abstract class MedicationDatabase extends RoomDatabase {
    
    private static MedicationDatabase instance;
    
    public abstract MedicationDao medicationDao();
    
    public static synchronized MedicationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    MedicationDatabase.class,
                    "medication_database"
            )
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}

