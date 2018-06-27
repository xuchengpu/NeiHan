package com.xcp.baselibrary.base;

/**
 * Created by 许成谱 on 2018/6/19 18:28.
 * qq:1550540124
 * 热爱生活每一天！
 * builder设计模式自定义标题栏规范
 */

public interface ITitleBar {
    /**
     * 绑定的自定义view的layout id
     */
    int bindViewId();

    /**
     * 将我们添加的参数设置进view
     */
    void apply();
}
