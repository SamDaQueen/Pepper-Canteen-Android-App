package com.mukess.android.pepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class CategoryFragment extends Fragment {

    Intent intent;

    LinearLayout tea;
    LinearLayout snacks;
    LinearLayout chat;
    LinearLayout frankie;
    LinearLayout south;
    LinearLayout lunch;
    LinearLayout sandwich;
    LinearLayout pavbhaji;
    LinearLayout chinese;
    LinearLayout salad;
    LinearLayout spldosa;
    LinearLayout tandoori;
    LinearLayout splpunjabi;
    LinearLayout punjabi;
    LinearLayout kofta;
    LinearLayout rice;
    LinearLayout starters;
    LinearLayout juice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        tea = rootView.findViewById(R.id.tea);
        snacks = rootView.findViewById(R.id.snacks);
        chat = rootView.findViewById(R.id.bombaychat);
        frankie = rootView.findViewById(R.id.frankie);
        south = rootView.findViewById(R.id.south);
        lunch = rootView.findViewById(R.id.lunch);
        sandwich = rootView.findViewById(R.id.sandwich);
        pavbhaji = rootView.findViewById(R.id.pavbhaji);
        chinese = rootView.findViewById(R.id.chinese);
        salad = rootView.findViewById(R.id.salad);
        spldosa = rootView.findViewById(R.id.spldosa);
        tandoori = rootView.findViewById(R.id.tandoori);
        splpunjabi = rootView.findViewById(R.id.splpunjabi);
        punjabi = rootView.findViewById(R.id.punjabi);
        tandoori = rootView.findViewById(R.id.tandoori);
        kofta = rootView.findViewById(R.id.kofta);
        rice = rootView.findViewById(R.id.rice);
        starters = rootView.findViewById(R.id.starters);
        juice = rootView.findViewById(R.id.juices);

        addListenerOnCategories();
        return rootView;
    }

    public void addListenerOnCategories() {
        intent = new Intent(getActivity(), MenuActivity.class);

        tea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "tea");
                startActivity(intent);
            }
        });
        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "snacks");
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "chat");
                startActivity(intent);
            }
        });
        frankie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "frankie");
                startActivity(intent);
            }
        });
        south.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "south");
                startActivity(intent);
            }
        });
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "lunch");
                startActivity(intent);
            }
        });
        sandwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "sandwich");
                startActivity(intent);
            }
        });
        pavbhaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "pavbhaji");
                startActivity(intent);
            }
        });
        chinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "chinese");
                startActivity(intent);
            }
        });
        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "salad");
                startActivity(intent);
            }
        });
        spldosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "spldosa");
                startActivity(intent);
            }
        });
        tandoori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "tandoori");
                startActivity(intent);
            }
        });
        splpunjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "splpunjabi");
                startActivity(intent);
            }
        });
        punjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "punjabi");
                startActivity(intent);
            }
        });
        kofta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "kofta");
                startActivity(intent);
            }
        });
        rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "rice");
                startActivity(intent);
            }
        });
        starters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "starters");
                startActivity(intent);
            }
        });
        juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("category", "juice");
                startActivity(intent);
            }
        });
    }
}