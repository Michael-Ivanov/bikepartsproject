package com.bikeparsing.bikepartsapp.entity;

import com.bikeparsing.bikepartsapp.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemJPATest {

    @Autowired
    private ProductService productService;

    @Test
    public void shouldCreateItemAndOptions() {
        Option option1 = new Option("white", "available");
        Option option2 = new Option("black", "2 to 7 days");
        Option option3 = new Option("purple", "not available");

        List<Option> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);

        Item item = new Item(
                "saddle",
                options,
                LocalDate.now(),
                "30 Euros",
                "www.saddles.com/bestsaddle/3"
        );
        item.setUserId(1);

        productService.save(item);

    }

    @Test
    public void shouldRetrieveOptions() {
        Item item = productService.getById(19);
        System.out.println(item.getOptions());
    }

}