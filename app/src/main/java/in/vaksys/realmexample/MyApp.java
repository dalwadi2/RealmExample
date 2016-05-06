package in.vaksys.realmexample;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Harsh on 25-03-2016.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .name("mydb.realm")
                .schemaVersion(41)
                .build();
        Realm.setDefaultConfiguration(configuration);

/*      Realm with Eencryption ....
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .encryptionKey(key)
                .build();

        Realm realm = Realm.getInstance(config);
        */
    }
}
