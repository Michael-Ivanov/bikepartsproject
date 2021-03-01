package com.bikeparsing.bikepartsapp.dao;

import com.bikeparsing.bikepartsapp.entity.Item;

import java.util.List;

public interface ProductDAO {

    List<Item> getAll();

    Item getById(int id);

    void save(Item item);

    void deleteById(int id);

}
