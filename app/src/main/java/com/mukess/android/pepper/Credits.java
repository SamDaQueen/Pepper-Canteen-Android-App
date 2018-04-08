package com.mukess.android.pepper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class Credits extends AppCompatActivity {
    LinearLayout aditiL, rheaL, samreenL, dishantL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        linkedinLinks();
    }

    private void linkedinLinks() {
        aditiL = findViewById(R.id.aditi);
        rheaL = findViewById(R.id.rhea);
        samreenL = findViewById(R.id.samreen);
        dishantL = findViewById(R.id.dishant);

        aditiL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getResources().getString(R.string.aditi)));
                startActivity(intent);
            }
        });
        rheaL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getResources().getString(R.string.rhea)));
                startActivity(intent);
            }
        });
        samreenL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getResources().getString(R.string.samreen)));
                startActivity(intent);
            }
        });
        dishantL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getResources().getString(R.string.dishant)));
                startActivity(intent);
            }
        });
    }
}
