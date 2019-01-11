package com.example.abhishekaryan.storageapp.Services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import com.example.abhishekaryan.storageapp.utils.Comments;
import com.example.abhishekaryan.storageapp.utils.DataItems;
import com.example.abhishekaryan.storageapp.utils.HttpHelper;
import com.google.gson.Gson;
import java.io.IOException;
import static com.example.abhishekaryan.storageapp.Activities.commentsActivity.RESULTS_COMMENTS;
import static com.example.abhishekaryan.storageapp.Activities.commentsActivity.URL_ADDRESS_COMMENT;
import static com.example.abhishekaryan.storageapp.MainActivity.URL_ADDRESS;


public class MyNetworkService extends IntentService {

    public static final String MESSGE_KEY ="MESSGE_KEY" ;



    public MyNetworkService() {
        super("My Service");
    }


    @Override
    public void onCreate() {
        super.onCreate();


        Toast.makeText(this,"Task has Started ",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onHandleIntent(Intent intent) {

        String response=null;
        if(intent.hasExtra(URL_ADDRESS)) {

            String urlAddress = intent.getStringExtra(URL_ADDRESS);
            try {
                response= HttpHelper.downloadUrl(this,urlAddress);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Gson gson=new Gson();
            DataItems[] dataItemses = gson.fromJson(response, DataItems[].class);
            Intent localIntent=new Intent("MY.Messgae");
            localIntent.putExtra(MESSGE_KEY,dataItemses);
            LocalBroadcastManager manager=LocalBroadcastManager.getInstance(this);
            manager.sendBroadcast(localIntent);
        }

        if(intent.hasExtra(URL_ADDRESS_COMMENT)){


            Uri urlAddress = intent.getParcelableExtra(URL_ADDRESS_COMMENT);
            try {
                response= HttpHelper.downloadUrl(this,urlAddress.toString());
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Gson gson=new Gson();

            Comments[] commentses=gson.fromJson(response,Comments[].class);
            Intent localIntent=new Intent("MY.comments");
            localIntent.putExtra(RESULTS_COMMENTS,commentses);
            LocalBroadcastManager manager=LocalBroadcastManager.getInstance(this);
            manager.sendBroadcast(localIntent);



        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this,"Task has finished ",Toast.LENGTH_SHORT).show();

    }


}
