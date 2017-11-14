package com.kongqw.serialport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kongqw.serialport.adapter.DeviceAdapter;
import com.kongqw.serialportlibrary.Device;
import com.kongqw.serialportlibrary.SerialPortFinder;

import java.util.ArrayList;

public class SelectSerialPortActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = SelectSerialPortActivity.class.getSimpleName();
    private DeviceAdapter mDeviceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_serial_port);

        ListView listView = (ListView) findViewById(R.id.lv_devices);


        SerialPortFinder serialPortFinder = new SerialPortFinder();

        ArrayList<Device> devices = serialPortFinder.getDevices();

        if (listView != null) {
            listView.setEmptyView(findViewById(R.id.tv_empty));
            mDeviceAdapter = new DeviceAdapter(getApplicationContext(), devices);
            listView.setAdapter(mDeviceAdapter);
            listView.setOnItemClickListener(this);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Device device = mDeviceAdapter.getItem(position);

        Intent intent = new Intent(this, SerialPortActivity.class);
        intent.putExtra(SerialPortActivity.DEVICE, device);
        startActivity(intent);
    }
}
