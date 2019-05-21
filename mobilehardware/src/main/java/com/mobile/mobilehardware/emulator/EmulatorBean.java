package com.mobile.mobilehardware.emulator;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class EmulatorBean extends BaseBean {
    private static final String TAG = EmulatorBean.class.getSimpleName();

    /**
     * build
     */
    private String checkBuild;

    /**
     * 包名修改
     */
    private String checkPkg;

    /**
     * 管道检测
     */
    private String checkPipes;

    /**
     * 驱动程序检测
     */
    private String checkQEmuDriverFile;

    /**
     * 光传感器检测
     */
    private String checkHasLightSensorManager;

    /**
     * cpu架构检测
     */
    private String checkCpuInfo;

    public String getCheckBuild() {
        return checkBuild;
    }

    public void setCheckBuild(String checkBuild) {
        this.checkBuild = checkBuild;
    }

    public String getCheckPkg() {
        return checkPkg;
    }

    public void setCheckPkg(String checkPkg) {
        this.checkPkg = checkPkg;
    }

    public String getCheckPipes() {
        return checkPipes;
    }

    public void setCheckPipes(String checkPipes) {
        this.checkPipes = checkPipes;
    }

    public String getCheckQEmuDriverFile() {
        return checkQEmuDriverFile;
    }

    public void setCheckQEmuDriverFile(String checkQEmuDriverFile) {
        this.checkQEmuDriverFile = checkQEmuDriverFile;
    }

    public String getCheckHasLightSensorManager() {
        return checkHasLightSensorManager;
    }

    public void setCheckHasLightSensorManager(String checkHasLightSensorManager) {
        this.checkHasLightSensorManager = checkHasLightSensorManager;
    }

    public String getCheckCpuInfo() {
        return checkCpuInfo;
    }

    public void setCheckCpuInfo(String checkCpuInfo) {
        this.checkCpuInfo = checkCpuInfo;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Emulator.CHECK_BUILD, isEmpty(checkBuild));
            jsonObject.put(BaseData.Emulator.CHECK_PKG, isEmpty(checkPkg));
            jsonObject.put(BaseData.Emulator.CHECK_PIPES, isEmpty(checkPipes));
            jsonObject.put(BaseData.Emulator.CHECK_QEMU_DRIVER_FILE, isEmpty(checkQEmuDriverFile));
            jsonObject.put(BaseData.Emulator.CHECK_HAS_LIGHT_SENSOR_MANAGER, isEmpty(checkHasLightSensorManager));
            jsonObject.put(BaseData.Emulator.CHECK_CPU_INFO, isEmpty(checkCpuInfo));

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
