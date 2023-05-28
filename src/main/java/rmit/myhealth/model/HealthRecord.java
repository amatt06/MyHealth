package rmit.myhealth.model;

import java.time.LocalDateTime;

public class HealthRecord {
    private double weight;
    private double temperature;
    private double bloodPressureUpper;

    private double bloodPressureLower;
    private String note;
    private LocalDateTime dateTime;

    public HealthRecord(double weight, double temperature, double bloodPressureUpper, double bloodPressureLower, String note, LocalDateTime dateTime) {
        this.weight = weight;
        this.temperature = temperature;
        this.bloodPressureUpper = bloodPressureUpper;
        this.bloodPressureLower = bloodPressureLower;
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

    public double getBloodPressureUpper() {
        return bloodPressureUpper;
    }

    public double getBloodPressureLower() {
        return bloodPressureLower;
    }

    public void setBloodPressureUpper(double bloodPressureUpper) {
        this.bloodPressureUpper = bloodPressureUpper;
    }

    public void setBloodPressureLower(double bloodPressureLower) {
        this.bloodPressureLower = bloodPressureLower;
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

