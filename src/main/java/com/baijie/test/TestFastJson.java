package com.baijie.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

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

        //复杂嵌套类
        HashMap<String, ArrayList<User>> map1 = new HashMap<String,ArrayList<User>>();
        map1.put("group1",users);
        ArrayList<User> users1 = new ArrayList<User>();
        users1.add(new User("user3","333"));
        map1.put("group2",users1);
        String map1Json = JSON.toJSONString(map1);
        System.out.println("复杂map对象:" + map1Json);

        ArrayList<Map<String, ArrayList<User>>> list = new ArrayList<Map<String, ArrayList<User>>>();
        list.add(map1);
        HashMap<String, ArrayList<User>> map2 = new HashMap<String,ArrayList<User>>();
        map2.put("",null);
        list.add(map2);
        String listJson = JSON.toJSONString(list);
        System.out.println("复杂list对象:" + listJson);
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

    /**
     * 读取类路径下的配置文件
     * 解析成对象数组并返回
     * @throws IOException
     */
    public List<Query> testParseJsonToBean() throws IOException {
        // 读取类路径下的query.json文件
        ClassLoader cl = this.getClass().getClassLoader();
        InputStream inputStream = cl.getResourceAsStream("test.json");
        String jsontext = IOUtils.toString(inputStream, "utf8");
        // 先将字符jie串转为List数组
        List<Query> queryList = JSON.parseArray(jsontext, Query.class);
        for (Query query : queryList) {
            List<Column> columnList = new ArrayList<Column>();
            List<LinkedHashMap<String, Object>> columns = query.getColumn();
            for (LinkedHashMap<String, Object> linkedMap : columns) {
                //将map转化为java实体类
                Column column = (Column) map2Object(linkedMap, Column.class);
                System.out.println(column.toString());
                columnList.add(column);
            }
            query.setColumnList(columnList); //为columnList属性赋值
        }
        return queryList;
    }

    /**
     * Map转成实体对象
     * @param map map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                String flag = (String) map.get(field.getName());
                if(flag != null){
                    if(flag.equals("false") || flag.equals("true")){
                        field.set(obj, Boolean.parseBoolean(flag));
                    }else{
                        field.set(obj, map.get(field.getName()));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Test
    public void test() throws IOException {
        List<Query> queryList = testParseJsonToBean();
        System.out.println(queryList.toString());
    }
}
