package com.hieund.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioGroup radioGroup2;

    private RadioButton radioButton1;
    private RadioButton radioButton2;

    private Button btn_ok;
    private Button btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        radioGroup = findViewById(R.id.radioGroup);
        radioGroup2 = findViewById(R.id.radioGroup2);
        btn_ok = findViewById(R.id.btn_ok);
        btn_cancel = findViewById(R.id.btn_cancel);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId1 = radioGroup.getCheckedRadioButtonId();
                int selectedId2 = radioGroup2.getCheckedRadioButtonId();

                radioButton1 = findViewById(selectedId1);
                radioButton2 = findViewById(selectedId2);

                String curr1 = radioButton1.getText().toString();
                String curr2 = radioButton2.getText().toString();

                Intent intent = new Intent(ChooseActivity.this, MainActivity.class);
                intent.putExtra("curr1", curr1);
                intent.putExtra("curr2", curr2);

                startActivity(intent);
                finish();
            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}