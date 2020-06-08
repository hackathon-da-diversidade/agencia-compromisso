package com.thoughtworks.agenciacompromisso.builders;

import com.thoughtworks.agenciacompromisso.models.Sizes;

public class SizesBuilder {
    private Double totalBustCircumference = 10.0;
    private Double totalWaistCircumference = 10.0;
    private Double totalHipCircumference = 10.0;
    private Double height = 10.0;
    private String shirtSize = "10";
    private int pantsSize = 10;
    private int shoeSize = 10;

    public void setTotalBustCircumference(Double totalBustCircumference) {
        this.totalBustCircumference = totalBustCircumference;
    }

    public void setTotalWaistCircumference(Double totalWaistCircumference) {
        this.totalWaistCircumference = totalWaistCircumference;
    }

    public void setTotalHipCircumference(Double totalHipCircumference) {
        this.totalHipCircumference = totalHipCircumference;
    }

    public void setHeight(Double height) {
        this.height = height;
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

    public Sizes build() {
        return new Sizes(
                totalBustCircumference,
                totalWaistCircumference,
                totalHipCircumference,
                height,
                shirtSize,
                pantsSize,
                shoeSize
        );
    }
}
