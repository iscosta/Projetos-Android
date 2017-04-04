package br.com.fiap.persistencia.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.persistencia.modelo.Restaurante;

public class RestauranteDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Restaurantes.db";
    private static final int DATABASE_VERSION = 1;

    public RestauranteDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TBL_RESTAURANTE (" +
                        "ID INTEGER PRIMARY KEY, " +
                        "NM_RESTAURANTE TEXT," +
                        "DS_ESPECIALIDADE TEXT," +
                        "NM_PRATO_PREFERIDO TEXT," +
                        "VL_PRECO REAL," +
                        "DS_SITE TEXT," +
                        "DS_ENDERECO TEXT," +
                        "DS_TELEFONE TEXT," +
                        "VL_RATING REAL)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS TBL_RESTAURANTE";
        db.execSQL(sql);
        onCreate(db);
    }

    //Método que insere o Restaurante na Base
    public void inserir(Restaurante restaurante) {
        SQLiteDatabase db = getWritableDatabase();
        //Classe que encapsula os valores que serao passados como paramtros
        ContentValues cv = pegaDados(restaurante);
        // INSERT INTO TBL_RESTAURANTE (NM_RESTAURANTE, VL_PRECO) VALUES (...,...)
        //null forma de tratamento de valores nulos
        db.insert("TBL_RESTAURANTE", null, cv);
        db.close();
    }

    //Método que atualiza o Restaurante na Base
    public void atualizar(Restaurante restaurante) {
        SQLiteDatabase db = getWritableDatabase();
        String strId = String.valueOf(restaurante.getId());
        //Classe que encapsula os valores que serao passados como paramtros
        ContentValues cv = pegaDados(restaurante);
        String[] id = {strId};
        // UPDATE TBL_RESTAURANTE SET NM_RESTAURANTE=?, VL_PRECO=? WHERE ID=?
        //P3 Where P4 Parametro
        db.update("TBL_RESTAURANTE", cv, "ID=?", id);
        db.close();
    }

    //Método que exclui o Restaurante na Base
    public void excluir(String strId) {
        SQLiteDatabase db = getWritableDatabase();
        String[] id = {strId};
        // DELETE FROM TBL_RESTAURANTE WHERE ID=?
        //P2 Where P3 Parametro
        db.delete("TBL_RESTAURANTE", "ID=?", id);
        db.close();
    }

    //Método que carrega o listRestaurantes
    public List<Restaurante> buscaRestaurantes() {
        SQLiteDatabase db = getWritableDatabase();
        List<Restaurante> restaurantes = new ArrayList<>();
        //Select * from TBL_RESTAURANTE
        Cursor cursor = db.query("TBL_RESTAURANTE", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            Restaurante restaurante = new Restaurante();
            restaurante.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            restaurante.setNome(cursor.getString(cursor.getColumnIndex("NM_RESTAURANTE")));
            restaurante.setEspecialidade(cursor.getString(cursor.getColumnIndex("DS_ESPECIALIDADE")));
            restaurante.setPrato(cursor.getString(cursor.getColumnIndex("NM_PRATO_PREFERIDO")));
            restaurante.setWebSite(cursor.getString(cursor.getColumnIndex("DS_SITE")));
            restaurante.setEndereco(cursor.getString(cursor.getColumnIndex("DS_ENDERECO")));
            restaurante.setTelefone(cursor.getString(cursor.getColumnIndex("DS_TELEFONE")));
            restaurante.setPreco(cursor.getFloat(cursor.getColumnIndex("VL_PRECO")));
            restaurante.setRating(cursor.getFloat(cursor.getColumnIndex("VL_RATING")));
            restaurantes.add(restaurante);
        }
        db.close();
        return restaurantes;
    }

    private ContentValues pegaDados(Restaurante restaurante) {
        ContentValues cv = new ContentValues();
        cv.put("NM_RESTAURANTE", restaurante.getNome());
        cv.put("DS_ESPECIALIDADE", restaurante.getEspecialidade());
        cv.put("NM_PRATO_PREFERIDO", restaurante.getPrato());
        cv.put("DS_SITE", restaurante.getWebSite());
        cv.put("VL_PRECO", restaurante.getPreco());
        cv.put("DS_TELEFONE", restaurante.getTelefone());
        cv.put("DS_ENDERECO", restaurante.getEndereco());
        cv.put("VL_RATING", restaurante.getRating());
        return cv;
    }
}
