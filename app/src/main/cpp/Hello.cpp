//
// Created by xuchengpu on 2018/11/26.
//

#include "Hello.h"
#include "com_xcp_neihan_MainActivity.h"

JNIEXPORT jstring JNICALL Java_com_xcp_neihan_MainActivity_sayHello
        (JNIEnv *env, jobject){
    return env->NewStringUTF("今天发工资啦");
}