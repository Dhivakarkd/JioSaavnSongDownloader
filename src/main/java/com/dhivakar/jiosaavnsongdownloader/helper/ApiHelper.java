package com.dhivakar.jiosaavnsongdownloader.helper;

import com.dhivakar.jiosaavnsongdownloader.constants.UrlConstants;
import com.dhivakar.jiosaavnsongdownloader.model.SongModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

@Slf4j
public class ApiHelper {

    public static SongModel getSongModel(String link) throws IOException {

        UrlConstants constants = new UrlConstants();

        String id = UrlHelper.extractIdFromLink(link);

        String requestUrl = constants.getSongFromID(id);

        log.info("Song ID : {}  Fetched From the Link ", id);


        String input = sendGet(requestUrl);

        log.info("Fetching Song Details using the Link -> {}", requestUrl);

        int index = StringUtils.ordinalIndexOf(input, "{", 2);

        String refactoredJsonString = StringUtils.chop(StringUtils.overlay(input, "", 0, index));


        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(refactoredJsonString, SongModel.class);
    }

    private static String sendGet(String query) throws IOException {
        // one instance, reuse
        final CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet(query);


        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            HttpEntity entity = response.getEntity();

            // return it as a String
            return EntityUtils.toString(entity);


        }

    }
}
