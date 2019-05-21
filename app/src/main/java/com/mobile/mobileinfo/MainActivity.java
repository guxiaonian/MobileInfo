package com.mobile.mobileinfo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mobile.mobilehardware.MobileHardWareHelper;
import com.mobile.mobilehardware.block.CpuInternals;
import com.mobile.mobilehardware.camera.CameraHelper;
import com.mobile.mobilehardware.emulator.EmulatorHelper;
import com.mobile.mobilehardware.memory.MemoryHelper;
import com.mobile.mobilehardware.network.NetWorkHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
//        CpuInternals.getInstance().setCpuRateTime(1000L);
//        CpuInternals.getInstance().setMaxEntryCount(10);
//        CpuInternals.getInstance().getCpuSampler().start();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.i("MOB", MobileHardWareHelper.mobileInfo(getApplicationContext()).toString());
                Log.i("SSSSSSSSSSSSSSSSSSSSSS", NetWorkHelper.mobGetMobNetWork(getApplicationContext()).toString());
//                Log.i("MOB_CPU", CpuInternals.getInstance().getCpuSampler().getCpuList().toString());
//                CpuInternals.getInstance().getCpuSampler().stop();
//                Log.i("MOB_AUDIO",MobileHardWareHelper.audioInfo(getApplicationContext()).toString());
            }
        });
    }


    /**
     * 动态权限申请
     */
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = null;
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permissions = new ArrayList<>();
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (permissions == null) {
                    permissions = new ArrayList<>();
                }
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (permissions == null) {
                    permissions = new ArrayList<>();
                }
                permissions.add(Manifest.permission.CAMERA);
            }
            if (permissions != null) {
                String[] permissionArray = new String[permissions.size()];
                permissions.toArray(permissionArray);
                requestPermissions(permissionArray, 0);
            }
        }
    }
}
