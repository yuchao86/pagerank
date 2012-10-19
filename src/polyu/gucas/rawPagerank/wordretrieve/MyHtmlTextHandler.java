/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.wordretrieve;
/*
 * Tutorial 05: "Inverted File Indexing"
 * compile and run with :
 * WordUtil,MyHtmlTextHandler,HtmlWordExtractor
 * together with your own code.
 */

import java.util.*;
import javax.swing.text.html.*;

public class MyHtmlTextHandler extends HTMLEditorKit.ParserCallback {
    public Set<String> wordSet=new HashSet<String>();
    protected WordUtil wutil;

    private String delim_num="0123456789";
    private String delim_pun=",.<>/?;':\"[]{}`~!@#$%^&*()-_=+";
    private String delim_ctrl=" \t\n\r";
    private String delim=delim_num+delim_pun+delim_ctrl;
    
    
    public MyHtmlTextHandler(WordUtil wu){
    	wutil=wu;
    }
    
    public void reset(){
    	wordSet.clear();
    }

    // This method is inherited from the super class
    //     HTMLEditorKit.ParserCallback
    public void handleText(char[] data, int pos)  {
    	String text=new String(data);
    	if(text==null||text.length()<1)return;
    	StringTokenizer tok=new StringTokenizer(text,delim);
    	while(tok.hasMoreTokens()){
    		String temp=tok.nextToken();
    		if(temp==null||temp.length()<2)continue;
    		if(wutil.isStopWord(temp))continue;
    		temp=wutil.stem(temp);
    		wordSet.add(temp);
    	}
    }
}