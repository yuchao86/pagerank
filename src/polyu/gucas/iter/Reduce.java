/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.iter;

import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;


public class Reduce extends MapReduceBase 
	implements Reducer<WritableComparable, Writable, WritableComparable, Text> {

	public void reduce(WritableComparable key, Iterator<Writable> values, 
			OutputCollector<WritableComparable, Text> output, Reporter reporter) throws IOException {
		Text title = (Text) key;
		double score = 0;
		String outLinks = "";

		while (values.hasNext()) {
			String curr = ((Text)values.next()).toString();
			int start = curr.indexOf("##@@");
			if ((start != -1)) {
				outLinks = curr.substring(start+4);
				continue;
			}
		
			//try{
				score += Double.parseDouble(curr);
			//} catch (Exception e) {
				//	;
			//}
		}
		
		double d = 0.85;
		score = (1-d) + d*score;
		String toEmit;
		if (outLinks.length() > 0) {
			toEmit = (new Double(score)).toString() + "@@@" + outLinks;
		} else {
			toEmit = (new Double(score)).toString();
		}
		output.collect(title, new Text(toEmit));
	}

}
