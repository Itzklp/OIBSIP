package com.example.unitconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class area extends AppCompatActivity {
    String[] area={"Centimeter^2","Inch^2","Meter^2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area);

        Spinner spinner=findViewById(R.id.xxx);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(area.this, android.R.layout.simple_spinner_item,area);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView cent=(TextView)findViewById(R.id.c);
                TextView inch=(TextView)findViewById(R.id.i);
                TextView meter=(TextView)findViewById(R.id.m);
                EditText val=(EditText)findViewById(R.id.value);
                String value=val.getText().toString();
                if(i == 0){
                    double x=Double.parseDouble(value);
                    double in= x*0.155;
                    double m= x/10000.0;
                    cent.setText(value);
                    inch.setText(String.valueOf(in));
                    meter.setText(String.valueOf(m));
                }
                else if(i == 1){
                    double x=Double.parseDouble(value);
                    double c= x*6.4516;
                    double m= x/10000.0;
                    cent.setText(String.valueOf(c));
                    inch.setText(value);
                    meter.setText(String.valueOf(m));
                }
                else if(i == 2){
                    double x=Double.parseDouble(value);
                    double cm= x*10000.0;
                    double in= x/6.4516;
                    cent.setText(String.valueOf(cm));
                    inch.setText(String.valueOf(in));
                    meter.setText(value);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}