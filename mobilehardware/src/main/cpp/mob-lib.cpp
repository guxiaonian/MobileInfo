#include "mob-info.h"

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_bootIdC(JNIEnv *env, jclass type) {
    return env->NewStringUTF(getBootId().c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_entropyAvailC(JNIEnv *env, jclass type) {
    return env->NewStringUTF(getEntropyAvail().c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_poolSizeC(JNIEnv *env, jclass type) {
    return env->NewStringUTF(getPoolSize().c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_readWakeupThresholdC(JNIEnv *env, jclass type) {
    return env->NewStringUTF(getReadWakeupThreshold().c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_writeWakeupThresholdC(JNIEnv *env, jclass type) {
    return env->NewStringUTF(getWriteWakeupThreshold().c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_uuidC(JNIEnv *env, jclass type) {
    return env->NewStringUTF(getUuid().c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_uRandomMinReseedSecsC(JNIEnv *env, jclass type) {
    return env->NewStringUTF(getURandomMinReseedSecs().c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_kennel(JNIEnv *env, jclass type) {
    return env->NewStringUTF(getKennel().c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_getBuildInfo64(JNIEnv *env, jclass type,
                                                                 jstring data) {
    const char *nativeString = env->GetStringUTFChars(data,NULL);
    return env->NewStringUTF(getBuildInfo64(nativeString).c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_mobile_mobilehardware_MobileNativeHelper_getBuildInfo256(JNIEnv *env, jclass type,
                                                                 jstring data) {
    const char *nativeString = env->GetStringUTFChars(data,NULL);
    return env->NewStringUTF(getBuildInfo256(nativeString).c_str());
}
