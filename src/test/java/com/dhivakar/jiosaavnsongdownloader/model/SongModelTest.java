package com.dhivakar.jiosaavnsongdownloader.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongModelTest {


    @Test
    void TestGetSong() {
        SongModel model = new SongModel();

        model.setSong("Naan Pizhai (From Kaathuvaakula Rendu Kaadhal)");

        assertEquals("Naan_Pizhai",model.getSongName());

    }
}