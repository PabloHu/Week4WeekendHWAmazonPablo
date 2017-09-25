package com.example.admin.week4weekendhwamazonpablo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.week4weekendhwamazonpablo.model.Response;

import java.util.ArrayList;
import java.util.List;
import android.content.SharedPreferences;

public class MyDynamicReceiver extends BroadcastReceiver {
    private static final String MY_PREF_FILE = "com.example.admin.week4weekendhwamazonpablo";
    @Override
    public void onReceive(Context context, Intent intent) {
    //    Log.d(TAG, "onReceive: ");

        String toastString = "";
        switch (intent.getAction()) {
            /*
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
                if (isAirplaneModeOn)
                    toastString = "Airplane on";
                    // Toast.makeText(context, "Airplane on", Toast.LENGTH_SHORT).show();

                else
                    toastString = "aurplane off";
                //  Toast.makeText(context, "Airplaine OFF", Toast.LENGTH_SHORT).show();
                break;
*/
            case "myActionTest":
                toastString = "from action test0";
                String dataKey = intent.getStringExtra("dataListKey");

                toastString = dataKey;



             //   SharedPreferences sharedPreferences = getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);

               // String aa  = sharedPreferences.getString(dataKey, "default");

               // toastString = aa.substring(1,10);
                try {
                    MainActivity  .getInstace().updateTheTextView(toastString);
                } catch (Exception e) {

                }

                    // gatherList = (List<Response>) intent.getExtras().getSerializable("dataList");
               // toastString = gatherList.size()+"";
               // toastString = intent.getStringExtra("data");//, Toast.LENGTH_SHORT).show();
                break;
        }
        Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();

    }




}
