package com.kongqw.serialport;

import android.app.Application;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import com.kongqw.serialportlibrary.SerialPort;
import com.kongqw.serialportlibrary.SerialPortFinder;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by kqw on 2016/10/26.
 * InitApplication
 */

public class InitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
