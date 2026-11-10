
package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    // 1. Matricule : remplacer 1234 par les 4 derniers chiffres
    // du matricule choisi par le groupe
    public static final String MATRICULE = "1234";

    // 2. Méthode statique pour construire les clés des Intent
    public static String getKey(String matricule, String nomChamp) {
        return matricule + "_" + nomChamp;
    }

    // 3. Déclaration des éléments
    TextView txtVerdict;
    TextView txtMessage;
    Button btnNouveau;
    LinearLayout layoutResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 4. Afficher le fichier XML
        setContentView(R.layout.activity_result);

        // 5. Récupérer les éléments XML
        txtVerdict = findViewById(R.id.txt_verdict_nj);
        txtMessage = findViewById(R.id.txt_message_nj);
        btnNouveau = findViewById(R.id.btn_nouveau_nj);
        layoutResult = findViewById(R.id.layout_result_nj);

        // 6. Récupérer les informations envoyées par Activity 2
        Intent intent = getIntent();

        String nom = intent.getStringExtra(
                getKey(MATRICULE, "NOM")
        );

        boolean approuve = intent.getBooleanExtra(
                getKey(MATRICULE, "DECISION"),
                false
        );

        // Vérifier si le nom existe
        if (nom == null || nom.trim().isEmpty()) {
            nom = "Voyageur";
        }

        // 7. Afficher le résultat
        if (approuve) {

            // Voyage approuvé
            layoutResult.setBackgroundColor(Color.rgb(200, 240, 210));

            txtVerdict.setText("VOYAGE APPROUVÉ");

            txtMessage.setText("Bon voyage " + nom + " !");

            txtVerdict.setTextColor(Color.rgb(0, 110, 40));

        } else {

            // Voyage refusé
            layoutResult.setBackgroundColor(Color.rgb(255, 210, 210));

            txtVerdict.setText("VOYAGE REFUSÉ");

            txtMessage.setText(
                    nom + " est en état d'arrestation temporelle."
            );

            txtVerdict.setTextColor(Color.RED);
        }

        // 8. Bouton Nouveau voyageur
        btnNouveau.setOnClickListener(v -> {

            Intent retour = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            // Fermer les anciennes Activities
            retour.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(retour);
        });
    }
}
