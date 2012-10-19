/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.wordretrieve;
/**
 * Tutorial 05: "Inverted File Indexing"
 * compile and run with :
 * WordUtil,MyHtmlTextHandler,HtmlWordExtractor
 * together with your own code.
 */

import java.io.*;
import java.util.*;
import javax.swing.text.html.parser.*;

public class HtmlWordExtractor {
	private WordUtil wuuil=new WordUtil();
	private MyHtmlTextHandler handler = new MyHtmlTextHandler(wuuil);
	
    public void parse(InputStream stream) throws IOException {
        if(stream==null)throw new IllegalArgumentException(
                "Illegal Argument :: Null Value :: stream");
        handler.reset();
        ParserDelegator pd=new ParserDelegator();
        try{
            pd.parse(new InputStreamReader(stream),handler,true);
        }finally{
            if(stream!=null)stream.close();
        }
    }
    
    public Set<String> getWordSet(){
    	return handler.wordSet;
    }
    
    public Iterator<String> getWordIterator(){
    	return handler.wordSet.iterator();
    }
}
