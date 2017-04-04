package br.com.fiap.persistencia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.persistencia.dao.RestauranteDAO;
import br.com.fiap.persistencia.modelo.Restaurante;

public class RestauranteActivity extends AppCompatActivity {

    private ListView listRestaurantes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistencia);

        listRestaurantes = (ListView) findViewById(R.id.listRestaurantes);
        //registerForContextMenu para exibir o menu no ListView listRestaurantes
        registerForContextMenu(listRestaurantes);
        //setOnItemClickListener para chamar o FormularioActivity passando o restauranteuto selecionado
        listRestaurantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurante restaurante = (Restaurante) listRestaurantes.getItemAtPosition(position);

                Intent intentChamaFormulario = new Intent(RestauranteActivity.this, FormularioActivity.class);
                intentChamaFormulario.putExtra("restaurante", restaurante);
                startActivity(intentChamaFormulario);
            }
        });
    }

    //Menu de Opções
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        //Utiliza o AdapterView para pegar o objeto sobre o qual o menu foi acionado
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //Pega através da posição do objeto, o restaurante correspondente no listRestaurantes
        final Restaurante restaurante = (Restaurante) listRestaurantes.getItemAtPosition(info.position);
        //Adiciona um item no menu
        MenuItem site = menu.add("Abrir Site do " + restaurante.getNome());
        //Cria um Intente para chamada de um app
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        //Busca a URL do restaurante e adiciona http://, se necessário
        String url = restaurante.getWebSite();
        if(!url.startsWith("http://")){
            url = "http://" + url;
        }
        //Seta o parâmetro a ser passado para o outro aplicativo
        //ao receber http:// o SO entende que deve abrir um browser
        intentSite.setData(Uri.parse(url));
        //configura o munu site para disparar o intent ao ser selecionado
        site.setIntent(intentSite);


        MenuItem fone = menu.add("Ligar para o " + restaurante.getNome());
        Intent intentFone = new Intent(Intent.ACTION_VIEW);
        //Uri tel: abre o app de telefone
        intentFone.setData(Uri.parse("tel:" + Uri.encode(restaurante.getTelefone())));
        fone.setIntent(intentFone);

        MenuItem mapa = menu.add("Localizar " + restaurante.getNome() + " no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        //Uri geo: abre o app de mapas
        intentMapa.setData(Uri.parse("geo:0,0?q=" + Uri.encode(restaurante.getEndereco())));
        mapa.setIntent(intentMapa);

        MenuItem sms = menu.add("Enviar dica sobre o " + restaurante.getNome());
        sms.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intentDica = new Intent(Intent.ACTION_VIEW);
                String dica = restaurante.getNome() + " Prato: " + restaurante.getPrato()
                        + " " + restaurante.getTelefone() + " " + restaurante.getWebSite();
                //Uri sms: abre o app de mensagens
                intentDica.setData(Uri.parse("sms:"));
                //putExtra envia a String dica como parâmetro para o intentDica
                intentDica.putExtra("sms_body", dica);
                startActivity(intentDica);
                return false;
            }
        });

        MenuItem deletar = menu.add("Excluir Restaurante");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                RestauranteDAO restauranteDAO = new RestauranteDAO(RestauranteActivity.this);

                String strId = restaurante.getId().toString();
                restauranteDAO.excluir(strId);
                restauranteDAO.close();
                carregaLista();
                return false;
            }
        });
    }

    //Método que carrega o listRestaurantes a partir do DAO.buscaRestaurantes
    private void carregaLista() {
        List<Restaurante> restaurantes = new ArrayList<>();
        RestauranteDAO restauranteDAO = new RestauranteDAO(this);
        restaurantes = restauranteDAO.buscaRestaurantes();
        restauranteDAO.close();
        //Popula o listRestaurantes
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, restaurantes);
        listRestaurantes.setAdapter(adapter);
    }

    //Método que chama o FormularioActivity sem parâmetro, para criar um novo
    public void novoRestaurante(View v){
        Intent intentChamaFormulario = new Intent(RestauranteActivity.this, FormularioActivity.class);
        startActivity(intentChamaFormulario);
    }

   //recarrega o list
    @Override
    protected void onResume() {
        super.onResume();
        //Carrega a lista sempre que onResume a chamado
        carregaLista();
    }
}

