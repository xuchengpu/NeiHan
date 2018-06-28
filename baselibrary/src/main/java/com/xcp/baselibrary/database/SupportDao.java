package com.xcp.baselibrary.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by 许成谱 on 2018/6/28 18:28.
 * qq:1550540124
 * 热爱生活每一天！
 * 增删改查的具体实现类，由工厂模式调用后具体执行增删改查操作
 */

public class SupportDao<T> implements ISupportDao<T> {

    private SQLiteDatabase mSqLiteDatabase;
    private Class<T> mClazz;

    @Override
    public void init(SQLiteDatabase sqLiteDatabase, Class<T> clazz) {
        this.mSqLiteDatabase = sqLiteDatabase;
        this.mClazz = clazz;
        // 创建表
         /*"create table if not exists Person ("
                + "id integer primary key autoincrement, "
                + "name text, "
                + "age integer, "
                + "flag boolean)";*/

        StringBuffer sb = new StringBuffer();
        sb.append("create table if not exists " + DaoUtil.getTableName(mClazz) + " ("
                + "id integer primary key autoincrement, ");
        Field[] fields = mClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String name = field.getName();
            String type = field.getType().getSimpleName();
            String columnType = DaoUtil.getColumnType(type);
            sb.append(name + columnType + ", ");
        }
        sb.replace(sb.length() - 2, sb.length(), ")");
        Log.e("tag", "表语句--> " + sb.toString());
        //创建数据库
        mSqLiteDatabase.execSQL(sb.toString());
    }

    @Override
    public int insert(T t) {
        //插入语句
//        ContentValues values = new ContentValues();
//        values.put("name", person.getName());
//        values.put("age", person.getAge());
//        mSqLiteDatabase.insert(DaoUtil.getTableName(mClazz), null, values);


        return 0;
    }
}
