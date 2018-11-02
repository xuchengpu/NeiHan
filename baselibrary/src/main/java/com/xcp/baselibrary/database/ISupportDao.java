package com.xcp.baselibrary.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by 许成谱 on 2018/6/28 17:54.
 * qq:1550540124
 * 热爱生活每一天！
 * 增删改查接口规范类
 */

public interface ISupportDao<T> {

    /**
     * 初始化，接收从工厂传递过来的数据库及资源类
     * @param database
     * @param clazz
     */
    void init(SQLiteDatabase database,Class<T> clazz);
    //增
    long insert(T t);
    //批量增
    void insert(List<T> t);

    //删
    int delete(String whereCause,String[] whereArgs);
    //改
    int update(T obj, String whereCause, String... whereArgs);

    //个性化拼接查
    QuerySupport<T> query();
}
