/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.robot;

import java.net.*;
import java.util.*;
import java.io.*;
import polyu.gucas.rawPagerank.pagerank.StringMap;

public class Irobot {	
    static StringMap back_link[] = new StringMap[10000];
    static  HashSet<String> urls = new HashSet<String>();
    static String rootUrl;
    static int i = 0,sindex = 0;
    
    public Irobot(String url){
    	rootUrl = url;
    }
    
    public int getUrlsize(){
    	return sindex;
    }
    
    public StringMap[] getUrls(int level) throws Exception{
    	extractLinks(rootUrl,level);
    	System.out.println("There're " +
        		sindex + " links retrieved");
        System.out.println("There're " +
        		urls.size() + " links has been retrieved.");
    	
    	return back_link;
    }
    
    public StringMap[] getUrls() throws Exception{
    	extractLinks(rootUrl);
    	System.out.println("There're " +
        		sindex + " links retrieved");
        System.out.println("There're " +
        		urls.size() + " links has been retrieved.");
        
/*        for(int i=0;i<sindex;i++){
        	System.out.println("Resource is " + back_link[i].getResource() + 
        			" and Destination is " + back_link[i].getDestination());
        }*/
    	
    	return back_link;
    }
    
    /*
    public static void main(String[] args) throws Exception {
        rootUrl = "http://www.3lian.com/zl/2004/10-4/213820.html";        
        extractLinks(rootUrl);
                
        System.out.println("There're " +
        		sindex + " links retrieved");
        System.out.println("There're " +
        		urls.size() + " links has been retrieved.");
        
        for(int i=0;i<sindex;i++){
        	System.out.println("Resource is " + back_link[i].getResource() + 
        			" and Destination is " + back_link[i].getDestination());
        }
        
        //Iterator it = urls.iterator();
        
        //while(it.hasNext()){
        //	System.out.println(it.next().toString());
        //}
        
        //System.out.println("polyu " + back_link.get("http://hi.baidu.com/polyu").toString());
    }    
    */
    
    public static Iterator<String> extractS(String surl) throws Exception{  
    	i ++;
        URL url = new URL(surl);   
        System.out.println("Now is reterive the URL: " + url.toString());
        
        LinkExtractor extractor = new LinkExtractor();
        if(url.openStream()!=null){
		    extractor.parse(url.openStream());
		    Iterator<String> it,it1;
		    
		    it = extractor.getExtLinkIterator();
		    while(it.hasNext()){
		    	String ss1 = it.next().toString();
	    	
	    		back_link[sindex] = new StringMap(url.toString(),ss1);
	    		sindex ++;
	    		urls.add(ss1);
		    }		   	
		    System.out.println("***********************" + i);
		    it1 = extractor.getExtLinkIterator();
		    return it1;
		}        
        return null;
    }
    
    public static void extractLinks(String surl) throws Exception {
        Iterator<String> its = extractS(surl);
        HashSet<String> level1 = new HashSet<String>();
        
        while(its.hasNext()){
        	level1.add(its.next().toString());
        }
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please input how many levels :");
        int level = Integer.parseInt(bf.readLine());
  
        if(level <= 0){
        	System.out.println("Error: Level <0. ");
        }else if(level  == 1){
        	
        }else if(level >= 2){
        	for(int i = 1;i < level; i++){
        		level1 = extractHashSet(level1);
        	}
        }
    }
    
    public static void extractLinks(String surl,int j) throws Exception {
        Iterator<String> its = extractS(surl);
        HashSet<String> level1 = new HashSet<String>();
        
        while(its.hasNext()){
        	level1.add(its.next().toString());
        }
          
        if(j <= 0){
        	System.out.println("Error: Level <0. ");
        }else if(j  == 1){
        	
        }else if(j >= 2){
        	for(int i = 1;i < j; i++){
        		level1 = extractHashSet(level1);
        	}
        }
    }
    
    public static HashSet<String> extractHashSet(HashSet<String> level) throws Exception{
    	Iterator<String> it = level.iterator();
    	HashSet<String> levels = new HashSet<String>();
    	while(it.hasNext() && urls.size() <100){
    		String str = it.next().toString();
    		
    		if(!str.contains(".pdf") && !str.contains(".exe")
    				&&!str.contains(".wmv") && !str.contains(".mp3")
    				&&!str.contains("mailto") && !str.contains(".swf")){
    			Iterator<String> it1 = extractS(str);
    			 while(it1.hasNext() && urls.size() < 100){
    	    			String sss = it1.next().toString();
    	    			levels.add(sss);
    	    	}
    		}    		
    	}
    	
    	return levels;
    }
    
    /*public static void extractI(Iterator i) throws Exception{
    	while(i.hasNext() && urls.size() < 100){
            String zzz = i.next().toString();  
            if(!zzz.contains("mailto") && !zzz.contains(".pdf")
            		&& !urls.contains(zzz)
            		&& urls.size() <= 100
            		&& !zzz.contains(".wmv") && back_link.size() < 1000
            		){
            	urls.add(zzz);
            	Iterator i1 = extractS(zzz);
            	
            	//extractI(i1);
            }
        }
    }*/
}