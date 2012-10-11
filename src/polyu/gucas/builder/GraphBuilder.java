/**
 * 香港理工大学-中国科学院研究生院数据挖掘-PageRank算法实现
 * @author chaoyu
 * @right Copyright by PolyU team
 * @github https://github.com/yuchao86/pagerank.git
 * @date 2012-10-10
 */
package polyu.gucas.builder;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.hadoop.mapred.TextInputFormat;

public class GraphBuilder {
	public static void main(String[] args) throws IOException {
        JobClient client = new JobClient();
        JobConf conf = new JobConf(GraphBuilder.class);
        conf.setJobName("graphbuilder");

        // specify output types
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(Text.class);

        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(SequenceFileOutputFormat.class);

        // specify input and output DIRECTORIES (not files)
        //FileInputFormat.setInputPaths(conf, new Path("/wiki"));
        FileInputFormat.setInputPaths(conf, new Path("hdfs://localhost:9000/user/chaoyu/website"));
        FileSystem.get(conf).delete(new Path("graph-0"), true);
        FileOutputFormat.setOutputPath(conf, new Path("graph-0"));

        // specify a mapper
	    conf.setMapperClass(Map.class);
	    //conf.setReducerClass(org.apache.hadoop.mapred.lib.IdentityReducer.class);
        // specify a reducer
        conf.setReducerClass(Reduce.class);
        //conf.setCombinerClass(Reduce.class);
        //conf.setNumMapTasks(38);
        //conf.setNumReduceTasks(0);

        client.setConf(conf);
        try {
                JobClient.runJob(conf);
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

}


