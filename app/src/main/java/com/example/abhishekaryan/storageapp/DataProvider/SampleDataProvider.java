package com.example.abhishekaryan.storageapp.DataProvider;

import java.util.ArrayList;
import java.util.HashMap;

public class SampleDataProvider {

    public static ArrayList<DataItem> dataItemArrayList;
    public static HashMap<String,DataItem> dataItemHashMap;


    static {

        dataItemArrayList=new ArrayList<>();
        dataItemHashMap=new HashMap<>();

        addItems(new DataItem(null,"Abhishek Aryan","This is first item in descrption","quinoa_salad.jpg"));
        addItems(new DataItem(null,"Asish","This is first item in descrption","quinoa_salad.jpg"));
        addItems(new DataItem(null,"Abhiya","This is first item in descrption","chef_salad.jpg"));
        addItems(new DataItem(null,"Abhishek Aryan","This is first item in descrption","house_salad.jpg"));
        addItems(new DataItem(null,"Abhishek Aryan","This is first item in descrption","mini_cheeseburgers.jpg"));
        addItems(new DataItem(null,"Abhishek Aryan","This is first item in descrption","stuffed_mushrooms.jpg"));
        addItems(new DataItem(null,"Abhishek Aryan","This is first item in descrption","french_onion_soup.jpg"));

    }


    public static void addItems(DataItem items){

        dataItemArrayList.add(items);
        dataItemHashMap.put(items.getItemId(),items);
    }




}
