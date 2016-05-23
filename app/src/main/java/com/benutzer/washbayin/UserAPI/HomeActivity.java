package com.benutzer.washbayin.UserAPI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.benutzer.washbayin.BaseDrawerActivity;
import com.benutzer.washbayin.R;
import com.benutzer.washbayin.activities.PriceListActivity;
import com.benutzer.washbayin.activities.SelectOrderActivity;
import com.benutzer.washbayin.fragments.HomeBelowFragments;
import com.benutzer.washbayin.utilities.CommonUtils;

public class HomeActivity extends BaseDrawerActivity implements HomeBelowFragments.SendHandlersToHome{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        super.onCreateDrawer();
        Toolbar _homeToolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(_homeToolbar);
        super.onCreateDrawer();
        CommonUtils commonUtils = new CommonUtils(this.getApplicationContext());
        Toast.makeText(this, commonUtils.getSessionToken(), Toast.LENGTH_SHORT).show();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.nextingLabelHome);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SelectOrderActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void handleIntentCreationHome(String message) {
        Intent intent = new Intent(HomeActivity.this, PriceListActivity.class);
        intent.putExtra("type", message);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
