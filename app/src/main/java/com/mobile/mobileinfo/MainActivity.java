package com.mobile.mobileinfo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
