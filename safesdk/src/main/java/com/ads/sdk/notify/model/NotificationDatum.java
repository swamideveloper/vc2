
package com.ads.sdk.notify.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NotificationDatum {

    @SerializedName("notify_title")
    @Expose
    private String notifyTitle;
    @SerializedName("notify_subtitle")
    @Expose
    private String notifySubtitle;
    @SerializedName("notify_image_link")
    @Expose
    private String notifyImageLink;
    @SerializedName("notify_link")
    @Expose
    private String notifyLink;

    public String getNotifyTitle() {
        return notifyTitle;
    }

    public void setNotifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
    }

    public String getNotifySubtitle() {
        return notifySubtitle;
    }

    public void setNotifySubtitle(String notifySubtitle) {
        this.notifySubtitle = notifySubtitle;
    }

    public String getNotifyImageLink() {
        return notifyImageLink;
    }

    public void setNotifyImageLink(String notifyImageLink) {
        this.notifyImageLink = notifyImageLink;
    }

    public String getNotifyLink() {
        return notifyLink;
    }

    public void setNotifyLink(String notifyLink) {
        this.notifyLink = notifyLink;
    }

}
