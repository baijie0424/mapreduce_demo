package com.baijie.writablecomparable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @ClassName FlowBean
 * @Description 将自定义序列化类放在key时，key需要是可以排序的，因此要自定义比较规则
 *              必须实现此接口WritableComparable不能分别实现Writtable和Comparable因为框架中的多态引用是前者
 * @Author bj
 * @Date 2019/9/2 23:14
 * @Version 1.0
 */
public class FlowBean implements WritableComparable<FlowBean> {
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

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }

    /*
     * @Author bj
     * @Description 降序排列
     * @Date 2019/9/2 23:21
     * @Param [o]
     * @Return int
     */
    @Override
    public int compareTo(FlowBean o) {
        return Long.compare(o.sumFlow, this.sumFlow);
    }
}
