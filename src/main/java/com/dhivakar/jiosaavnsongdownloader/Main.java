package com.dhivakar.jiosaavnsongdownloader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {


        String link = "https://www.jiosaavn.com/song/arabic-kuthu-from-beast/PhEOejlpQXE";


        String id = extractIdFromLink(link);




        
    }

    private static String extractIdFromLink(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();


        String id = doc.html().split("\"song\":\\{\"type\":\"")[1].split(",\"image\":")[0]
                .split("\"")[8];
        return id;
    }
}
