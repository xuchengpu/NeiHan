package com.xcp.baselibrary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;

/**
 * Created by 许成谱 on 2018/6/28 18:36.
 * qq:1550540124
 * 热爱生活每一天！
 * 使用工厂模式得到ISupportDao对象，方便后期扩展。
 * 系统提供的SQLiteOpenHelper太麻烦，跟其他的第三方一样，生成的数据库文件在data/data/packagename/database目录下，当软件卸载时也随之卸载了
 * 此处为方便应用卸载时，数据库也随之卸载，清楚垃圾数据，将数据库放在应用内的path==/storage/emulated/0/Android/data/com.xcp.neihan/files
 * 使用姿势：1、先在application中初始化
 * 2、在需要的地方调用
 */

public class SupportDaoFactory {
    //1、使用单例模式，是工程中每一处得到的都是同一个对象
    private static SupportDaoFactory instance;
    private Context mContext;//上下文，用户获取应用在存储卡的目录
    private SQLiteDatabase sqLiteDatabase;

    private SupportDaoFactory() {
    }

    public static SupportDaoFactory getInstance() {
        if (instance == null) {
            synchronized (SupportDaoFactory.class) {
                if (instance == null) {
                    instance = new SupportDaoFactory();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化的方法，使用前就调用
     *
     * @param context
     */
    public void init(Context context) {
        this.mContext = context;
        //2、初始化一个数据库出来
        //2.1 new 出一个在外部存储卡的文件，用来承载数据库
        //要是使用外部存储卡的话需要做是否有外部存储卡判断，做6.0权限判断.此处为避免权限检查，使用应用内存储目录
        File dbRoot = new File(mContext.getExternalFilesDir(null).getAbsolutePath() + File.separator + "database");
        if (!dbRoot.exists()) {
            dbRoot.mkdirs();
        }
        File file = new File(dbRoot, "nhdz.db");
        //2.2 用系统提供的方法生成一个数据库
        sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
    }

    public <T> ISupportDao getDao(Class<T> clazz) {
        ISupportDao<T> dao = new SupportDao<T>();
        dao.init(sqLiteDatabase, clazz);
        return dao;
    }

}
