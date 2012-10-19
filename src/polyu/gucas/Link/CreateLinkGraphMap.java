/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-15
 */
package polyu.gucas.Link;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class CreateLinkGraphMap extends MapReduceBase implements
		Mapper<WritableComparable, Writable, Text, Text> {
	public void map(WritableComparable key, Writable value,
			OutputCollector<Text, Text> output, Reporter reporter)
			throws IOException {
		char constChar = (char)17;
		String line = value.toString();
		StringBuilder linkList = new StringBuilder();
		linkList.append(constChar);
		linkList.append("1f");

		//find the "page name"
		int i = 0;
		while(line.charAt(i) != '>')
		{
			i++;
		}
		int offset = ++i;
		while(line.charAt(i) != '<')
		{
			i++;
		}
		String pageNameString = line.substring(offset, i);
		
		boolean isEmpty = true;
		//find link
		for(; i< line.length(); i++)
		{
			if( line.charAt(i) == '[' && line.charAt(i+1) == '[')
			{
				i += 2;
				int j;
				for (j = i;j < line.length(); j++)
				{
					if( line.charAt(j) == '|' ||(line.charAt(j) == ']' && line.charAt(j+1) == ']'))
					{
						isEmpty = false;
						String targetPage = line.substring(i, j);
						linkList.append(constChar);
						linkList.append(targetPage);
						break;
					}
				}
				
			}
		}
		if (isEmpty)
		{
			linkList.append(constChar);
		}
		output.collect(new Text(pageNameString), new Text(linkList.toString()));
	}
}

