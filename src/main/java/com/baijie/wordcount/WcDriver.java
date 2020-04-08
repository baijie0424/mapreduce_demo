package com.baijie.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @ClassName WcDriver
 * @Description TODO
 * @Author bj
 * @Date 2019/8/26 23:21
 * @Version 1.0
 */
public class WcDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取一个Job实例
        Job job = Job.getInstance(new Configuration());
        //设置我们的类路径（Classpath）
        job.setJarByClass(WcDriver.class);
        //设置Mapper和Reducer
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);
        //设置Mapper和Reducer的输出路径
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置Combiner
        job.setCombinerClass(WcReducer.class);
        //设置输入输出数据
        FileInputFormat.setInputPaths(job,new Path("D:\\BigData\\input"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\BigData\\output"));
        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
