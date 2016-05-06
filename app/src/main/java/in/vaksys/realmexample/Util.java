package in.vaksys.realmexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by vivz on 31/12/15.
 */
public class Util {
    public static final int DELAY = 1000;
    public static final int FREQUENCY = 1000 * 10;

    public static void scheduleAlarm(Context context) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, NotificationService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, DELAY, FREQUENCY, pendingIntent);
    }
}
