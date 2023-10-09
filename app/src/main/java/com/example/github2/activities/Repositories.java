package com.example.github2.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.github2.R;
import com.example.github2.adapter.ReposAdapter;
import com.example.github2.dao.RepositoriosDAO;
import com.example.github2.model.GitHubRepo;
import com.example.github2.repository.APIClient;
import com.example.github2.repository.GitHubRepoEndPoint;
import com.example.github2.utils.DBHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories extends AppCompatActivity {
    String receivedUserName;
    TextView userNameTV;
    RecyclerView mRecyclerView;
    List<GitHubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    Button btnSalvar, btnRepSalvo;

    private static final String ARQUIVO_PREFERENCIA = "ArquivoRepositorios";
    private RepositoriosDAO repositoriosDAO;

    public ListView listViewDados;
    private SQLiteDatabase db;

    public static final String TB_REPOSITORIOS = "TB_REPOSITORIOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        Bundle extras = getIntent().getExtras();

        repositoriosDAO = new RepositoriosDAO(this);

        receivedUserName = extras.getString("username");

        userNameTV = findViewById(R.id.userNameTV);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnRepSalvo = findViewById(R.id.btnRepSalvo);

        userNameTV.setText("User: " + receivedUserName);

        mRecyclerView = findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo, getApplicationContext());

        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();
//        criarDados();
//        inserirDados();
//        listarDados();

    }

    public void loadRepositories() {
        GitHubRepoEndPoint apiService =
                APIClient.getClient().create(GitHubRepoEndPoint.class);

        Call<List<GitHubRepo>> call = apiService.getRepo(receivedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                myDataSource.clear();
                myDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Repos", t.toString());
            }

        });

        //salvar
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //        saveData();
//                listarDados();
                Intent intent = new Intent(getApplicationContext(), RepositoriosSalvosActivity.class);
                startActivity(intent);
            }
        });

        btnRepSalvo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void saveData() {
        //method for saving the data in array list.
        //creating a variable for storing data in
        //shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.preference_file_key), Context.MODE_PRIVATE);

//        // creating a variable for editor to
//        // store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        //creating a new variable for gson
        Gson gson = new Gson();
//
//        //getting data from gson and storing it in a string
        String json = gson.toJson(myDataSource);
//
////        //below line is to sve data in shared
////        //prefs in the form os string
//        editor.putString("repositories", json);


        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            final String key = entry.getKey();
            final Object value = entry.getValue();
            Log.d("map values", key + ": " + value);
        }
//
//        // below line is to apply changes
//        // and save data in shared prefs.

        editor.apply();

        editor.commit();
        // after saving data we are displaying a toast message.
        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

//    public void criarDados() {
//        try {
//            db = openOrCreateDatabase(TB_REPOSITORIOS, MODE_PRIVATE, null);
//            db.execSQL("CREATE TABLE IF NOT EXISTS " + TB_REPOSITORIOS
//                    + " (ind INTEGER PRIMARY KEY AUTOINCREMENT, "
//                    + " name TEXT NOT NULL, "
//                    + " language TEXT NOT NULL," +
//                    " description TEXT NOT NULL);");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listarDados() {
//
//        try {
//            db = openOrCreateDatabase(TB_REPOSITORIOS, MODE_PRIVATE, null);
//            Cursor cursor = db.rawQuery("SELECT id, name FROM TB_REPOSITORIOS", null);
//            ArrayList<String> linhas = new ArrayList<String>();
//            ArrayAdapter adapter = new ArrayAdapter<String>(
//                    this,
//                    android.R.layout.simple_list_item_1,
//                    android.R.id.text1,
//                    linhas
//            );
//            listViewDados.setAdapter(adapter);
//            cursor.moveToFirst();
//            while (cursor != null) {
//
//                //acrescentando o que veio do banco de dados no array linhas
//                linhas.add(cursor.getString(1));
//                cursor.moveToNext();
//
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void inserirDados() {
//        try {
//            db = openOrCreateDatabase(TB_REPOSITORIOS, MODE_PRIVATE, null);
//            String sql = "INSERT INTO TB_REPOSITORIOS (ome) VALUES (?)";
//            SQLiteStatement statement = db.compileStatement(sql);
//            statement.bindString(1, "Java");
//            statement.executeInsert();
//
//            statement.bindString(2, "SQL");
//            statement.executeInsert();
//
//            statement.bindString(1, "JavaScript");
//            statement.executeInsert();
//            db.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//     }
//
  // }
}

