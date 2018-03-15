package com.mukess.android.pepper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ActionBar toolbar;
    List<MenuItem> objects;
    private ListView listView;
    private MenuAdapter menuAdapter;
    private Button goTocart;

    //Firebase DB
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle bundle = getIntent().getExtras();
        toolbar = getSupportActionBar();

        listView = findViewById(R.id.menuitemView);
        List<MenuItem> menuItems = new ArrayList<>();
        //ArrayList<MenuItem> menuItems = getIntent().getParcelableArrayListExtra("cartItems");
        menuAdapter = new MenuAdapter(this, R.layout.item_menu, menuItems);
        listView.setAdapter(menuAdapter);

        firebaseDatabase = FirebaseDatabase.getInstance();

        switch (bundle.getString("category")) {
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
                toolbar.setTitle("Salad");
                databaseReference = firebaseDatabase.getReference().child("Salad");
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
        attachDatabaseReadListener();
        GoToCart();
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

    public void newList(List<MenuItem> obj) {
        objects = obj;
    }

    public void GoToCart() {
        goTocart = findViewById(R.id.goToCart);
        goTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });
    }
}