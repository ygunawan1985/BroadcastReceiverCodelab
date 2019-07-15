package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";
    private boolean headsetConnected = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String intentAction = intent.getAction();

        if(intentAction != null) {
            String toastMessage = "unknown intent action";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    int randomNum = 0;
                    int total = 0;
                    if(intent.hasExtra("randomNumber")){
                        randomNum = intent.getIntExtra("randomNumber", 0);
                        total = randomNum * randomNum;
                    }
                    toastMessage = "Custom Broadcast Received!\nSquare of the random number (" + randomNum + ") is: " + total;
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    if(intent.hasExtra("state")) {
                        if (headsetConnected && intent.getIntExtra("state", 0) == 0){
                            headsetConnected = false;
                            toastMessage = "Wired headset is disconnected";
                        } else if (!headsetConnected && intent.getIntExtra("state", 0) == 1){
                            headsetConnected = true;
                            toastMessage = "Wired headset is connected!";
                        }
                    }

                    break;
            }

            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
