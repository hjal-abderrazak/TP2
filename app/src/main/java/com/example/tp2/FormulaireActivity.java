package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FormulaireActivity extends AppCompatActivity {
EditText nom,prenom,formation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire);
        nom = findViewById(R.id.nomField);
        prenom = findViewById(R.id.prenomField);
        formation = findViewById(R.id.formationField);

    }

    public void operationOk(View view) {
        String _nom = nom.getText().toString();
        String _prenom = prenom.getText().toString();
        String _formation = formation.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("nom", _nom);
        intent.putExtra("prenom", _prenom);
        intent.putExtra("formation", _formation);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void operationCancel(View view) {

        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

}