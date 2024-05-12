package com.example.tattooappointment2024;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.tattooappointment2024.ProfileActivity;
import com.example.tattooappointment2024.TattooAppointmentActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    // Method to open ProfileActivity
    public void goToProfileActivity(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    // Method to open TattooAppointmentActivity
    public void goToTattooAppointmentActivity(View view) {
        Intent intent = new Intent(this, TattooAppointmentActivity.class);
        startActivity(intent);
    }

    public void goToTattooGalleryActivity(View view) {
        Intent intent = new Intent(this, TattooGalleryActivity.class);
        startActivity(intent);
    }

    public void goToMyAppointments(View view) {
        Intent intent = new Intent(this, MyAppointmentsActivity.class);
        startActivity(intent);
    }



}
