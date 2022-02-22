package com.dhivakar.jiosaavnsongdownloader.constants;

public class UrlConstants {


    public static final String ID = "${id}";
    public static final String QUERY = "${query}";
    private final String albumFromId = "https://www.jiosaavn.com/api.php?__call=content.getAlbumDetails&_format=json&cc=in&_marker=0%3F_marker=0&albumid=${id}";
    private final String albumSearchFromString = "https://www.jiosaavn.com/api.php?__call=autocomplete.get&_format=json&_marker=0&cc=in&includeMetaTags=1&query=${query}";
    private final String songFromId = "https://www.jiosaavn.com/api.php?__call=song.getDetails&cc=in&_marker=0%3F_marker%3D0&_format=json&pids=${id}";
    private final String songSearchFromString = "https://www.jiosaavn.com/api.php?p=1&_format=json&_marker=0&api_version=4&ctx=wap6dot0&n=10&__call=search.getResults&q=${query}";
    private final String lyricsFromId = "https://www.jiosaavn.com/api.php?__call=lyrics.getLyrics&ctx=web6dot0&api_version=4&_format=json&_marker=0%3F_marker=0&lyrics_id=${id}";

    public String getAlbumFromID(String id) {
        return albumFromId.replace(ID, id);
    }

    public String getAlbumSearchFromSTRING(String query) {
        return albumSearchFromString.replace(QUERY, String.join("+", query.split(" ")));
    }

    public String getSongFromID(String id) {
        return songFromId.replace(ID, id);
    }

    public String getSongSearchFromSTRING(String query) {
        return songSearchFromString.replace(QUERY, String.join("+", query.split(" ")));
    }

    public String getLyricsFromID(String id) {
        return lyricsFromId.replace(ID, id);
    }
}
