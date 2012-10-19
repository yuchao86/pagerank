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
 * Modified by yuchao @2012
 **/

import java.io.*;
import java.util.*;
import java.net.*;

public class IndexBuilder {
	private Map<String,Set<String>> termMap=new HashMap<String,Set<String>>();
	private final Set<String> emptySet=new HashSet<String>();
	private Set<String> termSet=new HashSet<String>();
	private Set<String> docSet=new HashSet<String>();
	private WordUtil wu=new WordUtil();
	private Map<String,Set<String>> docTerm = new HashMap<String,Set<String>>();
	
	public Set<String> getDocTerm(String msg){
		return docTerm.get(msg);
	}
	
	public void feedUrl(String surl) throws IOException{
		docSet.add(surl);
        URL url = new URL(surl);
        HtmlWordExtractor extractor = new HtmlWordExtractor();
        extractor.parse(url.openStream());
        Iterator<String> it=extractor.getWordIterator();
        Set<String> s = extractor.getWordSet();
        docTerm.put(surl, s);
        
        while(it.hasNext()){
        	String sterm=(String)it.next();
        	HashSet<String> tempds=(HashSet<String>)termMap.get(sterm);
        	if(tempds==null){
        		//New Term
        		tempds=new HashSet<String>();
        	}
        	tempds.add(surl);
        	termSet.add(sterm);
        	termMap.put(sterm, tempds);
        }
	}
	
	public Iterator<String> lookUpDocForTerm(String sterm){
		sterm=wu.stem(sterm);
		Set<String> docSet=(Set<String>)termMap.get(sterm);
		if(docSet==null)
			return emptySet.iterator();
		return docSet.iterator();
	}
	
	public Iterator<String> getTermIterator(){
		return termSet.iterator();
	}
	
	public Iterator<String> getDocIterator(){
		return docSet.iterator();
	}
}
