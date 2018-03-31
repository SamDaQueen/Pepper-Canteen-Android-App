package com.mukess.android.pepper;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.mukess.android.pepper.CartFragment.total;

/**
 * Created by Samreen on 11-03-2018.
 */

public class MenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> object;

    MenuAdapter(Context context, int resource, ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        this.object = objects;
    }

    @NonNull
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

        assert item != null;
        Name.setText(item.getName());
        final String price = convertView.getResources().getString(R.string.rs) + String.valueOf(item.getPrice());

        Price.setText(price);
        Quantity.setText(String.valueOf(item.getQuantity()));

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(Quantity.getText().toString());
                if (count < 25) {
                    count++;
                    Quantity.setText(String.valueOf(count));

                    item.setQuantity(count);
                    object.set(position, item);

                    total += Float.parseFloat(String.valueOf(item.getPrice()));
                } else
                    Toast.makeText(getContext(), "Quantity > 25? Invite us to the party :)", Toast.LENGTH_LONG).show();
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(Quantity.getText().toString());
                if (count > 0) {
                    count--;
                    Quantity.setText(String.valueOf(count));

                    item.setQuantity(count);
                    object.set(position, item);

                    total -= Float.parseFloat(String.valueOf(item.getPrice()));
                } else
                    Toast.makeText(getContext(), "Quantity < 0? You're trying to sell??", Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }

    ArrayList<MenuItem> getCart() {
        return object;
    }

}
