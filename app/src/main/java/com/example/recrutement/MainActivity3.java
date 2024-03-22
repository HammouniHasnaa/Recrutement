package com.example.recrutement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    private EditText titreEditText, typeContratEditText, descriptionEditText;
    private Spinner categorieSpinner, secteurSpinner, villeSpinner;
    private Button suivantButton;
    private DBRecrutement db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        db =new DBRecrutement(this);

        // Récupérer les références des EditText
        titreEditText = findViewById(R.id.editTextText2);
        typeContratEditText = findViewById(R.id.editTextText4);
        descriptionEditText = findViewById(R.id.editText);

        // Récupérer les références des Spinners
        categorieSpinner = findViewById(R.id.spinner_categories);
        secteurSpinner = findViewById(R.id.spinner_secteurs);
        villeSpinner = findViewById(R.id.spinner5);

        // Récupérer la référence du bouton
        suivantButton = findViewById(R.id.suivant);

        Spinner spinner = findViewById(R.id.spinner5);
        String[] villes = getResources().getStringArray(R.array.villes_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, villes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinnercat = findViewById(R.id.spinner_categories);
        String[] categories = {"....","Informatique", "Vente", "Marketing", "Finance", "Ressources humaines"};
        ArrayAdapter<String> adaptercat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adaptercat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Utilisez adaptercat ici
        spinnercat.setAdapter(adaptercat);

        Spinner spinnersect = findViewById(R.id.spinner_secteurs);
        String[] secteurs = {"Choisissez un secteur","Industriel", "Iot", "IT", "internationnal", "Bancaire"};
        ArrayAdapter<String> adaptersect = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, secteurs);
        adaptersect.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersect.setAdapter(adaptersect);

        Button buttoncomp=findViewById(R.id.suivant);

        buttoncomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titre = titreEditText.getText().toString();
                String typeContrat = typeContratEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String categorie = categorieSpinner.getSelectedItem().toString();
                String secteur = secteurSpinner.getSelectedItem().toString();
                String ville = villeSpinner.getSelectedItem().toString();

               db.ajouterAnnonce(titre,categorie,secteur,description,ville);

                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                int personnesAAgadir = db.nombrePersonnesAAgadir();
                intent.putExtra("nombrePersonnesAAgadir", personnesAAgadir);
                startActivity(intent);
            }});

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}