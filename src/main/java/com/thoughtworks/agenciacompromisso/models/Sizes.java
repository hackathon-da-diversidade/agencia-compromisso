package com.thoughtworks.agenciacompromisso.models;

import javax.validation.constraints.NotNull;

public class Sizes {

    private Double totalBustCircumference;
    private Double totalWaistCircumference;
    private Double totalHipCircumference;
    private Double height;
    private String shirtSize;
    private int pantsSize;

    public Sizes(Double totalBustCircumference, Double totalWaistCircumference, Double totalHipCircumference, Double height, String shirtSize,  int pantsSize) {
        this.totalBustCircumference = totalBustCircumference;
        this.totalWaistCircumference = totalWaistCircumference;
        this.totalHipCircumference = totalHipCircumference;
        this.height = height;
        this.shirtSize = shirtSize;
        this.pantsSize = pantsSize;
    }

    public Double getTotalWaistCircumference() {
        return totalWaistCircumference;
    }

    public void setTotalWaistCircumference(Double totalWaistCircumference) {
        this.totalWaistCircumference = totalWaistCircumference;
    }

    public Double getTotalHipCircumference() {
        return totalHipCircumference;
    }

    public void setTotalHipCircumference(Double totalHipCircumference) {
        this.totalHipCircumference = totalHipCircumference;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getTotalBustCircumference() {
        return totalBustCircumference;
    }

    public void setTotalBustCircumference(Double totalBustCircumference) {
        this.totalBustCircumference = totalBustCircumference;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public int getPantsSize() { return pantsSize; }
}
