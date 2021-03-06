package com.thoughtworks.agenciacompromisso.models;

public class Sizes {

    private Double totalBustCircumference;
    private Double totalWaistCircumference;
    private Double totalHipCircumference;
    private Double height;
    private String shirtSize;
    private int pantsSize;
    private int shoeSize;

    public Sizes() {
    }

    public Sizes(Double totalBustCircumference, Double totalWaistCircumference, Double totalHipCircumference, Double height, String shirtSize, int pantsSize, int shoeSize) {
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

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public void setPantsSize(int pantsSize) {
        this.pantsSize = pantsSize;
    }

    public void setShoeSize(int shoeSize) {
        this.shoeSize = shoeSize;
    }
}
