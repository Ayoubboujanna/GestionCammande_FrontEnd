package com.example.clientstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProduitActivity extends AppCompatActivity {
    private Button retour;
    private Button ajouter;
    private TextView cat_designation;
    private TextInputEditText libelle;
    private TextInputEditText prix;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.8.107:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit);
        retour=findViewById(R.id.button2);
        ajouter=findViewById(R.id.button);
        libelle=findViewById(R.id.pro_lib);
        prix=findViewById(R.id.pro_prix);
        cat_designation=findViewById(R.id.cat_designation);
        Bundle valeurs=getIntent().getExtras();
        cat_designation.setText(valeurs.getString("cat"));
        String catId=valeurs.getString("id").toString();
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent il =new Intent(ProduitActivity.this,MainActivity.class);
                startActivity(il);
            }
        });
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                creatProduit(catId);
            }
        });

    }
    private void creatProduit(String catId){
        Produit art=new Produit(libelle.getText().toString(),Double.parseDouble(prix.getText().toString()),Integer.parseInt(catId));
        Call<Produit> call  = jsonPlaceHolderApi.AjouterProduit(art);
        call.enqueue(new Callback<Produit>() {
            @Override
            public void onResponse(Call<Produit> call, Response<Produit> response) {
                if(!response.isSuccessful()){
                    makeToast("Code: " + response.code());
                    return;
                }
                Produit artresp=response.body();
                String content="";
                content+="l'Produit avec l'id :"+artresp.getId()+" a ete ajoute ";
                makeToast(content);
                libelle.setText("");
                prix.setText("");
            }


            @Override
            public void onFailure(Call<Produit> call, Throwable t) {

            }
        });
    }
    //la fonction makeToast permet d'afficher les messages
    Toast t;
    public void makeToast(String message){
        if(t!=null) t.cancel();
        t=Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        t.show();
    }

}