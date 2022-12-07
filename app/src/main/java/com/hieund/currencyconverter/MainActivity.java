package com.hieund.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;

    private EditText from1;
    private EditText to1;

    private Button btn_convert;
    private Button btn_clear;
    private Button btn_choose;

    String curr1 = "";
    String curr2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        from1 = findViewById(R.id.from1);
        to1 = findViewById(R.id.to1);
        btn_convert = findViewById(R.id.btn_convert);
        btn_clear = findViewById(R.id.btn_clear);
        btn_choose = findViewById(R.id.btn_choose);

        //Clear
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from1.setText("");
                to1.setText("");
            }
        });

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ChooseActivity.class));
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();

        String curr1 = getIntent().getStringExtra("curr1");
        String curr2 = getIntent().getStringExtra("curr2");

        textView1.setText("From: " + curr1);
        textView2.setText("To: " + curr2);

        //Convert
        btn_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double rate1 = checkRate(curr1);
                double rate2 = checkRate(curr2);
                double num1 = Double.parseDouble(from1.getText().toString());
                double num2 = (num1 * rate2) / rate1;
                to1.setText(String.valueOf(num2));
            }
        });

        from1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                double rate1 = checkRate(curr1);
                double rate2 = checkRate(curr2);
                double num1;
                if (from1.length() > 0) {
                    num1 = Double.parseDouble(from1.getText().toString());
                } else {
                    num1 = 0;
                    from1.setText(String.format("%.0f",  num1));
                }
                double num2 = (num1 * rate2) / rate1;
                to1.setText(String.format("%.2f", num2));
            }

        });
    }
    private double checkRate(String curr) {
        double cnyRate = 7;
        double krwRate = 1320;
        double eurRate = 1;
        double usdRate = 1;
        double jpyRate = 140;
        double vndRate = 23500;

        double rate = 0;

        switch (curr) {
            case "CNY":
                rate = cnyRate;
                break;
            case "KRW":
                rate = krwRate;
                break;
            case "EUR":
                rate = eurRate;
                break;
            case "USD":
                rate = usdRate;
                break;
            case "JPY":
                rate = jpyRate;
                break;
            case "VND":
                rate = vndRate;
                break;
            default:
                break;
        }
        return rate;
    }
}