/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.common;

import java.io.File;
import java.util.logging.Logger;

import junit.framework.TestCase;
import polyu.gucas.common.PolyULog;

public class PolyULogTest extends TestCase {
	
	protected void setUp() throws Exception {
		super.setUp();
		File file = new File("polyu_gucas.log ");
		if (file.exists()) {
			file.delete();
		}
	}

	public void testLog() {
		File file = new File("polyu_gucas.log ");
		assertEquals(false, file.exists());
		PolyULog log = new PolyULog();
		log.DebugLog();
		assertEquals(true, file.exists());
		assertEquals(0, file.length());
		Logger logger = Logger.getLogger(" ");
		logger.fine("polyu_gucas,data mining papar");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PolyULogTest polytest = new PolyULogTest();
		polytest.testLog();
		System.out.println("test all successful!");

	}

}
