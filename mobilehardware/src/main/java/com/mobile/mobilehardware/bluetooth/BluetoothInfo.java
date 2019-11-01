package com.mobile.mobilehardware.bluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Created by gunaonian on 2018/3/28.
 */

class BluetoothInfo {
    private static final String TAG = BluetoothInfo.class.getSimpleName();

    @SuppressLint("MissingPermission")
    static JSONObject getMobBluetooth(Context context) {
        BluetoothBean bluetoothBean = new BluetoothBean();
        try {
            bluetoothBean.setBluetoothAddress(Settings.Secure.getString(context.getContentResolver(), "bluetooth_address"));
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter == null) {
                return bluetoothBean.toJSONObject();
            }
            bluetoothBean.setEnabled(bluetoothAdapter.isEnabled());
            bluetoothBean.setPhoneName(bluetoothAdapter.getName());
            Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
            List<JSONObject> list = new ArrayList<>();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    BluetoothBean.DeviceBean deviceBean = new BluetoothBean.DeviceBean();
                    deviceBean.setAddress(device.getAddress());
                    deviceBean.setName(device.getName());
                    list.add(deviceBean.toJSONObject());
                }
            }
            bluetoothBean.setDevice(list);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return bluetoothBean.toJSONObject();
    }

}
