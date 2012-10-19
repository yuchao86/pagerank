/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.test;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import java.util.StringTokenizer;

public class PageRankMap extends MapReduceBase implements
		Mapper<WritableComparable, Writable, Text, Text> {
	public void map(WritableComparable key, Writable value,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		char constChar = (char)17;
		String line = value.toString();
		StringTokenizer itr = new StringTokenizer(line,String.valueOf(constChar));
		int sum = itr.countTokens() - 2;
		if (itr.countTokens() <= 2)
			return;
		String pageNameSpace = itr.nextToken();
		String pageName = pageNameSpace.substring(0, pageNameSpace.length()-1);
		
		float d = Float.valueOf(itr.nextToken());
		d = d* 10000/sum;
		StringBuilder sLinkList = new StringBuilder();
		while(itr.hasMoreTokens())
		{
			String temp = itr.nextToken();
			sLinkList.append(constChar);
			sLinkList.append(temp);
			output.collect(new Text(temp), new Text(String.valueOf(d)));	
		}
		output.collect(new Text(pageName), new Text(sLinkList.toString()));
	}
}