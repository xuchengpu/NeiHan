package com.xcp.framelibrary.skin;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许成谱 on 2018/11/9 10:20.
 * qq:1550540124
 * 热爱生活每一天！
 * 解析皮肤类
 */
public class SkinAttrSupport {


    /**
     * 获取所有需要设置皮肤相关的属性
     *
     * @param context
     * @param attrs
     * @return
     */
    public static List<SkinParam> getSkinParams(Context context, AttributeSet attrs) {
        List<SkinParam> skinParams = new ArrayList<>();
        int count = attrs.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            SkinAttr skinAttr = getSkinAttr(attributeName);
            if (skinAttr != null) {
                String resName = getResName(context, attributeValue);
                if (!TextUtils.isEmpty(resName)) {
                    SkinParam skinParam = new SkinParam(resName, skinAttr);
                    skinParams.add(skinParam);
                }
            }
        }
        return skinParams;
    }

    /**
     * 把@开头的value值转化成字符串形式的资源名称
     *
     * @param context
     * @param attributeValue
     * @return
     */
    private static String getResName(Context context, String attributeValue) {
        if (attributeValue.startsWith("@")) {
            String value = attributeValue.substring(1);
            int id = Integer.parseInt(value);
            return context.getResources().getResourceEntryName(id);
        }
        return null;
    }

    /**
     * 获取单个属性类型
     *
     * @param attributeName
     * @return
     */
    private static SkinAttr getSkinAttr(String attributeName) {
        SkinAttr[] values = SkinAttr.values();
        for (SkinAttr value : values) {
            String attrType = value.getAttrType();
            if (attrType.equals(attributeName)) {
                return value;
            }
        }
        return null;
    }
}
