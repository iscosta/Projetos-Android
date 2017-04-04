package fiap.com.br.nac02appfaculdade.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import fiap.com.br.nac02appfaculdade.bean.Faculdade;


/**
 * Created by RM75095 on 18/10/2016.
 */
public class FaculdadeDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Faculdades.db";
    private static final int DATABASE_VERSION = 1;

    public FaculdadeDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE TBL_FACULDADE (" +
                "ID INTEGER PRIMARY KEY, " +
                "NM_FACULDADE TEXT," +
                "NM_CURSO TEXT," +
                "DS_DURACAO TEXT," +
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

    public void inserir(Faculdade faculdade) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = pegaDados(faculdade);
        db.insert("TBL_FACULDADE", null, cv);
        db.close();
    }

    public void atualizar(Faculdade faculdade) {
        SQLiteDatabase db = getWritableDatabase();
        String strId = String.valueOf(faculdade.getId());
        ContentValues cv = pegaDados(faculdade);
        String[] id = {strId};

        db.update("TBL_FACULDADE", cv, "ID=?", id);
        db.close();
    }


    public void excluir(String strId) {
        SQLiteDatabase db = getWritableDatabase();
        String[] id = {strId};

        db.delete("TBL_FACULDADE", "ID=?", id);
        db.close();
    }

    public List<Faculdade> buscaFaculdades() {
        SQLiteDatabase db = getWritableDatabase();
        List<Faculdade> faculdades = new ArrayList<>();

        Cursor cursor = db.query("TBL_FACULDADE", null, null, null, null, null, null);
        while(cursor.moveToNext()){
            Faculdade faculdade= new Faculdade();
            faculdade.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            faculdade.setNome(cursor.getString(cursor.getColumnIndex("NM_FACULDADE")));
            faculdade.setCurso(cursor.getString(cursor.getColumnIndex("NM_CURSO")));
            faculdade.setDuracao(cursor.getString(cursor.getColumnIndex("DS_DURACAO")));
            faculdade.setWebSite(cursor.getString(cursor.getColumnIndex("DS_SITE")));
            faculdade.setEndereco(cursor.getString(cursor.getColumnIndex("DS_ENDERECO")));
            faculdade.setTelefone(cursor.getString(cursor.getColumnIndex("DS_TELEFONE")));
            faculdade.setPreco(cursor.getFloat(cursor.getColumnIndex("VL_PRECO")));
            faculdade.setRating(cursor.getFloat(cursor.getColumnIndex("VL_RATING")));
            faculdades.add(faculdade);
        }
        db.close();
        return faculdades;
    }

    private ContentValues pegaDados(Faculdade faculdade) {
        ContentValues cv = new ContentValues();
        cv.put("NM_FACULDADE", faculdade.getNome());
        cv.put("NM_CURSO", faculdade.getCurso());
        cv.put("DS_DURACAO", faculdade.getDuracao());
        cv.put("DS_SITE", faculdade.getWebSite());
        cv.put("VL_PRECO", faculdade.getPreco());
        cv.put("DS_TELEFONE", faculdade.getTelefone());
        cv.put("DS_ENDERECO", faculdade.getEndereco());
        cv.put("VL_RATING", faculdade.getRating());
        return cv;
    }
}
