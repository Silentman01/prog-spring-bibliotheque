package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque();
        Livre livre1 = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "1943", "9782070612758", Categorie.LITTERATURE);
        Livre livre2 = new Livre("Une brève histoire du temps", "Stephen Hawking", "1988", "9780553380163", Categorie.SCIENCES);
        Livre livre3 = new Livre("Les Misérables", "Victor Hugo", "1862", "9782253004228", Categorie.LITTERATURE);

        bibliotheque.ajouterLivre(livre1);
        bibliotheque.ajouterLivre(livre2);
        bibliotheque.ajouterLivre(livre3);

        Etudiant etudiant1 = new Etudiant("Dupont", "Jean", "123456", "jean.dupont@example.com");
        Etudiant etudiant2 = new Etudiant("Durand", "Marie", "654321", "marie.durand@example.com");
        Etudiant etudiant3 = new Etudiant("Martin", "Paul", "789123", "paul.martin@example.com");

        bibliotheque.ajouterEtudiant(etudiant1);
        bibliotheque.ajouterEtudiant(etudiant2);
        bibliotheque.ajouterEtudiant(etudiant3);

        bibliotheque.getCategories();
        bibliotheque.getLivresByCategorie(Categorie.LITTERATURE);

        // Emprunt du livre1 par l'étudiant1
        Emprunt emprunt1 = new Emprunt(livre1, etudiant1, LocalDate.of(2024, 9, 1));

        // Emprunt du livre2 par l'étudiant2
        Emprunt emprunt2 = new Emprunt(livre2, etudiant2, LocalDate.of(2024, 9, 5));

        bibliotheque.ajouterEmprunt(emprunt1);
        bibliotheque.ajouterEmprunt(emprunt2);

        bibliotheque.retourEmprunt(emprunt1);

        bibliotheque.getEmpruntsEnCours();

        bibliotheque.getEmpruntsByEtudiants();

        bibliotheque.getLivresDisponibles();

        bibliotheque.getEmpruntsRetard();
    }
}
