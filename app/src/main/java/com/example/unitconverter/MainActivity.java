package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editText2;
    private Button button;
    private TextView textView6;
    private Spinner spinner,spinner2;
    private  String unit1,unit2,s;
    private  double val;
    int lbflag=0,ozflag=0,stflag=0,ozlbflag=0,stozflag=0,stlbflag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        spinner2=findViewById(R.id.spinner2);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("kg");
        arrayList.add("lb(pound)");
        arrayList.add("oz");
        arrayList.add("stone");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner2.setAdapter(arrayAdapter);
        button=findViewById(R.id.button);
        textView6=findViewById(R.id.textView6);
        editText2=findViewById(R.id.editText2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "YO", Toast.LENGTH_SHORT).show();
                try {
                    s = editText2.getText().toString();
                    unit1 = spinner.getSelectedItem().toString();
                    unit2 = spinner2.getSelectedItem().toString();
                    val = Double.parseDouble(s);
                    if (s.isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                    } else if (unit1.equals(unit2)) {
                        s = String.valueOf(val);
                        textView6.setText(s);
                    } else if (unit1.equals("kg") && unit2.equals("lb(pound)")) {
                        kgtolb();
                    } else if (unit1.equals("kg") && unit2.equals("oz")) {
                        kgtooz();
                    } else if (unit1.equals("kg") && unit2.equals("stone")) {
                        kgtostone();
                    } else if (unit1.equals("lb(pound)") && unit2.equals("kg")) {
                        lbflag = 1;
                        kgtolb();
                        lbflag = 0;
                    } else if (unit1.equals("oz") && unit2.equals("kg")) {
                        ozflag = 1;
                        kgtooz();
                        ozflag = 0;
                    } else if (unit1.equals("stone") && unit2.equals("kg")) {
                        stflag = 1;
                        kgtostone();
                        stflag = 0;
                    } else if (unit1.equals("lb(pound)") && unit2.equals("oz")) {
                        lbtooz();
                    } else if (unit1.equals("oz") && unit2.equals("lb(pound)")) {
                        ozlbflag = 1;
                        lbtooz();
                        ozlbflag = 0;
                    } else if (unit1.equals("lb(pound)") && unit2.equals("stone")) {
                        lbtost();
                    } else if (unit1.equals("stone") && unit2.equals("lb(pound)")) {
                        stlbflag = 1;
                        lbtost();
                        stlbflag = 0;
                    } else if (unit1.equals("oz") && unit2.equals("stone")) {
                        oztostone();
                    } else if (unit1.equals("stone") && unit2.equals("oz")) {
                        stozflag = 1;
                        oztostone();
                        stozflag = 0;
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        });



    }
    public void kgtolb(){
        if(lbflag==1)
        {
            double kg=precision(val/2.205);
            s=String.valueOf(kg);
            textView6.setText(s+" kg");
        }
        else {
            double pound = val * 2.205;
            s = String.valueOf(pound);
            textView6.setText(s + " lbs");
        }
    }
    public void kgtooz(){
        if(ozflag==1)
        {
            double kg=precision(val/35.274);
            s=String.valueOf(kg);
            textView6.setText(s+" kg");
        }
        else {
            double ounce = val * 35.274;
            s = String.valueOf(ounce);
            textView6.setText(s + " oz");
        }
    }
    public void kgtostone(){
        if(stflag==1)
        {
            double kg=val*6.35;
            s=String.valueOf(kg);
            textView6.setText(s+" kg");
        }
        else {
            double stone = precision(val / 6.35);
            s = String.valueOf(stone);
            textView6.setText(s + " st");
        }
    }
    public void lbtooz(){
        if(ozlbflag==1)
        {
            double lb=precision(val/16);
            s=String.valueOf(lb);
            textView6.setText(s + " lb");
        }
        else {
            double oz = val * 16;
            s = String.valueOf(oz);
            textView6.setText(s + " oz");
        }
    }
    public void lbtost(){
        if(stlbflag==1)
        {
            double lb=val*14;
            s=String.valueOf(lb);
            textView6.setText(s + " lb");
      }
        else {
            double st=precision(val/14);
            s=String.valueOf(st);
            textView6.setText(s + " st");
        }
    }
    public void oztostone(){
        if(stozflag==1)
        {
            double oz=val*224;
            s=String.valueOf(oz);
            textView6.setText(s + " oz");
        }
        else {
            double st = precision(val / 224);
            s = String.valueOf(st);
            textView6.setText(s + " st");
        }
    }
    public double precision(double unprec){
        double prec=BigDecimal.valueOf(unprec)
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
        return prec;
    }
    }

