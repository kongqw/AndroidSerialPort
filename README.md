# 说明

[android-serialport-api](https://code.google.com/archive/p/android-serialport-api/)

[![](https://jitpack.io/v/kongqw/AndroidSerialPort.svg)](https://jitpack.io/#kongqw/AndroidSerialPort)

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

``` Gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

``` Gradle
dependencies {
        compile 'com.github.kongqw:AndroidSerialPort:1.0.1'
}
```

## 查看串口

``` Java
SerialPortFinder serialPortFinder = new SerialPortFinder();
ArrayList<Device> devices = serialPortFinder.getDevices();
```

## 打开串口

### 初始化

``` Java
mSerialPortManager = new SerialPortManager();
```

### 添加打开串口监听

``` Java
mSerialPortManager.setOnOpenSerialPortListener(new OnOpenSerialPortListener() {
    @Override
    public void onSuccess(File device) {
        
    }

    @Override
    public void onFail(File device, Status status) {

    }
});
```

### 添加数据通信监听

``` Java
mSerialPortManager.setOnSerialPortDataListener(new OnSerialPortDataListener() {
    @Override
    public void onDataReceived(byte[] bytes) {
        
    }

    @Override
    public void onDataSent(byte[] bytes) {

    }
});
```

### 打开串口

- 参数1：串口
- 参数2：波特率
- 返回：串口打开是否成功

``` Java
boolean openSerialPort = mSerialPortManager.openSerialPort(device.getFile(), 115200);
```

### 发送数据

- 参数：发送数据 byte[]
- 返回：发送是否成功

``` Java
boolean sendBytes = mSerialPortManager.sendBytes(sendContentBytes);
```

## 关闭串口

``` Java
mSerialPortManager.closeSerialPort();
```

> PS：传输协议需自行封装