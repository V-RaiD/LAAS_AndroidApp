package com.benutzer.washbayin.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.benutzer.washbayin.BaseDrawerActivity;
import com.benutzer.washbayin.R;
import com.benutzer.washbayin.UserAPI.HTTPTasker;
import com.benutzer.washbayin.utilities.CommonUtils;
import com.benutzer.washbayin.utilities.HTTPResponseInterface;
import com.benutzer.washbayin.utilities.HTTPStructure;

import org.json.JSONObject;

import java.io.BufferedWriter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SelectOrderActivity extends BaseDrawerActivity implements HTTPResponseInterface{
    private int orderType = 0;
    private CommonUtils commonUtilities;

    @Bind(R.id.selectOrderCardId )LinearLayout _selectiveOrder;
    @Bind(R.id.selectOrderCardIdTwo )LinearLayout _commonOrder;
    @Bind(R.id.selectOrderCardIdThree)LinearLayout _bulkOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_order);
        super.onCreateDrawer();

        ButterKnife.bind(this);

        commonUtilities = new CommonUtils(this.getApplicationContext());
        Toolbar _selectToolbar = (Toolbar) findViewById(R.id.select_order_toolbar);
        setSupportActionBar(_selectToolbar);
        super.onCreateDrawer();
        CommonUtils commonUtils = new CommonUtils(this.getApplicationContext());
        Toast.makeText(this, commonUtils.getSessionToken(), Toast.LENGTH_SHORT).show();

        handleOnclicks();
    }

    private void handleOnclicks(){
        _selectiveOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderType = 0;
                createTransactionInstance();
            }
        });
        _commonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderType = 1;
                createTransactionInstance();
            }
        });
        _bulkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderType = 2;
                createTransactionInstance();
            }
        });
    }

    private void createTransactionInstance(){
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setMethod("POST");
        httpStructure.setContentType("application/json");
        httpStructure.setAuthToken(commonUtilities.getSessionToken());

        HTTPTasker httpTasker = new HTTPTasker();
        httpTasker.responseInterface = this;
        httpTasker.execute(httpStructure, null, null);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_order, menu);
        return true;
    }

    @Override
    public void publishResponse(String response) {
        try{
            JSONObject jsonObject = new JSONObject(response);
            switch(orderType){
                case 0 : {
                    Intent intent = new Intent(SelectOrderActivity.this, SelectiveActivity.class);
                    intent.putExtra("tid", jsonObject.getString("_id"));
                    startActivity(intent);
                    
                    break;
                }
                case 1 : {
                    Intent intent = new Intent(SelectOrderActivity.this, CommonOrder.class);
                    intent.putExtra("tid", jsonObject.getString("_id"));
                    startActivity(intent);

                    break;
                }
                case 2 : {
                    Intent intent = new Intent(SelectOrderActivity.this, BulkActivity.class);
                    intent.putExtra("tid", jsonObject.getString("_id"));
                    startActivity(intent);

                    break;
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
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
