package com.bikeparsing.bikepartsapp.parse.strategy;

import com.bikeparsing.bikepartsapp.entity.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BikeComponentsUrlHandler implements UrlHandler {

    public static void main(String[] args) {
        new BikeComponentsUrlHandler().parsePage(
                "https://www.bike-components.de/en/Chris-King/ISO-6-bolt-Disc-Rear-Hub-p63427/");
    }

    @Override
    public Item parsePage(String url) {
        System.out.println("fetching " + url + "...");

        Document doc = getDocument(url);

        // get array of elements with tag "option" containing argument "data-price"
        Elements options = doc.select("option[data-price]");

        String name = doc.select("article").attr("data-product-name");

        List<Item> itemOptions = new ArrayList<>();

        for (Element option : options) {
            System.out.println(name);
            String price = option.attr("data-price");
            String[] modelAvail = option.text().split("\\|");
            System.out.println(Arrays.toString(modelAvail));
            System.out.println(" >> " + price);
//            Item item = new Item(
//                    name,
//                    price,
//
//            )
        }


        return null;
    }

    private Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Cannot retrieve document.");
        }
    }
}
