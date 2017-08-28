package com.softstyle.familycash.data.local;



import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.softstyle.familycash.injection.ApplicationContext;

/**
 * Created by ap on 28.08.2017.
 */

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "familycash_pref_file";

    private final SharedPreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

}
