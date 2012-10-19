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
/**
  * This class provides two functions:
  * 1) Stemming
  * 2) Judge whether a word is stop word
  * The one you see now is only a dummy implementation
  * You should implement a "serious" one by yourself after class
  * @version jdk 1.6.0
  */

public class WordUtil {
	
	
	/**
	 * This function should implement stemming.
	 * Stemming is the process that translates a word's
	 * different variations into one thing. 
	 * 
	 * For example, translating "smile" and "smiling"
     * and "smiled" into "smile"
	 * 
	 * In tutorial we just show a dummy implementation.
	 * After class, you should implement one by yourself.
	 * 
	 * Hint: See http://www.tartarus.org/martin/PorterStemmer/
	 */
	public String stem(String word){
		word=word.toLowerCase();
		if(word.equals("smiled"))return "smile";
		return word;
	}
	
	/**
	 * This function judges whether a word is stopword.
	 * 
	 * In tutorial we only show a dummy implementation.
	 * 
	 * After class, you should implement one by yourself.
	 * 
	 * Hint: See http://www.dcs.gla.ac.uk/idom/ir_resources/linguistic_utils/stop_words
	 */
	public boolean isStopWord(String word){
		word=word.toLowerCase();
		if(word.equals("the"))return true;
		if(word.equals("of"))return true;
		if(word.equals("an"))return true;
		if(word.equals("a"))return true;
		if(word.equals("and"))return true;
		if(word.equals("the"))return true;
		return false;
	}
}
