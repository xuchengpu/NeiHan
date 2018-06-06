package com.xcp.baselibrary.fix;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;

import static com.xcp.baselibrary.utils.FileUtil.combineArray;
import static com.xcp.baselibrary.utils.FileUtil.copyFile;

/**
 * Created by 许成谱 on 2018/6/5 13:08.
 * qq:1550540124
 * 热爱生活每一天！
 * 热修复管理类
 * 使用方式：直接调用fixBug方法
 */

public class FixManager {
    private final Context mContext;
    private final File odexDir;
    private  Object sysDexElements;
    private  ClassLoader sysClassLoader;

    public FixManager(Context context) throws Exception{
        this.mContext=context;
        odexDir = context.getDir("odex", Context.MODE_PRIVATE);
        //1.找到系统的已经运行的 dexElement
        sysClassLoader = mContext.getClassLoader();
        sysDexElements = getDexElementsByClassLoader(sysClassLoader);
    }

    /**
     * 修复bug的具体执行方法
     * @param fixDexPath
     */
    public void fixBug(String fixDexPath) throws Exception{
        //移动补丁到系统能够访问的dex目录下
        File src=new File(fixDexPath);
        if(!src.exists()) {
            Log.e("TAG", "热修复源文件不存在");
            return;
        }
        final File dest=new File(odexDir,src.getName());
        if(dest.exists()) {
            Log.e("TAG", "热修复文件已经存在");
            return;
        }
        copyFile(src,dest);
        executFix(dest);
    }

    /**
     * 执行修复动作
     * @param dest
     * @throws Exception
     */
    private void executFix(File dest) throws  Exception{
        //2、获取下载好的补丁的 dexElement
        //2.1自己先new一个classloader
        //解压路径
        File decompressDir=new File(odexDir,"Decompress");
        if(!decompressDir.exists()) {
            decompressDir.mkdir();
        }
        BaseDexClassLoader fixClassLoader = new BaseDexClassLoader(dest.getAbsolutePath()// dex路径  必须要在应用目录下的odex文件中
                , decompressDir// 解压路径
                , null//.so文件位置
                , sysClassLoader);// 父ClassLoader
        //2.2通过自己new 的classloader来获取我们的补丁dexElement数组
        Object fixDexElements = getDexElementsByClassLoader(fixClassLoader);

        //3、把补丁的dexElement 插到 已经运行的 dexElement 的最前面  合并
        // 3.1 合并完成
        sysDexElements = combineArray(fixDexElements, sysDexElements);
        // 3.2 把合并的数组注入到原来的类中 app
        injectDexElements(sysClassLoader,sysDexElements);
    }

    private void injectDexElements(ClassLoader classLoader, Object sysDexElements) throws Exception{
        //1、通过类加载器拿到BaseDexClassLoader的成员变量 pathListField
        Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(classLoader);

        //2、通过pathList实例拿到DexPathList中的dexElements
        Field dexElements = pathList.getClass().getDeclaredField("dexElements");
        dexElements.setAccessible(true);
        dexElements.set(pathList,sysDexElements);
    }

    /**
     * 通过类加载器拿到dexElements数组
     * @param classLoader
     */
    private Object getDexElementsByClassLoader(ClassLoader classLoader) throws  Exception{
        //1、通过类加载器拿到BaseDexClassLoader的成员变量 pathListField
        Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
        pathListField.setAccessible(true);
        Object pathList = pathListField.get(classLoader);

        //2、通过pathList实例拿到DexPathList中的dexElements
        Field dexElements = pathList.getClass().getDeclaredField("dexElements");
        dexElements.setAccessible(true);
        return dexElements.get(pathList);

    }

    /**
     * 修复以前所有的.dex补丁文件
     * @throws Exception
     */
    public void loadFixDexs() throws  Exception{
        File[] files = odexDir.listFiles();
        for (File file : files) {
            if(file.getName().endsWith(".dex")) {//odexDir目录下以.dex结尾的所有文件
                executFix(file);
            }
        }
    }
}
