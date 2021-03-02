package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.entity.Item;

import java.util.List;

public interface ProductService {

    List<Item> getAll();

    List<Item> getAllById(int id);

    Item getById(int id);

    void save(Item item);

    void deleteById(int id);
}
