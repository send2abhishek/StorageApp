package com.example.abhishekaryan.storageapp.utils;
import android.os.Parcel;
import android.os.Parcelable;

public class DataItems implements Parcelable  {

    private int id;
    private String ItemName;
    private String category;
    private String description;
    private int sort;
    private int price;
    private String image;


    public DataItems(int id, String itemName, String category,
                     String description, int sort, int price, String image) {
        this.id = id;
        ItemName = itemName;
        this.category = category;
        this.description = description;
        this.sort = sort;
        this.price = price;
        this.image = image;
    }

    protected DataItems(Parcel in) {
        id = in.readInt();
        ItemName = in.readString();
        category = in.readString();
        description = in.readString();
        sort = in.readInt();
        price = in.readInt();
        image = in.readString();
    }

    public static final Creator<DataItems> CREATOR = new Creator<DataItems>() {
        @Override
        public DataItems createFromParcel(Parcel in) {
            return new DataItems(in);
        }

        @Override
        public DataItems[] newArray(int size) {
            return new DataItems[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public int getSort() {
        return sort;
    }

    public int getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(ItemName);
        dest.writeString(category);
        dest.writeString(description);
        dest.writeInt(sort);
        dest.writeInt(price);
        dest.writeString(image);
    }
}
