package com.baijie.writablecomparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @ClassName SortReducer
 * @Description Reducer此时的功能只是将键值翻个输出
 * @Author bj
 * @Date 2019/9/2 23:14
 * @Version 1.0
 */
public class SortReducer extends Reducer<FlowBean, Text, Text, FlowBean> {
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value, key);
        }
    }
}
