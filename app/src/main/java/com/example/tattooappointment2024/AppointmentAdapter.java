package com.example.tattooappointment2024;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppointmentAdapter extends ArrayAdapter<Appointments> {

    private List<Appointments> appointmentsList;
    private Context context;

    public AppointmentAdapter(@NonNull Context context, int resource, @NonNull List<Appointments> objects) {
        super(context, resource, objects);
        this.appointmentsList = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.appointment_item, parent, false);
        }

        TextView appointmentTextView = convertView.findViewById(R.id.appointmentItemTextView);
        Appointments appointment = appointmentsList.get(position);

        String name = appointment.getName() != null ? appointment.getName() : "N/A";
        String dateStr = appointment.getDatum() != null ? formatTimestamp(appointment.getDatum()) : "N/A";

        appointmentTextView.setText(name + " - " + dateStr);

        return convertView;
    }

    private String formatTimestamp(Timestamp timestamp) {
        Date date = timestamp.toDate(); // Convert Timestamp to Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault());
        return dateFormat.format(date);
    }
}
