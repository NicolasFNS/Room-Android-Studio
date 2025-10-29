package com.example.crudroom.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crudroom.entity.Medication;

import java.util.List;

@Dao
public interface MedicationDao {
    
    //CREATE
    @Insert
    void insertMedication(Medication medication);
    
    //READ - todas
    @Query("SELECT * FROM medications ORDER BY nextDoseDate ASC")
    LiveData<List<Medication>> getAllMedications();
    
    //READ
    @Query("SELECT * FROM medications WHERE medicationName LIKE :searchQuery OR dosage LIKE :searchQuery ORDER BY nextDoseDate ASC")
    LiveData<List<Medication>> searchMedications(String searchQuery);
    
    //UPDATE
    @Update
    void updateMedication(Medication medication);
    
    //DELETE
    @Delete
    void deleteMedication(Medication medication);
    
    //DELETE - todas
    @Query("DELETE FROM medications")
    void deleteAllMedications();
}

