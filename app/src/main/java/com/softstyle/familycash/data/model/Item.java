package com.softstyle.familycash.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;

/**
 * Created by ap on 28.08.2017.
 */
@AutoValue
public abstract class Item implements Parcelable {

    public abstract long            id();
    public abstract long            parent_id();                // we need items tree
    public abstract String          name();
    public abstract Operation.Sign  sign();                     // item may be for income or for expense
    @Nullable public abstract Date            date_end();                 // item has lifetime

    public static Item create(long id, long parent_id, String name, Operation.Sign sign, Date date_end) {
        return new AutoValue_Item(id, parent_id, name, sign, date_end);
    }

    public static TypeAdapter<Item> typeAdapter(Gson gson) {
        return new AutoValue_Item.GsonTypeAdapter(gson);
    }

}
