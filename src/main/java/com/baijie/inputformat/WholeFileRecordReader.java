package com.baijie.inputformat;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @ClassName WholeFileRecordReader
 * @Description 自定义RecordReader，处理一个文件，并把这个文件读成一个KV值
 * @Author bj
 * @Date 2019/8/31 13:57
 * @Version 1.0
 */
public class WholeFileRecordReader extends RecordReader<Text,BytesWritable> {

    private boolean notRead = true;
    private Text key = new Text();
    private BytesWritable value = new BytesWritable();
    private FSDataInputStream inputStream;
    private FileSplit fs;

    /*
     * @Author bj
     * @Description 初始化方法在开始的时候调用一次
     * @Date 2019/8/31 13:58
     * @Param [inputSplit, taskAttemptContext]
     * @Return void
     */
    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        //转换切片类型到文件切片
        fs = (FileSplit) inputSplit;
        //通过切片获取路径
        Path path = fs.getPath();
        //通过路径获取文件系统
        FileSystem fileSystem = path.getFileSystem(taskAttemptContext.getConfiguration());
        //开流
        inputStream = fileSystem.open(path);
    }

    /*
     * @Author bj
     * @Description 读取下一组KV值
     * @Date 2019/8/31 14:01
     * @Param []
     * @Return boolean 读到了返回true，读完了返回false
     */
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (notRead) {
            //读文件的具体过程
            //读Key
            key.set(fs.getPath().toString());
            //读Value
            byte[] buf = new byte[(int) fs.getLength()];
            inputStream.read(buf);
            value.set(buf, 0, buf.length);
            notRead = false;
            return true;
        } else {
            return false;
        }
    }
    
    /*
     * @Author bj
     * @Description 获取到当前读到的Key
     * @Date 2019/8/31 14:02
     * @Param []
     * @Return org.apache.hadoop.io.Text 当前Key
     */
    @Override
    public Text getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    /*
     * @Author bj
     * @Description 获取到当前值
     * @Date 2019/8/31 14:05
     * @Param []
     * @Return org.apache.hadoop.io.ByteWritable 当前值
     */
    @Override
    public BytesWritable getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    /*
     * @Author bj
     * @Description 当前数据读取的进度
     * @Date 2019/8/31 14:06
     * @Param []
     * @Return float
     */
    @Override
    public float getProgress() throws IOException, InterruptedException {
        return  notRead ? 0 : 1;
    }

    /*
     * @Author bj
     * @Description 释放资源
     * @Date 2019/8/31 14:07
     * @Param []
     * @Return void
     */
    @Override
    public void close() throws IOException {
        IOUtils.closeStream(inputStream);
    }
}
