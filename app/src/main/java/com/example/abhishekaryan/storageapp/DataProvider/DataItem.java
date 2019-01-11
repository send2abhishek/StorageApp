package com.example.abhishekaryan.storageapp.DataProvider;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by Abhishek Aryan on 18-12-2018.
 */

public class DataItem implements Parcelable {

    private String ItemId;
    private String ItemName;
    private String ItemDesc;
    private String ItemImage;

    public DataItem(String itemId, String itemName, String itemDesc, String itemImage) {

        if(itemId==null){
            itemId = UUID.randomUUID().toString();
        }
        ItemId = itemId;
        ItemName = itemName;
        ItemDesc = itemDesc;
        ItemImage = itemImage;
    }

    protected DataItem(Parcel in) {
        ItemId = in.readString();
        ItemName = in.readString();
        ItemDesc = in.readString();
        ItemImage = in.readString();
    }

    public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };

    public String getItemId() {
        return ItemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getItemDesc() {
        return ItemDesc;
    }

    public String getItemImage() {
        return ItemImage;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "ItemId='" + ItemId + '\'' +
                ", ItemName='" + ItemName + '\'' +
                ", ItemDesc='" + ItemDesc + '\'' +
                ", ItemImage='" + ItemImage + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ItemId);
        dest.writeString(ItemName);
        dest.writeString(ItemDesc);
        dest.writeString(ItemImage);
    }
}
