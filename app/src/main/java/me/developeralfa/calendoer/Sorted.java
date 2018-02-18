package me.developeralfa.calendoer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sorted extends AppCompatActivity {
    Button button;
    Intent parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted);
        button = findViewById(R.id.pending);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sorted.this,threeimp_tut.class));
                finish();
            }
        });

    }
}
