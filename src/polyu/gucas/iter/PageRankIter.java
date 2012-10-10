/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.iter;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.SequenceFileInputFormat;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;

public class PageRankIter {
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10; i++) {
			JobClient client = new JobClient();
	        JobConf conf = new JobConf(PageRankIter.class);
	        conf.setJobName("pagerankiter");

	        // specify output types
	        conf.setOutputKeyClass(Text.class);
	        conf.setOutputValueClass(Text.class);

	        conf.setInputFormat(SequenceFileInputFormat.class);
	        conf.setOutputFormat(SequenceFileOutputFormat.class);

	        // specify input and output DIRECTORIES (not files)
	        FileInputFormat.setInputPaths(conf, new Path("graph-" + i));
	        FileSystem.get(conf).delete(new Path("graph-" + (i+1)), true);
	        FileOutputFormat.setOutputPath(conf, new Path("graph-" + (i+1)));

	        // specify a mapper
	        conf.setMapperClass(Map.class);

	        // specify a reducer
	        conf.setReducerClass(Reduce.class);
	        //conf.setCombinerClass(Reduce.class);
	        //conf.setNumMapTasks(150);
	        //conf.setNumReduceTasks(10);

	        client.setConf(conf);
	        try {
	                JobClient.runJob(conf);
	        } catch (Exception e) {
	                e.printStackTrace();
	        }finally {
				//FileSystem.get(conf).delete(new Path("graph-" + i), true);
			}
		}
        
    }

}


