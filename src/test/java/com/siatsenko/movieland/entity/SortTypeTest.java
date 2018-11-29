package com.siatsenko.movieland.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortTypeTest {


    @Test
    public void contains() {

        assertTrue(SortType.contains("ASC"));
        assertTrue(SortType.contains("asc"));
        assertTrue(SortType.contains("Asc"));
        assertTrue(SortType.contains("DESC"));
        assertTrue(SortType.contains("desc"));
        assertTrue(SortType.contains("Desc"));

        assertFalse(SortType.contains("ASCc"));
        assertFalse(SortType.contains("Assi"));

    }
}