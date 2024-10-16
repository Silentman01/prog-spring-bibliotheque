package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@SpringBootApplication
public class BibliothequeController {
    private Bibliotheque bibliotheque;

    public BibliothequeController() {
        this.bibliotheque = new Bibliotheque();
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
    }

    // Question 8.1 - 8.6 - 9
    @GetMapping("/livres")
    public String livres(@RequestParam(value = "categorie", required = false) String categorie,
                         @RequestParam(value = "annee", required = false) Integer annee) {
        if(categorie != null) {
            Categorie cat = Categorie.valueOf(categorie.toUpperCase());
            return this.bibliotheque.getLivresByCategorie(cat).toString();
        }
        if(annee != null) {
            return this.bibliotheque.getLivresByAnnee(String.valueOf(annee)).toString();
        }
        return this.bibliotheque.getLivres().toString();
    }

    // Question 8.2
    @GetMapping("/etudiants")
    public String etudiants(@RequestParam(value = "numEtudiant", required = false) String numEtudiant) {
        if(numEtudiant != null) {
            return this.bibliotheque.getEtudiantByNumEtudiant(numEtudiant).toString();
        }
        return this.bibliotheque.getEtudiants().toString();
    }

    // Question 8.3
    @GetMapping("/livres/disponibles")
    public String livresDisponibles() {
        return this.bibliotheque.getLivresDisponibles().toString();
    }

    // Question 8.4
    @GetMapping("/livres/empruntes")
    public String livresEmpruntes() {
        return this.bibliotheque.getEmpruntsEnCours().toString();
    }

    // Question 8.5
    @GetMapping("/livres/empruntes/retard")
    public String livresEmpruntesRetard() {
        return this.bibliotheque.getEmpruntsRetard().toString();
    }

    // Question 8.7
    @GetMapping("/livres/{isbn}")
    public String livresIsbn(@PathVariable String isbn) {
        return this.bibliotheque.getLivreByIsbn(isbn).toString();
    }

    // Question 8.8
    @GetMapping("/etudiants/{numEtudiant}")
    public String etudiantsNumEtudiant(@PathVariable String numEtudiant) {
        return this.bibliotheque.getEtudiantByNumEtudiant(numEtudiant).toString();
    }

    // Question 8.9
    @GetMapping("/etudiants/{numEtudiant}/emprunts")
    public String etudiantsEmprunts(@PathVariable String numEtudiant) {
        return this.bibliotheque.getEmpruntsByEtudiant(numEtudiant).toString();
    }

    // Question 10
    @DeleteMapping("/livres/{isbn}")
    public String deleteLivreIsbn(@PathVariable String isbn) {
        Livre livre = this.bibliotheque.getLivreByIsbn(isbn);
        this.bibliotheque.supprimerLivre(livre);
        return "Le livre " + isbn + " a bien été supprimé.";
    }

    // Question 10
    @DeleteMapping("/etudiants/{numEtudiant}")
    public String deleteEtudiantNumEtudiant(@PathVariable String numEtudiant) {
        Etudiant etudiant = this.bibliotheque.getEtudiantByNumEtudiant(numEtudiant);
        this.bibliotheque.supprimerEtudiant(etudiant);
        return "L'étudiant " + numEtudiant + " a bien été supprimé.";
    }

    // Question 11
    @PostMapping("/emprunts/{isbn}/{numEtudiant}")
    public String ajouterEmprunt(@PathVariable String isbn,
                                 @PathVariable String numEtudiant) {
        Livre livre = this.bibliotheque.getLivreByIsbn(isbn);
        Etudiant etudiant = this.bibliotheque.getEtudiantByNumEtudiant(numEtudiant);
        LocalDate today = LocalDate.now();
        Emprunt emprunt = new Emprunt(livre, etudiant, today);
        this.bibliotheque.ajouterEmprunt(emprunt);
        return "Le livre " + isbn + " a été emprunté par l'étudiant " + numEtudiant + ".";
    }

    @GetMapping("/emprunts")
    public String emprunts() {
        return this.bibliotheque.getEmprunts().toString();
    }

