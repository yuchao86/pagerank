/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.viewer;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
import org.apache.hadoop.mapred.lib.IdentityReducer;

public class PageRankViewer {
	public static void main(String[] args) throws IOException {
        JobClient client = new JobClient();
        JobConf conf = new JobConf(PageRankViewer.class);
        conf.setJobName("pagerankviewer");

        // specify output types
        conf.setOutputKeyClass(FloatWritable.class);
        conf.setOutputValueClass(Text.class);

        conf.setInputFormat(SequenceFileInputFormat.class);

        // specify input and output DIRECTORIES (not files)
        FileInputFormat.setInputPaths(conf, new Path("graph-10"));
        FileSystem.get(conf).delete(new Path("pr_out4wiki1"), true);
        FileOutputFormat.setOutputPath(conf, new Path("pr_out4wiki1"));

        // specify a mapper
        conf.setMapperClass(Map.class);

        // specify a reducer
        conf.setReducerClass(IdentityReducer.class);
        conf.setOutputKeyComparatorClass(DescendingFloatComparator.class);

        client.setConf(conf);
        try {
                JobClient.runJob(conf);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
	private static class DescendingFloatComparator extends FloatWritable.Comparator {
		public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
			return -super.compare(b1, s1, l1, b2, s2, l2);
		}
		public int compare(WritableComparable a, WritableComparable b) {
			return -super.compare(a, b);
		}
	}

}


