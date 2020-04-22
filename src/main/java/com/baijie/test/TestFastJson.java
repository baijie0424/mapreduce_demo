package com.baijie.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestFastJson
 * @Description
 * @Author bj
 * @Date 2020/4/22 9:57
 * @Version 1.0
 */
public class TestFastJson {

    /**
     * @Author bj
     * @Description java对象转json字符串
     * @Date 2020/4/22 9:59
     * @Param []
     * @Return void
     */
    @Test
    public void objectToJson() {
        //简单java类转json字符串
        User user = new User("user", "123");
        String userJson = JSON.toJSONString(user);
        System.out.println("简单java类转json字符串:" + userJson);

        //List<Object>转json字符串
        ArrayList<User> users = new ArrayList<User>();
        User user1 = new User("user1", "111");
        User user2 = new User("user2", "222");
        users.add(user1);
        users.add(user2);
        String usersJson = JSON.toJSONString(users);
        System.out.println("List<Object>转json字符串:" + usersJson);

        //Map<Object,Object>转json字符串
        HashMap<String, User> userMap = new HashMap<String,User>();
        userMap.put("京东",user1);
        userMap.put("淘宝",user2);
        String mapJson = JSON.toJSONString(userMap);
        System.out.println("Map<Object,Object>转json字符串" + mapJson);

        //复杂java类转json字符串
        UserGroup group = new UserGroup("gourp", users);
        String groupJson = JSON.toJSONString(group);
        System.out.println("复杂java类转json字符串" + groupJson);
    }

    /**
     * @Author bj
     * @Description json字符串转java对象
     * @Date 2020/4/22 14:20
     * @Param []
     * @Return void
     */
    @Test
    public void jsonToObject() {
        //json字符串转简单java对象
        //{"password":"123","username":"user"}
        String userJson = "{'password':'123','username':'user'}";
        User user = JSON.parseObject(userJson, User.class);
        System.out.println("json字符串转简单java对象:" + user.toString());

        //json字符串转List<Object>对象
        //[{"password":"111","username":"user1"},{"password":"222","username":"user2"}]
        String usersJson = "[{'password':'111','username':'user1'},{'password':'222','username':'user2'}]";
        List<User> users = JSON.parseArray(usersJson, User.class);
//        System.out.println("json字符串转List<Object>对象:" + users.toString());
        for (User user1 : users) {
            System.out.println(user1.toString());
        }

        //json字符串转Map<Object,Object>对象
        //{"淘宝":{"password":"222","username":"user2"},"京东":{"password":"111","username":"user1"}}
        String mapJson = "{'淘宝':{'password':'222','username':'user2'},'京东':{'password':'111','username':'user1'}}";
        Map map = JSON.parseObject(mapJson, Map.class);
//        System.out.println("json字符串转Map<Object,Object>对象:" + users.toString());
        for (Object key : map.keySet()) {
            System.out.println(key + map.get(key).toString());
        }

        //json字符串转复杂java对象
        //{"groupName":"gourp","users":[{"password":"111","username":"user1"},{"password":"222","username":"user2"}]}
        String groupJson = "{'groupName':'gourp','users':[{'password':'111','username':'user1'},{'password':'222','username':'user2'}]}";
        UserGroup group = JSON.parseObject(groupJson, UserGroup.class);
        System.out.println("json字符串转复杂java对象:" + group.toString());

    }
}
