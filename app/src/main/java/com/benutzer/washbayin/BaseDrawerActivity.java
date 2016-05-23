package com.benutzer.washbayin;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.benutzer.washbayin.views.DrawerListAdapter;

import butterknife.ButterKnife;
import butterknife.Bind;

/**
 * Created by amitesh on 1/3/16.
 */
public class BaseDrawerActivity extends AppCompatActivity {
    @Bind(R.id.drawer_layout) DrawerLayout _drawerLayout;
    //@Bind(R.id.drawerFragment) LinearLayout _drawerFragment;
    @Bind(R.id.drawerList) ListView _drawerList;

    private Toolbar toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    protected void onCreateDrawer(){
        ButterKnife.bind(this);
        toolbar = new Toolbar(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle((Activity) this, _drawerLayout, toolbar, 0, 0) {
            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(R.string.navDrawerMenu);
            }
        };
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        //toolbar.setNavigationIcon(R.drawable.ic_drawer);
        _drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        _drawerList = (ListView) findViewById(R.id.drawerList);
        _drawerList.setAdapter(new DrawerListAdapter(this, 0));

        _drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                _drawerLayout.closeDrawer(_drawerList);
                switchActivities(position);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void switchActivities(int position){
        switch (position){
            case 0:{
                break;
            }
            case 1:{
                break;
            }
            case 2:{
                break;
            }
            case 3:{
                break;
            }
            case 4:{
                break;
            }
            case 5:{
                break;
            }
            case 6:{
                break;
            }

        }
    }


}
