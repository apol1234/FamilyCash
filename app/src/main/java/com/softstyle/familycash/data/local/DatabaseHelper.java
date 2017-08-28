package com.softstyle.familycash.data.local;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import com.softstyle.familycash.data.model.Account;
import com.softstyle.familycash.data.model.Item;
import com.softstyle.familycash.data.model.Operation;


/**
 * Created by ap on 28.08.2017.
 */


@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        SqlBrite.Builder briteBuilder = new SqlBrite.Builder();
        mDb = briteBuilder.build().wrapDatabaseHelper(dbOpenHelper, Schedulers.immediate());
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public Observable<Account> setAccounts(final Collection<Account> newAccounts) {
        return Observable.create(new Observable.OnSubscribe<Account>() {
            @Override
            public void call(Subscriber<? super Account> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.AccountTable.TABLE_NAME, null);
                    for (Account account : newAccounts) {
                        long result = mDb.insert(Db.AccountTable.TABLE_NAME,
                                Db.AccountTable.toContentValues(account),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(account);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Account>> getAccounts() {
        return mDb.createQuery(Db.AccountTable.TABLE_NAME,
                "SELECT * FROM " + Db.AccountTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Account>() {
                    @Override
                    public Account call(Cursor cursor) {
                        return Db.AccountTable.parseCursor(cursor);
                    }
                });
    }

    public Observable<Item> setItems(final Collection<Item> newItems) {
        return Observable.create(new Observable.OnSubscribe<Item>() {
            @Override
            public void call(Subscriber<? super Item> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.ItemTable.TABLE_NAME, null);
                    for (Item Item : newItems) {
                        long result = mDb.insert(Db.ItemTable.TABLE_NAME,
                                Db.ItemTable.toContentValues(Item),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(Item);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }



    public Observable<List<Item>> getItems() {
        return mDb.createQuery(Db.ItemTable.TABLE_NAME,
                "SELECT * FROM " + Db.ItemTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Item>() {
                    @Override
                    public Item call(Cursor cursor) {
                        return Db.ItemTable.parseCursor(cursor);
                    }
                });
    }

    public Observable<Operation> setOperations(final Collection<Operation> newOperations) {
        return Observable.create(new Observable.OnSubscribe<Operation>() {
            @Override
            public void call(Subscriber<? super Operation> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.OperationTable.TABLE_NAME, null);
                    for (Operation Operation : newOperations) {
                        long result = mDb.insert(Db.OperationTable.TABLE_NAME,
                                Db.OperationTable.toContentValues(Operation),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(Operation);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }



    public Observable<List<Operation>> getOperations() {
        return mDb.createQuery(Db.OperationTable.TABLE_NAME,
                "SELECT * FROM " + Db.OperationTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Operation>() {
                    @Override
                    public Operation call(Cursor cursor) {
                        return Db.OperationTable.parseCursor(cursor);
                    }
                });
    }    

}

