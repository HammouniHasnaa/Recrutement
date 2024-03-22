package com.example.recrutement;



import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
         private RadioGroup etat;
         private EditText mail ,password, confirmer;
         private Button btncreation;
         private DBRecrutement db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        //LA BASE DE DONNÉE
        etat=findViewById(R.id.etat);
        mail=findViewById(R.id.mail);
        password=findViewById(R.id.password);
        confirmer=findViewById(R.id.confirmer);
        btncreation=findViewById(R.id.buttoncreation);
       db=new DBRecrutement(this);
       Button buttoncomp=findViewById(R.id.buttoncreation);

        buttoncomp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
                String motdepasse = password.getText().toString().trim();
                String confirmerpass = confirmer.getText().toString().trim();

                boolean isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); // Vérification de la validité de l'e-mail
                boolean isRadioButtonSelected = etat.getCheckedRadioButtonId() != -1;
                boolean ispasswordNotEmpty = !motdepasse.isEmpty();
                boolean isPasswordMatch = motdepasse.equals(confirmerpass);
                if (!isEmailValid) {
                    Toast.makeText(getApplicationContext(), "Veuillez saisir une adresse e-mail valide", Toast.LENGTH_SHORT).show();
                } else if (!isRadioButtonSelected) {
                    Toast.makeText(getApplicationContext(), "Veuillez sélectionner un état", Toast.LENGTH_SHORT).show();
                } else if (!ispasswordNotEmpty) {
                    Toast.makeText(getApplicationContext(), "Veuillez saisir un mot de passe", Toast.LENGTH_SHORT).show();
                } else if (!isPasswordMatch) {
                    Toast.makeText(getApplicationContext(), "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                } else {
                    db.ajouterCompte(email, motdepasse);
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    startActivity(intent);
                }
            }

        });
        int resourceId = getResources().getIdentifier("button_background", "drawable", getPackageName());

        Spinner spinner = findViewById(R.id.spinner);

        String[] villes = getResources().getStringArray(R.array.villes_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, villes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });


    }

}