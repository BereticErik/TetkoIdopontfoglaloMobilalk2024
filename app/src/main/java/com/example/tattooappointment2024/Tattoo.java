package com.example.tattooappointment2024;

public class Tattoo {
    private int imageResource;
    private String description;

    public Tattoo(int imageResource, String description) {
        this.imageResource = imageResource;
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getDescription() {
        return description;
    }
}
