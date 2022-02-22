package com.dhivakar.jiosaavnsongdownloader;

import com.dhivakar.jiosaavnsongdownloader.constants.UrlConstants;
import com.dhivakar.jiosaavnsongdownloader.helper.UrlHelper;
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

public class Main {

    public static void main(String[] args) throws Exception {


        UrlConstants constants = new UrlConstants();


        String link = "https://www.jiosaavn.com/song/manasellam-mazhaiye/ChJdeA5JXEQ";


        String id = UrlHelper.extractIdFromLink(link);


        System.out.println(id);

        String requesturl = constants.getSongFromID(id);

        System.out.println(requesturl);

        String input = sendGet(requesturl);

        int n= StringUtils.ordinalIndexOf(input,"{",2);

        String in1 = StringUtils.chop(StringUtils.overlay(input,"",0,n));


        System.out.println(in1);

        ObjectMapper objectMapper = new ObjectMapper();
        SongModel model = objectMapper.readValue(in1,SongModel.class);


        System.out.println(model);



    }

    private static String sendGet(String query) throws IOException {
        // one instance, reuse
        final CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet request = new HttpGet(query);


        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);


            // return it as a String
            return EntityUtils.toString(entity);


        }

    }


}
