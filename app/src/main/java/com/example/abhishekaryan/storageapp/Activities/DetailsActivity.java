package com.example.abhishekaryan.storageapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.abhishekaryan.storageapp.R;
import com.example.abhishekaryan.storageapp.utils.DataItems;
import com.squareup.picasso.Picasso;
import static com.example.abhishekaryan.storageapp.Activities.commentsActivity.COMMENT_ACTIVITY;
import static com.example.abhishekaryan.storageapp.viewHolder.dataViewHolder.BASE_IMAGE_URL;
import static com.example.abhishekaryan.storageapp.viewHolder.dataViewHolder.OBJECT_KEY;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView headerText;
    private WebView pageContent;
    private DataItems dataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        imageView=(ImageView)findViewById(R.id.activity_details_imageView);
        headerText=(TextView)findViewById(R.id.activity_details_itemNameTextView);
        pageContent=(WebView)findViewById(R.id.activity_details_webView);
        dataItem=getIntent().getParcelableExtra(OBJECT_KEY);
        loadData(dataItem);
    }

    private void loadData(DataItems dataItem) {


        String imageUrl=BASE_IMAGE_URL + dataItem.getImage();

        Picasso.with(this)
                .load(imageUrl).into(imageView);

        headerText.setText(dataItem.getItemName());
        pageContent.loadData(dataItem.getDescription(),"text/html; charset=utf-8", "UTF-8");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_details_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){


            case R.id.activity_details_menu_comments:

                handleComments(dataItem);
                break;

        }


        return false;

    }

    private void handleComments(DataItems dataItem) {

        Intent intent=new Intent(this,commentsActivity.class);
        intent.putExtra(COMMENT_ACTIVITY,dataItem);
        startActivity(intent);
    }

}
