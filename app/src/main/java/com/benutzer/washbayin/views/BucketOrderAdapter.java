package com.benutzer.washbayin.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.benutzer.washbayin.R;
import com.benutzer.washbayin.utilities.CommonUtils;

import org.json.JSONObject;

/**
 * Created by amitesh on 24/5/16.
 */
public class BucketOrderAdapter extends ArrayAdapter {
    JSONObject[] jO;
    public BucketOrderAdapter(Context ctx, Object[] array){
        super(ctx, R.layout.custom_order_bucket, array);

        jO = (JSONObject[]) array;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View contextView = layoutInflater.inflate(R.layout.custom_order_bucket, parent, false);

        try{
            TextView textViewOrderId = (TextView) contextView.findViewById(R.id.transactionBucketOrderId);
            TextView textViewStatus = (TextView) contextView.findViewById(R.id.statusTransBucketOrderId);
            TextView textViewtos = (TextView) contextView.findViewById(R.id.tosTransBucketOrderId);
            TextView textViewQuantity = (TextView) contextView.findViewById(R.id.quantityTransBucketOrderId);
            TextView textViewCost = (TextView) contextView.findViewById(R.id.costTransBucketOrderId);
            TextView textViewOt = (TextView) contextView.findViewById(R.id.otypeTransBucketOrderId);

            textViewOrderId.setText(jO[position].getString("_id"));
            if(Integer.parseInt(jO[position].getString("status").toString()) == 0)
                textViewStatus.setText("Status : Bucket");
            else if(Integer.parseInt(jO[position].getString("status").toString()) == 1)
                textViewStatus.setText("Status : Order Placed");
            else if(Integer.parseInt(jO[position].getString("status").toString()) > 8)
                textViewStatus.setText("Status : Complete");
            else
                textViewStatus.setText("Status : Pending");

            if(Integer.parseInt(jO[position].getString("tos").toString()) == 0)
                textViewtos.setText("TOS : Wash And Fold");
            else if(Integer.parseInt(jO[position].getString("tos").toString()) == 1)
                textViewtos.setText("TOS : Iron");
            else if(Integer.parseInt(jO[position].getString("tos").toString()) == 2)
                textViewStatus.setText("TOS : Wash And Iron");
            else if(Integer.parseInt(jO[position].getString("tos").toString()) == 3)
                textViewStatus.setText("TOS : Dry Clean");

            if(Integer.parseInt(jO[position].getString("otype").toString()) == 0)
                textViewOt.setText("Selective Order");
            else if(Integer.parseInt(jO[position].getString("otype").toString()) == 1)
                textViewOt.setText("Order : Common");
            else if(Integer.parseInt(jO[position].getString("otype").toString()) == 2)
                textViewOt.setText("Bulk Order");

            textViewCost.setText("Cost : " + jO[position].getString("cost"));
            textViewQuantity.setText("Quantity : " + jO[position].getString("quantity"));

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return contextView;
    }
}
