package com.mobile.mobilehardware.sdcard;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.util.Log;

import com.mobile.mobilehardware.MobileHardWareHelper;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author guxiaonian
 */
class SDCardInfo {

    private static final String TAG = SDCardInfo.class.getSimpleName();

    static JSONObject getSdCard() {
        SDCardBean sdCardBean = new SDCardBean();
        try {
            sdCardBean.setSDCardEnable(isSDCardEnableByEnvironment());
            sdCardBean.setsDCardPath(getSDCardPathByEnvironment());
            String extendedMemoryPath=getExtendedMemoryPath(MobileHardWareHelper.getContext());
            sdCardBean.setExtendedMemory(!TextUtils.isEmpty(extendedMemoryPath));
            sdCardBean.setExtendedMemoryPath(extendedMemoryPath);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return sdCardBean.toJSONObject();
    }

    private static String getSDCardPathByEnvironment() {
        if (isSDCardEnableByEnvironment()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }


    private static boolean isSDCardEnableByEnvironment() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private static String getExtendedMemoryPath(Context mContext) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
