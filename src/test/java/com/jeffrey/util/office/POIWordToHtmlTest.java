package com.jeffrey.util.office;

import org.junit.Test;

import static org.junit.Assert.*;

public class POIWordToHtmlTest {
    @Test
    public void word2Html(){
        POIWordToHtml.wordToHtml("D:\\e-weaver5.0.12653.docx", "D:\\images", "D:\\imagesTest.html");

        POIWordToHtml.wordToHtml("D:\\123.docx", "D:\\images", "D:\\imagesTest.html");
    }
}