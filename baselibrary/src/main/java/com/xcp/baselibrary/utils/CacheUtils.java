package com.xcp.baselibrary.utils;

import com.xcp.baselibrary.database.ISupportDao;
import com.xcp.baselibrary.database.SupportDaoFactory;
import com.xcp.baselibrary.http.CacheBean;

import java.util.List;

/**
 * Created by 许成谱 on 2018/11/2 16:42.
 * qq:1550540124
 * 热爱生活每一天！
 * 这个地方可以根据开闭原则用面向接口编程的思想，去实现一个接口，提高扩展性
 */
public class CacheUtils {
    public static String getCache(String mUrlKey) {
        //此逻辑适用于新闻类的缓存，注意并不会减少联网次数及流量消耗，仅为了提高弱网情况下的显示速度。如想通过缓存减少流量消耗，可根据实际业务相应调整缓存策略
        //先从缓存中查询是否有数据，有就先显示出来
        ISupportDao<CacheBean> dao = SupportDaoFactory.getInstance().getDao(CacheBean.class);
        List<CacheBean> datas = dao.query().selection("mUrlKey = ?").selectionArgs(MD5Encoder.encode(mUrlKey)).query();
        if (datas != null && datas.size() > 0) {
            String cacheResonse = datas.get(0).getmJsonResponse();
            return cacheResonse;
        }
        return null;
    }

    public static long updataCache(CacheBean cacheBean) {
        ISupportDao<CacheBean> dao = SupportDaoFactory.getInstance().getDao(CacheBean.class);
        dao.delete("mUrlKey = ?", new String[]{MD5Encoder.encode(MD5Encoder.encode(cacheBean.getmUrlKey()))});
        return dao.insert(cacheBean);
    }
}
