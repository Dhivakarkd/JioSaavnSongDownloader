package com.dhivakar.jiosaavnsongdownloader.helper;

import com.dhivakar.jiosaavnsongdownloader.model.SongModel;
import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;

import java.io.File;
import java.io.IOException;

@Slf4j
public class AudioHelper {

    public static void writeMetaDataToAudioFile(SongModel model, File audioFile) throws CannotReadException, IOException, TagException, ReadOnlyFileException, InvalidAudioFrameException, CannotWriteException {
        AudioFile audioMetaFile = AudioFileIO.read(audioFile);

        Tag audioTag = audioMetaFile.getTag();

        audioTag.setField(Artwork.createArtworkFromFile(new File(model.defaultImageFilePath())));
        audioTag.setField(FieldKey.ALBUM, model.getAlbum());
        audioTag.setField(FieldKey.TITLE, model.getSong());
        audioTag.setField(FieldKey.ARTIST, model.getSingers());
        audioTag.setField(FieldKey.LANGUAGE, model.getLanguage());
        audioTag.setField(FieldKey.COMPOSER, model.getMusic());
        audioTag.setField(FieldKey.YEAR, model.getYear());

        AudioFileIO.write(audioMetaFile);

        log.info("Audio MetaData Written to file Successfully");
    }
}
