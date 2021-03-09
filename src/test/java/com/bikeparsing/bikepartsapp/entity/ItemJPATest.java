package com.bikeparsing.bikepartsapp.entity;

import com.bikeparsing.bikepartsapp.service.ProductService;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
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
    @Disabled
    public void shouldCreateItemAndOptions() {
        Option option1 = new Option("size s", "250 eur", "available");
        Option option2 = new Option("size m", "270 eur","13 to 50 days");
        Option option3 = new Option("size l", "300 eur","unavailable");

        List<Option> options = new ArrayList<>();
        options.add(option1);
        options.add(option2);
        options.add(option3);

        Item item = new Item(
                "frame",
                options,
                LocalDate.now(),
                "www.frames.com/frame-alu/3"
        );
        item.setUserId(1);
        item.setSelectedOption(options.get(0));

        productService.save(item);

    }

    @Test
    @Disabled
    public void shouldRetrieveOptions() {
        Item item = productService.getById(19);
        System.out.println(item.getOptions());
    }

}