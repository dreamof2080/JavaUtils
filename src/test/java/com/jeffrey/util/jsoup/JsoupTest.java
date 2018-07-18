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
        Document document = Jsoup.parse("");//  Document document = new Document();
        Element element = document.createElement("input");
        element.attr("type","text").attr("name","field1").attr("id","field1");
        element.val("123");
        document.appendChild(element);
        System.out.println(element.outerHtml());

        Element span = document.createElement("span");
        span.appendText("123").attr("id","xxspan");
        System.out.println(span.outerHtml());

        Element img = document.createElement("img");
        img.attr("src","/images/base/checkinput.gif");
        System.out.println(img.outerHtml());
    }
}
