package hassan.example.com.notificatoinlistener;

import android.app.Application;
import android.content.Context;

/**
 * Created by ut on 12/7/2016.
 */
public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}