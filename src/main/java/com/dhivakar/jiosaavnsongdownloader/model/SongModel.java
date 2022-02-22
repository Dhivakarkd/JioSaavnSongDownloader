package com.dhivakar.jiosaavnsongdownloader.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongModel {

    private String id;
    private String song;
    private String album;
    private String primary_artists;
    private String singers;
    private String image;
    private String media_preview_url;
    private String release_date;

}
