package com.timecho.example;

import org.apache.autoconfigure.starter.Device;
import org.apache.autoconfigure.starter.Sensor;

public class A {
    @Sensor("time1")
    long timeStamp;
    @Device("root.test")
    String device;

    @Sensor("sensor")
    String sensor;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }
}
