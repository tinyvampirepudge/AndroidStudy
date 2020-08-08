package com.tinytongtong.androidstudy.java.csdn;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * https://blog.csdn.net/sinat_42483341/article/details/96134556
 * --------------------自动刷CSDN博客访问量程序--------------------
 *
 * 将要刷访问量的博客id填写入24行的变量userId中，点击运行
 * 本程序访问该博主【用户ID】名下所有博客链接
 *
 * 仅供学习测试使用，不要真的用于刷访问量~
 */
public class UrlCrawBoke {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (String item : args) {
            System.out.println(item);
        }

        if (args.length > 0) {
            if ("1".equals(args[0])) {
                // 循环刷某个用户全部的blog
                System.out.println("循环刷某个用户全部的blog");
                if (args.length > 2) {
                    if (args[1] != null && args[1].length() > 0) {
                        int count = 1;
                        try {
                            count = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("请输入正确的刷新次数");
                        }
                        System.out.println("userId:" + args[1]);
                        System.out.println("刷新次数:" + count);
                        refreshAllBlogs(args[1], count);
                    } else {
                        System.out.println("请输入userId");
                    }
                } else {
                    System.out.println("请输入userId和刷新次数");
                }
            } else if ("2".equals(args[0])) {
                // 随机刷某个用户的blog
                System.out.println("随机刷某个用户的blog");
                if (args.length > 2) {
                    if (args[1] != null && args[1].length() > 0) {
                        int count = 1;
                        try {
                            count = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("请输入正确的刷新次数");
                        }
                        System.out.println("userId:" + args[1]);
                        System.out.println("刷新次数:" + count);
                        randomRefreshAllBlogs(args[1], count);
                    } else {
                        System.out.println("请输入userId");
                    }
                } else {
                    System.out.println("请输入userId和刷新次数");
                }
            } else if ("3".equals(args[0])) {
                // 循环刷对应的链接
                System.out.println("循环刷对应的链接");
                if (args.length > 2) {
                    // 获取blog链接
                    if (args[1] != null && args[1].length() > 0) {
                        // 获取刷新次数
                        int count = 1;
                        try {
                            count = Integer.parseInt(args[2]);
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                            System.out.println("请输入正确的刷新次数");
                        }
                        System.out.println("刷新链接:" + args[1]);
                        System.out.println("刷新次数:" + count);
                        refreshSpecialBlog(args[1], count);
                    } else {
                        System.out.println("请输入对应的链接");
                    }
                } else {
                    System.out.println("请输入对应链接和刷新次数");
                }
            } else {
                System.out.println("暂不支持该类型");
            }
        } else {
            System.out.println("请输入相应的参数");
        }
    }

    public static void refreshSpecialBlog(String url, int count) throws IOException, InterruptedException {

        // 循环次数
        int cycleCount = 0;
        while (cycleCount < count) {
            doGet(url);
            System.out.println("成功访问链接:" + url);
            cycleCount++;
            int sleepSeconds = 5 + new Random().nextInt(100);
            System.out.println("sleeping " + sleepSeconds + "seconds");
            TimeUnit.SECONDS.sleep(sleepSeconds);
        }
    }

    public static void randomRefreshAllBlogs(String userId, int count) throws IOException, InterruptedException {
        HashSet<String> urls = new HashSet<>();

        // ----------------------------------------------遍历每一页 获取文章链接----------------------------------------------
        final String homeUrl = "https://blog.csdn.net/" + userId + "/article/list/";// 后面加pageNum即可
        int totalPage = 0;
        InputStream is;
        String pageStr;
        StringBuilder curUrl = null;
        for (int i = 1; i < 100; i++) {
            Thread.sleep(1000);
            System.out.println("finding page " + i);
            curUrl = new StringBuilder(homeUrl);
            curUrl.append(i);
            System.out.println(curUrl);
            is = doGet(curUrl.toString());
            pageStr = inputStreamToString(is, "UTF-8");// 一整页的html源码

            List<String> list = getMatherSubstrs(pageStr, "(?<=href=\")https://blog.csdn.net/" + userId + "/article/details/[0-9]{8,9}(?=\")");
            urls.addAll(list);

            if (pageStr.lastIndexOf("空空如也") != -1) {
                System.out.println("No This Page!");
                break;
            } else {
                System.out.println("Success~");
            }
            totalPage = i;
        }
        System.out.println("总页数为: " + totalPage);

        // ---------------------------------------------------打印每个链接---------------------------------------------------
        ArrayList<String> urlsArrays = new ArrayList<>();
        System.out.println("打印每个链接");
        for (String s : urls) {
            System.out.println(s);
            urlsArrays.add(s);
        }
        System.out.println("打印每个链接完毕");

        // ---------------------------------------------------访问每个链接---------------------------------------------------

        // 循环次数
        int cycleCount = 0;
        while (cycleCount < count) {
            int randomIndex = new Random().nextInt(urlsArrays.size());
            String targetUrl = urlsArrays.get(randomIndex);
            doGet(targetUrl);
            System.out.println("成功访问第" + (randomIndex) + "个链接,共" + urlsArrays.size() + "个:" + targetUrl);
            // ---------------------------------------------------程序结束---------------------------------------------------
            cycleCount++;
            int sleepMinutes = new Random().nextInt(10);
            System.out.println(String.format("共%d次，第%d次", count, cycleCount));
            System.out.println("sleeping " + sleepMinutes + "seconds");
            TimeUnit.SECONDS.sleep(sleepMinutes);
        }

    }

    public static void refreshAllBlogs(String userId, int count) throws IOException, InterruptedException {
        Set<String> urls = new HashSet<String>();

        // ----------------------------------------------遍历每一页 获取文章链接----------------------------------------------
        final String homeUrl = "https://blog.csdn.net/" + userId + "/article/list/";// 后面加pageNum即可
        int totalPage = 0;
        InputStream is;
        String pageStr;
        StringBuilder curUrl = null;
        for (int i = 1; i < 100; i++) {
            Thread.sleep(1000);
            System.out.println("finding page " + i);
            curUrl = new StringBuilder(homeUrl);
            curUrl.append(i);
            System.out.println(curUrl);
            is = doGet(curUrl.toString());
            pageStr = inputStreamToString(is, "UTF-8");// 一整页的html源码

            List<String> list = getMatherSubstrs(pageStr, "(?<=href=\")https://blog.csdn.net/" + userId + "/article/details/[0-9]{8,9}(?=\")");
            urls.addAll(list);

            if (pageStr.lastIndexOf("空空如也") != -1) {
                System.out.println("No This Page!");
                break;
            } else {
                System.out.println("Success~");
            }
            totalPage = i;
        }
        System.out.println("总页数为: " + totalPage);

        // ---------------------------------------------------打印每个链接---------------------------------------------------
        System.out.println("打印每个链接");
        for (String s : urls) {
            System.out.println(s);
        }
        System.out.println("打印每个链接完毕");

        // ---------------------------------------------------访问每个链接---------------------------------------------------
        // 循环次数
        int cycleCount = 0;
        while (cycleCount < count) {
            int i = 0;
            for (String s : urls) {
                doGet(s);
                System.out.println("成功访问第" + (++i) + "个链接,共" + urls.size() + "个:" + s);
            }
            // ---------------------------------------------------程序结束---------------------------------------------------
            System.out.println("运行完毕，成功增加访问数：" + urls.size());
            cycleCount++;
            TimeUnit.MINUTES.sleep(new Random().nextInt(10));
        }

    }

    public static InputStream doGet(String urlstr) throws IOException {
        URL url = new URL(urlstr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        InputStream inputStream = conn.getInputStream();
        return inputStream;
    }

    public static String inputStreamToString(InputStream is, String charset) throws IOException {
        byte[] bytes = new byte[1024];
        int byteLength = 0;
        StringBuffer sb = new StringBuffer();
        while ((byteLength = is.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, byteLength, charset));
        }
        return sb.toString();
    }

    // 正则匹配
    public static List<String> getMatherSubstrs(String str, String regex) {
        List<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }
}
