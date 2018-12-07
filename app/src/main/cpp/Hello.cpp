//
// Created by xuchengpu on 2018/12/7.
//

#include <jni.h>
#include "Hello.h"
#include "com_xcp_neihan_MainActivity.h"

JNIEXPORT jstring JNICALL Java_com_xcp_neihan_MainActivity_sayHello
  (JNIEnv *env, jobject obj){
  return env->NewStringUTF("我来自Hello.cpp文件/version2.0");
  }
