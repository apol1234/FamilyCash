package com.softstyle.familycash.ui.mainmenu;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.view.MenuItem;

import com.mikepenz.materialdrawer.Drawer;
import com.softstyle.familycash.FamilycashApplication;
import com.softstyle.familycash.R;
import com.softstyle.familycash.injection.ActivityContext;
import com.softstyle.familycash.injection.ApplicationContext;
import com.softstyle.familycash.injection.component.ApplicationComponent;
import com.softstyle.familycash.ui.base.Toolbarable;
import com.softstyle.familycash.ui.main.AboutActivity;
import com.softstyle.familycash.ui.main.MainActivity;
import com.softstyle.familycash.ui.main.SettingsActivity;
import com.softstyle.familycash.ui.navdrawer.NavDrawer;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by ap on 20.10.2017.
 */

@Singleton
public class MainMenu {

    public enum IDS {
        ACCOUNTS(1),
        OPERS(2),
        TRANS(3),
        SETTINGS(4),
        ABOUT(8),
        EXIT(9);


        private final int value;

        private IDS(int value) {
            this.value = value;
        }
        IDS(long value) {
            this.value = (int)value;
        }
        public int getValue() {
            return value;
        }
        public static IDS fromInt(int id) {
            for (IDS type : IDS.values()) {
                if (type.getValue() == id) {
                    return type;
                }
            }
            return null;
        }
    }

    private static Context mContext;


    private static HashMap<IDS,MainMenuItem>  Items;


    @Inject
    public MainMenu(@ApplicationContext Context context) {

        mContext = context;
        Items = new HashMap<>(10);

        MainMenuItem mi = new MainMenuItem(IDS.ACCOUNTS,mContext.getResources().getString(R.string.drawer_item_accounts), R.drawable.ic_accounting, new MainMenuItem.StartActivity() {
            @Override
            public void start(Context ctx) {
                Intent it = MainActivity.getStartIntent(ctx, true);
                ctx.startActivity(it);
            }
        });
        Items.put(mi.getId(), mi);

        mi = new MainMenuItem(IDS.TRANS,mContext.getResources().getString(R.string.drawer_item_trans), R.drawable.ic_opers, new MainMenuItem.StartActivity() {
            @Override
            public void start(Context ctx) {
                Intent it = SettingsActivity.getStartIntent(ctx, true);
                ctx.startActivity(it);
            }
        });
        Items.put(mi.getId(), mi);

        mi = new MainMenuItem(IDS.SETTINGS,mContext.getResources().getString(R.string.drawer_item_settings), R.drawable.ic_settings, new MainMenuItem.StartActivity() {
            @Override
            public void start(Context ctx) {
                Intent it = SettingsActivity.getStartIntent(ctx, true);
                ctx.startActivity(it);
            }
        });
        Items.put(mi.getId(), mi);

        mi = new MainMenuItem(IDS.ABOUT,mContext.getResources().getString(R.string.drawer_item_about), R.drawable.ic_help, new MainMenuItem.StartActivity() {
            @Override
            public void start(Context ctx) {
                Intent it = AboutActivity.getStartIntent(ctx, true);
                ctx.startActivity(it);
            }
        });
        Items.put(mi.getId(), mi);


        mi = new MainMenuItem(IDS.EXIT,mContext.getResources().getString(R.string.drawer_item_exit), R.drawable.icon_exit, new MainMenuItem.StartActivity() {
            @Override
            public void start(Context ctx) {
                System.exit(0);
            }
        });
        Items.put(mi.getId(), mi);

/*

        mi = new MainMenuItem(2L, ctx.getResources().getText(R.string.drawer_item_trans).toString(), MainActivity.class);
        Items.put(mi.getId(), mi);

        mi = new MainMenuItem(3L, ctx.getResources().getText(R.string.drawer_item_settings).toString(), MainActivity.class);
        Items.put(mi.getId(), mi);

        mi = new MainMenuItem(4L, ctx.getResources().getText(R.string.drawer_item_about).toString(), MainActivity.class);
        Items.put(mi.getId(), mi);

        mi = new MainMenuItem(5L, ctx.getResources().getText(R.string.drawer_item_exit).toString(), MainActivity.class);
        Items.put(mi.getId(), mi);
*/

    }
    public static MainMenuItem getItem(MainMenu.IDS id) throws Exception {
        MainMenuItem mi = Items.get(id);
        if( mi == null ) {
            throw new Exception("Menu item not found!");
        }
        return mi;
    }

}
