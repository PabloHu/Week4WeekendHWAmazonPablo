package com.example.admin.week4weekendhwamazonpablo;

/**
 * Created by Admin on 9/24/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.admin.week4weekendhwamazonpablo.model.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Admin on 9/17/2017.
 */

public class ThreadAccessAmazonHandler extends Thread{
    private static final String CAR_OPTIONS_FRAGMENT_TAG = "AmazonThreadTAG";
    private Context context;
    String loginUser;
    private static final String TAG = "Handler" ;
    android.os.Handler handler;
    public static String BASE_URL = "https://api.github.com/users/";
    private OkHttpClient client;
    String a;
    Response myResponse;

    public ThreadAccessAmazonHandler(android.os.Handler handler, String loginUser){
        this.handler = handler;
        this.loginUser = loginUser;
    }


    @Override
    public void run(){
        //super.run();

        Log.d(TAG, "a: ");
        final retrofit2.Call<List<Response>> callRepos = RetrofitHelper.createCallWeather();


        // Log.d(TAG, "a2: " + callRepos.toString());
        //create a thread to make the rest call on a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "runnable start: ");

                try {
                    Log.d(TAG, "runnable try: ");
                    final List<Response> repoName = (List<Response>) callRepos.execute().body();


                    for (int i = 0; i < 5; i++) {
                        Log.d(TAG, "run: "+ repoName.get(i).getTitle());
                    }

                    /*
                    AmazonFragment amazoneFragment  = AmazonFragment.newInstance(repoName);
                    ((MainActivity)context).getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragHolderCarOptions, amazoneFragment, CAR_OPTIONS_FRAGMENT_TAG)
                            .addToBackStack(CAR_OPTIONS_FRAGMENT_TAG)

                            .commit();
*/


                    Bundle bundle = new Bundle();
                    //      bundle.putString("data", data);
                    bundle.putSerializable("profileSeri", (Serializable) repoName);
                    //cteate the messagee object and add bundle objecct
                    Message message = new Message();
                    message.setData(bundle);
                    //send that bundle object in the
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        //----------------------------------------


    }
}


