package com.example.zakatcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener
{
    EditText weightvalue, goldvalue;
    Button calculateButton, resetButton;
    TextView TotalGold, ZakatPayable, TotalZakat;
    RadioButton radioKeep, radioWear;
    ArrayAdapter<CharSequence> adapter;
    float gweight;
    float gvalue;

    SharedPreferences sharedPref;
    SharedPreferences sharedPref2;
    private Menu menu;

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightvalue = (EditText) findViewById(R.id.weightvalue);
        goldvalue = (EditText) findViewById(R.id.goldvalue);
        TotalGold = (TextView) findViewById(R.id.TotalGold);
        ZakatPayable = (TextView) findViewById(R.id.ZakatPayable);
        TotalZakat = (TextView) findViewById(R.id.TotalZakat);
        calculateButton = (Button) findViewById(R.id.buttonCalculate);
        resetButton = (Button) findViewById(R.id.buttonReset);
        radioKeep = (RadioButton) findViewById(R.id.radioKeep);
        radioWear = (RadioButton) findViewById(R.id.radioWear);

        calculateButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
        gweight = sharedPref.getFloat("weight", 0.0F);

        sharedPref2 = this.getSharedPreferences("value", Context.MODE_PRIVATE);
        gvalue = sharedPref2.getFloat("value", 0.0F);

        weightvalue.setText(" " + gweight);
        goldvalue.setText(" " + gvalue);
        }

        public boolean onCreateOptionsMenu(Menu menu)
        {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.main_menu, menu);

            return true;
        }
        public boolean onOptionsItemSelected(MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.abtpage:
                Intent intent = new Intent(this, activity_aboutpage.class);
                startActivity(intent);
                break;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onClick(View v) {
            try {
                switch (v.getId()) {
                    case R.id.buttonCalculate:
                        calc();
                        break;

                    case R.id.buttonReset:
                        weightvalue.setText("");
                        goldvalue.setText("");
                        TotalGold.setText("Total GOld Value: RM");
                        ZakatPayable.setText("Zakat Payable: RM");
                        TotalZakat.setText("Total Zakat: RM");

                        break;
                }
            } catch (java.lang.NumberFormatException nfe) {
                Toast.makeText(this, "Input Missing!", Toast.LENGTH_SHORT).show();
            } catch (Exception exp) {
                Toast.makeText(this, "Unknown Exception" + exp.getMessage(), Toast.LENGTH_SHORT).show();

                Log.d("Excpetion", exp.getMessage());
            }
        }
    public void calc()
    {
        DecimalFormat df = new DecimalFormat("##.00");
        float gweight = Float.parseFloat(weightvalue.getText().toString());
        float gvalue = Float.parseFloat(goldvalue.getText().toString());
        double tvalue, uruf, zakatpay, tzakat;

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat("weight", gweight);
        editor.apply();

        SharedPreferences.Editor editor2 = sharedPref2.edit();
        editor2.putFloat("value", gvalue);
        editor2.apply();

        if (radioKeep.isChecked())
        {
            tvalue = gweight * gvalue;
            uruf = gweight - 85;

            if (uruf >= 0.0)
            {
                zakatpay = uruf * gvalue;
                tzakat = zakatpay * 0.025;
            }
            else
            {
                zakatpay = 0.0;
                tzakat = zakatpay * 0.025;
            }
            TotalGold.setText("Total Fold Value  : RM" + df.format(tvalue));
            ZakatPayable.setText("Zakat Payable  : RM" + df.format(zakatpay));
            TotalZakat.setText("Total Zakat      : RM" + df.format(tzakat));
        }
        else
        {
            tvalue = gweight * gvalue;
            uruf = gweight - 200;

            if (uruf >= 0.0)
            {
                zakatpay = uruf * gvalue;
                tzakat = zakatpay * 0.025;
            }
            else
            {
                zakatpay = 0.0;
                tzakat = zakatpay * 0.025;
            }
        }
        TotalGold.setText("Total Fold Value  : RM" + df.format(tvalue));
        ZakatPayable.setText("Zakat Payable  : RM" + df.format(zakatpay));
        TotalZakat.setText("Total Zakat      : RM" + df.format(tzakat));
    }
}






