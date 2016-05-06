package in.vaksys.realmexample;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;

import br.com.goncalves.pugnotification.notification.PugNotification;
import io.realm.Realm;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class NotificationService extends IntentService {
    public static int serviceRunning = 0;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            Task results1 = realm.allObjects(Task.class)
                    .where().contains("title", "hello now time is :").findFirst();
            fireNotification(results1);

        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        serviceRunning = 1;
        return serviceRunning;
    }

    @Override
    public void onDestroy() {
        serviceRunning = 0;
    }

    private void fireNotification(Task drop) {
        String message = getString(R.string.notif_message) + "\"" + drop.getDescription() + "\"";
        PugNotification.with(this)
                .load()
                .title(R.string.notif_title)
                .message("asjdhajsgdags")
                .bigTextStyle("ksdfjgskdajfjhsdgfkj dshgfkj sdafsdka sdjfg sdjkfg aksjdfgs dkjfhsdkfj sdfkj gsdakfjhjks jd" +
                        " sdfajkgsdj agfjksdg fsdjkf sdhf kjsdafkjsda gfsdjkfg jsdafds " +
                        "fds fsdgfjhgsdjfkhgsdlf sdkajfgsdjkah fsdjkafhg ds" +
                        "f sdkjafhsdkjahfkjsdhfkjsd fsdfsd")
                .smallIcon(R.mipmap.ic_launcher)
                .largeIcon(R.mipmap.ic_launcher)
                .flags(Notification.DEFAULT_ALL)
                .autoCancel(true)
                .click(MainActivity.class)
                .simple()
                .build();
    }
}
