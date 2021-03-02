package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.dao.ProductDAO;
import com.bikeparsing.bikepartsapp.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO dao;

    @Autowired
    public ProductServiceImpl(@Qualifier(value = "jpaProductDaoImpl") ProductDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Item> getAll() {
        return dao.getAll();
    }

    @Override
    public List<Item> getAllById(int id) {
        return dao.getAllById(id);
    }

    @Override
    public Item getById(int id) {
        return dao.getById(id);
    }

    @Override
    public void save(Item item) {
        dao.save(item);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
