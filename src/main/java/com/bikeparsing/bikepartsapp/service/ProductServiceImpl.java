package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.dao.ProductDAO;
import com.bikeparsing.bikepartsapp.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(@Qualifier(value = "jdbcDaoImpl") ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Item> getAll() {
        return productDAO.getAll();
    }

    @Override
    public List<Item> getAllById(int id) {
        return productDAO.getAllById(id);
    }

    @Override
    public Item getById(int id) {
        return productDAO.getById(id);
    }

    @Override
    public void save(Item item) {
        productDAO.save(item);
    }

    @Override
    public void deleteById(int id) {
        productDAO.deleteById(id);
    }
}
