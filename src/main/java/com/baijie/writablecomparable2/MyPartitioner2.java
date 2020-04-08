package com.baijie.writablecomparable2;

import com.baijie.writablecomparable.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @ClassName MyPartitioner2
 * @Description
 * @Author bj
 * @Date 2019/9/3 0:00
 * @Version 1.0
 */
public class MyPartitioner2 extends Partitioner<FlowBean, Text> {

    @Override
    public int getPartition(FlowBean flowBean, Text text, int i) {
        String phone = text.toString().substring(0, 3);
        switch (phone) {
            case "136":
                return 0;
            case "137":
                return 1;
            case "138":
                return 2;
            case "139":
                return 3;
            default:
                return 4;
        }
    }
}
