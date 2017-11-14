package com.kongqw.serialportlibrary.listener;

import java.io.File;

/**
 * Created by Kongqw on 2017/11/14.
 * 打开串口监听
 */

public interface OnOpenSerialPortListener {

    void onSuccess(File device);

    void onFail(File device, Status status);

    enum Status {
        NO_READ_WRITE_PERMISSION,
        OPEN_FAIL
    }
}
