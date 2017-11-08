package tang.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by 唐
 * on 2017/11/6.
 */

public class NetworkReceiver extends BroadcastReceiver {

    private NetworkChangedEvent event;

    public NetworkReceiver(NetworkChangedEvent event) {
        this.event = event;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case ConnectivityManager.CONNECTIVITY_ACTION:
                int networkState = NetUtil.getNetworkState(context);
                event.networkChange(networkState);
                Log.d("network:", String.valueOf(networkState));
                break;
        }
    }
}
