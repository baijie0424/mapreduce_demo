package com.baijie.test;

import java.util.ArrayList;

/**
 * @ClassName UserGroup测试类
 * @Description
 * @Author bj
 * @Date 2020/4/22 9:47
 * @Version 1.0
 */
public class UserGroup {
    private String groupName;
    private ArrayList<User> users = new ArrayList<User>();

    public UserGroup() {
    }

    public UserGroup(String groupName, ArrayList<User> users) {
        this.groupName = groupName;
        this.users = users;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroup userGroup = (UserGroup) o;

        if (groupName != null ? !groupName.equals(userGroup.groupName) : userGroup.groupName != null) return false;
        return users != null ? users.equals(userGroup.users) : userGroup.users == null;
    }

    @Override
    public int hashCode() {
        int result = groupName != null ? groupName.hashCode() : 0;
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "groupName='" + groupName + '\'' +
                ", users=" + users +
                '}';
    }
}
