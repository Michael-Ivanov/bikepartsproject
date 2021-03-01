package com.bikeparsing.bikepartsapp.entity;

public class Item {

    private int id;

    private String name;

    private String price;

    private String availability;

    public Item() {
    }

    public Item(String name, String price, String availability) {
        this.name = name;
        this.price = price;
        this.availability = availability;
    }

    public Item(int id, String name, String price, String availability) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}
