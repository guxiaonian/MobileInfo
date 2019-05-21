package com.mobile.mobilehardware.dns;

import android.util.Log;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author gunaonian
 * @date 2018/5/10
 */

public class DnsHelper {

    private static final String TAG = DnsHelper.class.getSimpleName();

    public static String mobDNS(String host) {
        List<String> list = new ArrayList<>();
        InetAddress[] addresses = new InetAddress[0];
        try {
            addresses = InetAddress.getAllByName(host);
        } catch (Exception e) {
            Log.i(TAG, e.toString());
        }
        for (InetAddress inetAddress : addresses) {
            list.add(inetAddress.getHostAddress());
        }
        return list.toString();

    }
}
