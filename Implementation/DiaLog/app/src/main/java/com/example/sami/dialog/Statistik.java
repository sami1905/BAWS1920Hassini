package com.example.sami.dialog;

public class Statistik {

    private int countBZ;
    private int countLow;
    private int countHigh;
    private int countInRange;
    private float averageSugar;
    private float hba1c;
    private float bePerMeal;
    private float iesPerDay;

    public Statistik(int countBZ, int countLow, int countHigh, int countInRange, float averageSugar, float hba1c, float bePerMeal, float iesPerDay) {
        this.countBZ = countBZ;
        this.countLow = countLow;
        this.countHigh = countHigh;
        this.countInRange = countInRange;
        this.averageSugar = averageSugar;
        this.hba1c = hba1c;
        this.bePerMeal = bePerMeal;
        this.iesPerDay = iesPerDay;
    }

    public int getCountBZ() {
        return countBZ;
    }

    public void setCountBZ(int countBZ) {
        this.countBZ = countBZ;
    }

    public int getCountLow() {
        return countLow;
    }

    public void setCountLow(int countLow) {
        this.countLow = countLow;
    }

    public int getCountHigh() {
        return countHigh;
    }

    public void setCountHigh(int countHigh) {
        this.countHigh = countHigh;
    }

    public int getCountInRange() {
        return countInRange;
    }

    public void setCountInRange(int countInRange) {
        this.countInRange = countInRange;
    }

    public float getAverageSugar() {
        return averageSugar;
    }

    public void setAverageSugar(float averageSugar) {
        this.averageSugar = averageSugar;
    }

    public float getHba1c() {
        return hba1c;
    }

    public void setHba1c(float hba1c) {
        this.hba1c = hba1c;
    }

    public float getBePerMeal() {
        return bePerMeal;
    }

    public void setBePerMeal(float bePerMeal) {
        this.bePerMeal = bePerMeal;
    }

    public float getIesPerDay() {
        return iesPerDay;
    }

    public void setIesPerDay(float iesPerDay) {
        this.iesPerDay = iesPerDay;
    }
}
