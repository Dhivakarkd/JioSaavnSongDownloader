package com.dhivakar.jiosaavnsongdownloader.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class UrlHelper {

    public static String extractIdFromLink(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();


        return doc.html().split("\"song\":\\{\"type\":\"")[1].split(",\"image\":")[0]
                .split("\"")[8];
    }
}
