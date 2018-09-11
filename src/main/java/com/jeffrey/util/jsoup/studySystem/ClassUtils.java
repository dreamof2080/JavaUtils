package com.jeffrey.util.jsoup.studySystem;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 获取课时的工具类
 *
 * @author Jeffrey.Liu
 * @date 2018-07-30 9:47
 **/

public class ClassUtils {
    private static final Map<String, String> cookies = new HashMap<>();

    /**
     * 获取cookie
     *
     * @param userNo
     * @param userName
     * @param userEmail
     * @param deptName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> getCookies(String userNo, String userName, String userEmail, String deptName) throws UnsupportedEncodingException {
        if (cookies.get("stuid") == null) {
            String parameters = userNo + "#" + userName + "#" + userEmail + "#" + deptName;
            String url = "http://edu.bestlink.net.cn/OALogin.ashx";
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            List<BasicNameValuePair> paramList = new ArrayList<>();
            paramList.add(new BasicNameValuePair("parameters", new String(Base64.getEncoder().encode(parameters.getBytes()))));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
            httpPost.setEntity(entity);

            try {
                // 执行post请求
                HttpResponse httpResponse = httpClient.execute(httpPost);
                Header[] headers = httpResponse.getHeaders("Set-Cookie");
                for (Header header : headers) {
                    String setCookie = header.getValue();
                    if (setCookie != null && setCookie.indexOf(";") > -1) {
                        String cookie = setCookie.split(";")[0];
                        if (cookie.indexOf("=") > -1) {
                            String[] strings = cookie.split("=");
                            cookies.put(strings[0], strings[1]);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cookies;
    }

    /**
     * 获取olid
     *
     * @param couid
     * @param cookies
     * @return
     * @throws IOException
     */
    public static List<Integer> getAllOlid(int couid, Map<String, String> cookies) throws IOException {
        List<Integer> olids = new ArrayList<>();
        String url = "http://edu.bestlink.net.cn/newoutline.ashx?couid=" + couid;
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
                .cookies(cookies)
                .get();

        Elements linkImgs = document.getElementsByClass("linkImg");
        for (Element linkImg : linkImgs) {
            Elements as = linkImg.getElementsByTag("a");
            if (!as.isEmpty()) {
                Element element = as.get(0);
                String hrefUrl = element.attr("href");
                if (hrefUrl.indexOf("=") > -1) {
                    String[] urlparams = hrefUrl.split("=");
                    if (urlparams.length > 1) {
                        olids.add(Integer.parseInt(urlparams[1]));
                    }
                }
            }
        }
        System.out.println(olids.toString());
        return olids;
    }

    /**
     * 获取totoltime
     *
     * @param olid
     * @param cookies
     * @return
     * @throws IOException
     */
    public static int getTotalTime(int olid, Map<String, String> cookies) throws IOException {
        String url = "http://edu.bestlink.net.cn/newoutline.ashx?olid=" + olid;
        int total = 0;
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
                .cookies(cookies)
                .get();
        Element totalTime = document.getElementById("totalTime");
        if (totalTime != null) {
            String html = totalTime.html();
            if (html != null) {
                total = Integer.parseInt(html);
            }
        }
        return total;
    }


    public static void setBuyed(int couid) throws IOException {
        String stid = cookies.get("edu_Student");
        String url = "http://edu.bestlink.net.cn/ajax/CourseIsBuy.ashx";
        CookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        for (String key : cookies.keySet()) {
            BasicClientCookie basicClientCookie = new BasicClientCookie(key, cookies.get(key));
            basicClientCookie.setPath("/");
            basicClientCookie.setVersion(0);
            cookieStore.addCookie(basicClientCookie);
        }
        //请求参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("couid", couid + ""));
        params.add(new BasicNameValuePair("stid", stid));
        HttpPost httpPost = new HttpPost(url);
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
        httpPost.setEntity(entity);
        httpClient.execute(httpPost);
    }


    /**
     * 插入课程学习记录
     * @param couid
     * @param olid
     * @param totalTime
     * @throws IOException
     */
    public static void setData(int couid, int olid, int totalTime) throws IOException {
        String url = "http://edu.bestlink.net.cn/Ajax/StudentStudy.ashx";
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36")
                .data("couid",couid+"")
                .data("olid",olid+"")
                .data("studyTime",totalTime+"")
                .data("playTime",totalTime*1000+"")
                .data("totalTime",totalTime*1000+"")
                .cookies(cookies)
                .get();
        // 创建cookie store的本地实例
//        CookieStore cookieStore = new BasicCookieStore();
//        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
//
//        for (String key : cookies.keySet()) {
//            BasicClientCookie basicClientCookie = new BasicClientCookie(key, cookies.get(key));
//            basicClientCookie.setPath("/");
//            basicClientCookie.setVersion(0);
//            cookieStore.addCookie(basicClientCookie);
//        }
//
//        //请求参数
//        List<NameValuePair> params = new ArrayList<>();
//        params.add(new BasicNameValuePair("couid", couid + ""));
//        params.add(new BasicNameValuePair("olid", olid + ""));
//        params.add(new BasicNameValuePair("studyTime", totalTime + ""));
//        params.add(new BasicNameValuePair("playTime", totalTime * 1000 + ""));
//        params.add(new BasicNameValuePair("totalTime", totalTime * 1000 + ""));
//        String str = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
//        HttpGet httpGet = new HttpGet(url + "?" + str);
//
//        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//        HttpEntity entity = httpResponse.getEntity();
//        if (entity != null) {
//            InputStream is = entity.getContent();
//            //转换为字节输入流
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));
//            String body = null;
//            while ((body = br.readLine()) != null) {
//                System.out.println(body);
//            }
//        }
    }
}
