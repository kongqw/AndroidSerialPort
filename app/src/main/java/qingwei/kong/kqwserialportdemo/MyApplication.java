package qingwei.kong.kqwserialportdemo;

import android.app.Application;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import qingwei.kong.serialportlibrary.SerialPort;
import qingwei.kong.serialportlibrary.SerialPortFinder;

/**
 * Created by kqw on 2016/10/26.
 * MyApplication
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    public SerialPortFinder mSerialPortFinder = new SerialPortFinder();
    private SerialPort mSerialPort = null;

    public SerialPort getSerialPort() throws SecurityException, IOException, InvalidParameterException {
        if (mSerialPort == null) {
//            /* Read serial port parameters */
//            SharedPreferences sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
//            String path = sp.getString("DEVICE", "");
//            int baudrate = Integer.decode(sp.getString("BAUDRATE", "-1"));
//            String path = sp.getString("DEVICE", "/dev/ttyS2");
//            int baudrate = Integer.decode(sp.getString("BAUDRATE", "115200"));
//
//            String path = "/dev/ttyS2";
//            int baudrate = 115200;
//
//            Log.i(TAG, "getSerialPort: path = " + path + "   baudrate = " + baudrate);
//            /* Check parameters */
//            if ((path.length() == 0) || (baudrate == -1)) {
//                throw new InvalidParameterException();
//            }
//
//			/* Open the serial port */
//            mSerialPort = new SerialPort(new File(path), baudrate, 0);

            mSerialPort = new SerialPort(new File("/dev/ttyS2"), 115200, 0);
        }
        return mSerialPort;
    }

    public void closeSerialPort() {
        if (mSerialPort != null) {
//            mSerialPort.close();
            mSerialPort = null;
        }
    }
}
