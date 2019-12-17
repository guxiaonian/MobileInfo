package com.mobile.mobileinfo.oaid;

import android.content.Context;

import com.bun.miitmdid.core.ErrorCode;
import com.bun.miitmdid.core.IIdentifierListener;
import com.bun.miitmdid.core.MdidSdkHelper;
import com.bun.miitmdid.supplier.IdSupplier;

import org.json.JSONObject;

public class MiIdHelper implements IIdentifierListener {
    private boolean isSupport;
    private String oaid, vaid, aaid;


    public JSONObject getDeviceIds(Context cxt) {
        long startTime = System.currentTimeMillis();
        int code = CallFromReflect(cxt);
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("description", descriptionCode(code));
            jsonObject.put("code", code);
            jsonObject.put("time", time);
            jsonObject.put("isSupport", isSupport);
            jsonObject.put("oaid", oaid);
            jsonObject.put("vaid", vaid);
            jsonObject.put("aaid", aaid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private int CallFromReflect(Context cxt) {
        return MdidSdkHelper.InitSdk(cxt, true, this);
    }


    @Override
    public void OnSupport(boolean isSupport, IdSupplier _supplier) {
        this.isSupport = isSupport;
        if (_supplier != null) {
            this.oaid = _supplier.getOAID();
            this.vaid = _supplier.getVAID();
            this.aaid = _supplier.getAAID();
            _supplier.shutDown();
        }
    }

    private String descriptionCode(int code) {
        switch (code) {
            case ErrorCode.INIT_ERROR_DEVICE_NOSUPPORT:
                return "DEVICE_NOSUPPORT";
            case ErrorCode.INIT_ERROR_LOAD_CONFIGFILE:
                return "LOAD_CONFIGFILE";
            case ErrorCode.INIT_ERROR_MANUFACTURER_NOSUPPORT:
                return "MANUFACTURER_NOSUPPORT";
            case ErrorCode.INIT_ERROR_RESULT_DELAY:
                return "RESULT_DELAY";
            case ErrorCode.INIT_HELPER_CALL_ERROR:
                return "HELPER_CALL_ERROR";
            default:
                return "SUCCESS";
        }
    }
}
