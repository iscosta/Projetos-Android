package br.com.fiap.persistencia.util;

import android.media.Rating;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.fiap.persistencia.FormularioActivity;
import br.com.fiap.persistencia.R;
import br.com.fiap.persistencia.modelo.Restaurante;

public class PersistenciaHelper {
    private Restaurante restaurante;
    private final EditText edtRestaurante, edtEspecialidade, edtPrato,
            edtPreco, edtId, edtWebSite, edtTelefone, edtEndereco;
    private final RatingBar rbNota;

    public PersistenciaHelper(FormularioActivity activity) {
        edtRestaurante = (EditText) activity.findViewById(R.id.edtRestaurante);
        edtPreco = (EditText) activity.findViewById(R.id.edtPreco);
        edtId = (EditText) activity.findViewById(R.id.edtId);
        edtEspecialidade = (EditText) activity.findViewById(R.id.edtEspecialidade);
        edtPrato = (EditText) activity.findViewById(R.id.edtPrato);
        edtWebSite = (EditText) activity.findViewById(R.id.edtWebSite);
        edtTelefone = (EditText) activity.findViewById(R.id.edtTelefone);
        edtEndereco = (EditText) activity.findViewById(R.id.edtEndereco);
        rbNota =  (RatingBar) activity.findViewById(R.id.ratBar);
        restaurante = new Restaurante();
    }

    //Método que pega os valores dos EditTexts e retorna um objeto Restaurante
    public Restaurante pegaRestaurante() {
        if(edtId.getText().length()>0)
            restaurante.setId(Integer.valueOf(edtId.getText().toString()));

        restaurante.setNome(edtRestaurante.getText().toString());
        restaurante.setPreco(Float.valueOf(edtPreco.getText().toString()));
        restaurante.setPrato(edtPrato.getText().toString());
        restaurante.setEspecialidade(edtEspecialidade.getText().toString());
        restaurante.setWebSite(edtWebSite.getText().toString());
        restaurante.setTelefone(edtTelefone.getText().toString());
        restaurante.setEndereco(edtEndereco.getText().toString());
        restaurante.setRating(rbNota.getRating());
        return restaurante;
    }

    //Métodos que preenche o Edits com os valores de um objeto Restaurante
    public void preencheEdts(Restaurante restaurante){
        if(restaurante != null){
           try {
               edtRestaurante.setText(restaurante.getNome());
               edtEspecialidade.setText(restaurante.getEspecialidade());
               edtPrato.setText(restaurante.getPrato());
               edtPreco.setText(restaurante.getPreco().toString());
               edtId.setText(restaurante.getId().toString());
               edtWebSite.setText(restaurante.getWebSite());
               edtEndereco.setText(restaurante.getEndereco());
               edtTelefone.setText(restaurante.getTelefone());
               rbNota.setRating(restaurante.getRating());
               this.restaurante = restaurante;
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }
    //Método que valida se os editTexts estão preenchidos
    public boolean validaCampos() {
        String restaurante = edtRestaurante.getText().toString().trim();
        String preco = edtPreco.getText().toString().trim();
        return (!TextUtils.isEmpty(restaurante) && !TextUtils.isEmpty(preco));
    }
}

