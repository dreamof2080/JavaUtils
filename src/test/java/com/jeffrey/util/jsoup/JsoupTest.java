package com.jeffrey.util.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

/**
 * @author Jeffrey.Liu
 * @date 2018-07-18 18:03
 **/
public class JsoupTest {
    @Test
    public void test(){
        Document document = new Document("");
        Element element = document.createElement("a");
        element.addClass("test");
        document.appendChild(element);
        System.out.println(element.outerHtml());
        System.out.println(document.outerHtml());
    }
}
