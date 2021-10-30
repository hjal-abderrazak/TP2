package com.example.tp2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
ListView list;
Button ajoutBtn;
Vector itemVulues;
ArrayList listEtud;
CustomArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Gestionnaire des Etudiant");
        ajoutBtn = findViewById(R.id.ajoutBtn);
        list =findViewById(R.id.list);
         listEtud = new ArrayList();
        Vector itemVulues = new Vector();
        itemVulues.add("hjal");
        itemVulues.add("abderrazak");
        itemVulues.add("MPISI 2");

        listEtud.add(itemVulues);

        arrayAdapter = new CustomArrayAdapter(this , listEtud,getLayoutInflater());
        list.setAdapter(arrayAdapter);


        ajoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FormulaireActivity.class);
               // launchSomeActivity.launch(intent);
                startActivityForResult(intent,0);
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Vector itemVector = (Vector) list.getItemAtPosition(i);

                Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                intent.putExtra("nom",itemVector.get(0)+ " "+ itemVector.get(1));
                intent.putExtra("formation", itemVector.get(2)+"");
                intent.putExtra("index",i);
                startActivityForResult(intent,1);

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Etudiant")
                        .setTitle("info");

                builder.setPositiveButton("info", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Vector itemVector = (Vector) list.getItemAtPosition(i);
                        Intent intent = new Intent(getApplicationContext(),InfoActivity.class);
                        intent.putExtra("nom",itemVector.get(0)+ " "+ itemVector.get(1));
                        intent.putExtra("formation", itemVector.get(2)+"");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Supprimer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        listEtud.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();



                return true;
            }
        });












    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){

                Vector vector = new Vector();
                vector.add(data.getStringExtra("nom") );
                vector.add(data.getStringExtra("prenom") );
                vector.add(data.getStringExtra("formation") );
                listEtud.add(vector);
                arrayAdapter.notifyDataSetChanged();

            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // Write your code if there's no result
            }
        }
       else if (requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                int index = data.getIntExtra("index",0);

                listEtud.remove(index);
                arrayAdapter.notifyDataSetChanged();
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.miNouvea:{

                ajoutEdutiant();
                return true;
            }

            case R.id.miSupprimer:{
                supprimerEtudiant();
                return true;
            }

            case R.id.miQuitter:{
                finish();
                return true ;
            }

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void supprimerEtudiant() {
        listEtud.clear();
        arrayAdapter.notifyDataSetChanged();

    }

    private void ajoutEdutiant() {
        Intent intent = new Intent(getApplicationContext(),FormulaireActivity.class);
        startActivityForResult(intent,0);

    }



}