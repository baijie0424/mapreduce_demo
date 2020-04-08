package com.baijie.flow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ClassName FlowBean
 * @Description hadoop自定义序列化类   实现Writable接口
 *              如果需要将自定义的bean放在key中传输，则还需要实现Comparable接口，因为MapReduce框中的Shuffle过程要求对key必须能排序
 * @Author bj
 * @Date 2019/9/2 1:03
 * @Version 1.0
 */
public class FlowBean implements Writable {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public FlowBean() {
    }

    public void set(long upFlow, long downFlow) {
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    //注意反序列化的顺序要和序列化的顺序完全一致
    /*
     * @Author bj
     * @Description 序列化方法   框架给我们提供的数据出口由此将数据输出给框架
     * @Date 2019/9/2 1:16
     * @Param [dataOutput]
     * @Return void
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    /*
     * @Author bj
     * @Description 反序列化方法  框架提供的数据来源由此从框架中读取数据
     * @Date 2019/9/2 1:16
     * @Param [dataInput]
     * @Return void
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }
}
