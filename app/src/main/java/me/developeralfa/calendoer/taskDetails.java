package me.developeralfa.calendoer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class taskDetails extends AppCompatActivity {
    TextView tNamedet,descdet;
    Intent parent;
    Button back,edit;
    ListView listComments;
    ArrayList<String> comments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
         parent = getIntent();
        tNamedet = findViewById(R.id.tNamedet);
        descdet = findViewById(R.id.descdet);
        tNamedet.setText(parent.getStringExtra("tNamedet"));
        descdet.setText(parent.getStringExtra("descdet"));
        back = findViewById(R.id.back);
        listComments = findViewById(R.id.listerComments);

        edit = findViewById(R.id.edit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(taskDetails.this,taskForm.class);
                i.putExtra("tName",parent.getStringExtra("tNamedet"));
                i.putExtra("desc",parent.getStringExtra("descdet"));
                startActivityForResult(i,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1)
        {
            if(resultCode==1)
            {
                setResult(1,data);
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
