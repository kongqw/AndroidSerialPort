package com.kongqw.serialportlibrary;

import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class SerialPortFinder {

    private static final String TAG = SerialPortFinder.class.getSimpleName();
    private static final String DRIVERS_PATH = "/proc/tty/drivers";
    private static final String SERIAL_FIELD = "serial";

    public SerialPortFinder() {
        File file = new File(DRIVERS_PATH);
        boolean b = file.canRead();
        Log.i(TAG, "SerialPortFinder: file.canRead() = " + b);
    }

    /**
     * 获取 Drivers
     *
     * @return Drivers
     * @throws IOException IOException
     */
    private ArrayList<Driver> getDrivers() throws IOException {
        ArrayList<Driver> drivers = new ArrayList<>();
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(DRIVERS_PATH));
        String readLine;
        while ((readLine = lineNumberReader.readLine()) != null) {
            String driverName = readLine.substring(0, 0x15).trim();
            String[] fields = readLine.split(" +");
            if ((fields.length >= 5) && (fields[fields.length - 1].equals(SERIAL_FIELD))) {
                Log.d(TAG, "Found new driver " + driverName + " on " + fields[fields.length - 4]);
                drivers.add(new Driver(driverName, fields[fields.length - 4]));
            }
        }
        return drivers;
    }

    /**
     * 获取串口
     *
     * @return 串口
     */
    public ArrayList<Device> getDevices() {
        ArrayList<Device> devices = new ArrayList<>();
        try {
            ArrayList<Driver> drivers = getDrivers();
            for (Driver driver : drivers) {
                String driverName = driver.getName();
                ArrayList<File> driverDevices = driver.getDevices();
                for (File file : driverDevices) {
                    String devicesName = file.getName();
                    devices.add(new Device(devicesName, driverName, file));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return devices;
    }
}
