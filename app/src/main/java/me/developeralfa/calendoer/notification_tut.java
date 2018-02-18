package me.developeralfa.calendoer;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class notification_tut extends AppCompatActivity {
    Button skip,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_tut);
        skip = findViewById(R.id.skiprem);
        next = findViewById(R.id.nextrem);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(notification_tut.this,allTasks.class));
                finish();

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(notification_tut.this,recurring_tut.class));
                finish();
            }
        });
    }

}
