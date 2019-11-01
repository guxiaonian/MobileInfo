package com.mobile.mobilehardware.root;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.mobile.mobilehardware.MobileHardWareHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author 谷闹年
 * @date 2018/1/5
 */
public class RootHelper {
    private static final String TAG =RootHelper.class.getSimpleName();

    public static boolean mobileRoot() {
        return existingRWPaths().size() > 0 || existingRootPackages(MobileHardWareHelper.getContext()).size() > 0 || existingDangerousProperties().size() > 0 || existingRootFiles().size() > 0;
    }

    private static final String[] SU_PATHS = {
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"};
    private static final String[] KNOWN_ROOT_APPS_PACKAGES = {
            "com.noshufou.android.su",
            "com.noshufou.android.su.elite",
            "eu.chainfire.supersu",
            "com.koushikdutta.superuser",
            "com.thirdparty.superuser",
            "com.yellowes.su"};
    private static final String[] KNOWN_DANGEROUS_APPS_PACKAGES = {
            "com.koushikdutta.rommanager",
            "com.dimonvideo.luckypatcher",
            "com.chelpus.lackypatch",
            "com.ramdroid.appquarantine"};
    private static final String[] KNOWN_ROOT_CLOAKING_PACKAGES = {
            "com.devadvance.rootcloak",
            "de.robv.android.xposed.installer",
            "com.saurik.substrate",
            "com.devadvance.rootcloakplus",
            "com.zachspong.temprootremovejb",
            "com.amphoras.hidemyroot",
            "com.formyhm.hideroot"};
    private static final String[] PATHS_THAT_SHOULD_NOT_BE_WRITABLE = {
            "/system",
            "/system/bin",
            "/system/sbin",
            "/system/xbin",
            "/vendor/bin",
            "/sbin",
            "/etc"};
    private static final Map<String, String> DANGEROUS_PROPERTIES = new HashMap<>();


    private static List<String> existingRootFiles() {
        List<String> filesFound = new ArrayList<>();
        for (String path : SU_PATHS) {
            if (new File(path).exists()) {
                filesFound.add(path);
            }
        }
        return filesFound;
    }

    /**
     * Checks for packages that are known to indicate root.
     *
     * @return - list of such packages found
     */
    private static List<String> existingRootPackages(Context context) {
        ArrayList<String> packages = new ArrayList<>();
        packages.addAll(Arrays.asList(KNOWN_ROOT_APPS_PACKAGES));
        packages.addAll(Arrays.asList(KNOWN_DANGEROUS_APPS_PACKAGES));
        packages.addAll(Arrays.asList(KNOWN_ROOT_CLOAKING_PACKAGES));

        PackageManager pm = context.getPackageManager();
        List<String> packagesFound = new ArrayList<>();

        for (String packageName : packages) {
            try {
                // Root app detected
                pm.getPackageInfo(packageName, 0);
                packagesFound.add(packageName);
            } catch (PackageManager.NameNotFoundException e) {
                Log.i(TAG, e.toString());
            }
        }
        return packagesFound;
    }

    /**
     * Checks system properties for any dangerous properties that indicate root.
     *
     * @return - list of dangerous properties that indicate root
     */
    private static List<String> existingDangerousProperties() {
        DANGEROUS_PROPERTIES.put("[ro.debuggable]", "[1]");
        DANGEROUS_PROPERTIES.put("[ro.secure]", "[0]");
        String[] lines = propertiesReader();
        List<String> propertiesFound = new ArrayList<>();
        assert lines != null;
        for (String line : lines) {
            for (String key : DANGEROUS_PROPERTIES.keySet()) {
                if (line.contains(key) && line.contains(DANGEROUS_PROPERTIES.get(key))) {
                    propertiesFound.add(line);
                }
            }
        }
        return propertiesFound;
    }

    /**
     * When you're root you can change the write permissions on common system directories.
     * This method checks if any of the paths in PATHS_THAT_SHOULD_NOT_BE_WRITABLE are writable.
     *
     * @return all paths that are writable
     */
    private static List<String> existingRWPaths() {
        String[] lines = mountReader();
        List<String> pathsFound = new ArrayList<>();
        assert lines != null;
        for (String line : lines) {
            // Split lines into parts
            String[] args = line.split(" ");
            if (args.length < 4) {
                // If we don't have enough options per line, skip this and log an error
                continue;
            }
            String mountPoint = args[1];
            String mountOptions = args[3];

            for (String pathToCheck : PATHS_THAT_SHOULD_NOT_BE_WRITABLE) {
                if (mountPoint.equalsIgnoreCase(pathToCheck)) {
                    // Split options out and compare against "rw" to avoid false positives
                    for (String option : mountOptions.split(",")) {
                        if ("rw".equalsIgnoreCase(option)) {
                            pathsFound.add(pathToCheck);
                            break;
                        }
                    }
                }
            }
        }
        return pathsFound;
    }

    /**
     * Used for existingDangerousProperties().
     *
     * @return - list of system properties
     */
    private static String[] propertiesReader() {
        InputStream inputstream = null;
        try {
            inputstream = Runtime.getRuntime().exec("getprop").getInputStream();
        } catch (IOException e) {
            Log.i(TAG, e.toString());
        }
        if (inputstream == null) {
            return null;
        }

        String allProperties = "";
        try {
            allProperties = new Scanner(inputstream).useDelimiter("\\A").next();
        } catch (NoSuchElementException e) {
            Log.i(TAG, e.toString());
        }
        return allProperties.split("\n");
    }

    /**
     * Used for existingRWPaths().
     *
     * @return - list of directories and their properties
     */
    private static String[] mountReader() {
        InputStream inputstream = null;
        try {
            inputstream = Runtime.getRuntime().exec("mount").getInputStream();
        } catch (IOException e) {
            Log.i(TAG, e.toString());
        }
        if (inputstream == null) {
            return null;
        }

        String allPaths = "";
        try {
            allPaths = new Scanner(inputstream).useDelimiter("\\A").next();
        } catch (NoSuchElementException e) {
            Log.i(TAG, e.toString());
        }
        return allPaths.split("\n");
    }
}
