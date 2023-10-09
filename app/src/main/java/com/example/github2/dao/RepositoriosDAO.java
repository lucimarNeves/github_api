package com.example.github2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.github2.model.GitHubRepo;
import com.example.github2.utils.DBHelper;

public class RepositoriosDAO {

    private final SQLiteDatabase write;
    private final SQLiteDatabase read;

    public RepositoriosDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        this.write =  dbHelper.getWritableDatabase();
        this.read = dbHelper.getReadableDatabase();
    }

    public void salvarRepositorio(GitHubRepo gitHubRepo){

        ContentValues cv = new ContentValues();
        cv.put("nome", gitHubRepo.getName());
        cv.put("linguagem", gitHubRepo.getLanguage());
        cv.put("descrição", gitHubRepo.getDescription());

        try{

            write.insert(DBHelper.TB_REPOSITORIOS, null, cv);
            //write.close()

        }catch (Exception e){
            Log.i("ERROR", "Erro ao salvar repositório:" + e.getMessage());

        }
    }
}
