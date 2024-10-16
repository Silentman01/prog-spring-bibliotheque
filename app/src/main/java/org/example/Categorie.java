package org.example;

import java.util.Arrays;
import java.util.List;

public enum Categorie {
    LITTERATURE,
    SCIENCES,
    HISTOIRE,
    INFORMATIQUE,
    ARTS,
    PHILOSOPHIE,
    ECONOMIE,
    DROIT;

    public static List<Categorie> getCategories() {
        return Arrays.asList(Categorie.values());
    }
}