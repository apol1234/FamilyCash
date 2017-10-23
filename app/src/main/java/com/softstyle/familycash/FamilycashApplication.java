package com.softstyle.familycash;


/**
 * Created by ap on 29.08.2017.
 */

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import timber.log.Timber;
import com.softstyle.familycash.injection.component.ApplicationComponent;
import com.softstyle.familycash.injection.component.DaggerApplicationComponent;
import com.softstyle.familycash.injection.module.ApplicationModule;

public class FamilycashApplication extends Application  {

    ApplicationComponent mApplicationComponent;
    private static FamilycashApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    public static FamilycashApplication get(Context context) {
        return (FamilycashApplication) context.getApplicationContext();
    }

    public static FamilycashApplication get() {
        return mApp;
    }
    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
