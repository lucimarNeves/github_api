package com.example.github2.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.github2.model.GitHubRepo;


public class DBHelper extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_DB = "DB_APP";
    public static  final String TB_REPOSITORIOS = "TB_REPOSITORIOS";

    public DBHelper(Context context) {
        super(context, NOME_DB, null, VERSAO);
    }

    //executado sempre que o aplicativo cria o banco de dados pela 1a.vez
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TB_REPOSITORIOS
                + " (ind INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " name TEXT NOT NULL, " +
                " language TEXT NOT NULL," +
                " description TEXT NOT NULL);" ;

        try{

            sqLiteDatabase.execSQL( sql);
            Log.i("Sucesso", "Sucesso ao criar a tabela: ");

        }catch(Exception e){
            Log.i("ERROR", "Erro ao criar tablela: " + e.getMessage());
        }
        close();
    }


    //executado sempre que trocar a versãodo banco de dados
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
