/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.UnitTest;
/**
 * Tutorial 05: "Inverted File Indexing"
 * compile and run with :
 * WordUtil,MyHtmlTextHandler,HtmlWordExtractor
 * together with your own code.
 */
/**
  * This file provides a demo for using the WordExtractor
  * store it into the external or internal iterator
  * try to replace the url with another one
  * @version jdk 1.6.0
  */

import java.net.*;
import java.util.*;

import polyu.gucas.rawPagerank.wordretrieve.HtmlWordExtractor;

public class TestHtmlWordExtractor {
    public static void main(String[] args) throws Exception {
        //String surl = "http://www.baidu.com/";
    	String surl = "http://127.0.0.1/polyu.html";
        extractWords(surl);
    }

    public static void extractWords(String surl) throws Exception {
        URL url = new URL(surl);
        HtmlWordExtractor extractor = new HtmlWordExtractor();
        extractor.parse(url.openStream());
        Iterator<String> it;
        printSeparator('-');
        println("Words:");
        printSeparator('*');
        it = extractor.getWordIterator();
        while (it.hasNext()) {
            println((String) it.next());
        }
        printSeparator('=');
        println("Thank you for using. :)");
    }

    public static void println(String str) {
        System.out.println(str);
    }

    public static void printSeparator(char ch) {
        for (int i = 0; i < 20; ++i) {
            System.out.print(ch);
        }
        System.out.println();
    }
}
