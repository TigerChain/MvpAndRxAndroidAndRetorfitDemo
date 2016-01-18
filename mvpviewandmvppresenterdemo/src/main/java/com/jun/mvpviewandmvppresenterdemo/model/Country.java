package com.jun.mvpviewandmvppresenterdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by chenjunjun on 1/10/16.
 */
 public class Country implements Parcelable {
    String name;

    private Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        //CountryParcelablePlease.writeToParcel(this, dest, flags);
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        public Country createFromParcel(Parcel source) {
            Country target = new Country();
            // CountryParcelablePlease.readFromParcel(target, source);
            return target;
        }

        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
