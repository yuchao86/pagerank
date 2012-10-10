/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.viewer;

import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Map extends MapReduceBase implements 
Mapper<WritableComparable, Writable, FloatWritable, WritableComparable> {

	public void map(WritableComparable  key, Writable value, 
			OutputCollector<FloatWritable, WritableComparable> output, Reporter reporter) throws IOException {

		Text title = (Text)key;
				
		String data = ((Text)value).toString();
	    int index = data.indexOf("@@@");
	    if (index == -1) {
	      return;
	    }
	    
	    
	    String toParse = data.substring(0, index).trim();
	    double currScore = Double.parseDouble(toParse);
	    //output.collect(new FloatWritable((float) - currScore), title);
	    output.collect(new FloatWritable((float)currScore), title);
		
	}

}

