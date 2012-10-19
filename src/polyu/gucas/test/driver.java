/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-18
 */
package polyu.gucas.test;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

import polyu.gucas.test.PageRankMap;
import polyu.gucas.test.PageRankReduce;
import polyu.gucas.test.CleanupMap;
import polyu.gucas.test.CleanupReduce;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.fs.*;

public class driver {
	public static void main(String[] args) throws Exception {
		int fileName = 0;
		JobConf conf = new JobConf(driver.class);
		conf.setJobName("CreateLinkGraph");
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(conf, new Path("../../data/polyu"));
		FileOutputFormat.setOutputPath(conf, new Path(String.valueOf(fileName)));
		// specify a mapper
		conf.setMapperClass(CreateLinkGraphMap.class);
		// specify a reducer
		conf.setReducerClass(CreateLinkGraphReduce.class);
		conf.setNumReduceTasks(15); 
		JobClient.runJob(conf);

		for(;fileName < 10; fileName ++)
		{
				JobConf pagerank = new JobConf(driver.class);
				pagerank.setJobName("pageRank");
				pagerank.setOutputKeyClass(Text.class);
				pagerank.setOutputValueClass(Text.class);
				FileInputFormat.setInputPaths(pagerank, new Path(String.valueOf(fileName)));
				FileOutputFormat.setOutputPath(pagerank, new Path(String.valueOf(fileName+1)));
				// specify a mapper
				pagerank.setMapperClass(PageRankMap.class);
				// specify a reducer
				pagerank.setReducerClass(PageRankReduce.class);
				pagerank.setNumReduceTasks(15); 
				JobClient.runJob(pagerank);
				FileSystem.get(pagerank).delete(new Path(String.valueOf(fileName)), true);
		}

		JobConf Cleanup = new JobConf(driver.class);
		Cleanup.setJobName("cleanup");
		Cleanup.setOutputKeyClass(FloatWritable.class);
		Cleanup.setOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(Cleanup, new Path(String.valueOf(fileName)));
		FileOutputFormat.setOutputPath(Cleanup, new Path("output"));
		// specify a mapper
		Cleanup.setMapperClass(CleanupMap.class);
		// specify a reducer
		Cleanup.setReducerClass(CleanupReduce.class);
		JobClient.runJob(Cleanup);
		FileSystem.get(Cleanup).delete(new Path(String.valueOf(fileName)), true);
	}
}
