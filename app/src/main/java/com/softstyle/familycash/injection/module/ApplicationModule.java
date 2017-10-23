package com.softstyle.familycash.injection.module;

/**
 * Created by ap on 29.08.2017.
 */
import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
//import com.softstyle.familycash.data.remote.FamilycashService;
import com.softstyle.familycash.injection.ApplicationContext;
import com.softstyle.familycash.ui.mainmenu.MainMenu;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    /*
    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }
    */

    @Provides
    @Singleton
    MainMenu provideMainMenu() {
        return new MainMenu(provideContext());
    }

}
