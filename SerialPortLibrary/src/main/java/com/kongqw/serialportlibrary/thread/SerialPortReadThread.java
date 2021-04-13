package com.kongqw.serialportlibrary.thread;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;


/**
 * Created by Kongqw on 2017/11/14.
 * Updated by wenshuoc on 2021/04/13.
 * 串口消息读取线程
 */
public abstract class SerialPortReadThread extends Thread {

    public abstract void onDataReceived(byte[] bytes);

    private static final String TAG = SerialPortReadThread.class.getSimpleName();
    private InputStream mInputStream;
    private final byte[] mReadBuffer;
    private final int mReadDelay;

    /**
     * 初始化读串口的线程
     * @param inputStream 串口文件的输入流
     * @param readDelay 读取过程中的延迟（ms）
     */
    public SerialPortReadThread(InputStream inputStream, int readDelay) {
        mInputStream = inputStream;
        mReadBuffer = new byte[1024];
        mReadDelay = readDelay;
    }

    @Override
    public void run() {
        super.run();

        while (!isInterrupted()) {
            try {
                Log.i(TAG, "run: ");
                if (mInputStream != null && mInputStream.available() == 0) {
                    // 如果输入流有效但是没有输入值，便重新等待输入
                    continue;
                }
                if (null == mInputStream) {
                    // 输入流为null说明线程结束了，直接退出
                    return;
                }
                // 输入流产生了输入值，可以开始读取

                // 有的串口发送程序会产生一定的延迟，直接读取会导致串口消息分段
                // 因此需要根据设置情况等待一段时间，保证发送端将消息发送完毕
                if (mReadDelay > 0) {
                    Thread.sleep(mReadDelay);
                }

                // 读取串口输入
                int size = mInputStream.read(mReadBuffer);
                if (-1 == size || 0 >= size) {
                    return;
                }

                byte[] readBytes = new byte[size];

                System.arraycopy(mReadBuffer, 0, readBytes, 0, size);

                Log.i(TAG, "run: readBytes = " + new String(readBytes));
                onDataReceived(readBytes);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    /**
     * 关闭线程 释放资源
     */
    public void release() {
        interrupt();

        if (null != mInputStream) {
            try {
                mInputStream.close();
                mInputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
