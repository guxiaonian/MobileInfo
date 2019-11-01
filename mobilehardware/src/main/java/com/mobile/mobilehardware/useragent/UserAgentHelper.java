package com.mobile.mobilehardware.useragent;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebSettings;

import com.mobile.mobilehardware.MobileHardWareHelper;
import com.mobile.mobilehardware.base.BaseData;

import java.lang.reflect.Method;

/**
 * @author gunaonian
 */
public class UserAgentHelper {

    public static String getDefaultUserAgent() {
        String userAgent = null;
        try {
            Method localMethod = WebSettings.class.getDeclaredMethod("getDefaultUserAgent", new Class[]{Context.class});
            if (localMethod != null) {
                userAgent = (String) localMethod.invoke(WebSettings.class, new Object[]{MobileHardWareHelper.getContext()});
            }
            if (userAgent == null) {
                userAgent = System.getProperty("http.agent");
            }
        } catch (Exception localException) {

        }
        return TextUtils.isEmpty(userAgent) ? BaseData.UNKNOWN_PARAM : userAgent;
    }
}
