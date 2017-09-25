package com.example.admin.week4weekendhwamazonpablo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.week4weekendhwamazonpablo.model.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
public class MainActivity extends AppCompatActivity {
    MyDynamicReceiver myDynamicReceiver = new MyDynamicReceiver();
    private static MainActivity ins;
    IntentFilter intentFilter = new IntentFilter();
    private static final String MY_PREF_FILE = "com.example.admin.week4weekendhwamazonpablo";
    private static final String CAR_OPTIONS_FRAGMENT_TAG ="AMAZON_FRAG" ;
    List<Response> responseList = new ArrayList<>();
    final java.util.List weatherFragment = new ArrayList<>();
    String WEATHER_KEY = "cfcf895c3e3cb655115ab6a68ea386d2";
   // MyPageAdapter myPageAdapter;
    private static final String TAG ="mainTAG" ;
    private OkHttpClient client;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ins = this;
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");




        //activateIntentService();
 //       a();

        startAutoService();
    }
    public void startAutoService(){
        Intent myIntent = new Intent(this, MyDynamicReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,  0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 60); // first time
        long frequency= 60 * 1000; // in ms
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), frequency, pendingIntent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        intentFilter.addAction("myActionTest");
        registerReceiver(myDynamicReceiver,intentFilter);

    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myDynamicReceiver);
    }

    private void activateIntentService() {
      /*  //crate intent service
        Intent intIntent = new Intent(this,MyIntentService.class);
        intIntent.putExtra("data","testing");
        startService(intIntent);
*/


    }


    public void a() {


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

                    AmazonFragment amazoneFragment  = AmazonFragment.newInstance(repoName);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.flFragHolderCarOptions, amazoneFragment, CAR_OPTIONS_FRAGMENT_TAG)
                            .addToBackStack(CAR_OPTIONS_FRAGMENT_TAG)

                            .commit();



                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }


    public void clicking(View view) {
      /*
        Intent intent = new Intent();
        intent.setAction("myActionTest");
        intent.putExtra("data", "PiruLikes");
        sendBroadcast(intent);
        */
//a();
        //if(myResponse.getLogin()!=null)
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
        */


        Intent intIntent = new Intent(this,MyIntentService.class);
        intIntent.putExtra("data","testing");
        startService(intIntent);
    }



    public static MainActivity  getInstace(){
        return ins;
    }

    public void updateTheTextView(final String t) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
       //         Toast.makeText(MainActivity.this, "MAIN: "+t, Toast.LENGTH_SHORT).show();

               /*
                   SharedPreferences sharedPreferences = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);

                 String aa  = sharedPreferences.getString(t, "default");
            //    Toast.makeText(MainActivity.this, "LENGTH  "+aa.length(), Toast.LENGTH_LONG).show();


                List<Response> res = new ArrayList<Response>();
               String[] itemWords =aa.split(";");
                String []test;
Response r;
                String [] dummy;
                for (int i = 0; i < itemWords.length; i++) {
                    dummy = new String[2];
                    dummy = itemWords[i].split(",");
                    r=new Response();
                    r.setTitle(dummy[0]);
                    r.setImageURL(dummy[1]);
                    res.add(r);
                }

                Toast.makeText(MainActivity.this, res.get(0).getTitle(), Toast.LENGTH_LONG).show();
                Toast.makeText(MainActivity.this, res.get(0).getImageURL(), Toast.LENGTH_LONG).show();

*/
                List<Response> resp = ShareData.getInstance().getData();
                AmazonFragment amazoneFragment  = AmazonFragment.newInstance(resp);
                (MainActivity.this).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flFragHolderCarOptions, amazoneFragment, CAR_OPTIONS_FRAGMENT_TAG)
                        .addToBackStack(CAR_OPTIONS_FRAGMENT_TAG)

                        .commit();

            }
        });
    }
}