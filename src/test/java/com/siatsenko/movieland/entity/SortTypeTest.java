package com.siatsenko.movieland.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortTypeTest {


    @Test
    public void isValid() {

        assertTrue(SortType.isValid("ASC"));
        assertTrue(SortType.isValid("asc"));
        assertTrue(SortType.isValid("Asc"));
        assertTrue(SortType.isValid("DESC"));
        assertTrue(SortType.isValid("desc"));
        assertTrue(SortType.isValid("Desc"));

        assertFalse(SortType.isValid("ASCc"));
        assertFalse(SortType.isValid("Assi"));

    }
}