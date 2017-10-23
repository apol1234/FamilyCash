package com.softstyle.familycash.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.softstyle.familycash.injection.ApplicationContext;

/**
 * Created by ap on 28.08.2017.
 */



@Singleton
public class DbOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "familycash.db";
    public static final int DATABASE_VERSION = 2;

    @Inject
    public DbOpenHelper(@ApplicationContext Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(Db.AccountTable.CREATE);
            db.execSQL(Db.ItemTable.CREATE);
            db.execSQL(Db.OperationTable.CREATE);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("insert into " + Db.AccountTable.TABLE_NAME + "(id,name,currency,amount) values (1,'a1','rur','0.0');");
    }

}

