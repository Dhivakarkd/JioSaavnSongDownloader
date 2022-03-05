package com.dhivakar.jiosaavnsongdownloader.helper;

import com.dhivakar.jiosaavnsongdownloader.constants.FileConstants;
import com.dhivakar.jiosaavnsongdownloader.model.SongModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class FileHelper {

    public static void downloadFiles(SongModel model, File audioFile) {

        try {
            log.info("Cleaning Files in Directory");
            FileUtils.cleanDirectory(new File(FileConstants.DEFAULTFOLDERPATH));
        } catch (IOException e) {
            log.error("Error Occurred While Cleaning Directory due to ", e);
        }
        try {
            downloadImage(model);
        } catch (IOException e) {
            log.error("Error Occurred While Downloading Image due to ", e);
        }

        try {
            downloadSong(model, audioFile);
        } catch (IOException e) {
            log.error("Error Occurred While Downloading Song due to ", e);
        }
    }

    private static void downloadImage(SongModel model) throws IOException {
        try (InputStream in = new URL(model.generateImageUrl()).openStream()) {

            Files.copy(in, Paths.get(model.defaultImageFilePath()));

        }
        log.info("Image for song {} downloaded Successfully", model.getSong());

    }

    private static void downloadSong(SongModel model, File audioFile) throws IOException {
        FileUtils.copyURLToFile(
                new URL(model.generateMediaUrl()),
                audioFile);

        log.info("Song : {} downloaded Successfully", model.getSong());
    }
}
