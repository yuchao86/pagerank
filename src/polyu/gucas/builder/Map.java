/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		
		String page = value.toString();
		//String page = value.toString().toLowerCase();
        
		String title = this.GetTitle(page, reporter);
		if (title.length() == 0) 
			return;
		
		ArrayList<String> outlinks = this.GetOutlinks(page);
		StringBuilder builder = new StringBuilder();
		for (String link : outlinks) {
			//link = link.replace(" ", "_");
			builder.append(link);
			builder.append("^^");
		}
		output.collect(new Text(title), new Text(builder.toString()));
	}

	public String GetTitle(String page, Reporter reporter) throws IOException{
		//int start = page.indexOf("&lttitle&gt")+11;
		int start = page.indexOf("<title>");
		if (start == -1) 
			return "";
		//int end = page.indexOf("&lt/title&gt", start);
		int end = page.indexOf("</title>", start + 7);
		if (end == -1)
			return "";
		
		String title = page.substring(start+7, end);
		return title;
	}

	public ArrayList<String> GetOutlinks(String page){
		//int end;
		ArrayList<String> outlinks = new ArrayList<String>();
		//int start=page.indexOf("[[");
		Pattern linkPattern = Pattern.compile("\\[\\[([^\\]])+\\]\\]");
		Matcher linkMatcher = linkPattern.matcher(page);
		//String link = "";
		while (linkMatcher.find()) {
			String tempLink = page.substring(linkMatcher.start() + 2,linkMatcher.end() - 2);
			int verLineIndex = tempLink.indexOf('|');
			if (verLineIndex != -1)
				tempLink = tempLink.substring(0, verLineIndex);
			//tempLink = tempLink.concat("^^");
			//link = link.concat(tempLink);	
			outlinks.add(tempLink);
		}
		/*
		while (start > 0) {
			start = start+2;
			end = page.indexOf("]]", start);
			//if((end==-1)||(end-start<0))
			if (end == -1) {
				break;
			}

			String toAdd = page.substring(start);
			toAdd = toAdd.substring(0, end-start);
			outlinks.add(toAdd);
			start = page.indexOf("[[", end+1);
		}*/
		return outlinks;
	}
}

