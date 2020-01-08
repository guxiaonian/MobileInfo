package com.mobile.mobilehardware.cpu;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class CpuBean extends BaseBean {
    private static final String TAG = CpuBean.class.getSimpleName();

    /**
     * CPU名字
     */
    private String cpuName;
    private String cpuPart;
    private String bogomips;
    private String features;
    private String cpuImplementer;
    private String cpuArchitecture;
    private String cpuVariant;

    public String getBogomips() {
        return bogomips;
    }

    public void setBogomips(String bogomips) {
        this.bogomips = bogomips;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getCpuImplementer() {
        return cpuImplementer;
    }

    public void setCpuImplementer(String cpuImplementer) {
        this.cpuImplementer = cpuImplementer;
    }

    public String getCpuArchitecture() {
        return cpuArchitecture;
    }

    public void setCpuArchitecture(String cpuArchitecture) {
        this.cpuArchitecture = cpuArchitecture;
    }

    public String getCpuVariant() {
        return cpuVariant;
    }

    public void setCpuVariant(String cpuVariant) {
        this.cpuVariant = cpuVariant;
    }

    /**
     * CPU频率
     */
    private String cpuFreq;

    /**
     * CPU最大频率
     */
    private String cpuMaxFreq;

    /**
     * CPU最小频率
     */
    private String cpuMinFreq;

    /**
     * CPU硬件名
     */
    private String cpuHardware;

    /**
     * CPU核数
     */
    private int cpuCores;

    /**
     * CPU温度
     */
    private String cpuTemp;

    /**
     * CPU架构
     */
    private String cpuAbi;

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getCpuFreq() {
        return cpuFreq;
    }

    public void setCpuFreq(String cpuFreq) {
        this.cpuFreq = cpuFreq;
    }

    public String getCpuMaxFreq() {
        return cpuMaxFreq;
    }

    public void setCpuMaxFreq(String cpuMaxFreq) {
        this.cpuMaxFreq = cpuMaxFreq;
    }

    public String getCpuMinFreq() {
        return cpuMinFreq;
    }

    public void setCpuMinFreq(String cpuMinFreq) {
        this.cpuMinFreq = cpuMinFreq;
    }

    public String getCpuHardware() {
        return cpuHardware;
    }

    public void setCpuHardware(String cpuHardware) {
        this.cpuHardware = cpuHardware;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    public String getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(String cpuTemp) {
        this.cpuTemp = cpuTemp;
    }

    public String getCpuAbi() {
        return cpuAbi;
    }

    public void setCpuAbi(String cpuAbi) {
        this.cpuAbi = cpuAbi;
    }

    public String getCpuPart() {
        return cpuPart;
    }

    public void setCpuPart(String cpuPart) {
        this.cpuPart = cpuPart;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Cpu.CPU_NAME, isEmpty(cpuName));
            jsonObject.put(BaseData.Cpu.CPU_PART, isEmpty(cpuPart));
            jsonObject.put(BaseData.Cpu.BOGO_MIPS, isEmpty(bogomips));
            jsonObject.put(BaseData.Cpu.FEATURES, isEmpty(features));
            jsonObject.put(BaseData.Cpu.CPU_IMPLEMENTER, isEmpty(cpuImplementer));
            jsonObject.put(BaseData.Cpu.CPU_ARCHITECTURE, isEmpty(cpuArchitecture));
            jsonObject.put(BaseData.Cpu.CPU_VARIANT, isEmpty(cpuVariant));
            jsonObject.put(BaseData.Cpu.CPU_FREQ, isEmpty(cpuFreq));
            jsonObject.put(BaseData.Cpu.CPU_MAX_FREQ, isEmpty(cpuMaxFreq));
            jsonObject.put(BaseData.Cpu.CPU_MIN_FREQ, isEmpty(cpuMinFreq));
            jsonObject.put(BaseData.Cpu.CPU_HARDWARE, isEmpty(cpuHardware));
            jsonObject.put(BaseData.Cpu.CPU_CORES, cpuCores);
            jsonObject.put(BaseData.Cpu.CPU_TEMP, isEmpty(cpuTemp));
            jsonObject.put(BaseData.Cpu.CPU_ABI, isEmpty(cpuAbi));
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return super.toJSONObject();
    }
}
