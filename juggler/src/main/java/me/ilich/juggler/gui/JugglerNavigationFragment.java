package me.ilich.juggler.gui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;

import me.ilich.juggler.Log;
import me.ilich.juggler.R;

public abstract class JugglerNavigationFragment extends JugglerFragment {

    private static final String ARG_SELECTED_ITEM = "selected_item";
    private static final String ARG_DRAWER_INDICATOR_ENABLED = "drawer_indicator_enabled";

    protected static Bundle addSelectedItemToBundle(@Nullable Bundle bundle, int itemIndex) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(ARG_SELECTED_ITEM, itemIndex);
        return bundle;
    }

    protected static Bundle addDrawerIndicatorEnabled(@Nullable Bundle bundle, boolean b) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(ARG_DRAWER_INDICATOR_ENABLED, b);
        return bundle;
    }

    private int defaultSelectedItem = 0;
    private boolean drawerIndicatorEnabled = true;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    public JugglerNavigationFragment() {
        super();
    }

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            defaultSelectedItem = getArguments().getInt(ARG_SELECTED_ITEM, 0);
            drawerIndicatorEnabled = getArguments().getBoolean(ARG_DRAWER_INDICATOR_ENABLED, true);
        }
    }

    protected final int getDefaultSelectedItem() {
        return defaultSelectedItem;
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = (DrawerLayout) getActivity().findViewById(getDrawerLayoutId());
        drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, getOpen(), getClose());
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        drawerToggle.setDrawerIndicatorEnabled(drawerIndicatorEnabled);
    }

    protected DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    @IdRes
    protected int getDrawerLayoutId() {
        return R.id.drawer_layout;
    }

    @StringRes
    protected int getOpen() {
        return R.string.drawer_open;
    }

    @StringRes
    protected int getClose() {
        return R.string.drawer_close;
    }

    @Override
    @CallSuper
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    @CallSuper
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean b = drawerToggle.onOptionsItemSelected(menuItem);
        if (!b) {
            b = super.onOptionsItemSelected(menuItem);
        }
        return b;
    }

    public void close() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

}
