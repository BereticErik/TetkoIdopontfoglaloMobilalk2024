package com.example.tattooappointment2024;
import com.google.firebase.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Appointments {
    private String name;
    private Timestamp datum;

    public Appointments() {
    }

    public Appointments(String name, Timestamp datum) {
        this.name = name;
        this.datum = datum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDatum() {
        return datum;
    }

    public void setDatum(Timestamp datum) {
        this.datum = datum;
    }
}