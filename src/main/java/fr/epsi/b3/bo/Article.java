package fr.epsi.b3.bo;

public class Article {
    private int id;
    private String nom;
    private double prix;
    private Fournisseur fournisseur;

    public Article() {
    }

    public Article(String nom, double prix, Fournisseur fournisseur) {
        this.nom = nom;
        this.prix = prix;
        this.fournisseur = fournisseur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", nom='" + nom + '\'' + ", prix=" + prix + ", fournisseur=" + fournisseur + '}';
    }
}
