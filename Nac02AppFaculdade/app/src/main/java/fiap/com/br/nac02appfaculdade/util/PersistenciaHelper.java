package fiap.com.br.nac02appfaculdade.util;

import android.media.Rating;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RatingBar;

import fiap.com.br.nac02appfaculdade.FormularioActivity;
import fiap.com.br.nac02appfaculdade.R;
import fiap.com.br.nac02appfaculdade.bean.Faculdade;

/**
 * Created by RM75095 on 18/10/2016.
 */


public class PersistenciaHelper {

    private Faculdade faculdade;
    private final EditText edtFaculdade, edtCurso, edtDuracao,
            edtPreco, edtId, edtWebSite, edtTelefone, edtEndereco;
    private final RatingBar rbNota;

    public PersistenciaHelper(FormularioActivity activity) {
        edtFaculdade = (EditText) activity.findViewById(R.id.edtFaculdade);
        edtPreco = (EditText) activity.findViewById(R.id.edtPreco);
        edtId = (EditText) activity.findViewById(R.id.edtId);
        edtCurso = (EditText) activity.findViewById(R.id.edtCurso);
        edtDuracao = (EditText) activity.findViewById(R.id.edtDuracao);
        edtWebSite = (EditText) activity.findViewById(R.id.edtWebSite);
        edtTelefone = (EditText) activity.findViewById(R.id.edtTelefone);
        edtEndereco = (EditText) activity.findViewById(R.id.edtEndereco);
        rbNota =  (RatingBar) activity.findViewById(R.id.ratBar);
        faculdade = new Faculdade();
    }

    public Faculdade pegaFaculdade() {
        if(edtId.getText().length()>0)
            faculdade.setId(Integer.valueOf(edtId.getText().toString()));

        faculdade.setNome(edtFaculdade.getText().toString());
        faculdade.setPreco(Float.valueOf(edtPreco.getText().toString()));
        faculdade.setDuracao(edtDuracao.getText().toString());
        faculdade.setCurso(edtCurso.getText().toString());
        faculdade.setWebSite(edtWebSite.getText().toString());
        faculdade.setTelefone(edtTelefone.getText().toString());
        faculdade.setEndereco(edtEndereco.getText().toString());
        faculdade.setRating(rbNota.getRating());
        return faculdade;
    }

    public void preencheEdts(Faculdade faculdade){
        if(faculdade != null){
            try {
                edtFaculdade.setText(faculdade.getNome());
                edtCurso.setText(faculdade.getCurso());
                edtDuracao.setText(faculdade.getDuracao());
                edtPreco.setText(faculdade.getPreco().toString());
                edtId.setText(faculdade.getId().toString());
                edtWebSite.setText(faculdade.getWebSite());
                edtEndereco.setText(faculdade.getEndereco());
                edtTelefone.setText(faculdade.getTelefone());
                rbNota.setRating(faculdade.getRating());
                this.faculdade = faculdade;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public boolean validaCampos() {
        String faculdade = edtFaculdade.getText().toString().trim();
        String preco = edtPreco.getText().toString().trim();
        return (!TextUtils.isEmpty(faculdade) && !TextUtils.isEmpty(preco));
    }
}


