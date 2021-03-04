package com.bikeparsing.bikepartsapp.parse.strategy;

import com.bikeparsing.bikepartsapp.entity.Item;

public interface UrlHandler {

    Item parsePage(String url);
}
