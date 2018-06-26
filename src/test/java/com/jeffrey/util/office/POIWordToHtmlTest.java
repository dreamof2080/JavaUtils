package com.jeffrey.util.office;

import org.junit.Test;

import static org.junit.Assert.*;

public class POIWordToHtmlTest {
    @Test
    public void word2Html(){
        POIWordToHtml.wordToHtml("D:\\123.doc", "D:\\images", "D:\\imagesTest.html");
    }
}