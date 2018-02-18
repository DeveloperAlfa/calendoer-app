package me.developeralfa.calendoer;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class taskForm extends AppCompatActivity {
    EditText tName,desc;
    Button Submit;
    RadioGroup due;
    RadioButton selected;
    String task,description,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskform);
        Intent parent = getIntent();

        tName = findViewById(R.id.tName);
        desc = findViewById(R.id.desc);
        due = findViewById(R.id.due);
        selected = findViewById(due.getCheckedRadioButtonId());
        tName.setText(parent.getStringExtra("tName"));
        desc.setText(parent.getStringExtra("desc"));
        Submit = findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task= tName.getText().toString();
                description = desc.getText().toString();

                Intent data = new Intent();
                data.putExtra("tName",task);
                data.putExtra("desc",description);
                data.putExtra("date",date);
                setResult(1,data);

                finish();


            }
        });


    }

}
