package com.example.unitconvertor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {
     long pressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);
        Button area=(Button)findViewById(R.id.area);
        Button temp=(Button) findViewById(R.id.temperature);
        Button weight=(Button) findViewById(R.id.weight);
        Button length=(Button) findViewById(R.id.length);


        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this,area.class);
                startActivity(intent);
            }
        });

        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this,temp.class);
                startActivity(intent);
            }
        });

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this,weight.class);
                startActivity(intent);
            }
        });

        length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainPage.this,length.class);
                startActivity(intent);
            }
        });

    }

    public void onBackPressed(){
        if(pressedTime + 2000 >System.currentTimeMillis()){
            super.onBackPressed();
            finishAffinity();
        }else{
            Toast.makeText(getApplicationContext(),"Press Back Again To Exit",Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }
}
