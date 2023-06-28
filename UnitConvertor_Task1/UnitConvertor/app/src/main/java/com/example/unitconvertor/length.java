package com.example.unitconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class length extends AppCompatActivity {
    String[] len={"Nanometer","Centimeter","Meter"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.length);
        Spinner spinner=findViewById(R.id.xxx);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(length.this, android.R.layout.simple_spinner_item,len);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView nan=(TextView)findViewById(R.id.nm);
                TextView cent=(TextView)findViewById(R.id.cm);
                TextView met=(TextView)findViewById(R.id.m);
                EditText val=(EditText)findViewById(R.id.value);
                String value=val.getText().toString();
                if(i == 0){
                    double x=Double.parseDouble(value);
                    double cm= x/10000000.0;
                    double m= x/1000000000.0;
                    nan.setText(value);
                    cent.setText(String.valueOf(cm));
                    met.setText(String.valueOf(m));
                }
                else if(i == 1){
                    double x=Double.parseDouble(value);
                    double nm= x*10000000.0;
                    double m= x/100;
                    nan.setText(String.valueOf(nm));
                    cent.setText(value);
                    met.setText(String.valueOf(m));
                }
                else if(i == 2){
                    double x=Double.parseDouble(value);
                    double nm= x*1000000000.0;
                    double cm= x*100;
                    nan.setText(String.valueOf(nm));
                    cent.setText(String.valueOf(cm));
                    met.setText(value);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
