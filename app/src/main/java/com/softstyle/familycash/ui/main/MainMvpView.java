package com.softstyle.familycash.ui.main;

/**
 * Created by ap on 29.08.2017.
 */

import java.util.List;

import com.softstyle.familycash.data.model.Account;
import com.softstyle.familycash.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showAccounts(List<Account> accounts);

    void showAccountsEmpty();

    void showError();

}
