
package com.ads.sdk.notify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ModelNotify {

    @SerializedName("Notification_Time")
    @Expose
    private String notificationTime;
    @SerializedName("Notification_Data")
    @Expose
    private List<NotificationDatum> notificationData;

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public List<NotificationDatum> getNotificationData() {
        return notificationData;
    }

    public void setNotificationData(List<NotificationDatum> notificationData) {
        this.notificationData = notificationData;
    }

}
