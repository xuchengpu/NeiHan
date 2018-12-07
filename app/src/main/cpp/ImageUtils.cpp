//
// Created by xuchengpu on 2018/12/7.
//

#include "ImageUtils.h"
#include "com_xcp_neihan_utils_ImageUtil.h"

JNIEXPORT jint JNICALL Java_com_xcp_neihan_utils_ImageUtil_compressBitmap
        (JNIEnv *env, jclass jclass1, jobject jobject1, jint jint1, jstring jstring1){

}
JNIEXPORT jstring JNICALL Java_com_xcp_neihan_utils_ImageUtil_getMessage
        (JNIEnv *jniEnv, jclass jclass1){
    return jniEnv->NewStringUTF("我来自ImageUtils.cpp文件");
}