package com.bikeparsing.bikepartsapp.parse.strategy;

import com.bikeparsing.bikepartsapp.entity.Item;
import com.bikeparsing.bikepartsapp.entity.Option;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class BikeComponentsUrlHandler implements UrlHandler {

    private static final String HUB_URL = "https://www.bike-components.de/en/DT-Swiss/350-Disc-6-bolt-Front-Hub-p42225/";
    private static final String TYRE_URL = "https://www.bike-components.de/en/Schwalbe/Nobby-Nic-Evolution-ADDIX-Speedgrip-SnakeSkin-29-Folding-Tyre-p57253/";

    public static void main(String[] args) {
        new BikeComponentsUrlHandler().parsePage(HUB_URL);
    }

    @Override
    public Item parsePage(String url) {
        System.out.println("fetching " + url + "...");

        Document doc = getDocument(url);

        // get item name
        String name = doc
                .select("article#module-product-detail")
                .attr("data-product-name");
        System.out.println(name);
        // get root options element by id
        Element moduleProductDetailOptions
                = doc.select("div#module-product-detail-options").first();
        // get elements with attribute "data-price" from root-options
        Elements options = moduleProductDetailOptions.getElementsByAttribute("data-price");

        List<Option> optionList = new ArrayList<>();

        for (Element option : options) {
            optionList.add(new Option(
                    trimText(option.text()),
                    option.attr("data-price"),
                    option.attr("data-stock")
            ));
        }
        Item item = new Item(name , optionList, LocalDate.now(), url);
        System.out.println(item);
        return null;
    }

    private String trimText(String text) {
        if (text.contains("|")) {
            text = text.split("\\|")[0].trim(); // get first element / first part of option tag text
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
