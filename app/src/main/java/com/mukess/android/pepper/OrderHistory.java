package com.mukess.android.pepper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class OrderHistory extends AppCompatActivity {
    static final int Dialog_id = 0;
    ActionBar toolbar;
    myDBHandler dbHandler;
    Button clear, date, all;
    int year_x, month_x, day_x;
    private TextView textView;
    private String history = "";

    private DatePickerDialog.OnDateSetListener dateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            Toast.makeText(OrderHistory.this, "Selected date is " + day_x + "/" + month_x + "/" + year_x, Toast.LENGTH_SHORT).show();
            StringBuffer onDate = dbHandler.databaseOnDate(day_x + "/" + month_x + "/" + year_x);
            textView.setText(onDate);
        }
    };

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
            history = bundle.getString("OrderHistory");
        textView.setText(history);

        clear = findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                dbHandler.deleteAll();
            }
        });

        all = findViewById(R.id.all);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.databaseToString();
                textView.setText(history);
            }
        });

        showDialogForDate();
        final Calendar calendar = Calendar.getInstance();
        year_x = calendar.get(Calendar.YEAR);
        month_x = calendar.get(Calendar.MONTH);
        day_x = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void showDialogForDate() {
        date = findViewById(R.id.datePicker);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(Dialog_id);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == Dialog_id)
            return new DatePickerDialog(this, dateSetListener, year_x, month_x, day_x);
        else
            return null;
    }
}
