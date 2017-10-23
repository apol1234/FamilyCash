package com.softstyle.familycash.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.Drawer;
import com.softstyle.familycash.FamilycashApplication;
import com.softstyle.familycash.R;
import com.softstyle.familycash.ui.base.BaseActivity;
import com.softstyle.familycash.ui.base.BaseActivityDrawer;
import com.softstyle.familycash.ui.base.Toolbarable;
import com.softstyle.familycash.ui.navdrawer.NavDrawer;
import com.softstyle.familycash.util.DialogFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class AboutActivity extends BaseActivityDrawer {





    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "com.softstyle.familycash.ui.main.AboutActivity.EXTRA_TRIGGER_SYNC_FLAG";

    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, AboutActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

    }
}
