package com.thoughtworks.agenciacompromisso.models;

import javax.validation.constraints.NotNull;

public class Sizes {

    @NotNull
    private Double totalBustCircumference;

    @NotNull
    private Double totalWaistCircumference;

    @NotNull
    private Double totalHipCircumference;

    @NotNull
    private Double height;

    @NotNull
    private String shirtSize;

    @NotNull
    private int pantsSize;

    @NotNull
    private int shoeSize;

    public Sizes(@NotNull Double totalBustCircumference, @NotNull Double totalWaistCircumference, @NotNull Double totalHipCircumference, @NotNull Double height, @NotNull String shirtSize, int pantsSize, int shoeSize) {

        this.totalBustCircumference = totalBustCircumference;
        this.totalWaistCircumference = totalWaistCircumference;
        this.totalHipCircumference = totalHipCircumference;
        this.height = height;
        this.shirtSize = shirtSize;
        this.pantsSize = pantsSize;
        this.shoeSize = shoeSize;
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

    public int getShoeSize() {
        return shoeSize;
    }
}
