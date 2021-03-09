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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "selected_option")
    private Option selectedOption;

    @Column(name = "add_date")
    private LocalDate date;

    @Column(name = "item_url")
    private String itemUrl;

    @Column(name = "user_id")
    private int userId;

    public Item() {
    }

    public Item(String name, String itemUrl) {
        this.name = name;
        this.date = LocalDate.now();
        this.itemUrl = itemUrl;
    }

    public Item(String name, List<Option> options, LocalDate date, String itemUrl) {
        this.name = name;
        addOptions(options);
        this.date = date;
        this.itemUrl = itemUrl;
    }

    public Item(int id, String name) {
        this.id = id;
        this.name = name;
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

    public Option getOptionById(int id) { // todo: clear
        for (Option option : options) {
            System.out.println("check option id: >>>" + option.getId());
            if (option.getId() == id) {
                return option;
            }
        }
        throw new RuntimeException("No option found. Id: " + id);
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

    public Option getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(Option selectedOption) {
        this.selectedOption = selectedOption;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                ", itemUrl='" + itemUrl + '\'' +
                ", userId=" + userId +
                '}';
    }
}
