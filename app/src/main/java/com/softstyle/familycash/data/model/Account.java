package com.softstyle.familycash.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by alexey on 08.08.2017.
 */



@AutoValue
public abstract class Account implements Parcelable {
    public abstract String first();
    public abstract String last();

    public static Account create(String first, String last) {
        return new AutoValue_Account(first, last);
    }

    public static TypeAdapter<Account> typeAdapter(Gson gson) {
        return new AutoValue_Account.GsonTypeAdapter(gson);
    }

}
