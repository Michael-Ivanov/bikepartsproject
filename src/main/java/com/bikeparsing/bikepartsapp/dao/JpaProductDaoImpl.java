package com.bikeparsing.bikepartsapp.dao;

import com.bikeparsing.bikepartsapp.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaProductDaoImpl implements ProductDAO {

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public List<Item> getAllByUserId(int id) {
        TypedQuery<Item> query = entityManager.createQuery(
                "from Item where userId=:theUserId",
                Item.class);
        query.setParameter("theUserId", id);
        return query.getResultList();
    }

    @Override
    public Item getById(int id) {
        return null;
    }

    @Override
    public void save(Item item) {
        entityManager.merge(item);
    }

    @Override
    public void deleteById(int id) {

    }

}
