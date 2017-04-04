package fiap.com.br.nac02appfaculdade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import fiap.com.br.nac02appfaculdade.bean.Faculdade;
import fiap.com.br.nac02appfaculdade.dao.FaculdadeDAO;
import fiap.com.br.nac02appfaculdade.util.PersistenciaHelper;

/**
 * Created by RM75095 on 18/10/2016.
 */
public class FormularioActivity extends AppCompatActivity{

    private EditText edtId ;
    private PersistenciaHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new PersistenciaHelper(this);
        edtId = (EditText) findViewById(R.id.edtId);

        Intent intent = getIntent();
        Faculdade faculdade = (Faculdade) intent.getSerializableExtra("faculdade");
        if(faculdade != null) {
            helper.preencheEdts(faculdade);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
               FaculdadeDAO faculdadeDAO = new FaculdadeDAO(this);
                Faculdade faculdade= new Faculdade();

                if(helper.validaCampos()){
                    String strId = edtId.getText().toString().trim();
                    faculdade = helper.pegaFaculdade();

                    if(TextUtils.isEmpty(strId))
                        faculdadeDAO.inserir(faculdade);
                    else
                        faculdadeDAO.atualizar(faculdade);
                    faculdadeDAO.close();
                    Toast.makeText(FormularioActivity.this, "Faculdade" + faculdade.getNome() + " salvo!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
                }
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }




}
