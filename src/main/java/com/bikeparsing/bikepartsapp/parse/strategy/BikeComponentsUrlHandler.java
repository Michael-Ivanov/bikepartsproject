package com.bikeparsing.bikepartsapp.parse.strategy;

import com.bikeparsing.bikepartsapp.entity.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BikeComponentsUrlHandler implements UrlHandler {

    private static final String HUB_URL = "https://www.bike-components.de/en/DT-Swiss/350-Disc-6-bolt-Front-Hub-p42225/";
    private static final String TYRE_URL = "https://www.bike-components.de/en/Schwalbe/Nobby-Nic-Evolution-ADDIX-Speedgrip-SnakeSkin-29-Folding-Tyre-p57253/";

    public static void main(String[] args) {
        new BikeComponentsUrlHandler().parsePage(TYRE_URL);
    }

    @Override
    public Item parsePage(String url) {
        System.out.println("fetching " + url + "...");

        Document doc = getDocument(url);

        // get root options element by id
        Element moduleProductDetailOptions
                = doc.select("div#module-product-detail-options").first();

        // get elements with attribute "data-price" from root-options
        Elements options = moduleProductDetailOptions.getElementsByAttribute("data-price");

        List<Item> itemOptions = new ArrayList<>();

        for (Element option : options) {
            System.out.println(option);
            System.out.println("Price: " + option.attr("data-price"));
            System.out.println("Availability: " + option.attr("data-stock"));
            System.out.println("Option: " + trimText(option.text()));

            // todo: add String option field to Item class. Get this option from element.

        }

        return null;
    }

    private String trimText(String text) {
        if (text.contains("|")) {
            System.out.println("test:>> " + text);
            text = text.split("\\|")[0].trim();
        }
        return text;
    }

    private Document getDocument(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Cannot retrieve document.");
        }
    }
}
