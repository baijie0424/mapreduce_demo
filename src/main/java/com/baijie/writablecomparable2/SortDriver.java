package com.baijie.writablecomparable2;

import com.baijie.writablecomparable.FlowBean;
import com.baijie.writablecomparable.SortMapper;
import com.baijie.writablecomparable.SortReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @ClassName SortDriver
 * @Description
 * @Author bj
 * @Date 2019/9/2 23:14
 * @Version 1.0
 */
public class SortDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(SortDriver.class);

        job.setMapperClass(SortMapper.class);
        job.setReducerClass(SortReducer.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        job.setNumReduceTasks(5);
        job.setPartitionerClass(MyPartitioner2.class);

        FileInputFormat.setInputPaths(job, new Path("D:\\BigData\\output"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\BigData\\output_partition"));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
