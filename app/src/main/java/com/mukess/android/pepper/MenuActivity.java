package com.mukess.android.pepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

import static com.mukess.android.pepper.CartFragment.checker;
import static com.mukess.android.pepper.CartFragment.total;

public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";
    static ArrayList<MenuItem> menuItems;
    ActionBar toolbar;
    private MenuAdapter menuAdapter;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private TextView textView;
    //private AdView mAdView;
    //App ID: ca-app-pub-4677501330220530~1273349880

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//                //Toast.makeText(getApplicationContext(), "Ad closed", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdFailedToLoad(int i) {
//                super.onAdFailedToLoad(i);
//                //Toast.makeText(getApplicationContext(), "Ad failed to load", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                super.onAdLeftApplication();
//                //Toast.makeText(getApplicationContext(), "Ad left application", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onAdOpened() {
//                super.onAdOpened();
//            }
//
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//            }
//
//            @Override
//            public void onAdClicked() {
//                super.onAdClicked();
//            }
//        });
//        mAdView.loadAd(adRequest);

        Bundle bundle = getIntent().getExtras();
        toolbar = getSupportActionBar();
        assert toolbar != null;
        toolbar.setDisplayHomeAsUpEnabled(true);

        ListView listView = findViewById(R.id.menuitemView);
        menuItems = new ArrayList<>();
        menuAdapter = new MenuAdapter(this, R.layout.item_menu, menuItems);
        listView.setAdapter(menuAdapter);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        assert bundle != null;
        switch (Objects.requireNonNull(bundle.getString("category"))) {
            case "tea":
                toolbar.setTitle("Tea & Coffee");
                databaseReference = firebaseDatabase.getReference().child("Tea & Coffee");
                break;
            case "snacks":
                toolbar.setTitle("Snacks");
                databaseReference = firebaseDatabase.getReference().child("Snacks");
                break;
            case "chat":
                toolbar.setTitle("Bombay Chat");
                databaseReference = firebaseDatabase.getReference().child("Bombay Chat");
                break;
            case "frankie":
                toolbar.setTitle("Frankie");
                databaseReference = firebaseDatabase.getReference().child("Frankie");
                break;
            case "south":
                toolbar.setTitle("South Indian");
                databaseReference = firebaseDatabase.getReference().child("South Indian");
                break;
            case "lunch":
                toolbar.setTitle("Lunch & Dinner");
                databaseReference = firebaseDatabase.getReference().child("Lunch & Dinner");
                break;
            case "sandwich":
                toolbar.setTitle("Sandwich");
                databaseReference = firebaseDatabase.getReference().child("Sandwich");
                break;
            case "pavbhaji":
                toolbar.setTitle("Pav Bhaji");
                databaseReference = firebaseDatabase.getReference().child("Pav Bhaji");
                break;
            case "chinese":
                toolbar.setTitle("Chinese Dishes");
                databaseReference = firebaseDatabase.getReference().child("Chinese Dishes");
                break;
            case "salad":
                toolbar.setTitle("Curd, Salad & Fruits");
                databaseReference = firebaseDatabase.getReference().child("Curd, Salad and Fruits");
                break;
            case "spldosa":
                toolbar.setTitle("Special Dosa");
                databaseReference = firebaseDatabase.getReference().child("Special Dosa");
                break;
            case "tandoori":
                toolbar.setTitle("Tandoori");
                databaseReference = firebaseDatabase.getReference().child("Tandoori");
                break;
            case "splpunjabi":
                toolbar.setTitle("Special Punjabi");
                databaseReference = firebaseDatabase.getReference().child("Special Punjabi");
                break;
            case "punjabi":
                toolbar.setTitle("Punjabi");
                databaseReference = firebaseDatabase.getReference().child("Punjabi");
                break;
            case "kofta":
                toolbar.setTitle("Kofta Dishes");
                databaseReference = firebaseDatabase.getReference().child("Kofta Dishes");
                break;
            case "rice":
                toolbar.setTitle("Basmati Rice");
                databaseReference = firebaseDatabase.getReference().child("Basmati Rice");
                break;
            case "starters":
                toolbar.setTitle("Starters");
                databaseReference = firebaseDatabase.getReference().child("Starters");
                break;
            case "juice":
                toolbar.setTitle("Juices And Shakes");
                databaseReference = firebaseDatabase.getReference().child("Juices And Shakes");
                break;
        }
        textView = findViewById(R.id.totalMenu);
        updateTotal();
        attachDatabaseReadListener();
        checker = true;
        //Toast.makeText(this, String.valueOf(checker), Toast.LENGTH_SHORT).show();

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
            databaseReference.addChildEventListener(childEventListener);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //sending the MenuAdapter object to MainActivity
        onPause();
        return true;
    }

    private void updateTotal() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        MenuActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String cart = " " + getResources().getString(R.string.rs) + total;
                                textView.setText(cart);
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

    @Override
    protected void onPause() {
        super.onPause();
        //mAdView.pause();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("order", menuAdapter.getCart());
        startActivity(new Intent(this, MainActivity.class).putExtra("items", bundle));
    }

}