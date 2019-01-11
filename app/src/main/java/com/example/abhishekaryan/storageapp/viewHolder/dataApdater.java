package com.example.abhishekaryan.storageapp.viewHolder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abhishekaryan.storageapp.DataProvider.DataItem;
import com.example.abhishekaryan.storageapp.R;
import com.example.abhishekaryan.storageapp.utils.DataItems;

import java.util.ArrayList;
import java.util.List;

public class dataApdater extends RecyclerView.Adapter<dataViewHolder>  {
        private List<DataItems> dataItemArrayList;
        private Context context;
        private LayoutInflater layoutInflater;

    public dataApdater(List<DataItems> dataItemArrayList, Context context) {
        this.dataItemArrayList = dataItemArrayList;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public dataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.view_layout,parent,false);



        return new dataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(dataViewHolder holder, int position) {

        holder.populate(context,dataItemArrayList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataItemArrayList.size();
    }


}
