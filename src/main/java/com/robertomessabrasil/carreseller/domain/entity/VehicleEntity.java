package com.robertomessabrasil.carreseller.domain.entity;

public class VehicleEntity {
    private int id;
    private String brand;
    private String model;
    private String version;
    private String year;

    public int getId() {
        return id;
    }

    public VehicleEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public VehicleEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public VehicleEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public VehicleEntity setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getYear() {
        return year;
    }

    public VehicleEntity setYear(String year) {
        this.year = year;
        return this;
    }
}
