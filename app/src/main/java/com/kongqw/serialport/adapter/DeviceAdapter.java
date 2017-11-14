package com.kongqw.serialport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kongqw.serialport.R;
import com.kongqw.serialportlibrary.Device;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Kongqw on 2017/11/13.
 * 串口列表适配器
 */

public class DeviceAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Device> devices;

    public DeviceAdapter(Context context, ArrayList<Device> devices) {
        this.mInflater = LayoutInflater.from(context);
        this.devices = devices;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Device getItem(int position) {
        return devices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_device, null);
            holder.device = (TextView) convertView.findViewById(R.id.tv_device);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String deviceName = devices.get(position).getName();
        String driverName = devices.get(position).getRoot();
        File file = devices.get(position).getFile();
        boolean canRead = file.canRead();
        boolean canWrite = file.canWrite();
        boolean canExecute = file.canExecute();
        String path = file.getAbsolutePath();

        StringBuffer permission = new StringBuffer();
        permission.append("\t权限[");
        permission.append(canRead ? " 可读 " : " 不可读 ");
        permission.append(canWrite ? " 可写 " : " 不可写 ");
        permission.append(canExecute ? " 可执行 " : " 不可执行 ");
        permission.append("]");

        holder.device.setText(String.format("%s [%s] (%s)  %s", deviceName, driverName, path, permission));

        return convertView;
    }

    private class ViewHolder {
        TextView device;
    }
}
