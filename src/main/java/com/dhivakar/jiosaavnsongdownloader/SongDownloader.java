package com.dhivakar.jiosaavnsongdownloader;

import com.dhivakar.jiosaavnsongdownloader.exception.NoArgumentException;
import com.dhivakar.jiosaavnsongdownloader.helper.ApiHelper;
import com.dhivakar.jiosaavnsongdownloader.helper.AudioHelper;
import com.dhivakar.jiosaavnsongdownloader.helper.FileHelper;
import com.dhivakar.jiosaavnsongdownloader.model.SongModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.util.logging.LogManager;

@Slf4j
public class SongDownloader {

    public static void main(String[] args) throws NoArgumentException {


        // To disable unnecessary logging of JAudioTagger Library
        LogManager.getLogManager().reset();



        if (args.length == 1) {

            log.info("Downloading Song for the Link : {}", args[0]);
            initializeDownload(args[0]);

        } else {

            throw new NoArgumentException("No Arguments were Provided for Reading the File");
        }

        //ffmpeg -i .mp4 -b:a 320K -vn .mp3

    }

    private static void initializeDownload(String link) {

        SongModel model = null;
        try {

            model = ApiHelper.getSongModel(link);

            log.info("Retrieved Meta Data from Api : {}", model);


            File audioFile = new File(model.defaultDownloadFilePath());


            FileHelper.downloadFiles(model, audioFile);

            AudioHelper.convertFileToMp3(model.getSongName());

            File downloadedFile = new File(model.defaultSongFilePath());

            AudioHelper.writeMetaDataToAudioFile(model, downloadedFile);

        } catch (IOException | CannotWriteException | TagException | ReadOnlyFileException | InvalidAudioFrameException | CannotReadException e) {
            log.error("Exception Occurred during Song download due to : ", e);
        }


    }


}
