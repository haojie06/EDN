package com.example.haojie06.everydayn.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by haojie06 on 2018/3/28.
 */

public class Sound  implements Parcelable {
    private String soundTitle,soundAuthor,soundAnchor,soundPicUrl,soundSoundUrl;

    public Sound() {
    }



    public Sound(String soundTitle, String soundAuthor, String soundAnchor, String soundPicUrl, String soundSoundUrl) {
        this.soundTitle = soundTitle;
        this.soundAuthor = soundAuthor;
        this.soundAnchor = soundAnchor;
        this.soundPicUrl = soundPicUrl;
        this.soundSoundUrl = soundSoundUrl;
    }



    public void setSoundTitle(String soundTitle) {
        this.soundTitle = soundTitle;
    }

    public void setSoundAuthor(String soundAuthor) {
        this.soundAuthor = soundAuthor;
    }

    public void setSoundAnchor(String soundAnchor) {
        this.soundAnchor = soundAnchor;
    }

    public void setSoundPicUrl(String soundPicUrl) {
        this.soundPicUrl = soundPicUrl;
    }

    public void setSoundSoundUrl(String soundSoundUrl) {
        this.soundSoundUrl = soundSoundUrl;
    }

    public String getSoundTitle() {
        return soundTitle;
    }

    public String getSoundAuthor() {
        return soundAuthor;
    }

    public String getSoundAnchor() {
        return soundAnchor;
    }

    public String getSoundPicUrl() {
        return soundPicUrl;
    }

    public String getSoundSoundUrl() {
        return soundSoundUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.soundTitle);
        dest.writeString(this.soundAuthor);
        dest.writeString(this.soundAnchor);
        dest.writeString(this.soundPicUrl);
        dest.writeString(this.soundSoundUrl);
    }

    protected Sound(Parcel in) {
        this.soundTitle = in.readString();
        this.soundAuthor = in.readString();
        this.soundAnchor = in.readString();
        this.soundPicUrl = in.readString();
        this.soundSoundUrl = in.readString();
    }

    public static final Creator<Sound> CREATOR = new Creator<Sound>() {
        @Override
        public Sound createFromParcel(Parcel source) {
            return new Sound(source);
        }

        @Override
        public Sound[] newArray(int size) {
            return new Sound[size];
        }
    };
}
