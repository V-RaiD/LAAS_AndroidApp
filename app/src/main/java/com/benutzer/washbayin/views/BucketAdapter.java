package com.benutzer.washbayin.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.benutzer.washbayin.R;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by amitesh on 24/5/16.
 */
public class BucketAdapter extends ArrayAdapter {
    JSONObject jsonArray[];
    Context ctx;
    public BucketAdapter(Context ctx, Object[] array){
        super(ctx, R.layout.custom_bucket, array);

        this.ctx = ctx;
        jsonArray = (JSONObject[]) array;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View contextView = layoutInflater.inflate(R.layout.custom_bucket, parent, false);
        try{
            TextView textViewTransId = (TextView) contextView.findViewById(R.id.transactionBucketId);
            TextView textViewStatus = (TextView) contextView.findViewById(R.id.statusTransBucketId);
            ListView listView = (ListView) contextView.findViewById(R.id.bucketAdapListId);

            textViewTransId.setText(jsonArray[position].getString("_id").toString());
            if(Integer.parseInt(jsonArray[position].getString("status").toString()) == 0)
                textViewStatus.setText("Bucket");
            else if(Integer.parseInt(jsonArray[position].getString("status").toString()) == 1)
                textViewStatus.setText("Order Placed");
            else if(Integer.parseInt(jsonArray[position].getString("status").toString()) > 8)
                textViewStatus.setText("Complete");
            else
                textViewStatus.setText("Pending");

            JSONArray jA = new JSONArray(jsonArray[position].getString("order"));
            System.out.println(jA);
            JSONObject jO[] = new JSONObject[jA.length()];
            for(int i = 0; i < jA.length(); i++){
                jO[i] = jA.getJSONObject(i);
            }

            listView.setAdapter(new BucketOrderAdapter(ctx, jO));
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return contextView;
    }
}
