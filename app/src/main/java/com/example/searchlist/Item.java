package com.example.searchlist;

public class Item {

    private int imageSrc;
    private String name;
    private String description;

    public Item(int imageSrc, String name, String description) {
        this.imageSrc = imageSrc;
        this.name = name;
        this.description = description;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
