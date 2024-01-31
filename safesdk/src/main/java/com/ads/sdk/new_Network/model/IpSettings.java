
package com.ads.sdk.new_Network.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class IpSettings {

    @SerializedName("loc_type")
    @Expose
    private String locType;
    @SerializedName("loc_array")
    @Expose
    private List<LocArray> locArray;

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    public List<LocArray> getLocArray() {
        return locArray;
    }

    public void setLocArray(List<LocArray> locArray) {
        this.locArray = locArray;
    }

}
