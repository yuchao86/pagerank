/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.common;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class PolyULog {
	public void DebugLog() {
		/*
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date today=new Date();    
		String logpath = new String();
		logpath = "log"+format.format(today)+"polyu_gucas.log";
		*/
		try {
			Logger.getLogger(" ").setLevel(Level.ALL);
			Handler handler = new FileHandler("polyu_gucas.log", true);
			handler.setFormatter(new SimpleFormatter());
			Logger.getLogger(" ").addHandler(handler);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
