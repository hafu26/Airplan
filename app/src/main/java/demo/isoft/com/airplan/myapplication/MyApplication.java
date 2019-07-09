package demo.isoft.com.airplan.myapplication;

import android.app.Application;

import org.xutils.x;

/**
 * Created by hafu_16 on 2019/7/9.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
