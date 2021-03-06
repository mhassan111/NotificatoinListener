package hassan.example.com.notificatoinlistener;

import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;


public class NotificationService extends NotificationListenerService {

    Context context;
    private int i;

    @Override

    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();

    }

    @Override

    public void onNotificationPosted(StatusBarNotification sbn) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            try {
                String pack = sbn.getPackageName();
                if (pack.equals("com.whatsapp")) {
                    String ticker = sbn.getNotification().tickerText.toString();
                    Bundle extras = sbn.getNotification().extras;
                    String title = extras.getString("android.title");
                    String text = extras.getCharSequence("android.text").toString();

                    Log.d("text ","android");
                    Log.d("dsaf","sdf");
                    String extaText = "";
                    Notification notification = sbn.getNotification();
                    int i = 0;
                    while (i < 3 && extaText.equals("")) {
                        CharSequence[] lines = notification.extras.getCharSequenceArray(Notification.EXTRA_TEXT_LINES);
                        if (lines != null) {
                            if (lines.length > 1)
                                extaText = lines[lines.length - 1].toString();
                        }
                        i++;
                    }
                    if (extaText.equals(""))
                        ticker = text;
                    else
                        ticker = extaText;

                    Log.i("Package", pack);
                    Log.i("Ticker", ticker);
                    Log.i("Title", title);

                } else {
                    String ticker = sbn.getNotification().tickerText.toString();
                    Bundle extras = sbn.getNotification().extras;
                    String title = extras.getString("android.title");
                    String text = extras.getCharSequence("android.text").toString();
                    String appName = getPackageName(pack);

                    Log.i("AppName",appName);
                    Log.i("Package", pack);
                    Log.i("Ticker", ticker);
                    Log.i("Title", title);
                    Log.i("Text", text);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("Msg", "Notification Removed");
    }

    private static String getPackageName(String packageName){
        PackageManager packageManager = MyApplication.getAppContext().getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
        } catch (final PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        final String title = (String)((applicationInfo != null) ? packageManager.getApplicationLabel(applicationInfo) : "???");
        return title;
    }

}