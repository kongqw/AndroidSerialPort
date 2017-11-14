package com.kongqw.serialportlibrary;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

public class SerialPort {

    static {
        System.loadLibrary("SerialPort");
    }


    private static final String TAG = SerialPort.class.getSimpleName();

    /*
     * Do not remove or rename the field mFd: it is used by native method close();
     * root base root
     */
//    protected FileDescriptor mFd;
//    protected FileInputStream mFileInputStream;
//    protected FileOutputStream mFileOutputStream;

//    public SerialPort(File device, int baudrate, int flags) throws SecurityException, IOException {
//
//		/* Check access permission */
//        if (!device.canRead() || !device.canWrite()) {
//            try {
//                /* Missing read/write permission, trying to chmod the file */
//                Process su;
//                su = Runtime.getRuntime().exec("/system/bin/su");
//                String cmd = "chmod 666 " + device.getAbsolutePath() + "\n" + "exit\n";
//                su.getOutputStream().write(cmd.getBytes());
//                if ((su.waitFor() != 0) || !device.canRead()
//                        || !device.canWrite()) {
//                    throw new SecurityException();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new SecurityException();
//            }
//        }
//
//        mFd = open(device.getAbsolutePath(), baudrate, flags);
//        if (mFd == null) {
//            Log.e(TAG, "native open returns null");
//            throw new IOException();
//        }
//        mFileInputStream = new FileInputStream(mFd);
//        mFileOutputStream = new FileOutputStream(mFd);
//    }

    // Getters and setters
//    public InputStream getInputStream() {
//        return mFileInputStream;
//    }
//
//    public OutputStream getOutputStream() {
//        return mFileOutputStream;
//    }

    /**
     * 文件设置最高权限 777 可读 可写 可执行
     *
     * @param file 文件
     * @return 权限修改是否成功
     */
    boolean chmod777(File file) {
        if (null == file || !file.exists()) {
            // 文件不存在
            return false;
        }
        try {
            // 获取ROOT权限
            Process su = Runtime.getRuntime().exec("/system/bin/su");
            // 修改文件属性为 [可读 可写 可执行]
            String cmd = "chmod 777 " + file.getAbsolutePath() + "\n" + "exit\n";
            su.getOutputStream().write(cmd.getBytes());
            if (0 == su.waitFor() && file.canRead() && file.canWrite() && file.canExecute()) {
                return true;
            }
        } catch (IOException | InterruptedException e) {
            // 没有ROOT权限
            e.printStackTrace();
        }
        return false;
    }

    // 打开串口
    protected native FileDescriptor open(String path, int baudRate, int flags);

    // 关闭串口
    protected native void close();
}
