package com.example.github2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.github2.R;
import com.example.github2.adapter.ReposAdapter;
import com.example.github2.model.GitHubRepo;
import com.example.github2.utils.DBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepositoriosSalvosActivity extends AppCompatActivity {


      private List<GitHubRepo> repos = new ArrayList<>();
      public ListView listViewDados;

      public Button voltar;

//      private TextView repoName, repoDescription, repoLanguage;

    private SQLiteDatabase db;

    public static  final String TB_REPOSITORIOS = "TB_REPOSITORIOS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorios_salvos);

        listViewDados = findViewById(R.id.listViewDados);
        voltar = findViewById(R.id.btn_voltar);

//        criarDados();
//        inserirDados();
//        listarDados();

//        DBHelper dbHelper = new DBHelper((getApplicationContext()));
//
//        ContentValues cv = new ContentValues();
//        cv.put("nome", " " );
//
//        dbHelper.getWritableDatabase().insert("repositorios", null, cv );

//        repoName = findViewById(R.id.repoName);
//        repoDescription = findViewById(R.id.repoDescription);
//        repoLanguage = findViewById(R.id.repoLanguage);

       // recuperarDados();

    }




//    private void recuperarDados() {
//
//
////     SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);
////
////        String nome = sharedPreferences.getString("name", "");
////        String descricao = sharedPreferences.getString("description", "");
////        String linguagem = sharedPreferences.getString("language", "");
////
////
////        repoName.setText(nome);
////        repoDescription.setText(descricao);
////        repoLanguage.setText(linguagem);
//
//
//    }
    public void criarDados() {

    }
    public void listarDados(){



    }
    private void inserirDados() {

    }

}