package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.entity.Item;

import java.util.List;

public interface ProductService {

    List<Item> getAll();

    Item getById(int id);

    void save(Item item);

    void deleteById(int id);
}
