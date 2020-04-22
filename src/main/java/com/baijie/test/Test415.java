package com.baijie.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName Test415
 * @Description
 * @Author bj
 * @Date 2020/4/15 16:48
 * @Version 1.0
 */
public class Test415 {
    public static void main(String[] args) {
//        String reg = "[World of Warcraft]";
//        Pattern pattern = Pattern.compile(reg);
//        Matcher matcher = pattern.matcher(content);
//        if (matcher.find()) {
//            System.out.println("find WOW");
//        } else {
//            System.out.println("not find");
//        }

        String content = "I love World of Warcraft!";
//        String reg = ".*World of Warcraft*.";
//        boolean matches = Pattern.matches(reg, content);
//        if (matches) {
//            System.out.println("find WOW");
//        } else {
//            System.out.println("not find WOW");
//        }
        String reg = "[World of Warcraft]";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            System.out.println("find WOW");
        } else {
            System.out.println("not find WOW");
        }
    }
}
