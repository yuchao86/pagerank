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
import org.apache.hadoop.io.FloatWritable;

public class CleanupReduce extends MapReduceBase implements
		Reducer<WritableComparable, Writable, FloatWritable, Text> {
	public void reduce(WritableComparable _key, Iterator<Writable> values,
			OutputCollector<FloatWritable, Text> output, Reporter reporter)
			throws IOException {
		String sb = values.next().toString();
		String key = _key.toString().substring(1);
		output.collect(new FloatWritable (Float.valueOf(key)), new Text(sb));
	}
}
