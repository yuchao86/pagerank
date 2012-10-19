/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.rawPagerank.UnitTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import polyu.gucas.rawPagerank.robot.Irobot;
import polyu.gucas.rawPagerank.pagerank.*;
import polyu.gucas.rawPagerank.pagerank.StringMap;

public class PageRank {
	static Irobot i;
	static StringMap sm[];
	static UrlPageRank ur;
	
	public static void printHelp(){
		System.out.println("Welcome to use the PageRank test!");
		System.out.println("retrieve [URL] -- retrieve the given URL.");
		System.out.println("save [path] -- save the pagerank to the file.");
		System.out.println("load [path] -- load the result of previous.");
		System.out.println("p [URL] -- Look for the pagerank value.");
		System.out.println("quit -- exit the system");
		System.out.print("Enjoy using! \n:");
	}
	
	public static void printWelcome(){
		System.out.println("PageRank Test!!\n" +
						   "'help' -- for help.");		
	}
	
	public static void loadFile(String path) throws Exception{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				path));
		
		ur = (UrlPageRank)in.readObject();
		ur.print();
		
		in.close();
	}
	
	public static void main(String[] args) throws Exception {
		printWelcome();
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String msg = bf.readLine();

		while (!msg.equalsIgnoreCase("quit")) {
			String query[] = msg.split(" ");

			if (query[0].equalsIgnoreCase("retrieve")) {
				String url = "http://" + query[1];
				i = new Irobot(url);
				sm = i.getUrls();

				ur = new UrlPageRank(sm, i.getUrlsize());
				ur.print(10000, 0.85);
			}else if(query[0].equalsIgnoreCase("help")){
				printHelp();
			}else if(query[0].equalsIgnoreCase("p")){
				if(query.length == 2){
					int value = ur.getNodeValue(query[1]);
					
					System.out.println(value);
				}
			}else if(query[0].equalsIgnoreCase("load")){
				if(query.length == 2){
					loadFile(query[1]);
				}
			}else if(query[0].equalsIgnoreCase("save")){
				String path = "";
				if(query.length==1){
					System.out.println("please input the filename");
					path = bf.readLine();
				}else if(query.length==2){
					path = query[1];
				}
				
				if(ur!=null){
					ur.saveFile(path);
				}
			}

			msg = bf.readLine();
		}

		System.out.println("Thank you for using!!");
	}
}
