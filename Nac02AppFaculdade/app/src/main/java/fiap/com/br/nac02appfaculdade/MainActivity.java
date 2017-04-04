package fiap.com.br.nac02appfaculdade;

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

import fiap.com.br.nac02appfaculdade.bean.Faculdade;
import fiap.com.br.nac02appfaculdade.dao.FaculdadeDAO;

public class MainActivity extends AppCompatActivity {

    private ListView listFaculdades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFaculdades = (ListView) findViewById(R.id.listFaculdades);
        registerForContextMenu(listFaculdades);

        listFaculdades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Faculdade faculdade = (Faculdade) listFaculdades.getItemAtPosition(position);

                Intent intentChamaFormulario = new Intent(MainActivity.this, FormularioActivity.class);
                intentChamaFormulario.putExtra("faculdade", faculdade);
                startActivity(intentChamaFormulario);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Faculdade faculdade = (Faculdade) listFaculdades.getItemAtPosition(info.position);

        MenuItem site = menu.add("Acessar o site da " + faculdade.getNome());
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String url = faculdade.getWebSite();

        if(!url.startsWith("http://")){
            url = "http://" + url;
        }

        intentSite.setData(Uri.parse(url));
        site.setIntent(intentSite);


        MenuItem telefone = menu.add("Ligar para a " + faculdade.getNome());
        Intent intentFone = new Intent(Intent.ACTION_VIEW);
        //Uri telefone
        intentFone.setData(Uri.parse("tel:" + Uri.encode(faculdade.getTelefone())));
        telefone.setIntent(intentFone);

        MenuItem mapa = menu.add("Lozalizar: " + faculdade.getNome() + " no Mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        //Uri mapa
        intentMapa.setData(Uri.parse("geo:0,0?q=" + Uri.encode(faculdade.getEndereco())));
        mapa.setIntent(intentMapa);

        MenuItem sms = menu.add("Enviar informações sobre a " + faculdade.getNome()  + " por SMS");
        sms.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intentInfo = new Intent(Intent.ACTION_VIEW);
                String info = faculdade.getNome() + " Curso: " + faculdade.getCurso()
                        + " " + faculdade.getTelefone() + " " + faculdade.getWebSite();
                //Uri sms
                intentInfo.setData(Uri.parse("sms:"));
                intentInfo.putExtra("sms_body", info);
                startActivity(intentInfo);
                return false;
            }
        });

        MenuItem deletar = menu.add("Deletar Faculdade");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FaculdadeDAO faculdadeDAO= new FaculdadeDAO(MainActivity.this);

                String strId = faculdade.getId().toString();
                faculdadeDAO.excluir(strId);
                faculdadeDAO.close();
                carregaLista();
                return false;
            }
        });
    }

    private void carregaLista() {
        List<Faculdade> faculdades= new ArrayList<>();
        FaculdadeDAO faculdadeDAO= new FaculdadeDAO(this);
        faculdades = faculdadeDAO.buscaFaculdades();
        faculdadeDAO.close();

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faculdades);
        listFaculdades.setAdapter(adapter);
    }

    public void novaFaculdade(View v){
        Intent intentChamaFormulario = new Intent(MainActivity.this, FormularioActivity.class);
        startActivity(intentChamaFormulario);
    }

    //recarregando o list
    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}

