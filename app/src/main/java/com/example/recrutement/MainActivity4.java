package com.example.recrutement;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);


        // Récupérer la valeur transmise depuis l'activité précédente
        int nombrePersonnesAAgadir = getIntent().getIntExtra("nombrePersonnesAAgadir", 0);

        // Utiliser la valeur comme bon vous semble, par exemple l'afficher dans un TextView
        TextView textView = findViewById(R.id.nbr);
        textView.setText("Nombre de personnes à Agadir : " + nombrePersonnesAAgadir);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}