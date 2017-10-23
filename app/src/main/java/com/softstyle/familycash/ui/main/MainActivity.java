package com.softstyle.familycash.ui.main;

/**
 * Created by ap on 29.08.2017.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

//import com.idescout.sql.SqlScoutServer;
import com.mikepenz.materialdrawer.Drawer;
import com.softstyle.familycash.FamilycashApplication;
import com.softstyle.familycash.R;
//import com.softstyle.familycash.data.SyncService;
import com.softstyle.familycash.data.model.Account;
import com.softstyle.familycash.injection.component.ApplicationComponent;
import com.softstyle.familycash.ui.base.BaseActivity;
import com.softstyle.familycash.ui.base.BaseActivityDrawer;
import com.softstyle.familycash.ui.base.Toolbarable;
import com.softstyle.familycash.ui.mainmenu.MainMenu;
import com.softstyle.familycash.ui.navdrawer.NavDrawer;
import com.softstyle.familycash.util.DialogFactory;

public class MainActivity extends BaseActivityDrawer implements MainMvpView, Toolbarable {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.softstyle.familycash.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject MainPresenter mMainPresenter;
    @Inject AccountsAdapter mAccountsAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;



    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(mAccountsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        mMainPresenter.loadAccounts();

        /*
        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }
        */
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showAccounts(List<Account> accounts) {
        mAccountsAdapter.setAccounts(accounts);
        mAccountsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_accounts))
                .show();
    }

    @Override
    public void showAccountsEmpty() {
        mAccountsAdapter.setAccounts(Collections.<Account>emptyList());
        mAccountsAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_accounts, Toast.LENGTH_LONG).show();
    }

}
