package com.example.firzasmacbookpro.mygarage.Model;

public class ModelData {
    String id, model, make, category, datemake;

    public ModelData (){}

    public ModelData(String id, String model, String make, String category, String datemake) {
        this.id = id;
        this.model = model;
        this.make = make;
        this.category = category;
        this.datemake = datemake;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDatemake() {
        return datemake;
    }

    public void setDatemake(String datemake) {
        this.datemake = datemake;
    }
}
