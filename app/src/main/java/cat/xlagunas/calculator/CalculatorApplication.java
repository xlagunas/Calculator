package cat.xlagunas.calculator;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by xlagunas on 30/04/16.
 */
public class CalculatorApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
