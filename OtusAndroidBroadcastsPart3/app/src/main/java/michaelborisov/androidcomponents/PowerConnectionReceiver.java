package michaelborisov.androidcomponents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class PowerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(
                context, intent.getAction(), Toast.LENGTH_LONG
        ).show();
    }
}
