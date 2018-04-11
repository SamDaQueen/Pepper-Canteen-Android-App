package com.mukess.android.pepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.mukess.android.pepper.CartFragment.checker;

public class CategoryFragment extends Fragment {

    static ArrayList<MenuItem> menuItems;
    static MenuAdapter menuAdapter;
    public DatabaseReference hintDatabaseReference;
    protected Intent intent;
    protected TextView notea, nosnacks, nochat, nofrankie, nosouth, nolunch, nosandwich, nopavbhaji, nochinese, nosalad;
    protected TextView nospldosa, notandoori, nosplpunjabi, nopunjabi, nokofta, norice, nostarters, nojuice;
    protected LinearLayout tea, snacks, chat, frankie, south, lunch, sandwich, pavbhaji, chinese, salad;
    protected LinearLayout spldosa, tandoori, splpunjabi, punjabi, kofta, rice, starters, juice;
    protected FirebaseDatabase firebaseDatabase;
    protected DatabaseReference teadatabaseReference, snacksdatabaseReference, chatdatabaseReference, frankiedatabaseReference;
    protected DatabaseReference southdatabaseReference, lunchdatabaseReference, sandwichdatabaseReference;
    protected DatabaseReference pavbhajidatabaseReference, chinesedatabaseReference, saladdatabaseReference;
    protected DatabaseReference spldosadatabaseReference, tandooridatabaseReference, splpunjabidatabaseReference;
    protected DatabaseReference punjabidatabaseReference, koftadatabaseReference, ricedatabaseReference;
    protected DatabaseReference starterdatabaseReference, juicesdatabaseReference;
    SearchView searchView;
    ListView listView;
    ScrollView scrollView;
    private ChildEventListener childEventListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();

