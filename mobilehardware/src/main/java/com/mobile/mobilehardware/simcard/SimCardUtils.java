package com.mobile.mobilehardware.simcard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 谷闹年 on 2018/8/6.
 * 手机卡工具类
 */
@SuppressLint("MissingPermission")
public class SimCardUtils {
    private static final String TAG = "SimCardUtils";
    private static volatile SimCardUtils mGopSimCardUtils;
    private SimCardInfo mSimCardInfo = null;

    private SimCardUtils() {
    }

    public static SimCardUtils instance() {
        if (mGopSimCardUtils == null) {
            mGopSimCardUtils = new SimCardUtils();
        }

        return mGopSimCardUtils;
    }

    public SimCardInfo getmSimCardInfo(Context context) {
        this.getmGopSimCardUtils(context);
        return this.mSimCardInfo;
    }

    private SimCardUtils getmGopSimCardUtils(Context context) {
        this.mSimCardInfo = new SimCardInfo();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            this.collectionSimInfoNewVersion(context);
        } else {
            this.collectionSimInfoOldVersion(context);
        }

        this.getDefaultSub(context);
        this.getDefaultSimState(context);
        return this;

    }

    @SuppressLint({"NewApi"})
    private void getDefaultSub(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            SubscriptionManager subscriptionManager = SubscriptionManager.from(context.getApplicationContext());
            if (subscriptionManager != null) {
                try {
                    SubscriptionInfo subscriptionInfo = this.getSubInfo(subscriptionManager, "getDefaultDataSubscriptionInfo", (Object[]) null);
                    if (subscriptionInfo != null) {
                        this.mSimCardInfo.simSub = subscriptionInfo.getSimSlotIndex();
                    }
                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                }
            }
        } else {
            this.mSimCardInfo.simSub = -1;
        }

    }

    private void getDefaultSimState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                if (networkInfo.getType() == 1 && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    this.mSimCardInfo.simState = 1;
                } else if (networkInfo.getType() == 0 && networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    this.mSimCardInfo.simState = 0;
                } else {
                    this.mSimCardInfo.simState = -1;
                }
            }
        }

    }

    @SuppressLint({"NewApi"})
    private void collectionSimInfoNewVersion(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            List list = this.getSubInfoList(context);
            this.deviceInfo(list, telephonyManager);
            this.deviceInfoTwo(list, telephonyManager);
        }
    }


    private void collectionSimInfoOldVersion(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        this.mSimCardInfo.setSim1SlotIndex(0);
        this.mSimCardInfo.setSim2SlotIndex(1);
        this.mSimCardInfo.setSimSub(-1);

        String str1;
        String str2;
        String str3;
        try {
            str1 = this.simDeviceInfo(telephonyManager, "getDeviceId", 0);
            this.mSimCardInfo.setSim1Imei(str1);
            str2 = this.simDeviceInfo(telephonyManager, "getDeviceId", 1);
            this.mSimCardInfo.setSim2Imei(str2);
        } catch (Exception e1) {
            try {
                str2 = this.simDeviceInfo(telephonyManager, "getDeviceIdGemini", 0);
                this.mSimCardInfo.setSim1Imei(str2);
                str3 = this.simDeviceInfo(telephonyManager, "getDeviceIdGemini", 1);
                this.mSimCardInfo.setSim2Imei(str3);
            } catch (Exception e2) {
                try {
                    str3 = telephonyManager.getDeviceId();
                    this.mSimCardInfo.setSim1Imei(str3);
                } catch (Exception e3) {

                }
            }
        }

        try {
            str1 = this.simDeviceInfo(telephonyManager, "getSubscriberId", 0);
            this.mSimCardInfo.setSim1Imsi(str1);
            str2 = this.simDeviceInfo(telephonyManager, "getSubscriberId", 1);
            this.mSimCardInfo.setSim2Imsi(str2);
        } catch (Exception e1) {
            try {
                str2 = this.simDeviceInfo(telephonyManager, "getSubscriberIdGemini", 0);
                this.mSimCardInfo.setSim1Imsi(str2);
                str3 = this.simDeviceInfo(telephonyManager, "getSubscriberIdGemini", 1);
                this.mSimCardInfo.setSim2Imsi(str3);
            } catch (Exception e2) {
                try {
                    str3 = telephonyManager.getSubscriberId();
                    this.mSimCardInfo.setSim1Imsi(str3);
                } catch (Exception e3) {


                }
            }
        }

        boolean state;
        try {
            boolean simState = this.isSimdevice(telephonyManager, "getSimState", 0);
            this.mSimCardInfo.setSim1State(simState);
            state = this.isSimdevice(telephonyManager, "getSimState", 1);
            this.mSimCardInfo.setSim2State(state);
        } catch (Exception e1) {
            boolean simMini;
            try {
                state = this.isSimdevice(telephonyManager, "getSimStateGemini", 0);
                this.mSimCardInfo.setSim1State(state);
                simMini = this.isSimdevice(telephonyManager, "getSimStateGemini", 1);
                this.mSimCardInfo.setSim2State(simMini);
            } catch (Exception e2) {
                try {
                    simMini = telephonyManager.getSimState() == 5;
                    this.mSimCardInfo.setSim1State(simMini);
                } catch (Exception e3) {


                }
            }
        }

        try {
            str1 = this.simDeviceInfo(telephonyManager, "getSimOperator", 0);
            this.mSimCardInfo.setSim1SimOperator(str1);
            str2 = this.simDeviceInfo(telephonyManager, "getSimOperator", 1);
            this.mSimCardInfo.setSim2SimOperator(str2);
        } catch (Exception e1) {
            try {
                str2 = this.simDeviceInfo(telephonyManager, "getSimOperatorGemini", 0);
                this.mSimCardInfo.setSim1SimOperator(str2);
                str3 = this.simDeviceInfo(telephonyManager, "getSimOperatorGemini", 1);
                this.mSimCardInfo.setSim2SimOperator(str3);
            } catch (Exception e2) {
                try {
                    str3 = telephonyManager.getSimOperator();
                    this.mSimCardInfo.setSim1SimOperator(str3);
                } catch (Exception e3) {

                }
            }
        }

        if (TextUtils.isEmpty(this.mSimCardInfo.getSim1Imsi()) && !TextUtils.isEmpty(this.mSimCardInfo.getSim2Imsi())) {
            this.mSimCardInfo.setSim1Imei(this.mSimCardInfo.getSim2Imei());
            this.mSimCardInfo.setSim2Imei("");
            this.mSimCardInfo.setSim1Imsi(this.mSimCardInfo.getSim2Imsi());
            this.mSimCardInfo.setSim2Imsi("");
            this.mSimCardInfo.setSim1SlotIndex(this.mSimCardInfo.getSim2SlotIndex());
            this.mSimCardInfo.setSim2SlotIndex(-1);
            this.mSimCardInfo.setSim1State(this.mSimCardInfo.isSim2State());
            this.mSimCardInfo.setSim2State(false);
            this.mSimCardInfo.setSim1SimOperator(this.mSimCardInfo.getSim2SimOperator());
            this.mSimCardInfo.setSim2SimOperator("");
            this.mSimCardInfo.setSimSub(this.mSimCardInfo.getSim1SlotIndex());
        } else if (!TextUtils.isEmpty(this.mSimCardInfo.getSim1Imsi()) && TextUtils.isEmpty(this.mSimCardInfo.getSim2Imsi())) {
            this.mSimCardInfo.setSim2Imei("");
            this.mSimCardInfo.setSim2State(false);
            this.mSimCardInfo.setSim2SlotIndex(-1);
            this.mSimCardInfo.setSimSub(this.mSimCardInfo.getSim1SlotIndex());
        } else if (TextUtils.isEmpty(this.mSimCardInfo.getSim1Imsi()) && TextUtils.isEmpty(this.mSimCardInfo.getSim2Imsi())) {
            this.mSimCardInfo.setSim1Imei("");
            this.mSimCardInfo.setSim2Imei("");
            this.mSimCardInfo.setSim1SlotIndex(-1);
            this.mSimCardInfo.setSim2SlotIndex(-1);
            this.mSimCardInfo.setSim1State(false);
            this.mSimCardInfo.setSim2State(false);
            this.mSimCardInfo.setSimSub(-1);
        }

    }

    @SuppressLint({"NewApi"})
    private void deviceInfo(List<SubscriptionInfo> list, TelephonyManager telephonyManager) {
        int subList = list != null ? list.size() : 0;
        SubscriptionInfo subscriptionInfo = null;
        if (subList == 1) {
            subscriptionInfo = (SubscriptionInfo) list.get(0);
        } else {
            if (subList <= 1) {
                return;
            }

            subscriptionInfo = this.getSubsInfo(list, 0);
        }

        this.mSimCardInfo.setSim1SlotIndex(subscriptionInfo.getSimSlotIndex());
        this.mSimCardInfo.setSim1SubscriptionId(subscriptionInfo.getSubscriptionId());

        try {
            this.mSimCardInfo.setSim1Imei(this.simDeviceInfo(telephonyManager, "getDeviceId", subscriptionInfo.getSimSlotIndex()));
        } catch (Exception e1) {
            try {
                this.mSimCardInfo.setSim1Imei(this.simDeviceInfo(telephonyManager, "getDeviceIdGemini", subscriptionInfo.getSimSlotIndex()));
            } catch (Exception e2) {
                try {
                    this.mSimCardInfo.setSim1Imei(telephonyManager.getDeviceId());
                } catch (Exception e3) {

                }
            }
        }

        try {
            this.mSimCardInfo.sim1State = this.isSimdevice(telephonyManager, "getSimState", subscriptionInfo.getSimSlotIndex());
        } catch (Exception e1) {
            try {
                this.mSimCardInfo.sim1State = this.isSimdevice(telephonyManager, "getSimStateGemini", subscriptionInfo.getSimSlotIndex());
            } catch (Exception e2) {
                try {
                    this.mSimCardInfo.setSim1State(telephonyManager.getSimState() == 5);
                } catch (Exception e3) {


                }
            }
        }

        int num = chargeBrand() ? subscriptionInfo.getSimSlotIndex() : subscriptionInfo.getSubscriptionId();

        try {
            this.mSimCardInfo.setSim1Imsi(this.simDeviceInfo(telephonyManager, "getSubscriberId", subscriptionInfo.getSubscriptionId()));
        } catch (Exception e1) {
            try {
                this.mSimCardInfo.setSim1Imsi(this.simDeviceInfo(telephonyManager, "getSubscriberIdGemini", num));
            } catch (Exception e2) {
                try {
                    this.mSimCardInfo.setSim1Imsi(telephonyManager.getSubscriberId());
                } catch (Exception e3) {


                }

            }
        }

        try {
            this.mSimCardInfo.setSim1SimOperator(this.simDeviceInfo(telephonyManager, "getSimOperator", subscriptionInfo.getSubscriptionId()));
        } catch (Exception e1) {
            try {
                this.mSimCardInfo.setSim1SimOperator(this.simDeviceInfo(telephonyManager, "getSimOperatorGemini", num));
            } catch (Exception e2) {
                try {

                } catch (Exception e3) {

                    this.mSimCardInfo.setSim1SimOperator(telephonyManager.getSimOperator());
                }
            }
        }

    }

    @SuppressLint({"NewApi"})
    private void deviceInfoTwo(List<SubscriptionInfo> list, TelephonyManager telephonyManager) {
        int num = list != null ? list.size() : 0;
        if (num > 1) {
            try {
                this.mSimCardInfo.setSim2Imei(this.simDeviceInfo(telephonyManager, "getDeviceId", 1));
            } catch (Exception e1) {
                try {
                    this.mSimCardInfo.setSim2Imei(this.simDeviceInfo(telephonyManager, "getDeviceIdGemini", 1));
                } catch (Exception e2) {
                    Log.i(TAG, e2.toString());
                }
            }

            try {
                this.mSimCardInfo.sim2State = this.isSimdevice(telephonyManager, "getSimState", 1);
            } catch (Exception e3) {
                try {
                    this.mSimCardInfo.sim2State = this.isSimdevice(telephonyManager, "getSimStateGemini", 1);
                } catch (Exception e4) {
                    Log.i(TAG, e4.toString());
                }
            }

            SubscriptionInfo subscriptionInfo = this.getSubsInfo(list, 1);
            this.mSimCardInfo.setSim2SlotIndex(subscriptionInfo.getSimSlotIndex());
            this.mSimCardInfo.setSim2SubscriptionId(subscriptionInfo.getSubscriptionId());
            int brandId = chargeBrand() ? 1 : subscriptionInfo.getSubscriptionId();

            try {
                this.mSimCardInfo.setSim2Imsi(this.simDeviceInfo(telephonyManager, "getSubscriberId", subscriptionInfo.getSubscriptionId()));
            } catch (Exception e5) {
                try {
                    this.mSimCardInfo.setSim2Imsi(this.simDeviceInfo(telephonyManager, "getSubscriberIdGemini", brandId));
                } catch (Exception e6) {
                    Log.i(TAG, e6.toString());
                }
            }

            try {
                this.mSimCardInfo.setSim2SimOperator(this.simDeviceInfo(telephonyManager, "getSimOperator", subscriptionInfo.getSubscriptionId()));
            } catch (Exception e7) {
                try {
                    this.mSimCardInfo.setSim2SimOperator(this.simDeviceInfo(telephonyManager, "getSimOperatorGemini", brandId));
                } catch (Exception e) {
                    Log.i(TAG, e.toString());
                }
            }
        }

    }

    private boolean chargeBrand() {
        return "samsung".equalsIgnoreCase(Build.BRAND);
    }

    @SuppressLint({"NewApi"})
    private SubscriptionInfo getSubsInfo(List<SubscriptionInfo> list, int i) {
        SubscriptionInfo subscriptionInfo = (SubscriptionInfo) list.get(0);
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            SubscriptionInfo info = (SubscriptionInfo) iterator.next();
            if (info.getSimSlotIndex() == i) {
                subscriptionInfo = info;
            }
        }

        return subscriptionInfo;
    }

    @SuppressLint({"NewApi"})
    private List<SubscriptionInfo> getSubInfoList(Context context) {
        SubscriptionManager subscriptionManager = SubscriptionManager.from(context.getApplicationContext());
        List list = null;
        if (subscriptionManager != null) {
            list = subscriptionManager.getActiveSubscriptionInfoList();
        }

        return list;
    }

    private String simDeviceInfo(TelephonyManager telephonyManager, String str, int num) {
        String info = null;
        Object obj = this.subscriptionInfo(telephonyManager, str, new Object[]{Integer.valueOf(num)}, new Class[]{Integer.TYPE});
        if (obj != null) {
            info = obj.toString();
        }

        return info;
    }

    private boolean isSimdevice(TelephonyManager telephonyManager, String str, int num) {
        boolean isDevice = false;
        Object obj = this.subscriptionInfo(telephonyManager, str, new Object[]{Integer.valueOf(num)}, new Class[]{Integer.TYPE});
        if (obj != null) {
            int i = Integer.parseInt(obj.toString());
            if (i == 5) {
                isDevice = true;
            }
        }

        return isDevice;
    }

    private SubscriptionInfo getSubInfo(Object subManager, String str, Object[] objects) {
        return (SubscriptionInfo) this.subscriptionInfo(subManager, str, objects, (Class[]) null);
    }

    private Object subscriptionInfo(Object subManager, String str, Object[] objects, Class[] classes) {
        try {
            Class mClass = Class.forName(subManager.getClass().getName());
            Method method;
            Object obj;
            if (objects != null && classes != null) {
                method = mClass.getMethod(str, classes);
                obj = method.invoke(subManager, objects);
                return obj;
            } else {
                method = mClass.getMethod(str, new Class[0]);
                obj = method.invoke(subManager, new Object[0]);
                return obj;
            }
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        return null;
    }

    public static class SimCardInfo {
        private String sim1Imei = "";
        private String sim2Imei = "";
        private String sim1Imsi = "";
        private String sim2Imsi = "";
        private boolean sim1State = false;
        private boolean sim2State = false;
        private int sim1SlotIndex = -1;
        private int sim2SlotIndex = -1;
        private int sim1SubscriptionId = -1;
        private int sim2SubscriptionId = -1;
        private String sim1SimOperator = "";
        private String sim2SimOperator = "";
        private int simSub = -1;
        private int simState = -1;

        public SimCardInfo() {
        }

        @Override
        public String toString() {
            return "SimCardInfo{" +
                    "sim1Imei='" + sim1Imei + '\'' +
                    ", sim2Imei='" + sim2Imei + '\'' +
                    ", sim1Imsi='" + sim1Imsi + '\'' +
                    ", sim2Imsi='" + sim2Imsi + '\'' +
                    ", sim1State=" + sim1State +
                    ", sim2State=" + sim2State +
                    ", sim1SlotIndex=" + sim1SlotIndex +
                    ", sim2SlotIndex=" + sim2SlotIndex +
                    ", sim1SubscriptionId=" + sim1SubscriptionId +
                    ", sim2SubscriptionId=" + sim2SubscriptionId +
                    ", sim1SimOperator='" + sim1SimOperator + '\'' +
                    ", sim2SimOperator='" + sim2SimOperator + '\'' +
                    ", simSub=" + simSub +
                    ", simState=" + simState +
                    '}';
        }

        public int getSimState() {
            return simState;
        }

        public void setSimState(int simState) {
            this.simState = simState;
        }

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

        public boolean isSim1State() {
            return sim1State;
        }

        public void setSim1State(boolean sim1State) {
            this.sim1State = sim1State;
        }

        public boolean isSim2State() {
            return sim2State;
        }

        public void setSim2State(boolean sim2State) {
            this.sim2State = sim2State;
        }

        public int getSim1SlotIndex() {
            return sim1SlotIndex;
        }

        public void setSim1SlotIndex(int sim1SlotIndex) {
            this.sim1SlotIndex = sim1SlotIndex;
        }

        public int getSim2SlotIndex() {
            return sim2SlotIndex;
        }

        public void setSim2SlotIndex(int sim2SlotIndex) {
            this.sim2SlotIndex = sim2SlotIndex;
        }

        public int getSim1SubscriptionId() {
            return sim1SubscriptionId;
        }

        public void setSim1SubscriptionId(int sim1SubscriptionId) {
            this.sim1SubscriptionId = sim1SubscriptionId;
        }

        public int getSim2SubscriptionId() {
            return sim2SubscriptionId;
        }

        public void setSim2SubscriptionId(int sim2SubscriptionId) {
            this.sim2SubscriptionId = sim2SubscriptionId;
        }

        public String getSim1SimOperator() {
            return sim1SimOperator;
        }

        public void setSim1SimOperator(String sim1SimOperator) {
            this.sim1SimOperator = sim1SimOperator;
        }

        public String getSim2SimOperator() {
            return sim2SimOperator;
        }

        public void setSim2SimOperator(String sim2SimOperator) {
            this.sim2SimOperator = sim2SimOperator;
        }

        public int getSimSub() {
            return simSub;
        }

        public void setSimSub(int simSub) {
            this.simSub = simSub;
        }


        public String getImsi(int num) {
            return this.sim1SlotIndex == num ? this.sim1Imsi : (this.sim2SlotIndex == num ? this.sim2Imsi : "");
        }

        public String getImei(int num) {
            return this.sim1SlotIndex == num ? this.sim1Imei : (this.sim2SlotIndex == num ? this.sim2Imei : "");
        }

        public String getOperator(int num) {
            return this.sim1SlotIndex == num ? this.sim1SimOperator : (this.sim2SlotIndex == num ? this.sim2SimOperator : "");
        }
    }

}
