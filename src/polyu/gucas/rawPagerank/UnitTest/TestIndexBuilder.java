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
 * WordUtil,MyHtmlTextHandler,HtmlWordExtractor,IndexBuilder
 * together with your own code.
 */
/**
  * This class demos how to use IndexBuilder
  * @version jdk 1.6.0
  */
import java.io.*;
import java.util.*;

import polyu.gucas.rawPagerank.wordretrieve.IndexBuilder;

public class TestIndexBuilder {
	public static final String[] urlArr={
		"http://www.polyu.edu.hk/poc/abt.html",
		"http://www.polyu.edu.hk/cpa/polyu/index.php",
		"http://www.cs.cityu.edu.hk/news",
    };
	
	public static void main(String[] args) {
		IndexBuilder ib=new IndexBuilder();
		for(int i=0;i<urlArr.length;++i){
			try {
				ib.feedUrl(urlArr[i]);
			} catch (IOException e) {
				System.err.println("URL IS Not Accesible:"+urlArr[i]);
			}
		}
		
		Iterator<String> it;
		
		//Demo: Look up where a term appears
		it=ib.lookUpDocForTerm("polyu");
		System.out.println("Term \"polyu\" is in document:");
		printIterator(it);
		
		//Another Demo: Look up where a term appears
		it=ib.lookUpDocForTerm("CMAO");
		System.out.println("Term \"CMAO\" is in document:");
		printIterator(it);
		
		//Demo: Enumerate through all documents
		it=ib.getDocIterator();
		System.out.println("Document List:");
		printIterator(it);
	
		//Demo: Enumerate through all terms
		it=ib.getTermIterator();
		System.out.println("Term List:");
		printIterator(it);

	}
	
	public static void printIterator(Iterator<String> it){
		System.out.print("[");
		while(it.hasNext()){
			System.out.print(it.next());
			System.out.print("; ");
		}
		System.out.println("]");
	}

}
