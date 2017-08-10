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
    public abstract long id();
    public abstract String name();
    public abstract String currency();
    public abstract Double amount();            // initial sum

    public static Account create(long id, String name, String cur, Double amount) {
        return new AutoValue_Account(id, name, cur, amount);
    }

    public static TypeAdapter<Account> typeAdapter(Gson gson) {
        return new AutoValue_Account.GsonTypeAdapter(gson);
    }

}
