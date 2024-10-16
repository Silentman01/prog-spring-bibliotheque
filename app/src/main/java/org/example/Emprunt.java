package org.example;

import java.time.LocalDate;

public class Emprunt {
    //private Long id;
    private Livre livre;
    private Etudiant etudiant;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(Livre livre, Etudiant etudiant, LocalDate dateEmprunt) {
        this.livre = livre;
        this.etudiant = etudiant;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = null;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "livre=" + livre +
                ", etudiant=" + etudiant +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                '}';
    }
}
