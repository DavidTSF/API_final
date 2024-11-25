package dev.davveg.api_final.models;

public class Pokemon {
    private String name;

    private String height;

    private String weight;

    public Pokemon(String name, String height, String weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }
}
