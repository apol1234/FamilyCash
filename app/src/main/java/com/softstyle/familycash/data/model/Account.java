package com.softstyle.familycash.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;

/**
 * Created by alexey on 08.08.2017.
 */



@AutoValue
public abstract class Account implements Parcelable {
    public abstract long id();
    public abstract String name();
    public abstract String currency();
    public abstract Double amount();            // initial sum
    @Nullable public abstract Date    date_end();         // account has lifetime

    public static Account create(long id, String name, String cur, Double amount, Date date_end) {
        return new AutoValue_Account(id, name, cur, amount, date_end);
    }

    public static TypeAdapter<Account> typeAdapter(Gson gson) {
        return new AutoValue_Account.GsonTypeAdapter(gson);
    }

}
