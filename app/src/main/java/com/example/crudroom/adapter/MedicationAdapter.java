package com.example.crudroom.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudroom.R;
import com.example.crudroom.entity.Medication;

import java.util.ArrayList;
import java.util.List;
//responsável pelo recyclerview
public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder> {
    private List<Medication> medications = new ArrayList<>();
    private OnMedicationClickListener listener;
    
    public interface OnMedicationClickListener {
        void onMedicationClick(Medication medication);
        void onMedicationLongClick(Medication medication);
    }
    
    public void setOnMedicationClickListener(OnMedicationClickListener listener) {
        this.listener = listener;
    }
    
    @NonNull
    @Override
    public MedicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.medication_item, parent, false);
        return new MedicationViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NonNull MedicationViewHolder holder, int position) {
        Medication currentMedication = medications.get(position);
        holder.textViewMedicationName.setText(currentMedication.getMedicationName());
        holder.textViewDosage.setText(currentMedication.getDosage());
        holder.textViewNextDoseDate.setText(currentMedication.getNextDoseDate());
    }
    
    @Override
    public int getItemCount() {
        return medications.size();
    }
    
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
        notifyDataSetChanged();
    }
    
    class MedicationViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMedicationName;
        private TextView textViewDosage;
        private TextView textViewNextDoseDate;
        
        public MedicationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMedicationName = itemView.findViewById(R.id.textViewMedicationName);
            textViewDosage = itemView.findViewById(R.id.textViewDosage);
            textViewNextDoseDate = itemView.findViewById(R.id.textViewNextDoseDate);
            
            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onMedicationClick(medications.get(position));
                }
            });
            
            itemView.setOnLongClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onMedicationLongClick(medications.get(position));
                }
                return true;
            });
        }
    }
}

