package com.example.unitconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class weight extends AppCompatActivity {
    String[] weight={"Gram","Pound","Kilogram"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight);

        Spinner spinner=findViewById(R.id.xxx);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(weight.this, android.R.layout.simple_spinner_item,weight);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView gram=(TextView)findViewById(R.id.g);
                TextView pound=(TextView)findViewById(R.id.p);
                TextView kilo=(TextView)findViewById(R.id.kg);
                EditText val=(EditText)findViewById(R.id.value);
                String value=val.getText().toString();
                if(i == 0){
                    double x=Double.parseDouble(value);
                    double p= x*0.00220462;
                    double k= x/1000;
                    gram.setText(value);
                    pound.setText(String.valueOf(p));
                    kilo.setText(String.valueOf(k));
                }
                else if(i == 1){
                    double x=Double.parseDouble(value);
                    double g= x*453.592;
                    double k= x*0.453592;
                    gram.setText(String.valueOf(g));
                    pound.setText(value);
                    kilo.setText(String.valueOf(k));
                }
                else if(i == 2){
                    double x=Double.parseDouble(value);
                    double g= x*1000;
                    double p= x*2.20462;
                    gram.setText(String.valueOf(g));
                    pound.setText(String.valueOf(p));
                    kilo.setText(value);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}