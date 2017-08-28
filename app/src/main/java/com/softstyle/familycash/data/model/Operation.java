package com.softstyle.familycash.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
/**
 * Created by alexey on 09.08.2017.
 */

@AutoValue
public abstract class Operation implements Parcelable {
    public enum Sign {
        PLUS , MINUS;
        int sign() {
            if( this.equals(PLUS) ) return 1;
            else return -1;
        }
    }
    public abstract long    id();
    public abstract long    acc_id();
    public abstract String  name();
    public abstract long    item_id();

    public abstract Sign     sign();
    public abstract Double   sum();
    @Nullable public abstract long link_op_id();        // linked operation. Need for move money between accounts


    public static Operation create(long id, long acc_id, String name, long item_id, Sign sign, Double sum, long lo_id) {
        return new AutoValue_Operation(id, acc_id, name, item_id, sign, sum, lo_id);
    }

    public static TypeAdapter<Operation> typeAdapter(Gson gson) {
        return new AutoValue_Operation.GsonTypeAdapter(gson);
    }

}
