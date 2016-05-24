package com.benutzer.washbayin.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.benutzer.washbayin.BaseDrawerActivity;
import com.benutzer.washbayin.BuildConfig;
import com.benutzer.washbayin.R;
import com.benutzer.washbayin.UserAPI.HTTPTasker;
import com.benutzer.washbayin.utilities.CommonUtils;
import com.benutzer.washbayin.utilities.HTTPResponseInterface;
import com.benutzer.washbayin.utilities.HTTPStructure;
import com.benutzer.washbayin.views.BucketAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;

public class BucketActivity extends BaseDrawerActivity implements HTTPResponseInterface{
    private CommonUtils commonUtils;

    @Bind(R.id.bucketList)ListView _listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);
        onCreateDrawer();
        Toolbar _bucketToolbar = (Toolbar) findViewById(R.id.bucket_toolbar);
        setSupportActionBar(_bucketToolbar);

        commonUtils = new CommonUtils(this.getApplicationContext());

        loadListOfTransactions();

    }

    public void loadListOfTransactions(){
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setUrl(BuildConfig.WB_LAAS_ADDRESS + "/w1/transaction");
        httpStructure.setMethod("GET");
        httpStructure.setContentType("application/json");
        httpStructure.setAuthToken(commonUtils.getSessionToken());

        HTTPTasker httpTasker = new HTTPTasker();
        httpTasker.responseInterface = this;
        httpTasker.execute(httpStructure, null, null);
    }

    @Override
    public void publishResponse(String response) {
        try{
            System.out.println(response);
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject[] = new JSONObject[jsonArray.length()];
            for(int i = 0; i < jsonArray.length(); i++)
                jsonObject[i] = jsonArray.getJSONObject(i);
            _listView.setAdapter(new BucketAdapter(this, jsonObject));
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bucket, menu);
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
