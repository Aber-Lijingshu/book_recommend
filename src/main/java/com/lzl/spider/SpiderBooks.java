package com.lzl.spider;

import com.lzl.bean.Book;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: li_zhilei
 * @Date: create in 19:25 17/5/22.
 * @description:
 */
public class SpiderBooks {
    public static void main(String[] args) {
        SpiderBooks.jsoupBooks();
    }
    public static void jsoupBooks(){
        try {
            Document document = Jsoup.connect("https://book.douban.com/").get();
            Elements elements = document.select("div.bd");
            System.out.println("elements.size() = " + elements.size());
            List<Book> list = new ArrayList<Book>();
            Element element = elements.get(3);
            Elements lis = element.getElementsByTag("li");
            System.out.println("lis.size() = " + lis.size());
            for (Element eli : lis){
                Book b = new Book();
                //获取title
                Elements titles = eli.getElementsByClass("title");
                b.setTitle(titles.text());
                //获取a
                Elements img = eli.getElementsByTag("img");
                b.setImageUrl(img.attr("src"));
                //获取author
                Elements author = eli.getElementsByClass("author");
                b.setAuthor(author.text());
                //获取评分
                Elements score = eli.getElementsByClass("average-rating");
                b.setScore(Double.parseDouble(score.text()));
                //获取类型
                Elements type = eli.getElementsByClass("book-list-classification");
                b.setType(type.text());
                list.add(b);
            }
            System.out.println("list = " + list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void jsoupDiv(){
        try {
            Document document = Jsoup.connect("https://book.douban.com/").get();
            Elements elements = document.select("div.bd");
            List<String> list = new ArrayList<String>();
            for(Element element : elements){
                String text = element.text();
                list.add(text);
            }
            System.out.println("list = " + list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void jsoupHtml(){
        try {
            Document document = Jsoup.connect("https://book.douban.com/").get();
            Elements elements = document.getElementsByClass("title");
            List<String> list = new ArrayList<String>();
            for(Element element : elements){
                String text = element.text();
                list.add(text);
            }
            System.out.println("list = " + list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
