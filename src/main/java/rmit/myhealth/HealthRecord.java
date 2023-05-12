package rmit.myhealth;

import java.time.LocalDateTime;

public class HealthRecord {
    private double weight;
    private double temperature;
    private String bloodPressure;
    private String note;
    private LocalDateTime dateTime;

    public HealthRecord(double weight, double temperature, String bloodPressure, String note, LocalDateTime dateTime) {
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.note = note;
        this.dateTime = dateTime;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

