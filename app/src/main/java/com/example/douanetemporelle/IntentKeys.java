package com.example.douanetemporelle;

//Hedhi class t3ml génération auto ll clés Intent selon le matricule mt3 l user
//  bech tnadhm w tmayez les informations elli bech yet3addaw bin les activités

public class IntentKeys {

    // Création dynamique des clés Intent
    public static String creerCle(String matricule, String information) {

        if (matricule == null || matricule.length() < 4) {
            throw new IllegalArgumentException("Matricule invalide");
        }
    // Test Git
        if (information == null || information.trim().isEmpty()) {
            throw new IllegalArgumentException("Information invalide");
        }

        String derniersChiffres =
                matricule.substring(matricule.length() - 4);

        return derniersChiffres + "_" + information;
    }
}
