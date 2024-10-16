package org.example;

public class Etudiant {
    //private Long id;
    private String nom;
    private String prenom;
    private String numEtudiant;
    private String email;

    public Etudiant(String nom, String prenom, String numEtudiant, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.numEtudiant = numEtudiant;
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numEtudiant='" + numEtudiant + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