        searchView = rootView.findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchQuery();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listView.setVisibility(View.VISIBLE);
                return false;
            }
        });

        //the number of items
        notea = rootView.findViewById(R.id.noTea);
        nosnacks = rootView.findViewById(R.id.noSnacks);
        nochat = rootView.findViewById(R.id.noChat);
        nofrankie = rootView.findViewById(R.id.noFrankie);
        nosouth = rootView.findViewById(R.id.noSouth);
        nolunch = rootView.findViewById(R.id.noLunch);
        nosandwich = rootView.findViewById(R.id.noSandwich);
        nopavbhaji = rootView.findViewById(R.id.noPavbhaji);
        nochinese = rootView.findViewById(R.id.noChinese);
        nosalad = rootView.findViewById(R.id.noSalad);
        nospldosa = rootView.findViewById(R.id.noSplDosa);
        notandoori = rootView.findViewById(R.id.noTandoori);
        nosplpunjabi = rootView.findViewById(R.id.noSplPunjabi);
        nopunjabi = rootView.findViewById(R.id.noPunjabi);
        notandoori = rootView.findViewById(R.id.noTandoori);
        nokofta = rootView.findViewById(R.id.noKofta);
        norice = rootView.findViewById(R.id.noRice);
        nostarters = rootView.findViewById(R.id.noStarters);
        nojuice = rootView.findViewById(R.id.noJuice);

        //database references for each category
        teadatabaseReference = firebaseDatabase.getReference("Tea & Coffee");
        snacksdatabaseReference = firebaseDatabase.getReference("Snacks");
        chatdatabaseReference = firebaseDatabase.getReference("Bombay Chat");
        frankiedatabaseReference = firebaseDatabase.getReference("Frankie");
        southdatabaseReference = firebaseDatabase.getReference("South Indian");
        lunchdatabaseReference = firebaseDatabase.getReference("Lunch & Dinner");
        sandwichdatabaseReference = firebaseDatabase.getReference("Sandwich");
        pavbhajidatabaseReference = firebaseDatabase.getReference("Pav Bhaji");
        chinesedatabaseReference = firebaseDatabase.getReference("Chinese Dishes");
        saladdatabaseReference = firebaseDatabase.getReference("Curd, Salad and Fruits");
        spldosadatabaseReference = firebaseDatabase.getReference("Special Dosa");
        tandooridatabaseReference = firebaseDatabase.getReference("Tandoori");
        splpunjabidatabaseReference = firebaseDatabase.getReference("Special Punjabi");
        punjabidatabaseReference = firebaseDatabase.getReference("Punjabi");
        koftadatabaseReference = firebaseDatabase.getReference("Kofta Dishes");
        ricedatabaseReference = firebaseDatabase.getReference("Basmati Rice");
        starterdatabaseReference = firebaseDatabase.getReference("Starters");
        juicesdatabaseReference = firebaseDatabase.getReference("Juices And Shakes");

        //updating the number of items
        updateCount(teadatabaseReference, notea);
        updateCount(snacksdatabaseReference, nosnacks);
        updateCount(chatdatabaseReference, nochat);
        updateCount(frankiedatabaseReference, nofrankie);
        updateCount(southdatabaseReference, nosouth);
        updateCount(lunchdatabaseReference, nolunch);
        updateCount(sandwichdatabaseReference, nosandwich);
        updateCount(pavbhajidatabaseReference, nopavbhaji);
        updateCount(chinesedatabaseReference, nochinese);
        updateCount(saladdatabaseReference, nosalad);
        updateCount(spldosadatabaseReference, nospldosa);
        updateCount(tandooridatabaseReference, notandoori);
        updateCount(splpunjabidatabaseReference, nosplpunjabi);
        updateCount(punjabidatabaseReference, nopunjabi);
        updateCount(koftadatabaseReference, nokofta);
        updateCount(ricedatabaseReference, norice);
        updateCount(starterdatabaseReference, nostarters);
        updateCount(juicesdatabaseReference, nojuice);

        //LinearLayouts
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
        kofta = rootView.findViewById(R.id.kofta);
        rice = rootView.findViewById(R.id.rice);
        starters = rootView.findViewById(R.id.starters);
        juice = rootView.findViewById(R.id.juices);

        searchView.setQueryHint("Search from " + 220 + " items");
        listView = rootView.findViewById(R.id.searchListView);
        listView.setVisibility(View.INVISIBLE);
        menuItems = new ArrayList<>();
        menuAdapter = new MenuAdapter(getActivity(), R.layout.item_menu, menuItems);
        listView.setAdapter(menuAdapter);

        scrollView = rootView.findViewById(R.id.scrollView2);
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                scrollView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.INVISIBLE);
            }
        });
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

    public void updateCount(DatabaseReference mdatabaseReference, final TextView textView) {
        mdatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String count = dataSnapshot.getChildrenCount() + " items";
                textView.setText(count);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void searchQuery() {
        checker = true;
        scrollView.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
        //menuAdapter.add();
        String hint = searchView.getQuery().toString().toLowerCase();
        if (hint.contains("tea") || hint.contains("coffee") || hint.contains("beverages")) {
            hintDatabaseReference = firebaseDatabase.getReference("Tea & Coffee");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("snacks") || hint.contains("samosa") || hint.contains("idli") || hint.contains("vada") || hint.contains("idli")) {
            hintDatabaseReference = firebaseDatabase.getReference("Snacks");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("chat") || hint.contains("chaat") || hint.contains("bhel")) {
            hintDatabaseReference = firebaseDatabase.getReference("Bombay Chat");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("frankie")) {
            hintDatabaseReference = firebaseDatabase.getReference("Frankie");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("south indian") || hint.contains("uttapa") || hint.contains("chaat")) {
            hintDatabaseReference = firebaseDatabase.getReference("South Indian");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("lunch") || hint.contains("dinner")) {
            hintDatabaseReference = firebaseDatabase.getReference("Lunch & Dinner");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("sandwich") || hint.contains("bread") || hint.contains("toast")) {
            hintDatabaseReference = firebaseDatabase.getReference("Sandwich");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("pav") || hint.contains("bhaji")) {
            hintDatabaseReference = firebaseDatabase.getReference("Pav Bhaji");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("chinese") || hint.contains("chilli") || hint.contains("soup") || hint.contains("schezwan") || hint.contains("triple") || hint.contains("noodles")) {
            hintDatabaseReference = firebaseDatabase.getReference("Chinese Dishes");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("salad") || hint.contains("raita") || hint.contains("fruits")) {
            hintDatabaseReference = firebaseDatabase.getReference("Curd, Salad & Fruits");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("dosa")) {
            hintDatabaseReference = firebaseDatabase.getReference("Special Dosa");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("paratha") || hint.contains("naan") || hint.contains("roti") || hint.contains("kulcha") || hint.contains("tandoori")) {
            hintDatabaseReference = firebaseDatabase.getReference("Tandoori");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("paneer")) {
            hintDatabaseReference = firebaseDatabase.getReference("Punjabi");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("dal") || hint.contains("aloo") || hint.contains("masala") || hint.contains("punjabi")) {
            hintDatabaseReference = firebaseDatabase.getReference("Special Punjabi");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("kofta")) {
            hintDatabaseReference = firebaseDatabase.getReference("Kofta Dishes");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("rice") || hint.contains("biryani") || hint.contains("pulao") || hint.contains("khichdi")) {
            hintDatabaseReference = firebaseDatabase.getReference("Basmati Rice");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("chilly") || hint.contains("crispy") || hint.contains("starters")) {
            hintDatabaseReference = firebaseDatabase.getReference("Starters");
            attachDatabaseReadListener();
            return;
        }
        if (hint.contains("juice") || hint.contains("shake") || hint.contains("lassi") || hint.contains("chass")) {
            hintDatabaseReference = firebaseDatabase.getReference("Juices And Shakes");
            attachDatabaseReadListener();
        } else {
            Toast.makeText(getActivity(), "Item Not Found", Toast.LENGTH_SHORT).show();
            menuAdapter.clear();
        }
    }

    private void attachDatabaseReadListener() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MenuItem menuItem = dataSnapshot.getValue(MenuItem.class);
                    menuAdapter.add(menuItem);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            hintDatabaseReference.addChildEventListener(childEventListener);
        }
    }

}