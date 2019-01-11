package com.example.abhishekaryan.storageapp.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class Comments implements Parcelable {

    private String itemName;
    private String comments;


    public Comments(String itemName, String comments) {
        this.itemName = itemName;
        this.comments = comments;
    }

    protected Comments(Parcel in) {
        itemName = in.readString();
        comments = in.readString();
    }

    public static final Creator<Comments> CREATOR = new Creator<Comments>() {
        @Override
        public Comments createFromParcel(Parcel in) {
            return new Comments(in);
        }

        @Override
        public Comments[] newArray(int size) {
            return new Comments[size];
        }
    };

    public String getItemName() {
        return itemName;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemName);
        dest.writeString(comments);
    }
}




