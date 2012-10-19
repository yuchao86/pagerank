/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.UnitTest;


import java.io.*;

import polyu.gucas.rawPagerank.pagerank.StringMap;
import polyu.gucas.rawPagerank.pagerank.UrlPageRank;

public class TestUrlPageRank {

	private static BufferedReader br;

	/**
	 * yuchao @2012,
	 * Used to test.en
	 * */
	public static void main(String[] args) throws Exception {
		StringMap sm[] = new StringMap[1000];
		int index = 0;
		/*String s1 = "a",s2 = "b";
		String s3 = "c",s4 = "a";
		String s5 = "a",s6 = "c";
		String s7 = "b",s8 = "c";
		
		StringMap sm[] = new StringMap[4];
		sm[0] = new StringMap(s1,s2);
		sm[1] = new StringMap(s3,s4);
		sm[2] = new StringMap(s5,s6);
		sm[3] = new StringMap(s7,s8);*/
		
        FileInputStream fi = new FileInputStream("./polyu.txt");
        InputStreamReader ipr = new InputStreamReader(fi);
        br = new BufferedReader(ipr);
        
        String str = br.readLine();
        while(str != null){
        	String st[] = str.split(",");
        	if(st.length>2){
        		System.out.println("Error, please modify" +
        			" the file like 'a,b' perline");
        		return;
        	}else{
        		sm[index] = new StringMap(st[0],st[1]);
        		index ++;
        	}
        	str = br.readLine();
        }
		
		UrlPageRank ur = new UrlPageRank(sm,index);
		ur.print(10000,0.85);
	}

}
