
package com.example.douanetemporelle;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    ImageView imgPassport;
    TextView txtTitre, txtNom, txtAge, txtMotif;
    Button btnApprouver, btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // Liaison avec les composants XML
        imgPassport = findViewById(R.id.img_passport_nj);
        txtTitre = findViewById(R.id.txt_titre_nj);
        txtNom = findViewById(R.id.txt_nom_nj);
        txtAge = findViewById(R.id.txt_age_nj);
        txtMotif = findViewById(R.id.txt_motif_nj);

        btnApprouver = findViewById(R.id.btn_approuver_nj);
        btnRefuser = findViewById(R.id.btn_refuser_nj);

        // Récupération des données envoyées
        String nom = getIntent().getStringExtra("nom");
        String age = getIntent().getStringExtra("age");
        String motif = getIntent().getStringExtra("motif");

        if (nom != null) {
            txtNom.setText("Nom : " + nom);
        }

        if (age != null) {
            txtAge.setText("Âge temporel : " + age);
        }

        if (motif != null) {
            txtMotif.setText("Motif : " + motif);
        }

        // Bouton Approuver
        btnApprouver.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    "Saut temporel approuvé !",
                    Toast.LENGTH_SHORT
            ).show();
        });

        // Bouton Refuser
        btnRefuser.setOnClickListener(v -> {
            Toast.makeText(
                    this,
                    "Saut temporel refusé ! Risque de paradoxe.",
                    Toast.LENGTH_SHORT
            ).show();
            finish();
        });
    }
}

