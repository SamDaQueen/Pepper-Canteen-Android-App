package com.mukess.android.pepper;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Samreen on 11-03-2018.
 */

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    Context context;
    List<MenuItem> object;

    public MenuAdapter(Context context, int resource, List<MenuItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.object = objects;
    }

    @Nullable
    @Override
    public View getView(final int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_menu, parent, false);
        }

        TextView Name = convertView.findViewById(R.id.itemName);
        TextView Price = convertView.findViewById(R.id.itemPrice);
        final TextView Quantity = convertView.findViewById(R.id.itemQuantity);

        Button increment = convertView.findViewById(R.id.increment);
        Button decrement = convertView.findViewById(R.id.decrement);

        final MenuItem item = getItem(position);

        Name.setText(item.getName());
        Price.setText(String.valueOf(item.getPrice()));
        Quantity.setText(String.valueOf(item.getQuantity()));

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(Quantity.getText().toString());
                if (count < 25) {
                    count++;
                    Quantity.setText(String.valueOf(count));

                    MenuItem item1 = item;
                    item1.setQuantity(count);

                    object.set(position, item1);
                    ((MenuActivity) context).newList(object);
                } else
                    Toast.makeText(getContext(), "Quantity > 25? Invite us to the party :)", Toast.LENGTH_LONG).show();
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(Quantity.getText().toString());
                if (count > 0) {
                    Quantity.setText(String.valueOf(--count));

                    MenuItem item1 = item;
                    item1.setQuantity(--count);

                    object.set(position, item1);
                    ((MenuActivity) context).newList(object);
                } else
                    Toast.makeText(getContext(), "Quantity < 0? You're trying to sell??", Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }
}
