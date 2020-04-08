package com.baijie.wordcount;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @ClassName WcMapper
 * @Description TODO
 * @Author bj
 * @Date 2019/8/26 23:18
 * @Version 1.0
 */
public class WcMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text word = new Text();
    private IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //拿到这一行数据
        String line = value.toString();
        //用空格将数据进行切分
        String[] words = line.split(" ");
        //遍历数组把单词变成(word,1)的形式交给框架
        for (String word : words) {
            this.word.set(word);
            context.write(this.word, this.one);
        }
    }
}
