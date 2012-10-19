/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-15
 */
package polyu.gucas.Link;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.io.FloatWritable;

public class CleanupMap extends MapReduceBase implements
		Mapper<WritableComparable, Writable, FloatWritable, Text> {
	public void map(WritableComparable key, Writable value,
			OutputCollector<FloatWritable, Text> output, Reporter reporter)
			throws IOException {
		char constChar = (char)17;
		String line = value.toString();
		String pageName = "";
		float f = 0f;
		for(int i = 0; i < line.length(); i ++)
		{
			if (line.charAt(i) == constChar)
			{
				pageName += line.substring(0, i-1);
				for (int j = i + 1; j < line.length(); j ++)
				{
					if (line.charAt(j) == constChar)
					{
						f = -Float.parseFloat(line.substring(i+1, j));
						break;
					}
				}
				break;
			}
		}
		output.collect(new FloatWritable(f), new Text(pageName));
	}
}