package com.softstyle.familycash.injection.component;

/**
 * Created by ap on 29.08.2017.
 */
import android.media.audiofx.BassBoost;

import dagger.Subcomponent;
import com.softstyle.familycash.injection.PerActivity;
import com.softstyle.familycash.injection.module.ActivityModule;
import com.softstyle.familycash.ui.main.AboutActivity;
import com.softstyle.familycash.ui.main.MainActivity;
import com.softstyle.familycash.ui.main.SettingsActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(SettingsActivity mainActivity);
    void inject(AboutActivity mainActivity);
}
