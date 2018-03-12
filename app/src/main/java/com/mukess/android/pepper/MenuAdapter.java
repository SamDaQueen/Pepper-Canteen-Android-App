package com.mukess.android.pepper;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Samreen on 11-03-2018.
 */

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    public MenuAdapter(Context context, int resource, List<MenuItem> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, @Nullable ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_menu, parent, false);
        }

        TextView Name = convertView.findViewById(R.id.itemName);
        TextView Price = convertView.findViewById(R.id.itemPrice);

        MenuItem item = getItem(position);

        Name.setText(item.getName());
        Price.setText(String.valueOf(item.getPrice()));

        return convertView;
    }
}
