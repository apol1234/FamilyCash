package com.softstyle.familycash.ui.base;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.Drawer;
import com.softstyle.familycash.FamilycashApplication;
import com.softstyle.familycash.R;
import com.softstyle.familycash.ui.navdrawer.NavDrawer;
import com.softstyle.familycash.util.DialogFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by ap on 23.10.2017.
 */

public class BaseActivityDrawer extends BaseActivity {
    @BindView(R.id.toolbar2)
    protected Toolbar mToolBar;

    protected Drawer mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        try {
            mDrawer = NavDrawer.MakeDrawer(this, FamilycashApplication.get().getComponent().mainMenu());
        }
        catch(Exception e) {
            Timber.e(e, "There was an error loading the main menu.");
            DialogFactory.createGenericErrorDialog(this, "There was an error loading the main menu.").show();;
        }

    }


    public Toolbar GetToolbar() {
        return mToolBar;
    }

    @Override
    public void onBackPressed() {
        // Закрываем Navigation Drawer по нажатию системной кнопки "Назад" если он открыт
        if (mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
