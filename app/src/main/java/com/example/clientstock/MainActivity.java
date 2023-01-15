package com.example.clientstock;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity  {
    private TextView designation;
    private TextView Nart;
    private ListView listeView;
    private Button button;
    private ArrayList<String> itemsN;
    private ArrayList Cats;
    private ArrayList CatsD;
    private ArrayList<Produit> ProduitsList;
    private ArrayAdapter<String> adap;
    private ProduitAdapter adapterList;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.8.107:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------ Designation textView
        designation = findViewById(R.id.designation);
        //------------------ moyan textView & nombre des Produit
        Nart=findViewById(R.id.Nart);

        //------------------ Spinner
        Spinner dropdown = findViewById(R.id.spinner1);
        Cats = new ArrayList();
        CatsD = new ArrayList();
        Cats.add("All");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Cats);
        dropdown.setAdapter(adapter);
        //------------------ Produit List textView
        listeView=findViewById(R.id.produitList);
        itemsN = new ArrayList<>();
        ProduitsList=new ArrayList<>();

        //adap=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,itemsN);


        button=findViewById(R.id.addart);
//------------------------------------------- fin des declarations
        getCategories();//ajoiter les categories dan le spinner
//------------------------------------------- sélection de la categorie
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                String select=adapterView.getItemAtPosition(i).toString();
                designation.setText(CatsD.get(i-1)+"");
                getArtByIdCat(Integer.parseInt(select));
                }else{
                    getAllProduits();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                makeToast("Erreur de sélection\n");
            }
        });

        ///button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il =new Intent(MainActivity.this,ProduitActivity.class);
                Bundle b=new Bundle();
                b.putString("cat",designation.getText().toString());
                il.putExtras(b);
                Bundle c=new Bundle();
                c.putString("id",dropdown.getSelectedItem().toString());
                il.putExtras(c);
                startActivity(il);
            }
        });
    }

    //------------------------------------------------------ les fonctions ----------------------------------------------------------
    //la fonction makeToast permet d'afficher les messages
    Toast t;
    public void makeToast(String s){
        if(t!=null) t.cancel();
        t=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        t.show();
    }
    //la fonction getCategories permet
    public void getCategories(){
        Call<List<Categorie>> call = jsonPlaceHolderApi.getCategories();
        call.enqueue(new Callback<List<Categorie>>() {
            @Override
            public void onResponse(Call<List<Categorie>> call, Response<List<Categorie>> response) {
                if (!response.isSuccessful()) {
                    makeToast("Code: " + response.code());
                    return;
                }
                List<Categorie> posts = response.body();
                for (Categorie post : posts) {
                    Cats.add(post.getId());
                    CatsD.add(post.getDesignation());
                }

            }
            @Override
            public void onFailure(Call<List<Categorie>> call, Throwable t) {
                makeToast(t.getMessage());
            }
        });
    }
    //la fonction getArtByIdCat
    public  void getArtByIdCat(int Selected){
        Call<List<Produit>> call2 = jsonPlaceHolderApi.getProduits(Selected);
        call2.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                if (!response.isSuccessful()) {
                    makeToast("Erreur");
                    return;
                }
                List<Produit> Produits = response.body();
                Nart.setText(""+Produits.size());
                adapterList.clear();
                double somme=0.0;
                for (Produit art : Produits) {
                    itemsN.add(art.getId()+"\t\t\t\t\t\t\t"+art.getLibelle()+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+art.getPrix());
                    somme=somme+art.getPrix();
                    ProduitsList.add(art);
                }
                //listeView.setAdapter(adap);

                listeView.setAdapter(adapterList);
            }
            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {
                makeToast(t.getMessage());
            }
        });
    }


    public  void getAllProduits(){
        Call<List<Produit>> call2 = jsonPlaceHolderApi.getAllProduits();
        call2.enqueue(new Callback<List<Produit>>() {
            @Override
            public void onResponse(Call<List<Produit>> call, Response<List<Produit>> response) {
                if (!response.isSuccessful()) {
                    makeToast("Erreur");
                    return;
                }
                List<Produit> Produits = response.body();
                Nart.setText(""+Produits.size());
                adapterList.clear();
                double somme=0.0;
                for (Produit art : Produits) {
                    itemsN.add(art.getId()+"\t\t\t\t\t\t\t"+art.getLibelle()+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+art.getPrix());
                    somme=somme+art.getPrix();
                    ProduitsList.add(art);
                }
                //listeView.setAdapter(adap);

                listeView.setAdapter(adapterList);
            }
            @Override
            public void onFailure(Call<List<Produit>> call, Throwable t) {
                makeToast(t.getMessage());
            }
        });
    }
}