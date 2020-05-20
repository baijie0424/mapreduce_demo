package com.baijie.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName Test516
 * @Description
 * @Author bj
 * @Date 2020/5/16 14:36
 * @Version 1.0
 */
public class Test516 {

    @Test
    public  void classLoadPath() {
        String path = Test516.class.getResource("Test516.class").toString();
        System.out.println(path);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss:SSSSSS");
        String dateStr = sdf.format(date);
        System.out.println(dateStr);
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(uuid);
    }
}
