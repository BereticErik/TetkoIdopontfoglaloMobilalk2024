package com.example.tattooappointment2024;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import com.google.firebase.Timestamp;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TattooAppointmentActivity extends AppCompatActivity {
    private Button confirmButton;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tattoo_appointment);

        // Initialize your views
        confirmButton = findViewById(R.id.confirmButton);

        // Set click listener for confirm button
        confirmButton.setOnClickListener(v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Handle date selection here
                    // You can update UI or perform any other action
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDay);
                    saveAppointment(selectedDate.getTimeInMillis());
                },
                year, month, day);

        datePickerDialog.show();
    }
    private void saveAppointment(long appointmentDate) {
        // Convert milliseconds to seconds
        long seconds = appointmentDate / 1000;

        // Get current user
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String currentUserId = currentUser.getUid();
            String currentUserName = currentUser.getDisplayName();

            // If display name is not available locally, fetch from Firebase Authentication
            if (currentUserName == null) {
                currentUserName = currentUser.getEmail(); // Use email if display name is not set
            }

            // Create a new appointment object
            Map<String, Object> appointment = new HashMap<>();
            appointment.put("name", currentUserName);
            appointment.put("timestamp", new Timestamp(seconds, 0)); // Use Timestamp constructor

            // Save the appointment to Firestore
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("appointments")
                    .add(appointment)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Appointment scheduled successfully", Toast.LENGTH_SHORT).show();
                        // Navigate back to main menu
                        backToMainMenu(null);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed to schedule appointment", Toast.LENGTH_SHORT).show();
                        Log.e("Firestore", "Error saving appointment: " + e.getMessage(), e);
                    });

        } else {
            // User not authenticated
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle menu item clicks
        if (id == R.id.action_profile) {
            // Open profile activity
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        } else if (id == R.id.action_appointments) {
            startActivity(new Intent(this, TattooAppointmentActivity.class));
            return true;
        } else if (id == R.id.action_settings) {
            // Open settings activity or perform desired action
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void backToMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
        finish(); // Finish this activity to prevent going back to it with back button
    }
}
