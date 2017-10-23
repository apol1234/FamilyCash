package com.softstyle.familycash.ui.main;

/**
 * Created by ap on 29.08.2017.
 */


import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import com.softstyle.familycash.data.DataManager;
import com.softstyle.familycash.data.model.Account;
import com.softstyle.familycash.injection.ConfigPersistent;
import com.softstyle.familycash.ui.base.BasePresenter;
import com.softstyle.familycash.util.RxUtil;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadAccounts() {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getAccounts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Account>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Account> accounts) {
                        if (accounts.isEmpty()) {
                            getMvpView().showAccountsEmpty();
                        } else {
                            getMvpView().showAccounts(accounts);
                        }
                    }
                });
    }

}
