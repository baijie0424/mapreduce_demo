package com.baijie.test;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @ClassName Query
 * @Description
 * @Author bj
 * @Date 2020/4/22 15:27
 * @Version 1.0
 */
public class Query {
    private String id;
    private String key;
    private String tableName;
    private String className;
    private List<LinkedHashMap<String, Object>> column;
    private List<Column> columnList;

    public Query() {
    }

    public Query(String id, String key, String tableName, String className, List<LinkedHashMap<String, Object>> column, List<Column> columnList) {

        this.id = id;
        this.key = key;
        this.tableName = tableName;
        this.className = className;
        this.column = column;
        this.columnList = columnList;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<LinkedHashMap<String, Object>> getColumn() {
        return column;
    }

    public void setColumn(List<LinkedHashMap<String, Object>> column) {
        this.column = column;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    @Override
    public String toString() {
        return "Query{" +
                "id='" + id + '\'' +
                ", key='" + key + '\'' +
                ", tableName='" + tableName + '\'' +
                ", className='" + className + '\'' +
                ", column=" + column +
                ", columnList=" + columnList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Query query = (Query) o;

        if (id != null ? !id.equals(query.id) : query.id != null) {
            return false;
        }
        if (key != null ? !key.equals(query.key) : query.key != null) {
            return false;
        }
        if (tableName != null ? !tableName.equals(query.tableName) : query.tableName != null) {
            return false;
        }
        if (className != null ? !className.equals(query.className) : query.className != null) {
            return false;
        }
        if (column != null ? !column.equals(query.column) : query.column != null) {
            return false;
        }
        return columnList != null ? columnList.equals(query.columnList) : query.columnList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (column != null ? column.hashCode() : 0);
        result = 31 * result + (columnList != null ? columnList.hashCode() : 0);
        return result;
    }
}
