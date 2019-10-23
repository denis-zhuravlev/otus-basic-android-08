package michaelborisov.androidcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AirplaneBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("FLIGHT_MODE_ACTION", intent.getAction());
        boolean isFlightModeOn = intent.getBooleanExtra("state", false);
        if(isFlightModeOn) {
            Toast.makeText(context, "FlightMode On", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "FlightMode Off", Toast.LENGTH_LONG).show();
        }
    }
}
