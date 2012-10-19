/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-15
 */
package polyu.gucas.Link;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class PageRankReduce extends MapReduceBase implements
		Reducer<WritableComparable, Writable, Text, Text> {
	public void reduce(WritableComparable _key, Iterator<Writable> values,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		char constChar = (char)17;
		Text key = (Text) _key;
		String pageList = "";

		float sum = 0f;
		while (values.hasNext()) {
			String value = new String(values.next().toString());
			if (value.charAt(0)== constChar)
			{
				pageList += value;
				break;
			}
			else
			{
				sum += Float.parseFloat(value);
			}
		}
		sum = sum/10000;
		sum = sum* 0.15f + 0.85f;

		StringBuilder sb = new StringBuilder();
		sb.append(constChar);
		sb.append(String.valueOf(sum));
		sb.append(pageList);
		if(pageList.length() == 0)
			sb.append(constChar);
		output.collect(key, new Text(sb.toString()));
	}
}