/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.test;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class CreateLinkGraphReduce extends MapReduceBase implements
		Reducer<WritableComparable, Writable, Text, Text> {
	public void reduce(WritableComparable _key, Iterator<Writable> values,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		Text key = (Text) _key;
		
		output.collect(key, new Text(values.next().toString()));
	}
}
