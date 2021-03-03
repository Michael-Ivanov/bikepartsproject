package com.bikeparsing.bikepartsapp.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "add_date")
    private LocalDate date;

    @Column(name = "price")
    private String price;

    @Column(name = "availability")
    private String availability;

    @Column(name = "item_url")
    private String itemUrl;

    @Column(name = "user_id")
    private int userId;

    public Item() {
    }

    public Item(String name, String price, String itemUrl) {
        this.name = name;
        this.date = LocalDate.now();
        this.price = price;
        this.availability = "def available";
        this.itemUrl = itemUrl;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", price='" + price + '\'' +
                ", availability='" + availability + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", userId=" + userId +
                '}';
    }
}
