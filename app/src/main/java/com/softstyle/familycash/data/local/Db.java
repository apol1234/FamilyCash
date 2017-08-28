package com.softstyle.familycash.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import com.softstyle.familycash.data.model.Account;
import com.softstyle.familycash.data.model.Item;
import com.softstyle.familycash.data.model.Operation;

import java.util.Date;

/**
 * Created by ap on 28.08.2017.
 */
public class Db {

    public Db() { }

    public abstract static class AccountTable {
        public static final String TABLE_NAME = "accounts";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CURRENCY = "currency";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_DATE_END = "date_end";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_CURRENCY + " TEXT NOT NULL, " +
                        COLUMN_AMOUNT + " INTEGER NOT NULL, " +             // in cents
                        COLUMN_DATE_END + " INTEGER  " +
                        " ); ";

        public static ContentValues toContentValues(Account account) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, account.id());
            values.put(COLUMN_NAME, account.name());
            values.put(COLUMN_CURRENCY, account.currency());
            values.put(COLUMN_AMOUNT, account.amount()*100);
            if( account.date_end() != null)  values.put(COLUMN_DATE_END, account.date_end().getTime());
            return values;
        }

        public static Account parseCursor(Cursor cursor) {
            Long deTime = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE_END));
            Date de = null;
            if(deTime != null) {
                de = new Date(deTime);
            }
            Account account = Account.create(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                                             cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                                             cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CURRENCY)),
                                             cursor.getInt(cursor.getColumnIndex(COLUMN_AMOUNT))/100.0,
                                             de
                                            );



            return account;
        }
    }

    public abstract static class ItemTable {
        public static final String TABLE_NAME = "items";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_PARENT_ID = "parent_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SIGN = "sign";
        public static final String COLUMN_DATE_END = "date_end";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_PARENT_ID + " INTEGER, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_SIGN + " INTEGER NOT NULL, " +
                        COLUMN_DATE_END + " INTEGER  " +
                        " ); ";

        public static ContentValues toContentValues(Item item) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, item.id());
            values.put(COLUMN_PARENT_ID, item.parent_id());
            values.put(COLUMN_NAME, item.name());
            values.put(COLUMN_SIGN, item.sign().sign());
            if( item.date_end() != null)  values.put(COLUMN_DATE_END, item.date_end().getTime());
            return values;
        }

        public static Item parseCursor(Cursor cursor) {
            Long deTime = cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_DATE_END));
            Date de = null;
            if(deTime != null) {
                de = new Date(deTime);
            }
            Item item = Item.create(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getLong(cursor.getColumnIndex(COLUMN_PARENT_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    Operation.Sign.fromInt(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SIGN))),
                    de
            );

            return item;
        }
    }
    public abstract static class OperationTable {
        public static final String TABLE_NAME = "operations";

        public static final String COLUMN_ID = "id";
        public static final String COLUMN_ACC_ID = "acc_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ITEM_ID = "item_id";
        public static final String COLUMN_SIGN = "sign";
        public static final String COLUMN_SUM = "sum";
        public static final String COLUMN_LINK_OP_ID = "link_op_id";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_ACC_ID + " INTEGER NOT NULL, " +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_ITEM_ID + " INTEGER NOT NULL, " +
                        COLUMN_SIGN + " INTEGER NOT NULL, " +
                        COLUMN_SUM + " INTEGER NOT NULL, " +
                        COLUMN_LINK_OP_ID + " INTEGER  " +
                        " ); ";

        public static ContentValues toContentValues(Operation oper) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, oper.id());
            values.put(COLUMN_ACC_ID, oper.acc_id());
            values.put(COLUMN_NAME, oper.name());
            values.put(COLUMN_ITEM_ID, oper.item_id());
            values.put(COLUMN_SIGN, oper.sign().sign());
            if( oper.link_op_id() != null)  values.put(COLUMN_LINK_OP_ID, oper.item_id());
            return values;
        }

        public static Operation parseCursor(Cursor cursor) {
            Operation oper = Operation.create(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getLong(cursor.getColumnIndex(COLUMN_ACC_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getLong(cursor.getColumnIndex(COLUMN_ITEM_ID)),
                    Operation.Sign.fromInt(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SIGN))),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_SUM))/100.0,
                    cursor.getLong(cursor.getColumnIndex(COLUMN_LINK_OP_ID))
            );

            return oper;
        }
    }
}

