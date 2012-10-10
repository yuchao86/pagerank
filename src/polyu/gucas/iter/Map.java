/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.iter;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class Map extends MapReduceBase implements 
Mapper<WritableComparable, Writable, WritableComparable, Text> {

	public void map(WritableComparable  key, Writable value, 
			OutputCollector<WritableComparable, Text> output, Reporter reporter) throws IOException {

		Text title = (Text) key;
		String data = ((Text)value).toString();
		String[] split1 = data.split("@@@");
		double currentPR = new Double(split1[0]).doubleValue();
		if (split1.length == 1) {
			output.collect(title, new Text("0.00"));
			return;
		}
		
		String[] pages = data.split("\\^\\^");
		//if (pages.length == 0) return;
		Text toEmit = new Text((new Double( currentPR / pages.length)).toString());
		for (String page : pages) {
			output.collect(new Text(page), toEmit);
		}
		//output.collect(key, new Text(".02"));
		output.collect(title, new Text("##@@" + data));

	}

}

