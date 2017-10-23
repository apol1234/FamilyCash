package com.softstyle.familycash.ui.navdrawer;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.softstyle.familycash.R;
import com.softstyle.familycash.ui.base.BaseActivityDrawer;
import com.softstyle.familycash.ui.base.Toolbarable;
import com.softstyle.familycash.ui.mainmenu.MainMenu;
import com.softstyle.familycash.ui.mainmenu.MainMenuItem;

/**
 * Created by ap on 19.10.2017.
 */

public class NavDrawer {

    private static PrimaryDrawerItem makeItemP(MainMenuItem mi) {
        return new PrimaryDrawerItem().withIdentifier(mi.getIntId()).withName(mi.getName()).withIcon(mi.getIconId()).withIconTintingEnabled(true);
    }
    private static SecondaryDrawerItem makeItemS(MainMenuItem mi) {
        return new SecondaryDrawerItem().withIdentifier(mi.getIntId()).withName(mi.getName()).withIcon(mi.getIconId()).withIconTintingEnabled(true);
    }

    public static <A extends BaseActivityDrawer> Drawer MakeDrawer(final A activity, MainMenu mm) throws Exception {

        PrimaryDrawerItem accounts =  makeItemP(mm.getItem(MainMenu.IDS.ACCOUNTS));
        PrimaryDrawerItem settings =  makeItemP(mm.getItem(MainMenu.IDS.SETTINGS));
        PrimaryDrawerItem exit = makeItemP(mm.getItem(MainMenu.IDS.EXIT));
        PrimaryDrawerItem trans = makeItemP(mm.getItem(MainMenu.IDS.TRANS));
        SecondaryDrawerItem about = makeItemS(mm.getItem(MainMenu.IDS.ABOUT));


        Drawer mDrawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(activity.GetToolbar())
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        accounts,
                        trans,
                        new DividerDrawerItem(),
                        settings,
                        new SectionDrawerItem().withName(R.string.drawer_item_helppart),
                        about,
                        new DividerDrawerItem(),
                        exit
                ).withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Скрываем клавиатуру при открытии Navigation Drawer
                        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }

                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {

                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        MainMenu.IDS id = MainMenu.IDS.fromInt((int) drawerItem.getIdentifier());
                        MainMenuItem mi = null;
                        try {
                            mi = MainMenu.getItem(id);
                        } catch (Exception e) {
                            mi = null;
                        }
                        mi.exec(activity);
                        return false;
                    }
                })
                .build();
        return mDrawer;
    }
}
