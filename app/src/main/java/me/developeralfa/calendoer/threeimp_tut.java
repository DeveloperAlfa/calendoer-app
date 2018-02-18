package me.developeralfa.calendoer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class threeimp_tut extends AppCompatActivity {
    Button skip,next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threeimp_tut);
        skip = findViewById(R.id.skip3imp);
        next = findViewById(R.id.next3imp);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(threeimp_tut.this,allTasks.class));
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(threeimp_tut.this,notification_tut.class));
                finish();
            }
        });

    }
}
