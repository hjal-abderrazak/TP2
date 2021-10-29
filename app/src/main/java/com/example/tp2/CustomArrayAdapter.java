package com.example.tp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Vector;

public class CustomArrayAdapter extends ArrayAdapter {
    ArrayList listValues;
    LayoutInflater inflater;

    public CustomArrayAdapter(@NonNull Context context, ArrayList arrayList, LayoutInflater layoutInflater) {
        super(context, -1,arrayList);
        this.listValues = arrayList;
        this.inflater = layoutInflater;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_row, parent, false);

        ImageView imgView = (ImageView)
                view.findViewById(R.id.imgList);
        TextView item1 = (TextView)
                view.findViewById(R.id.txtPrincipal);
        TextView item2 = (TextView)
                view.findViewById(R.id.txtFormation);
        Vector itemValues = (Vector) listValues.get(position);
        String nom, prenom, formation;
        nom = (String) itemValues.get(0);
        prenom = (String) itemValues.get(1);
        formation = (String) itemValues.get(2);

        item1.setText(nom + " " + prenom);
        item2.setText(formation);
        imgView.setImageResource(R.mipmap.ic_launcher);
        return view;

    }


}
