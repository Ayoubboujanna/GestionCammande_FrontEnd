package com.example.clientstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProduitAdapter extends ArrayAdapter<Produit> {
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView name;
        TextView birthday;
        TextView sex;
    }
    /**
     * Default constructor for the PersonListAdapter
     * @param context
     * @param resource
     * @param objects
     */
    public ProduitAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Produit> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the persons information
        String libelle = getItem(position).getLibelle();
        double prix = getItem(position).getPrix();
        int id = getItem(position).getId();
        //Create the person object with the information
        Produit person = new Produit(libelle,prix,0);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView Tvprix = (TextView) convertView.findViewById(R.id.text2);


        Tvprix.setText(libelle);

        return convertView;
    }
}
