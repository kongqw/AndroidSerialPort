package com.kongqw.serialportlibrary;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Kongqw on 2017/11/13.
 * Driver
 */

public class Driver {

    private static final String TAG = Driver.class.getSimpleName();

    private String mDriverName;
    private String mDeviceRoot;

    public Driver(String name, String root) {
        mDriverName = name;
        mDeviceRoot = root;
    }

    public ArrayList<File> getDevices() {
        ArrayList<File> devices = new ArrayList<>();
        File dev = new File("/dev");

        if (!dev.exists()) {
            Log.i(TAG, "getDevices: " + dev.getAbsolutePath() + " 不存在");
            return devices;
        }
        if (!dev.canRead()) {
            Log.i(TAG, "getDevices: " + dev.getAbsolutePath() + " 没有读取权限");
            return devices;
        }

        File[] files = dev.listFiles();

        int i;
        for (i = 0; i < files.length; i++) {
            if (files[i].getAbsolutePath().startsWith(mDeviceRoot)) {
                Log.d(TAG, "Found new device: " + files[i]);
                devices.add(files[i]);
            }
        }
        return devices;
    }

    public String getName() {
        return mDriverName;
    }

}
