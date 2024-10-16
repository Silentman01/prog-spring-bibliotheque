package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres;
    private List<Etudiant> etudiants;
    private List<Emprunt> emprunts;
    private int dureeEmprunt;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
        this.etudiants = new ArrayList<>();
        this.emprunts = new ArrayList<>();
        this.dureeEmprunt = 14;
    }

    // Getters
    public List<Livre> getLivres() {
        return livres;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    // Livres by isbn
    public Livre getLivreByIsbn(String isbn) {
        for(Livre livre : this.livres) {
            if(livre.getIsbn().equals(isbn)) {
                return livre;
            }
        }
        return null;
    }

    // Livres by categorie
    public List<Livre> getLivresByCategorie(Categorie categorie) {
        List<Livre> result = new ArrayList<>();
        System.out.println("Livres de catégorie " + categorie + " :");
        for(Livre livre : this.livres) {
            if(livre.getCategorie() == categorie) {
                System.out.println(livre);
                result.add(livre);
            }
        }
        System.out.println();
        return result;
    }

    // Livres by annee
    public List<Livre> getLivresByAnnee(String annee) {
        List<Livre> result = new ArrayList<>();
        System.out.println("Livres par année " + annee + " :");
        for(Livre livre : this.livres) {
            if(livre.getAnnee() == annee) {
                System.out.println(livre);
                result.add(livre);
            }
        }
        System.out.println();
        return result;
    }

    // Livres disponibles
    public List<Livre> getLivresDisponibles() {
        List<Livre> result = new ArrayList<>();
        System.out.println("Livres disponibles :");
        for(Livre livre : this.livres) {
            if(livre.getDisponible()) {
                System.out.println(livre.getTitre());
                result.add(livre);
            }
        }
        System.out.println();
        return result;
    }

    // Etudiants by numEtudiant
    public Etudiant getEtudiantByNumEtudiant(String numEtudiant) {
        for(Etudiant etudiant : this.etudiants) {
            if(etudiant.getNumEtudiant().equals(numEtudiant)) {
                return etudiant;
            }
        }
        return null;
    }

    // Emprunts en cours
    public List<Emprunt> getEmpruntsEnCours() {
        List<Emprunt> result = new ArrayList<>();

        System.out.println("Emprunts en cours :");
        for(Emprunt emprunt : this.emprunts) {
            if(emprunt.getDateRetour() == null) {
                System.out.println(emprunt);
                result.add(emprunt);
            }
        }
        System.out.println();
        return result;
    }

    // Emprunt byIsbn en cours
    public Emprunt getEmpruntLivreEnCours(String isbn) {
        for(Emprunt emprunt : this.emprunts) {
            if(emprunt.getLivre().getIsbn().equals(isbn) && !(emprunt.getLivre().getDisponible())) {
                return emprunt;
            }
        }
        return null;
    }

    // Emprunts pour chaque etudiants
    public List<Emprunt> getEmpruntsByEtudiants() {
        List<Emprunt> result = new ArrayList<>();

        for(Etudiant etudiant : this.etudiants) {
            System.out.println("L'étudiant " + etudiant.getNumEtudiant() + " a emprunté les livres :");
            for(Emprunt emprunt : this.emprunts) {
                if(emprunt.getEtudiant().getNumEtudiant().equals(etudiant.getNumEtudiant())) {
                    System.out.println(emprunt.getLivre());
                    result.add(emprunt);
                }
            }
        }
        System.out.println();
        return result;
    }

    // Emprunts par un étudiant
    public List<Emprunt> getEmpruntsByEtudiant(String numEtudiant) {
        List<Emprunt> result = new ArrayList<>();

        System.out.println("Livres empruntés par l'étudiant " + numEtudiant + " :");
        for(Emprunt emprunt : this.emprunts) {
            if(emprunt.getEtudiant().getNumEtudiant().equals(numEtudiant)) {
                System.out.println(emprunt.getLivre());
                result.add(emprunt);
            }
        }
        return result;
    }

    // Emprunts en retard
    public List<Emprunt> getEmpruntsRetard() {
        List<Emprunt> result = new ArrayList<>();
        LocalDate today = LocalDate.now();
        System.out.println("Emprunts en retard :");
        for(Emprunt emprunt : this.emprunts) {
            LocalDate dateRetourMax = emprunt.getDateEmprunt().plusDays(this.dureeEmprunt);
            if(emprunt.getDateRetour() == null && today.isAfter(dateRetourMax)) {
                System.out.println(emprunt);
                result.add(emprunt);
            }
        }
        return result;
    }

    public boolean checkLivreExists(Livre livre) {
        for(Livre l : this.livres) {
            if(l.getIsbn().equals(livre.getIsbn())) {
                System.out.println("Le livre " + livre.getIsbn() + " est déjà présent dans la bibliothèque.");
                return true;
            }
        }
        return false;
    }

    public boolean checkEtudiantExists(Etudiant etudiant) {
        for(Etudiant e : this.etudiants) {
            if(e.getNumEtudiant().equals(etudiant.getNumEtudiant())) {
                System.out.println("L'étudiant " + etudiant.getNumEtudiant() + " est déjà inscrit à la bibliothèque.");
                return true;
            }
        }
        return false;
    }

    // Livre add, remove
    public void ajouterLivre(Livre livre) {
        if(!checkLivreExists(livre)) {
            this.livres.add(livre);
        }
    }

    public void supprimerLivre(Livre livre) {
        this.livres.remove(livre);
    }

    // Etudiant add, remove
    public void ajouterEtudiant(Etudiant etudiant) {
        if(!checkEtudiantExists(etudiant)) {
            this.etudiants.add(etudiant);
        }
    }

    public void supprimerEtudiant(Etudiant etudiant) {
        this.etudiants.remove(etudiant);
    }

    // Emprunt
    public void ajouterEmprunt(Emprunt emprunt) {
        // Etudiant inscrit
        if(!this.etudiants.contains(emprunt.getEtudiant())) {
            System.out.println("L'étudiant " + emprunt.getEtudiant().getNumEtudiant() + " n'est pas inscrit dans la bibliothèque.");
            return;
        }

        // Livre existe
        if(!this.livres.contains(emprunt.getLivre())) {
            System.out.println("Le livre " + emprunt.getLivre().getIsbn() + " n'est pas dans la bibliothèque.");
            return;
        }

        // Livre disponible
        if(!emprunt.getLivre().getDisponible()) {
            System.out.println("Le livre " + emprunt.getLivre().getIsbn() + " n'est pas disponible.");
            return;
        }

        emprunt.getLivre().setDisponible(false);
        emprunt.setDateEmprunt(LocalDate.now());
        this.emprunts.add(emprunt);
        System.out.println("Emprunt ajouté pour l'étudiant " + emprunt.getEtudiant().getNumEtudiant() + " avec le livre " + emprunt.getLivre().getTitre());
        System.out.println();
    }

    public void retourEmprunt(Emprunt emprunt) {
        // Etudiant inscrit
        if(!this.etudiants.contains(emprunt.getEtudiant())) {
            System.out.println("L'étudiant " + emprunt.getEtudiant().getNumEtudiant() + " n'est pas inscrit dans la bibliothèque.");
            System.out.println();
            return;
        }

        // Livre disponible
        if(!this.livres.contains(emprunt.getLivre())) {
            System.out.println("Le livre " + emprunt.getLivre().getIsbn() + " n'est pas dans la bibliothèque.");
            System.out.println();
            return;
        }

        // Emprunt existe
        if(!this.emprunts.contains(emprunt)) {
            System.out.println("L'emprunt n'existe pas dans la bibliothèque");
            System.out.println();
            return;
        }

        emprunt.getLivre().setDisponible(true);
        emprunt.setDateRetour(LocalDate.now());
        System.out.println("Le livre " + emprunt.getLivre().getTitre() + " a été rendu par l'étudiant " + emprunt.getEtudiant().getNumEtudiant());
        System.out.println();
    }

    // List categories
    public List<Categorie> getCategories() {
        List<Categorie> categories = Categorie.getCategories();
        System.out.println("Catégories disponibles :");
        for(Categorie categorie : categories) {
            System.out.println(categorie);
        }
        System.out.println();
        return categories;
    }

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "livres=" + livres +
                ", etudiants=" + etudiants +
                ", emprunts=" + emprunts +
                '}';
    }
}
