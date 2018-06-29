package com.jeffrey.util.office;

import org.junit.Test;

import static org.junit.Assert.*;

public class POIWordToHtmlTest {
    @Test
    public void word2Html(){
        //07版本
        POIWordToHtml.wordToHtml("D:\\test.docx", "D:\\tmp", "D:\\tmp\\Test1.html");
        //03版本
        POIWordToHtml.wordToHtml("D:\\123.doc", "D:\\images", "D:\\iTest2.html");


    }

    @Test
    public void excel2Html(){
        POIExcelToHtml.excelToHtml("D:\\test2.xlsx", "D:\\222.html");
    }
}