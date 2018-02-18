package me.developeralfa.calendoer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailsForm extends AppCompatActivity {
    EditText fName,lName,Wake;
    Button formButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_form);
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        Wake = findViewById(R.id.Wake);
        formButton = findViewById(R.id.formButton);
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                Intent GetStarted = new Intent(DetailsForm.this,Sorted.class);
                data.putExtra("fName",fName.getText().toString());
                data.putExtra("lName",lName.getText().toString());
                data.putExtra("Wake",Wake.getText().toString());
                setResult(1,data);
                startActivity(GetStarted);
                finish();
            }
        });

    }
}
