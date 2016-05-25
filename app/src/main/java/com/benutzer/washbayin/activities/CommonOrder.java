package com.benutzer.washbayin.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.benutzer.washbayin.BaseDrawerActivity;
import com.benutzer.washbayin.BuildConfig;
import com.benutzer.washbayin.R;
import com.benutzer.washbayin.UserAPI.HTTPTasker;
import com.benutzer.washbayin.utilities.CommonUtils;
import com.benutzer.washbayin.utilities.HTTPResponseInterface;
import com.benutzer.washbayin.utilities.HTTPStructure;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CommonOrder extends BaseDrawerActivity implements HTTPResponseInterface{
    private int washDetGiven = 0;
    private CommonUtils commonUtils;
    private int washNum = 0;
    private int washIronNum = 0;
    private int ironNum = 0;
    private int dryCleanNum = 0;
    private String tid = "";
    private String cwash = "";
    private String cwi = "";
    private String ciron = "";
    private String cdry = "";
    private String jsonC = "";

    @Bind(R.id.nextingLabelCommon)LinearLayout _nextingCommon;
    @Bind(R.id.commonOrderEditId1)EditText _editText1;
    @Bind(R.id.commonOrderEditId2)EditText _editText2;
    @Bind(R.id.commonOrderEditId3)EditText _editText3;
    @Bind(R.id.commonOrderEditId4)EditText _editText4;
    //@Bind(R.id.commonOrderRelative5id)RelativeLayout _chooseWash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_order);
        super.onCreateDrawer();
        Toolbar _coToolbar = (Toolbar) findViewById(R.id.common_order_toolbar);
        setSupportActionBar(_coToolbar);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                tid= null;
            } else {
                tid= extras.getString("tid");
                jsonC = extras.getString("jsonC");
            }
        } else {
            tid= (String) savedInstanceState.getSerializable("tid");
            jsonC = (String) savedInstanceState.getSerializable("jsonC");
        }
        JSONObject jO = null;
        try{
            jO = new JSONObject(jsonC);


        cwash = jO.getString("cwash");
        cwi = jO.getString("cwi");
        ciron = jO.getString("ciron");
        cdry = jO.getString("cdry");
        System.out.println(cwash + "cw  cwi" + cwi + "cwi ciron" + ciron + "ciron cdry" + cdry);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        commonUtils = new CommonUtils(this.getApplicationContext());
        ButterKnife.bind(this);

        handleClickEvents();

    }

    private void handleClickEvents(){
//        _chooseWash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        _nextingCommon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!_editText1.getText().toString().isEmpty())
                    washNum = Integer.parseInt((_editText1.getText().toString()));
                if(!_editText2.getText().toString().isEmpty())
                    washIronNum = Integer.parseInt((_editText2.getText().toString()));
                if(!_editText3.getText().toString().isEmpty())
                    ironNum = Integer.parseInt((_editText3.getText().toString()));
                if(!_editText4.getText().toString().isEmpty())
                    dryCleanNum = Integer.parseInt((_editText4.getText().toString()));
                sendWashOrder();
                sendWIOrder();
                sendIronOrder();
                sendDryOrder();
                Intent intent = new Intent(CommonOrder.this, BucketActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    private void sendWashOrder(){
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setUrl(BuildConfig.WB_LAAS_ADDRESS + "/w1/order/" + tid);
        httpStructure.setMethod("PUT");
        httpStructure.setContentType("application/json");
        httpStructure.setAuthToken(commonUtils.getSessionToken());

        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("item", cwash.toString());
            jsonObject.put("tos", 0);
            jsonObject.put("tow", 0);
            jsonObject.put("quantity",washNum);
            jsonObject.put("status", null);
            jsonObject.put("cost", null);
            jsonObject.put("orderType", 1);
            jsonObject.put("sagent", null);
            jsonObject.put("delvagent", null);
            System.out.println(jsonObject);
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        httpStructure.setDataJson(jsonObject.toString());
        HTTPTasker httpTasker = new HTTPTasker();
        httpTasker.responseInterface = this;
        httpTasker.execute(httpStructure, null, null);
    }

    private void sendWIOrder(){
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setUrl(BuildConfig.WB_LAAS_ADDRESS + "/w1/order/" + tid);
        httpStructure.setMethod("PUT");
        httpStructure.setContentType("application/json");
        httpStructure.setAuthToken(commonUtils.getSessionToken());

        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("item", cwi.toString());
            jsonObject.put("tos", 1);
            jsonObject.put("tow", 0);
            jsonObject.put("quantity",washIronNum);
            jsonObject.put("status", null);
            jsonObject.put("cost", null);
            jsonObject.put("orderType", 1);
            jsonObject.put("sagent", null);
            jsonObject.put("delvagent", null);
            System.out.println(jsonObject);
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        httpStructure.setDataJson(jsonObject.toString());
        HTTPTasker httpTasker = new HTTPTasker();
        httpTasker.responseInterface = this;
        httpTasker.execute(httpStructure, null, null);
    }

    private void sendIronOrder(){
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setUrl(BuildConfig.WB_LAAS_ADDRESS + "/w1/order/" + tid);
        httpStructure.setMethod("PUT");
        httpStructure.setContentType("application/json");
        httpStructure.setAuthToken(commonUtils.getSessionToken());

        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("item", ciron.toString());
            jsonObject.put("tos", 2);
            jsonObject.put("tow", 0);
            jsonObject.put("quantity",ironNum);
            jsonObject.put("status", null);
            jsonObject.put("cost", null);
            jsonObject.put("orderType", 1);
            jsonObject.put("sagent", null);
            jsonObject.put("delvagent", null);
            System.out.println(jsonObject);
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        httpStructure.setDataJson(jsonObject.toString());
        HTTPTasker httpTasker = new HTTPTasker();
        httpTasker.responseInterface = this;
        httpTasker.execute(httpStructure, null, null);
    }

    private  void sendDryOrder(){
        HTTPStructure httpStructure = new HTTPStructure();
        httpStructure.setUrl(BuildConfig.WB_LAAS_ADDRESS + "/w1/order/" + tid);
        httpStructure.setMethod("PUT");
        httpStructure.setContentType("application/json");
        httpStructure.setAuthToken(commonUtils.getSessionToken());

        JSONObject jsonObject = null;
        try{
            jsonObject = new JSONObject();
            jsonObject.put("item", cdry.toString());
            jsonObject.put("tos", 3);
            jsonObject.put("tow", 0);
            jsonObject.put("quantity",dryCleanNum);
            jsonObject.put("status", null);
            jsonObject.put("cost", null);
            jsonObject.put("orderType", 1);
            jsonObject.put("sagent", null);
            jsonObject.put("delvagent", null);
            System.out.println(jsonObject);
        }catch(JSONException ex){
            ex.printStackTrace();
        }
        httpStructure.setDataJson(jsonObject.toString());
        HTTPTasker httpTasker = new HTTPTasker();
        httpTasker.responseInterface = this;
        httpTasker.execute(httpStructure, null, null);
    }

    @Override
    public void publishResponse(String response) {
        Toast.makeText(this, response, Toast.LENGTH_SHORT);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_common_order, menu);
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
