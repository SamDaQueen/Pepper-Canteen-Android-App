package com.mukess.android.pepper;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderHistory extends AppCompatActivity {
    ActionBar toolbar;
    myDBHandler dbHandler;
    Button clear;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        dbHandler = new myDBHandler(this, null, null, 1);

        toolbar = getSupportActionBar();
        if (toolbar != null)
            toolbar.setTitle("Order History");

        textView = findViewById(R.id.orderHistory);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            textView.setText(bundle.getString("OrderHistory"));

        clear = findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                dbHandler.deleteAll();
            }
        });
    }
}
