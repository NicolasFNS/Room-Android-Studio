package com.example.crudroom.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.crudroom.dao.MedicationDao;
import com.example.crudroom.database.MedicationDatabase;
import com.example.crudroom.entity.Medication;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//faz as execuções

public class MedicationRepository {
    private MedicationDao medicationDao;
    private ExecutorService executor;
    
    public MedicationRepository(Application application) {
        MedicationDatabase database = MedicationDatabase.getInstance(application);
        medicationDao = database.medicationDao();
        executor = Executors.newSingleThreadExecutor();
    }
    
    public void insertMedication(Medication medication) {
        executor.execute(() -> medicationDao.insertMedication(medication));
    }
    
    public void updateMedication(Medication medication) {
        executor.execute(() -> medicationDao.updateMedication(medication));
    }
    
    public void deleteMedication(Medication medication) {
        executor.execute(() -> medicationDao.deleteMedication(medication));
    }
    
    public void deleteAllMedications() {
        executor.execute(() -> medicationDao.deleteAllMedications());
    }
    
    public LiveData<List<Medication>> getAllMedications() {
        return medicationDao.getAllMedications();
    }
    
    public LiveData<List<Medication>> searchMedications(String searchQuery) {
        return medicationDao.searchMedications("%" + searchQuery + "%");
    }
}

