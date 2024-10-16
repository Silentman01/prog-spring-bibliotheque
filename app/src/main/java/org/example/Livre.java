package org.example;

public class Livre {
    //private Long id;
    private String titre;
    private String auteur;
    private String annee;
    private String isbn;
    private Categorie categorie;
    private boolean disponible;

    public Livre(String titre, String auteur, String annee, String isbn, Categorie categorie) {
        this.titre = titre;
        this.auteur = auteur;
        this.annee = annee;
        this.isbn = isbn;
        this.categorie = categorie;
        this.disponible = true;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", annee='" + annee + '\'' +
                ", isbn='" + isbn + '\'' +
                ", categorie=" + categorie +
                ", disponible=" + disponible +
                '}';
    }
}
