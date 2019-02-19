package com.wan.noveldownload.Util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by xen on 2019/2/12 0012.
 */

public class DownloadTool {



/*
        try {
            System.out.println("输入保存路径：");
            Scanner scanner = new Scanner(System.in);
            String path = scanner.nextLine();

            System.out.println("请输入小说网址：");
            String url = scanner.nextLine();
            System.out.println("下载中，请稍后...");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("全部下载完成！");
        }
    }*/



    public static void writeText(String title,String content, File file) throws IOException{
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file,true), "GBK");
        BufferedWriter writer = new BufferedWriter(outputStreamWriter);
        writer.newLine();
        writer.write(content);
        writer.close();
        /*System.out.print("当前章节： "+title);
        System.out.println("  已完成！");*/
    }
    /**
     * 处理空行和“&nbsp;”标志
     * @param input 内容
     * @return 处理过后的结果
     */
    public static String deleteCRLFOnce(String input) {
        return input.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("&nbsp;", "").replaceAll("本章未完，点击下一页继续阅读","");
    }

    public static String getNextPage(Document document) {
        String s = null;
        Elements elements = document.getElementsByClass("mlfy_page").select("a");
        s = elements.get(elements.size() - 1).attr("href");
        return s;
    }

    /**
     * 获得章节名和内容
     * @param url 网址
     * @return string
     */
    public static String[] getContent(String url) throws IOException{
        Document document = Jsoup.connect(("https://www.23qb.net" + url)).get();
        Element element = document.getElementById("mlfy_main_text");
        String title =element.select("h1").text();
        Element element1 = document.getElementById("TextContent");
        String text = Jsoup.clean(element1.html(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));//保存p标签换行
        String content = deleteCRLFOnce(text);//格式化文本，删除多余的空行和标志
        String s[] = new String[3];
        s[0] = title;
        s[1] = title+ content;
        s[2] = getNextPage(document);
        return s;
    }

    /**
     * 获得第一个章节
     * @return
     */
    public static String getStartChapter(String url)  throws IOException {
        Document document =  Jsoup.connect(url).get();
        Element element = document.getElementById("chapterList");
        Elements elements = element.select("li").select("a");
        String firstChapterUrl = elements.get(0).attr("href");
        return firstChapterUrl;
    }

    public static String getBookName(String url) throws IOException {
        Document document =  Jsoup.connect(url).get();
        Elements elements = document.getElementsByClass("d_title").select("h1");

        String bookName = null;
        for (int i = 0; i < elements.size(); i++) {
            bookName = elements.get(i).text();
        }
        return bookName;
    }
}
