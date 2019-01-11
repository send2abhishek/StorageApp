package com.example.abhishekaryan.storageapp.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.abhishekaryan.storageapp.R;
import com.example.abhishekaryan.storageapp.Services.MyNetworkService;
import com.example.abhishekaryan.storageapp.utils.Comments;
import com.example.abhishekaryan.storageapp.utils.DataItems;

import java.util.Arrays;
import java.util.List;

public class commentsActivity extends AppCompatActivity {

    public static final String COMMENT_ACTIVITY="COMMENT_ACTIVITY";
    public static final String RESULTS_COMMENTS="RESULTS_COMMENTS";
    private static final String COMMENT_URL ="COMMENT_URL" ;
    public static final String URL_ADDRESS_COMMENT = "URL_ADDRESS_COMMENT";
    private  String URL_ADDRESS="https://abhishekaryan.000webhostapp.com/MyProject/MyProject/itemFeed.php";
    private DataItems dataItems;
    private List<Comments> commentData;

    BroadcastReceiver brodcast=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Comments[] comments= (Comments[]) intent.getParcelableArrayExtra(RESULTS_COMMENTS);
            commentData= Arrays.asList(comments);

            StringBuilder data=new StringBuilder();

            for (Comments comment:commentData){

                data=data.append(comment.getComments());

            }

            Toast.makeText(commentsActivity.this,data,Toast.LENGTH_SHORT).show();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        dataItems=getIntent().getParcelableExtra(COMMENT_ACTIVITY);
        callService(dataItems);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter=new IntentFilter("MY.comments");
        LocalBroadcastManager manager=LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(brodcast,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager manager=LocalBroadcastManager.getInstance(this);
        manager.unregisterReceiver(brodcast);
    }

    private void callService(DataItems dataItems) {


        StringBuilder urlFinal=new StringBuilder();
        urlFinal=urlFinal.append(URL_ADDRESS).append("?").append("slNo").append("=").append(dataItems.getId());

        String url=urlFinal.toString();
        Intent intent=new Intent(this, MyNetworkService.class);
        intent.putExtra(URL_ADDRESS_COMMENT, Uri.parse(url));
        startService(intent);

    }
}

