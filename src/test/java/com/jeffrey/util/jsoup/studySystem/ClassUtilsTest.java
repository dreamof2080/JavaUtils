package com.jeffrey.util.jsoup.studySystem;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ClassUtilsTest {

    @Test
    public void getAllOlid() throws IOException {
        int couid = 598;
        Map<String,String> cookies = ClassUtils.getCookies("JH018782","刘春生","chunsheng.liu@bestlink.com.cn","软件开发部");
        List<Integer> allOlid = ClassUtils.getAllOlid(couid, cookies);
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer olid : allOlid) {
            int totalTime = ClassUtils.getTotalTime(olid, cookies);
            stringBuilder.append("$.get(\"/Ajax/StudentStudy.ashx\", {")
                    .append("\n")
                    .append("couid:").append("'").append(couid).append("',\n")
                    .append("olid:").append("'").append(olid).append("',\n")
                    .append("studyTime:").append(totalTime).append(",\n")
                    .append("playTime:").append(totalTime*1000).append(",\n")
                    .append("totalTime:").append(totalTime*1000).append("\n")
                    .append("});")
                    .append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void getCookieTest() throws UnsupportedEncodingException {
        ClassUtils.getCookies("JH018782","刘春生","chunsheng.liu@bestlink.com.cn","软件开发部");
    }

    @Test
    public void setData() throws IOException {
        int couid = 830;
        Map<String,String> cookies = ClassUtils.getCookies("JH008818","谢丽君","lijun.xie@bestlink.com.cn","软件开发部");
        List<Integer> allOlid = ClassUtils.getAllOlid(couid, cookies);
        if (!allOlid.isEmpty()){
            ClassUtils.setBuyed(couid);
            for (Integer olid : allOlid) {
                int totalTime = ClassUtils.getTotalTime(olid, cookies);
                ClassUtils.setData(couid,olid,totalTime);
            }
        }
    }
}