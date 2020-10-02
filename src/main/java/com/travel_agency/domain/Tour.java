package com.travel_agency.domain;

public class Tour {
    private Long id;
    private String tourName;
    private double price;

    public Tour() {
    }

    public Tour(String tourName, double price) {
        this.tourName = tourName;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
