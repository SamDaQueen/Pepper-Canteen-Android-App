package com.mukess.android.pepper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private ListView listView;
    private MenuAdapter menuAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        listView = rootView.findViewById(R.id.cartitemView);
        List<MenuItem> menuItems = new ArrayList<>();
        menuAdapter = new MenuAdapter(getActivity(), R.layout.item_menu, menuItems);
        listView.setAdapter(menuAdapter);

        return rootView;
    }

    public void addToCart() {
    }

    public void removeFromCart() {

    }
}