package com.example.tattooappointment2024;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MyAppointmentsActivity extends AppCompatActivity {

    private ListView appointmentsListView;
    private ArrayAdapter<String> appointmentAdapter;
    private List<String> appointmentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        appointmentsListView = findViewById(R.id.appointmentsListView);
        appointmentsList = new ArrayList<>();
        appointmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointmentsList);
        appointmentsListView.setAdapter(appointmentAdapter);

        fetchAppointments();
    }

    private void fetchAppointments() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String currentUserName = currentUser.getDisplayName(); // Get the name of the current user
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("appointments")
                    .whereEqualTo("name", currentUserName) // Match appointments where the name field matches the current user's name
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            appointmentsList.clear();
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                                String name = document.getString("name");
                                if (name != null) {
                                    appointmentsList.add(name);
                                }
                            }
                            appointmentAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(MyAppointmentsActivity.this,
                                    "Failed to fetch appointments: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToMainMenu(View view) {
        finish();
    }
}
