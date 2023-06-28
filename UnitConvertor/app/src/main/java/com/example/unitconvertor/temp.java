package com.example.unitconvertor;

import static com.example.unitconvertor.R.*;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class temp extends AppCompatActivity {
    String[] tempr={"Kelvin","Celsius","Fahrenheit"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.temperature);

        Spinner spinner=findViewById(R.id.xxx);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(temp.this, android.R.layout.simple_spinner_item,tempr);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView cal=(TextView)findViewById(id.cal);
                TextView kel=(TextView)findViewById(id.kel);
                TextView fah=(TextView)findViewById(id.fah);
                EditText val=(EditText)findViewById(id.value);
                String value=val.getText().toString();
                if(i == 0){
                    double x=Double.parseDouble(value);
                    double c= x-273.15;
                    double f= (x-273.15)*9/5+32;
                    kel.setText(value);
                    cal.setText(String.valueOf(c));
                    fah.setText(String.valueOf(f));
                }
                else if(i == 1){
                    double x=Double.parseDouble(value);
                    double k= x+273.15;
                    double f= (x)*9/5+32;
                    kel.setText(String.valueOf(k));
                    cal.setText(value);
                    fah.setText(String.valueOf(f));
                }
                else if(i == 2){
                    double x=Double.parseDouble(value);
                    double c= (x-32)*5/9;
                    double k= 5/9*(x)+459.67;
                    kel.setText(String.valueOf(k));
                    cal.setText(String.valueOf(c));
                    fah.setText(value);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}