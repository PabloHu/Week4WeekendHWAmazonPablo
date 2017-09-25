package com.example.admin.week4weekendhwamazonpablo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;

import com.example.admin.week4weekendhwamazonpablo.model.Response;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    private static final String CAR_OPTIONS_FRAGMENT_TAG = "MyiIntServiceTag";
    Context context;
    android.os.Handler handler;
    public static final String TAG = "MyiIntServiceTag";
    List<Response> repoName;
    public MyIntentService() {
        super("MyIntentService");
    }
    private static final String MY_PREF_FILE = "com.example.admin.week4weekendhwamazonpablo";
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        //a();
        b();
/*
        handler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                Log.d(TAG, "handleMessage: ht" + Thread.currentThread());


                List<Response> repoName1 = (List<Response>) msg.getData().getSerializable("profileSeri");


                Log.d(TAG, "handleMessage: " + repoName1.get(0).getTitle());

                //Log.d(TAG, "handleMessageResonseSize: "+ myResponse.getLogin());


                return false;
            }
        });
        ThreadAccessAmazonHandler accessProfile = new ThreadAccessAmazonHandler(handler, "dummy");
        accessProfile.start();
        Log.d(TAG, "onHandleIntent: "+ "HIIIIIIIIIIIIIIIIIIIIIIIII");
*/

        Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data")+" T:"+Thread.currentThread());

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "onHandleIntent: Task " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void a(){
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
                    repoName = (List<Response>) callRepos.execute().body();


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

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    public void b(){

        handler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                Log.d(TAG, "handleMessage: ht" + Thread.currentThread());


                repoName = (List<Response>) msg.getData().getSerializable("profileSeri");

                ShareData sd = ShareData.getInstance();
                sd.setData(repoName);

                Log.d(TAG, "handleMessage: " + repoName.get(0).getTitle());



                //---------------------------
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < repoName.size(); i++) {
                    sb.append(repoName.get(i).getTitle());
                    sb.append(",");
                    sb.append(repoName.get(i).getImageURL());
                    sb.append(";");

                }



                //-------------------------
                //Log.d(TAG, "handleMessageResonseSize: "+ myResponse.getLogin());

                SharedPreferences sharedPreferences = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();


                editor.putString("keyDataList", sb.toString());
//                editor.putString("keyDataList", repoName.toString());
                editor.commit();


                return false;
            }
        });
        ThreadAccessAmazonHandler accessProfile = new ThreadAccessAmazonHandler(handler, "dummy");
        accessProfile.start();
        Log.d(TAG, "onHandleIntent: "+ "HIIIIIIIIIIIIIIIIIIIIIIIII");
    }
    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
       /* Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(MyWebRequestReceiver.PROCESS_RESPONSE);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(RESPONSE_STRING, responseString);
        broadcastIntent.putExtra(RESPONSE_MESSAGE, responseMessage);
        sendBroadcast(broadcastIntent);
*/
       /*
        Intent intent = new Intent();
        intent.setAction("myActionTest");
        intent.putExtra("dataList", (Serializable) repoName);
      //  intent.putExtra("data", "PiruLikeschicken from service");
        sendBroadcast(intent);
   */
/*
        List<String> aa = new ArrayList<>();

        for (int i = 0; i < repoName.size(); i++) {
            if(repoName.get(i).getTitle()!=null)
            aa.add(repoName.get(i).getTitle());
        }
*/

        Intent intent = new Intent();
        intent.setAction("myActionTest");
        //  intent.putExtra("dataList", (Serializable) repoName);
 //       Bundle bundle = new Bundle();
        //      bundle.putString("data", data);
   //     bundle.putSerializable("profileSeri", (Serializable) repoName);
        intent.putExtra("dataListKey", "keyDataList");
       // intent.putStringArrayListExtra("dataList", (ArrayList<String>) aa);
        //  intent.putExtra("data", "PiruLikeschicken from service");
        sendBroadcast(intent);

       super.onDestroy();



    }
}
