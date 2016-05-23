package com.benutzer.washbayin.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.benutzer.washbayin.R;

/**
 * Created by amitesh on 5/3/16.
 */
public class DrawerListAdapter extends ArrayAdapter{
    private ImageView imageView;
    private TextView textView;

    public DrawerListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View contextView = layoutInflater.inflate(R.layout.custom_drawer_list, parent, false);

        imageView = (ImageView) contextView.findViewById(R.id.drawerListImageId);
        textView = (TextView) contextView.findViewById(R.id.drawerListTextId);

        setImageTextToView(position);

        return contextView;
    }

    public void setImageTextToView(int pos){
        switch (pos){
            case 0:{
                imageView.setImageResource(R.drawable.icon_about);
                textView.setText("About");
                break;
            }
            case 1:{
                imageView.setImageResource(R.drawable.icon_home);
                textView.setText("Home");
                break;
            }
            case 2:{
                imageView.setImageResource(R.drawable.icon_bucket);
                textView.setText("My Bucket");
                break;
            }
            case 3:{
                imageView.setImageResource(R.drawable.icon_history);
                textView.setText("History");
                break;
            }
            case 4:{
                imageView.setImageResource(R.drawable.icon_faq);
                textView.setText("F.A.Q");
                break;
            }
            case 5:{
                imageView.setImageResource(R.drawable.icon_wallet);
                textView.setText("Wallets");
                break;
            }
            case 6:{
                imageView.setImageResource(R.drawable.icon_feedback);
                textView.setText("Feedback");
                break;
            }
        }

    }
}
