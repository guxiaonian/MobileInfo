package com.mobile.mobilehardware.simcard;

import android.util.Log;

import com.mobile.mobilehardware.base.BaseBean;
import com.mobile.mobilehardware.base.BaseData;

import org.json.JSONObject;

/**
 * @author 谷闹年
 * @date 2018/8/14
 */
public class SimCardBean extends BaseBean {
    private static final String TAG = SimCardBean.class.getSimpleName();

    /**
     * imei  for sim1
     */
    private String sim1Imei;

    /**
     * imei for sim2
     */
    private String sim2Imei;

    /**
     * imsi for sim1
     */
    private String sim1Imsi;

    /**
     * imsi for sim2
     */
    private String sim2Imsi;

    /**
     * 有流量的卡的卡槽id
     */
    private String simSlotIndex;

    /**
     * meid
     */
    private String meid;

    /**
     * 卡1运营商
     */
    private String sim1ImsiOperator;

    /**
     * 卡2运营商
     */
    private String sim2ImsiOperator;

    /**
     * 卡1是否激活
     */
    private String sim1Ready;

    /**
     * 卡2是否激活
     */
    private String sim2Ready;

    /**
     * 是否有两张卡
     */
    private String isTwoCard;

    /**
     * 是否有卡
     */
    private String isHaveCard;

    /**
     * 流量卡运营商
     */
    private String operator;

    public String getSim1Imei() {
        return sim1Imei;
    }

    public void setSim1Imei(String sim1Imei) {
        this.sim1Imei = sim1Imei;
    }

    public String getSim2Imei() {
        return sim2Imei;
    }

    public void setSim2Imei(String sim2Imei) {
        this.sim2Imei = sim2Imei;
    }

    public String getSim1Imsi() {
        return sim1Imsi;
    }

    public void setSim1Imsi(String sim1Imsi) {
        this.sim1Imsi = sim1Imsi;
    }

    public String getSim2Imsi() {
        return sim2Imsi;
    }

    public void setSim2Imsi(String sim2Imsi) {
        this.sim2Imsi = sim2Imsi;
    }

    public String getSimSlotIndex() {
        return simSlotIndex;
    }

    public void setSimSlotIndex(String simSlotIndex) {
        this.simSlotIndex = simSlotIndex;
    }

    public String getMeid() {
        return meid;
    }

    public void setMeid(String meid) {
        this.meid = meid;
    }

    public String getSim1ImsiOperator() {
        return sim1ImsiOperator;
    }

    public void setSim1ImsiOperator(String sim1ImsiOperator) {
        this.sim1ImsiOperator = sim1ImsiOperator;
    }

    public String getSim2ImsiOperator() {
        return sim2ImsiOperator;
    }

    public void setSim2ImsiOperator(String sim2ImsiOperator) {
        this.sim2ImsiOperator = sim2ImsiOperator;
    }

    public String getSim1Ready() {
        return sim1Ready;
    }

    public void setSim1Ready(String sim1Ready) {
        this.sim1Ready = sim1Ready;
    }

    public String getSim2Ready() {
        return sim2Ready;
    }

    public void setSim2Ready(String sim2Ready) {
        this.sim2Ready = sim2Ready;
    }

    public String getIsTwoCard() {
        return isTwoCard;
    }

    public void setIsTwoCard(String isTwoCard) {
        this.isTwoCard = isTwoCard;
    }

    public String getIsHaveCard() {
        return isHaveCard;
    }

    public void setIsHaveCard(String isHaveCard) {
        this.isHaveCard = isHaveCard;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    protected JSONObject toJSONObject() {
        try {
            jsonObject.put(BaseData.SimCard.SIM1_IMEI, isEmpty(sim1Imei));
            jsonObject.put(BaseData.SimCard.SIM2_IMEI, isEmpty(sim2Imei));
            jsonObject.put(BaseData.SimCard.SIM1_IMSI, isEmpty(sim1Imsi));
            jsonObject.put(BaseData.SimCard.SIM2_IMSI, isEmpty(sim2Imsi));
            jsonObject.put(BaseData.SimCard.SIM_SLOT_INDEX, isEmpty(simSlotIndex));
            jsonObject.put(BaseData.SimCard.MEID, isEmpty(meid));
            jsonObject.put(BaseData.SimCard.SIM1_IMSI_OPERATOR, isEmpty(sim1ImsiOperator));
            jsonObject.put(BaseData.SimCard.SIM2_IMSI_OPERATOR, isEmpty(sim2ImsiOperator));
            jsonObject.put(BaseData.SimCard.SIM1_READY, isEmpty(sim1Ready));
            jsonObject.put(BaseData.SimCard.SIM2_READY, isEmpty(sim2Ready));
            jsonObject.put(BaseData.SimCard.IS_TWO_CARD, isEmpty(isTwoCard));
            jsonObject.put(BaseData.SimCard.IS_HAVE_CARD, isEmpty(isHaveCard));
            jsonObject.put(BaseData.SimCard.OPERATOR, isEmpty(operator));

        } catch (Exception e) {
            Log.e(TAG, e.toString());

        }
        return super.toJSONObject();
    }
}
