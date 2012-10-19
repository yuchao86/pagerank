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
 * compile and run with MyHtmlHrefHandler together with your own code.
 */
/**
  * This program extract the links from the input stream.
  * and put them in the external link iterator and local link iterator
  * @version jdk 1.6.0
  */

import java.io.*;
import java.util.*;
import javax.swing.text.html.parser.*;

public class LinkExtractor {
    private MyHtmlHrefHandler handler;

    /*public void parse(InputStream stream, String url) throws IOException {
        if(stream==null)throw new IllegalArgumentException(
                "Illegal Argument :: Null Value :: stream");
        handler  = new MyHtmlHrefHandler(url);
        handler.reset();
        ParserDelegator pd=new ParserDelegator();
        try{
            pd.parse(new InputStreamReader(stream),handler,true);
        }finally{
            if(stream!=null)stream.close();
        }
    }*/
    
    public void parse(InputStream stream) throws IOException {
        if(stream==null)throw new IllegalArgumentException(
                "Illegal Argument :: Null Value :: stream");
        handler  = new MyHtmlHrefHandler();
        handler.reset();
        ParserDelegator pd=new ParserDelegator();
        try{
            pd.parse(new InputStreamReader(stream),handler,true);
        }finally{
            if(stream!=null)stream.close();
        }
    }

    public Iterator<String> getExtLinkIterator() {
        return handler.extLinks.iterator();
    }

    public Iterator<String> getLocalLinkIterator() {
        return handler.localLinks.iterator();
    }

}

