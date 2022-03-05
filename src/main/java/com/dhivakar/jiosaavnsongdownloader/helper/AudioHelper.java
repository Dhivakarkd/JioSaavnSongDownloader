package com.dhivakar.jiosaavnsongdownloader.helper;

import com.dhivakar.jiosaavnsongdownloader.model.SongModel;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

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


    public static void convertFileToMp3(String fileName) {

        ProcessBuilder processBuilder = new ProcessBuilder();

        String convertionCommandTemplate = "/usr/bin/ffmpeg -i $fileName.mp4 -b:a 320K -vn $fileName.mp3";

        String convertionCommand = convertionCommandTemplate.replace("$fileName",fileName);


        // Run a shell command
        processBuilder.command("bash", "-c", convertionCommand);
        processBuilder.directory(new File("TempDir"));


        try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);

            } else {
                //abnormal...
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
