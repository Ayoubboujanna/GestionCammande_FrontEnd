package com.example.clientstock;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {
    @GET("getAllCategorie")
    Call<List<Categorie>> getCategories();


    @GET("getAllByCat1/{id}")
    Call<List<Produit>> getProduits(@Path("id") int ido);
    @GET("getAllProduit")
    Call<List<Produit>> getAllProduits();
    @POST("saveProduit")
    Call<Produit> AjouterProduit(@Body Produit art);
}