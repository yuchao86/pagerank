/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.robot;
/*
 * Tutorial 04: "Web Robots"
 * compile and run with LinkExtractor together with your own code.
 */
/**
  * This program parse the HTML files,look up for the href
  * store it into the external or internal iterator
  * @version jdk 1.6.0
  */


import java.util.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

public class MyHtmlHrefHandler extends HTMLEditorKit.ParserCallback {
    public Set<String> extLinks = new HashSet<String>();
    public Set<String> localLinks = new HashSet<String>();
    //public String root;
    
    /*public MyHtmlHrefHandler(String url){
    	super();
    	root = url;
    }
    */
    public MyHtmlHrefHandler(){
    	super();
    }

    public void reset(){
        extLinks.clear();
        localLinks.clear();
    }
    
 /*   public static String eraseStr(String surl){    	
    	if(surl.indexOf("/") != 0 ){
    		surl = "/" + surl;
    	}    	
    	if(surl.length() >= 1 && surl.charAt(surl.length()-1)=='/'){
            surl = surl.substring(0, surl.length()-1);
        }      	
    	return surl;
    }
*/
    // This method is inherited from the super class
    //     HTMLEditorKit.ParserCallback
    public void handleStartTag(
            HTML.Tag tag, MutableAttributeSet attributes, int pos) {
        //If the element is not a link, i.e.: Not <A HREF="...">...</A>
        //Then ignore it
        if(!(tag.equals(HTML.Tag.A))){
            return;
        }
        // If the element is <A HREF="hello.html">Hello</a>
        // Then link="hello.html"
        String link=(String) attributes.getAttribute(HTML.Attribute.HREF);
        if(link==null||link.length()==0)return;

        if(link.length()>=4&&link.substring(0,4).equalsIgnoreCase("http")){
            // An external link is like "http://www.google.com/"
            extLinks.add(link);
        }else{
            // An internal link is like "/~cs5286/index.html"
        	//link = eraseStr(link);
            localLinks.add(link);
        }
    }
}
