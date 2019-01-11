package com.example.abhishekaryan.storageapp.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abhishekaryan.storageapp.Activities.DetailsActivity;
import com.example.abhishekaryan.storageapp.DataProvider.DataItem;
import com.example.abhishekaryan.storageapp.R;
import com.example.abhishekaryan.storageapp.utils.DataItems;
import com.squareup.picasso.Picasso;


public class dataViewHolder extends RecyclerView.ViewHolder {

    public static final String OBJECT_KEY ="OBJECT_KEY";
    private ImageView imageView;
    private TextView textView;
    private View view;
    public static final String BASE_IMAGE_URL="https://abhishekaryan.000webhostapp.com/MyProject/MyProject/images/";
    public dataViewHolder(View itemView) {
        super(itemView);

        imageView=(ImageView)itemView.findViewById(R.id.view_layout_imageView);
        textView=(TextView)itemView.findViewById(R.id.view_layout_textView_name);
        view=itemView;
    }

    public void populate(final Context context, final DataItems dataItem){


        textView.setText(dataItem.getItemName());

        String imageUrl=BASE_IMAGE_URL + dataItem.getImage();
        Picasso.with(context)
                .load(imageUrl).into(imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,DetailsActivity.class);
                intent.putExtra(OBJECT_KEY,dataItem);
                context.startActivity(intent);
            }
        });
    }
}
