package com.mobile.mobilehardware.battery;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author gunaonian
 */
public class BatteryBean extends BaseBean {
    private static final String TAG = BatteryBean.class.getSimpleName();

    /**
     * 电量
     */
    private String br;

    /**
     * 电池状态
     */
    private String status;

    /**
     * 电池充电状态
     */
    private String plugState;

    /**
     * 电池健康状况
     */
    private String health;

    /**
     * 是否有电池
     */
    private boolean present;

    /**
     * 电池的技术制造
     */
    private String technology;

    /**
     * 电池温度
     */
    private String temperature;

    /**
     * 电池电压
     */
    private String voltage;

    /**
     * 电池总电量
     */
    private String power;

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlugState() {
        return plugState;
    }

    public void setPlugState(String plugState) {
        this.plugState = plugState;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.Battery.BR, isEmpty(br));
            jsonObject.put(BaseData.Battery.STATUS, isEmpty(status));
            jsonObject.put(BaseData.Battery.PLUG_STATE, isEmpty(plugState));
            jsonObject.put(BaseData.Battery.HEALTH, isEmpty(health));
            jsonObject.put(BaseData.Battery.PRESENT, present);
            jsonObject.put(BaseData.Battery.TECHNOLOGY, isEmpty(technology));
            jsonObject.put(BaseData.Battery.TEMPERATURE, isEmpty(temperature));
            jsonObject.put(BaseData.Battery.VOLTAGE, isEmpty(voltage));
            jsonObject.put(BaseData.Battery.POWER, isEmpty(power));
        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        return super.toJSONObject();
    }
}
