package com.softstyle.familycash.injection.component;

/**
 * Created by ap on 29.08.2017.
 */

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.softstyle.familycash.data.DataManager;
//import com.softstyle.familycash.data.SyncService;
import com.softstyle.familycash.data.local.DatabaseHelper;
import com.softstyle.familycash.data.local.PreferencesHelper;
//import com.softstyle.familycash.data.remote.FamilycashService;
import com.softstyle.familycash.injection.ApplicationContext;
import com.softstyle.familycash.injection.module.ApplicationModule;
import com.softstyle.familycash.ui.mainmenu.MainMenu;
import com.softstyle.familycash.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    //void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    //RibotsService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();
    MainMenu mainMenu();
}
