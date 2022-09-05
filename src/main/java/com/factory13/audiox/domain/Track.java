package com.factory13.audiox.domain;

import lombok.Getter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
public final class Track {

    private final String time;
    private final LocalTime localTime;
    private final String fileName;


    public Track(String time, String fileName) {
        var formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        this.time = sanitizeInput(addMissingHourTime(time), "[^0-9:]", "");
        this.localTime = LocalTime.parse(this.time, formatter);
        this.fileName = sanitizeInput(fileName, "[^a-zA-Z0-9 ]", "").trim() + ".mp3";
    }

    private String sanitizeInput(String input, String regex, String replacement) {
        return input.replaceAll(regex, replacement);
    }

    private String addMissingHourTime(String time) {
        var count = time.chars().filter(ch -> ch == ':').count();

        // Add missing minutes
        if (count == 0) {
            time = "00:" + time;
        }

        // Add missing hours
        if (count == 1) {
            time = "00:" + time;
        }

        // Add padding
        var parts = time.split(":");
        var result = new StringBuilder();
        for (int i = 0; i <=2; i++) {
            result.append(parts[i].chars().count() < 2 ? "0" + parts[i] : parts[i]);

            if(i != 2){
                result.append(":");
            }
        }

        return result.toString();
    }

}
