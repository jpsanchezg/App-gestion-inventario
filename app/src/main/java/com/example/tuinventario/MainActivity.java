package com.example.tuinventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn1 = findViewById(R.id.inv);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Inventari.class);
                startActivityForResult(intent, 0);
            }
        });
        Button btn2 = findViewById(R.id.esta);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Ajustes.class);
                startActivityForResult(intent, 0);
            }
        });
        Button btn3 = findViewById(R.id.gast);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Ajustes.class);
                startActivityForResult(intent, 0);
            }
        });
        Button btn4 = findViewById(R.id.ajus);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Ajustes.class);
                startActivityForResult(intent, 0);
            }
        });


    }

}