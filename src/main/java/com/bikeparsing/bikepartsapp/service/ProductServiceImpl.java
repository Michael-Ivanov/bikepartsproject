package com.bikeparsing.bikepartsapp.service;

import com.bikeparsing.bikepartsapp.dao.ProductDAO;
import com.bikeparsing.bikepartsapp.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
    public List<Item> getAllByUserId(int id) {
        return dao.getAllByUserId(id);
    }

    @Override
    public Item getById(int id) {
        return dao.getById(id);
    }

    @Override
    @Transactional
    public void save(Item item) {
        if (item.getDate() == null) {
            item.setDate(LocalDate.now());
        }
        dao.save(item);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dao.deleteById(id);
    }
}
