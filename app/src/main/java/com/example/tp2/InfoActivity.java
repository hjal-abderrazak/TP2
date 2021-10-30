package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {
TextView nomPrenom, formation;

int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        nomPrenom = findViewById(R.id.nomPrenomTxt);
        formation = findViewById(R.id.formationTxt);
        index = getIntent().getIntExtra("index",0);
        nomPrenom.setText(getIntent().getStringExtra("nom"));
        formation.setText(getIntent().getStringExtra("formation"));

    }

    public void operationSupp(View view) {
        Intent intent = new Intent();
        intent.putExtra("index",index);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void operationOk(View view) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}