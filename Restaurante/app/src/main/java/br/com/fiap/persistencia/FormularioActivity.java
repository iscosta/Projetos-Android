package br.com.fiap.persistencia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.com.fiap.persistencia.dao.RestauranteDAO;
import br.com.fiap.persistencia.modelo.Restaurante;
import br.com.fiap.persistencia.util.PersistenciaHelper;

public class FormularioActivity extends AppCompatActivity {
    private EditText edtId ;
    private PersistenciaHelper helper;

    //Instancia o helper
    //Recebe um restaurante serializado do listRestaurantes para alteração
    //Quando vem do btnNovorestaurante não recebe parâmetros, portanto, cria um novo registro
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new PersistenciaHelper(this);
        edtId = (EditText) findViewById(R.id.edtId);

        Intent intent = getIntent();
        //Recebe o Restaurante da RestauranteActivity
        Restaurante restaurante = (Restaurante) intent.getSerializableExtra("restaurante");
        //Se ele for diferente de nulo, popula os Edits para alteração
        if(restaurante != null) {
            helper.preencheEdts(restaurante);
        }
    }

    //Cria o Menu para o botão gravar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Insere ou atualiza um restaurante na base utilizando um MenuItem
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                RestauranteDAO restauranteDAO = new RestauranteDAO(this);
                Restaurante restaurante = new Restaurante();

                //Valida se os campos estão preenchidos
                if(helper.validaCampos()){
                    //Pega o ID do Edit edtId
                    String strId = edtId.getText().toString().trim();
                    restaurante = helper.pegaRestaurante();
                    //Se o ID for vazio
                    if(TextUtils.isEmpty(strId))
                        restauranteDAO.inserir(restaurante);
                    else
                        restauranteDAO.atualizar(restaurante);
                        restauranteDAO.close();
                    Toast.makeText(FormularioActivity.this, "Restaurante " + restaurante.getNome() + " salvo!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                }
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

  }
