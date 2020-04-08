package com.baijie.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @ClassName FlowReducer
 * @Description
 * @Author bj
 * @Date 2019/9/2 1:04
 * @Version 1.0
 */
public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    private FlowBean sumFlow = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFlow = 0;
        for (FlowBean value : values) {
            sumUpFlow += value.getUpFlow();
            sumDownFlow += value.getDownFlow();
        }
        sumFlow.set(sumUpFlow, sumDownFlow);
        context.write(key, sumFlow);
    }
}
