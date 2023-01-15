package com.example.clientstock;

public class Produit {
    private int id;
    private String libelle;
    private double prix;
    private int id_ctg;
    public Produit(String libelle, double prix, int id_ctg) {
        this.libelle = libelle;
        this.prix = prix;
        this.id_ctg = id_ctg;
    }
    public Produit() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getId_ctg() {
        return id_ctg;
    }

    public void setId_ctg(int id_ctg) {
        this.id_ctg = id_ctg;
    }
}

