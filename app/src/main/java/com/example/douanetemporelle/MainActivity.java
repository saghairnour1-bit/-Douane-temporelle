package com.example.douanetemporelle;
import com.example.douanetemporelle.;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
// test
    // Les 4 derniers chiffres du matricule
    private static final String MATRICULE = "1234";

    private EditText edtNom;
    private EditText edtNaissance;
    private EditText edtDestination;
    private Spinner spnMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des éléments XML
        edtNom = findViewById(R.id.edt_nom_xx);
        edtNaissance = findViewById(R.id.edt_naissance_xx);
        edtDestination = findViewById(R.id.edt_destination_xx);
        spnMotif = findViewById(R.id.spn_motif_nj);
        btnInspecter = findViewById(R.id.btn_inspecter_xx);

        // Remplissage du Spinner
        Spinner spnMotif = findViewById(R.id.spn_motif_nj);

        // Lecture des textes depuis strings.xml
        String motifChoisi = spnMotif.getSelectedItem().toString();

// Lecture des textes depuis strings.xml
        String[] motifs = getResources().getStringArray(R.array.motifs_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                motifs
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spnMotif.setAdapter(adapter);

        // Action du bouton
        btnInspecter.setOnClickListener(v -> {

            String nom = edtNom.getText().toString().trim();
            String naissance = edtNaissance.getText().toString().trim();
            String destination = edtDestination.getText().toString().trim();

            String motif = spnMotif.getSelectedItem().toString();

            // Vérification des champs
            if (nom.isEmpty() || naissance.isEmpty()
                    || destination.isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            int anneeNaissance;
            int anneeDestination;

            try {
                anneeNaissance = Integer.parseInt(naissance);
                anneeDestination = Integer.parseInt(destination);

            } catch (NumberFormatException e) {

                Toast.makeText(
                        MainActivity.this,
                        "Année invalide",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // Vérification des années
            if (anneeNaissance <= 0 || anneeDestination <= 0
                    || anneeDestination <= anneeNaissance) {

                Toast.makeText(
                        MainActivity.this,
                        "Veuillez vérifier les années saisies",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            // Création de l'Intent explicite
            Intent intent = new Intent(
                    MainActivity.this,
                    VerificationActivity.class
            );

            // Envoi des informations
            intent.putExtra(
                    IntentKeys.creerCle(MATRICULE, "NOM_VOYAGEUR"),
                    nom
            );

            intent.putExtra(
                    IntentKeys.creerCle(MATRICULE, "ANNEE_NAISSANCE"),
                    anneeNaissance
            );

            intent.putExtra(
                    IntentKeys.creerCle(MATRICULE, "ANNEE_DESTINATION"),
                    anneeDestination
            );

            intent.putExtra(
                    IntentKeys.creerCle(MATRICULE, "MOTIF"),
                    motif
            );

            // Ouverture de VerificationActivity
            startActivity(intent);
        });
    }
}
