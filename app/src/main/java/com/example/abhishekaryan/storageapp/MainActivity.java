package com.example.abhishekaryan.storageapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abhishekaryan.storageapp.Activities.SettingsActivity;
import com.example.abhishekaryan.storageapp.Activities.signInActivity;
import com.example.abhishekaryan.storageapp.DataProvider.DataItem;
import com.example.abhishekaryan.storageapp.DataProvider.SampleDataProvider;
import com.example.abhishekaryan.storageapp.Services.MyNetworkService;
import com.example.abhishekaryan.storageapp.utils.DataItems;
import com.example.abhishekaryan.storageapp.utils.NetworkHelper;
import com.example.abhishekaryan.storageapp.viewHolder.dataApdater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.abhishekaryan.storageapp.Activities.signInActivity.EMAIL_KEY;
import static com.example.abhishekaryan.storageapp.Services.MyNetworkService.MESSGE_KEY;

public class MainActivity extends AppCompatActivity {

    private static final int ACTIVITY_REQUEST_CODE = 100;
    private List<DataItems> dataItemList=null;
    private RecyclerView recyclerView;
    private dataApdater apdater;
    private Boolean IsNetworkAvaiable;
    private ProgressBar progressBar;

    public static final String URL_ADDRESS ="URL_ADDRESS" ;
    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;

    BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            DataItems[] dataItems=(DataItems[])intent.getParcelableArrayExtra(MESSGE_KEY);
            //textView.setText(Msg);

            dataItemList=  Arrays.asList(dataItems);
            apdater=new dataApdater(dataItemList,MainActivity.this);
            recyclerView.setAdapter(apdater);
             progressBar.setVisibility(View.GONE);



//            Toast.makeText(MainActivity.this,dataItems.getItemName(),Toast.LENGTH_SHORT).show();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.activity_main_progressBar);
        recyclerView=(RecyclerView)findViewById(R.id.activity_main_recyler_view);
        callService();

        final SharedPreferences prefences= PreferenceManager.getDefaultSharedPreferences(this);
        preferenceChangeListener=new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                Boolean result=prefences.getBoolean(getString(R.string.gridView),false);
                if(result){
                    recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
                }

                else {
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }

            }
        };

        prefences.registerOnSharedPreferenceChangeListener(preferenceChangeListener);


        Boolean result=prefences.getBoolean(getString(R.string.gridView),false);
        if(result){
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,3));
        }

        else {
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }



    }

    private void callService() {

        IsNetworkAvaiable= NetworkHelper.hasNetworkAccess(this);
        if(IsNetworkAvaiable) {
            String urlAddress = "https://abhishekaryan.000webhostapp.com/MyProject/MyProject/itemFeed.php";
            Intent intent = new Intent(this, MyNetworkService.class);
            intent.putExtra(URL_ADDRESS, urlAddress);
            startService(intent);
            progressBar.setVisibility(View.VISIBLE);


        }

        else {

            Toast.makeText(this,"Internet Connection not avaiable ",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        View decorView=getWindow().getDecorView();
        //decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE


       // );


    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter=new IntentFilter("MY.Messgae");

        LocalBroadcastManager manager=LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(broadcastReceiver,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager manager=LocalBroadcastManager.getInstance(this);
        manager.unregisterReceiver(broadcastReceiver);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (id){

            case R.id.activity_main_menu_signIn:

                Intent intent=new Intent(this, signInActivity.class);
                startActivityForResult(intent,ACTIVITY_REQUEST_CODE);

                break;

            case R.id.activity_main_menu_settings:
                Intent intent1=new Intent(this,SettingsActivity.class);
                startActivity(intent1);

                break;

        }

        return false;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){

            String emailData=data.getStringExtra(EMAIL_KEY);
            Toast.makeText(this,"You signed in as - "+emailData,Toast.LENGTH_SHORT).show();
        }
    }
}
