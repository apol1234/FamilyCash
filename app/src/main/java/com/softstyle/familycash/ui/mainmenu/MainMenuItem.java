package com.softstyle.familycash.ui.mainmenu;

import android.content.Context;

import com.softstyle.familycash.ui.base.BaseActivity;

/**
 * Created by ap on 20.10.2017.
 */

public  class MainMenuItem <A extends BaseActivity> {
    private MainMenu.IDS mId;
    private String  mName;
    private StartActivity mStartActivity;
    private int mIconId;

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int mIconId) {
        this.mIconId = mIconId;
    }

    public interface StartActivity {
        void start(Context ctx);
    }

    public MainMenuItem(MainMenu.IDS id, String name, int icon, StartActivity sa) {
        this.setId(id);
        this.setName(name);
        this.setIconId(icon);
        this.mStartActivity = sa;
    }

    public void exec(Context ctx) {
        mStartActivity.start(ctx);
    }

    public MainMenu.IDS getId() {
        return mId;
    }
    public int getIntId() {
        return mId.getValue();
    }

    public void setId(MainMenu.IDS Id) {
        this.mId = Id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String Name) {
        this.mName = Name;
    }



    public void setActivity(Class<A> a) {

    }
}