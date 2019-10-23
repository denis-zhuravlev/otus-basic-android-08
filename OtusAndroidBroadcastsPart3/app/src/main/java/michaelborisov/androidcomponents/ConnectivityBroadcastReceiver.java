package michaelborisov.androidcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class ConnectivityBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("CONNECTCTIVITY_ACTION", intent.getAction());
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            if (isWiFi) {
                Toast.makeText(context, "WiFi On", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "WiFi Off", Toast.LENGTH_LONG).show();
            }
        }
    }
}
