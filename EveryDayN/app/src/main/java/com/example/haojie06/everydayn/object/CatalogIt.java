package com.example.haojie06.everydayn.object;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haojie06 on 2018/4/1.
 */

public class CatalogIt implements Parcelable {
    private String name,url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.url);
    }

    public CatalogIt() {
    }

    protected CatalogIt(Parcel in) {
        this.name = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<CatalogIt> CREATOR = new Parcelable.Creator<CatalogIt>() {
        @Override
        public CatalogIt createFromParcel(Parcel source) {
            return new CatalogIt(source);
        }

        @Override
        public CatalogIt[] newArray(int size) {
            return new CatalogIt[size];
        }
    };
}
