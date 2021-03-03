package com.bikeparsing.bikepartsapp.dao;

import com.bikeparsing.bikepartsapp.entity.Item;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProductDaoImpl implements ProductDAO {

    private List<Item> items = new ArrayList<>();

    @PostConstruct
    private void populateList() {
        items.add(new Item(1, "tyre", "33", "available"));
        items.add(new Item(2, "stem", "10", "available"));
        items.add(new Item(3, "fork", "300", "5 to 7 days"));
        items.add(new Item(4, "spokes", "20", "available"));
        items.add(new Item(5, "chain", "15", "not available"));
    }

    @Override
    public List<Item> getAll() {
        return items;
    }

    @Override
    public List<Item> getAllByUserId(int id) {
        return null;
    }

    @Override
    public Item getById(int id) {
        return getItemById(id);
    }

    @Override
    public void save(Item item) {
        items.add(item);
    }

    @Override
    public void deleteById(int id) {
        Item item = getItemById(id);
        items.remove(item);
    }

    private Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new RuntimeException("Item id not found: " + id);
    }
}
