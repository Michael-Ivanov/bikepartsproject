package com.bikeparsing.bikepartsapp.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_name")
    private String name;

    @OneToMany( fetch = FetchType.EAGER, mappedBy = "item",
            cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Option> options;

    @Column(name = "add_date")
    private LocalDate date;

    @Column(name = "price")
    private String price;

    @Column(name = "item_url")
    private String itemUrl;

    @Column(name = "user_id")
    private int userId;

    public Item() {
    }

    public Item(String name, String price, String availability, String itemUrl) {
        this.name = name;
        this.date = LocalDate.now();
        this.price = price;
        this.itemUrl = itemUrl;
    }

    public Item(String name, List<Option> options, LocalDate date, String price, String itemUrl) {
        this.name = name;
        addOptions(options);
        this.date = date;
        this.price = price;
        this.itemUrl = itemUrl;
    }

    public Item(int id, String name, String price, String availability) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    private void addOptions(List<Option> addOptions) {
        if (options == null) {
            System.out.println("There was no List!!!");
            options = new ArrayList<>();
        }
        for (Option option : addOptions) {
            options.add(option);
            option.setItem(this);
        }
    }

    public void add(Option option) {
        if (options == null) {
            options = new ArrayList<>();
        }
        options.add(option);
        option.setItem(this);
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
                ", options=" + options +
                ", date=" + date +
                ", price='" + price + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", userId=" + userId +
                '}';
    }
}