    // Question 12
    @PostMapping("/retour/{isbn}")
    public String retourLivre(@PathVariable String isbn) {
        Livre livre = this.bibliotheque.getLivreByIsbn(isbn);
        Emprunt emprunt = this.bibliotheque.getEmpruntLivreEnCours(isbn);
        if(emprunt != null) {
            this.bibliotheque.retourEmprunt(emprunt);
            return "Le livre " + isbn + " a bien été retourné par l'étudiant";
        }
        return "Le livre n'est pas présent parmi les emprunts.";
    }

    // Question 13 - POST = CREATE
    @PostMapping("/creer/livre")
    public String creerLivre(@RequestParam(required = true) String isbn,
                             @RequestParam(required = true) String titre,
                             @RequestParam(required = true) String auteur,
                             @RequestParam(required = true) Integer annee,
                             @RequestParam(required = true) Categorie categorie) {
        Livre livre = new Livre(titre, auteur, String.valueOf(annee), isbn, categorie);
        if(!this.bibliotheque.checkLivreExists(livre)) {
            this.bibliotheque.ajouterLivre(livre);
            return "Le livre " + isbn + " a bien été ajouté.";
        }
        return "Le livre " + isbn + " est déjà présent dans la bibliothèque.";
    }

    @PostMapping("/creer/etudiant")
    public String creerEtudiant(@RequestParam(required = true) String nom,
                             @RequestParam(required = true) String prenom,
                             @RequestParam(required = true) String numEtudiant,
                             @RequestParam(required = true) String email) {
        Etudiant etudiant = new Etudiant(nom, prenom, numEtudiant, email);
        if(!this.bibliotheque.checkEtudiantExists(etudiant)) {
            this.bibliotheque.ajouterEtudiant(etudiant);
            return "L'étudiant " + numEtudiant + " a bien été ajouté.";
        }
        return "L'étudiant " + numEtudiant + " est déjà inscrit à la bibliothèque.";
    }

    // Question 14 - PUT = MAJ
    @PutMapping("/modifier/livre")
    public String modifierLivre(@RequestParam String isbn,
                                @RequestParam(required = false) String titre,
                                @RequestParam(required = false) String auteur,
                                @RequestParam(required = false) Integer annee,
                                @RequestParam(required = false) Categorie categorie) {
        Livre livre = this.bibliotheque.getLivreByIsbn(isbn);
        if(livre != null) {
            if(titre != null)
                livre.setTitre(titre);
            if(auteur != null)
                livre.setAuteur(auteur);
            if(annee != null)
                livre.setAnnee(String.valueOf(annee));
            if(categorie != null)
                livre.setCategorie(categorie);
            return "Le livre " + isbn + " a bien été modifié.";
        }
        return "Le livre " + isbn + " n'existe pas dans la bibliothèque.";
    }

    @PutMapping("/modifier/etudiant")
    public String modifierEtudiant(@RequestParam String numEtudiant,
                                @RequestParam(required = false) String nom,
                                @RequestParam(required = false) String prenom,
                                @RequestParam(required = false) String email) {
        Etudiant etudiant = this.bibliotheque.getEtudiantByNumEtudiant(numEtudiant);
        if(etudiant != null) {
            if(nom != null)
                etudiant.setNom(nom);
            if(prenom != null)
                etudiant.setPrenom(prenom);
            if(email != null)
                etudiant.setEmail(email);
            return "L'étudiant " + numEtudiant + " a bien été modifié.";
        }
        return "L'étudiant " + numEtudiant + " n'est pas inscrit dans la bibliothèque.";
    }

    @PutMapping("/modifier/json/livre")
    public String modifierLivreJSON(@RequestBody Map<String, Object> body) {
        String isbn = (String) body.get("isbn");
        Livre livre = this.bibliotheque.getLivreByIsbn(isbn);
        if(livre == null)
            return "Le livre " + isbn + " n'existe pas dans la bibliothèque.";

        if(body.containsKey("titre"))
            livre.setTitre((String) body.get("titre"));
        if(body.containsKey("auteur"))
            livre.setAuteur((String) body.get("auteur"));
        if(body.containsKey("annee"))
            livre.setAnnee(String.valueOf(body.get("annee")));
        if(body.containsKey("categorie"))
            livre.setCategorie(Categorie.valueOf((String) body.get("categorie")));

        return "Le livre " + isbn + " a bien été modifié.";
    }
}
