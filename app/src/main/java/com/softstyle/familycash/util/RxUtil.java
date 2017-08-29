package com.softstyle.familycash.util;

/**
 * Created by ap on 29.08.2017.
 */

import rx.Subscription;

public class RxUtil {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}