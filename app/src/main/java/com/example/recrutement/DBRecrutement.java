package com.example.recrutement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

    // Dans votre classe DBRecrutement, vous pouvez définir la méthode pour créer la table "compte"
    public class DBRecrutement extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "RecrutementDB";
        private static final String TABLE_COMPTE = "compte";
        private static final String TABLE_ANNONCE = "annonce";
        private static final String KEY_ID = "id";
        private static final String KEY_EMAIL = "email";
        private static final String KEY_PASSWORD = "password";
        private static final String KEY_TITRE = "titre";
        private static final String KEY_CATEGORIE = "categorie";
        private static final String KEY_SECTEUR = "secteur";
        private static final String KEY_DESCRIPTION = "description";
        private static final String KEY_VILLE = "ville";

        public DBRecrutement(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_COMPTE_TABLE = "CREATE TABLE " + TABLE_COMPTE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_EMAIL + " TEXT,"
                    + KEY_PASSWORD + " TEXT" + ")";
            db.execSQL(CREATE_COMPTE_TABLE);

            String CREATE_ANNONCE_TABLE = "CREATE TABLE " + TABLE_ANNONCE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_TITRE + " TEXT,"
                    + KEY_CATEGORIE + " TEXT,"
                    + KEY_SECTEUR + " TEXT,"
                    + KEY_DESCRIPTION + " TEXT,"
                    + KEY_VILLE + " TEXT" + ")";
            db.execSQL(CREATE_ANNONCE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMPTE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANNONCE);
            onCreate(db);
        }

        // Méthode pour ajouter un compte dans la table
        public void ajouterCompte(String email, String motdepasse) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_EMAIL, email);
            values.put(KEY_PASSWORD, motdepasse);

            db.insert(TABLE_COMPTE, null, values);
            db.close();
        }

        // Méthode pour ajouter une annonce dans la table
        public void ajouterAnnonce(String titre, String categorie, String secteur, String description, String ville) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(KEY_TITRE, titre);
            values.put(KEY_CATEGORIE, categorie);
            values.put(KEY_SECTEUR, secteur);
            values.put(KEY_DESCRIPTION, description);
            values.put(KEY_VILLE, ville);

            db.insert(TABLE_ANNONCE, null, values);
            db.close();
        }
        public int nombrePersonnesAAgadir() {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT COUNT(*) FROM " + TABLE_ANNONCE + " WHERE " + KEY_VILLE + " = ?";
            String[] selectionArgs = {"Agadir"};
            Cursor cursor = db.rawQuery(query, selectionArgs);
            int count = 0;
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    count = cursor.getInt(0);
                }
                cursor.close();
            }
            return count;
        }

    }





