package com.dhivakar.jiosaavnsongdownloader.model;

import com.dhivakar.jiosaavnsongdownloader.constants.FileConstants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongModel {

    private String id;
    private String song;
    private String album;
    private String year;
    private String music;
    private String primary_artists;
    private String singers;
    private String language;
    private String image;
    private String media_preview_url;
    private String release_date;

    public void setSong(String song) {
        this.song = StringUtils.replace(song, "&quot;", "");
    }

    public String generateImageUrl() {
        return StringUtils.replace(image, "150x150", "500x500");
    }

    public String generateMediaUrl() {
        String temp = StringUtils.replace(media_preview_url, "preview.saavncdn.com", "aac.saavncdn.com");
        return StringUtils.replace(temp, "_96_p", "_320");
    }

    public String defaultImageFilePath() {
        return FileConstants.DEFAULTIMAGEFOLDERNAME.replace("$name$", song);
    }

    public String defaultSongFilePath() {
        String fileName = song.replace(" ", "_");
        return FileConstants.DEFAULTSONGFOLDERNAME.replace("$name$", fileName);
    }
}
