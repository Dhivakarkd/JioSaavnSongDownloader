package com.dhivakar.jiosaavnsongdownloader.constants;

public class UrlConstants {

    private static final String ALBUMFROMID = "https://www.jiosaavn.com/api.php?__call=content.getAlbumDetails&_format=json&cc=in&_marker=0%3F_marker=0&albumid=${id}";
    private static final String ALBUMSEARCHFROMSTRING = "https://www.jiosaavn.com/api.php?__call=autocomplete.get&_format=json&_marker=0&cc=in&includeMetaTags=1&query=${query}";
    private static final String SONGFROMID = "https://www.jiosaavn.com/api.php?__call=autocomplete.get&_format=json&_marker=0&cc=in&includeMetaTags=1&query=${query}";
    private static final String SONGSEARCHFROMSTRING = "https://www.jiosaavn.com/api.php?p=1&_format=json&_marker=0&api_version=4&ctx=wap6dot0&n=10&__call=search.getResults&q=${query}";
    private static final String LYRICSFROMID = "https://www.jiosaavn.com/api.php?__call=lyrics.getLyrics&ctx=web6dot0&api_version=4&_format=json&_marker=0%3F_marker=0&lyrics_id=${id}";

}
