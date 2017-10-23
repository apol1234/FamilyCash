package com.softstyle.familycash.data;

/**
 * Created by alexey on 07.08.2017.
 */
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;
import com.softstyle.familycash.data.local.DatabaseHelper;
import com.softstyle.familycash.data.local.PreferencesHelper;
import com.softstyle.familycash.data.model.Account;
import com.softstyle.familycash.data.model.Operation;
import com.softstyle.familycash.data.model.Item;
//import uk.co.ribot.androidboilerplate.data.remote.RibotsService;

@Singleton
public class DataManager {

    //private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager( PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        //mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    /*
    public Observable<Ribot> syncRibots() {
        return mRibotsService.getRibots()
                .concatMap(new Func1<List<Ribot>, Observable<Ribot>>() {
                    @Override
                    public Observable<Ribot> call(List<Ribot> ribots) {
                        return mDatabaseHelper.setAccounts(ribots);
                    }
                });
    }
    */
    public Observable<List<Account>> getAccounts() {
        return mDatabaseHelper.getAccounts().distinct();
    }

    public Observable<List<Item>> getItems() {
        return mDatabaseHelper.getItems().distinct();
    }

    public Observable<List<Operation>> getOperations() {
        return mDatabaseHelper.getOperations().distinct();
    }

}

