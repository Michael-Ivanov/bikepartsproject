package com.bikeparsing.bikepartsapp.dao;

import com.bikeparsing.bikepartsapp.entity.Item;

import java.util.List;

public interface ProductDAO {

    List<Item> getAll();

    List<Item> getAllById(int id);

    Item getById(int id);

    void save(Item item);

    void deleteById(int id);

}
