package com.dhivakar.jiosaavnsongdownloader;

import com.dhivakar.jiosaavnsongdownloader.helper.ApiHelper;
import com.dhivakar.jiosaavnsongdownloader.model.SongModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.tag.id3.ID3v24Tag;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {


        String link = "https://www.jiosaavn.com/song/manasellam-mazhaiye/ChJdeA5JXEQ";


        SongModel model = ApiHelper.getSongModel(link);


        log.debug("Retrieved Meta Data from Api : {}", model);

        Path imagePath = Paths.get(model.defaultImageFilePath());

        try (InputStream in = new URL(model.generateImageUrl()).openStream()) {

            Files.copy(in, imagePath);

        }

        File audioFile = new File(model.defaultSongFilePath());

        FileUtils.copyURLToFile(
                new URL(model.generateMediaUrl()),
                audioFile);




        AudioFile audioMetaFile = AudioFileIO.read(audioFile);

        Tag audioTag = audioMetaFile.getTag();

        audioTag.setField(Artwork.createArtworkFromFile(imagePath.toFile()));
        audioTag.setField(FieldKey.ALBUM, model.getAlbum());
        audioTag.setField(FieldKey.TITLE, model.getSong());
        audioTag.setField(FieldKey.ARTIST, model.getSingers());
        audioTag.setField(FieldKey.LANGUAGE, model.getLanguage());
        audioTag.setField(FieldKey.COMPOSER,model.getMusic());
        audioTag.setField(FieldKey.YEAR, model.getYear());

        AudioFileIO.write(audioMetaFile);

        //ffmpeg -i .mp4 -b:a 320K -vn .mp3

    }


}
