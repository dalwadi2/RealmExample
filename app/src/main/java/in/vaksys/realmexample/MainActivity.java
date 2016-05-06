package in.vaksys.realmexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "harsh";
    private Realm realm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        Task t = realm.createObject(Task.class);

        t.setId(UUID.randomUUID().toString());
        t.setTitle("hello now timmmeeee is :" + new Date().getTime());
        t.setDescription("this is new description");
        Log.e(TAG, "onCreate: " + "commited");
        realm.commitTransaction();

        /***
         * here for all records
         */
        RealmResults<Task> results = realm.allObjects(Task.class);

        for (Task task : results) {
            Log.d(TAG, "onCreate: " + String.format("ID : %s , Title : %s , Des : %s "
                    , task.getId(), task.getTitle(), task.getDescription()));
        }

        /***
         *  here for single record....
         */
        Task results1 = realm.allObjects(Task.class)
                .where().contains("title", "hello now time is :").findFirst();

        try {
            Log.d(TAG, "onCreate111: " + results1.toString());
            Log.d(TAG, "onCreate11: " + String.format("ID : %s , Title : %s , Des : %s "
                    , results1.getId(), results1.getTitle(), results1.getDescription()));
        } catch (Exception e) {
            Log.e(TAG, "onCreate: " + e.getMessage());
        }
        String message = getString(R.string.notif_message) + "\"";

    }


    @Override
    protected void onStop() {
        super.onStop();
        realm.close();
    }
}
