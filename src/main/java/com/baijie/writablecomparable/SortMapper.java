package com.baijie.writablecomparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @ClassName SortMapper
 * @Description Mapper输出时已经完成对key的全排序排序
 * @Author bj
 * @Date 2019/9/2 23:14
 * @Version 1.0
 */
public class SortMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
    private FlowBean flow = new FlowBean();
    private Text phone = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        phone.set(fields[0]);
        flow.setUpFlow(Long.parseLong(fields[1]));
        flow.setDownFlow(Long.parseLong(fields[2]));
        flow.setSumFlow(Long.parseLong(fields[3]));
        context.write(flow, phone);
    }
}
