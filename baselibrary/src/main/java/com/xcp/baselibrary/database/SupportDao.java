package com.xcp.baselibrary.database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.ArrayMap;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by 许成谱 on 2018/6/28 18:28.
 * qq:1550540124
 * 热爱生活每一天！
 * 增删改查的具体实现类，由工厂模式调用后具体执行增删改查操作
 */

public class SupportDao<T> implements ISupportDao<T> {

    private SQLiteDatabase mSqLiteDatabase;
    private Class<T> mClazz;
    private Object[] puts = new Object[2];
    private ArrayMap<String, Method> methodArrayMap = new ArrayMap<>();//缓存反射的方法，提高性能
    private QuerySupport querySupport;

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
//        Log.e("tag", "表语句--> " + sb.toString());
        //创建数据库
        mSqLiteDatabase.execSQL(sb.toString());
    }

    @Override
    public long insert(T t) {
        //插入语句
        ContentValues values = getContentValuesByObj(t);
        return mSqLiteDatabase.insert(DaoUtil.getTableName(mClazz), null, values);
    }

    @Override
    public void insert(List<T> t) {
        //批量插入，开启事务，为大批量插入数据做准备
        mSqLiteDatabase.beginTransaction();
        for (T t1 : t) {
            insert(t1);
        }
        mSqLiteDatabase.setTransactionSuccessful();
        mSqLiteDatabase.endTransaction();
    }

    @Override
    public int delete(String whereCause, String[] whereArgs) {
        return mSqLiteDatabase.delete(DaoUtil.getTableName(mClazz), whereCause, whereArgs);
    }

    @Override
    public int update(T obj, String whereCause, String... whereArgs) {
        ContentValues values = getContentValuesByObj(obj);
        return mSqLiteDatabase.update(DaoUtil.getTableName(mClazz),
                values, whereCause, whereArgs);
    }

    @Override
    public QuerySupport<T> query() {
        if(querySupport==null) {
            querySupport=new QuerySupport(mSqLiteDatabase,mClazz);
        }
        return querySupport;
    }

    private String getColumnMethodName(Class<?> fieldType) {
        String typeName;
        if (fieldType.isPrimitive()) {
            typeName = DaoUtil.capitalize(fieldType.getName());
        } else {
            typeName = fieldType.getSimpleName();
        }
        String methodName = "get" + typeName;
        if ("getBoolean".equals(methodName)) {
            methodName = "getInt";
        } else if ("getChar".equals(methodName) || "getCharacter".equals(methodName)) {
            methodName = "getString";
        } else if ("getDate".equals(methodName)) {
            methodName = "getLong";
        } else if ("getInteger".equals(methodName)) {
            methodName = "getInt";
        }
        return methodName;
    }

    private ContentValues getContentValuesByObj(T obj) {
        ContentValues values = new ContentValues();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                Object value = field.get(obj);
                //用一个数组存储数据写入放射方法，能提高性能
                puts[0] = name;
                puts[1] = value;
                //先从缓存中读取
                Method put = methodArrayMap.get(field.getType().getName());
                if (put == null) {
                    //使用反射是因为我们不知道要存储的 数据类型，只能通过反射的方式往put里写入数据
                    put = ContentValues.class.getDeclaredMethod("put", String.class, value.getClass());
                    methodArrayMap.put(name, put);
                }
                put.invoke(values, puts);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("TAG", " " + e.getMessage());
            } finally {
                //置空
                puts[0] = null;
                puts[1] = null;
            }
        }
        return values;
    }
}
