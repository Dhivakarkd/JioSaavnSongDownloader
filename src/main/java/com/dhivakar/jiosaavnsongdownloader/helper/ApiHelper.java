package com.dhivakar.jiosaavnsongdownloader.helper;

import com.dhivakar.jiosaavnsongdownloader.constants.UrlConstants;
import com.dhivakar.jiosaavnsongdownloader.model.SongModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ApiHelper {
    public static SongModel getSongModel(String link) throws IOException {

        UrlConstants constants = new UrlConstants();

        String id = UrlHelper.extractIdFromLink(link);


        String requesturl = constants.getSongFromID(id);


        String input = sendGet(requesturl);

        int n = StringUtils.ordinalIndexOf(input, "{", 2);

        String in1 = StringUtils.chop(StringUtils.overlay(input, "", 0, n));


        ObjectMapper objectMapper = new ObjectMapper();
        SongModel model = objectMapper.readValue(in1, SongModel.class);
        return model;
    }

    public static String sendGet(String query) throws IOException {
        // one instance, reuse
        final CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet(query);


        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();

            // return it as a String
            return EntityUtils.toString(entity);


        }

    }
}
