package com.mukess.android.pepper;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    public static boolean checker = false;
    public static float total = 0;
    static ArrayList<MenuItem> ordered = new ArrayList<>(20);
    TextView cartTotal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        cartTotal = rootView.findViewById(R.id.total);
        updateTotal();

        Bundle args = getArguments();
        ArrayList<MenuItem> finalCartItems = new ArrayList<>(20);
        //Toast.makeText(getActivity(), String.valueOf(checker), Toast.LENGTH_SHORT).show();
        if (checker) {
            if (args != null)
                if (args.getParcelableArrayList("final_order") != null)
                    finalCartItems = args.getParcelableArrayList("final_order");
            assert finalCartItems != null;
            for (MenuItem menuItem : finalCartItems)   //a foreach loop
                if (menuItem.getQuantity() != 0)
                    ordered.add(menuItem);
            checker = false;
        }
        ListView listView = rootView.findViewById(R.id.cartitemView);
        MenuAdapter menuAdapter = new MenuAdapter(getActivity(), R.layout.item_menu, ordered);
        listView.setAdapter(menuAdapter);
        return rootView;
    }

    void updateTotal() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        if (getActivity() == null)
                            return;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String cart = " Total: " + getResources().getString(R.string.rs) + total;
                                cartTotal.setText(cart);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();
    }

}