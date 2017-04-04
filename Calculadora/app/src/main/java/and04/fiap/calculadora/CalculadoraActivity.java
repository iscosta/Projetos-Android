package and04.fiap.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalculadoraActivity extends AppCompatActivity {

    // referencias ao elementos da interface
    private EditText edtNum1;
    private EditText edtNum2;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        // define as referencia por meio de R.id
        // so pode ser feito apos o setContentView!!!
        edtNum1 = (EditText) findViewById(R.id.edtNumero1);
        edtNum2 = (EditText) findViewById(R.id.edtNumero2);
        txtResultado = (TextView) findViewById(R.id.txtResultado);

    }


    // todos os botoes (somar, subtratir, multiplicar e dividir) vao chamar o mesmo metodo calcular
    // analisando o parametro v conseguimos descobrir qual botao gerou o evento onClick
    public void calcular (View v) {

        // le o valor informado para o primeiro e segundo numeros e converte para double
        double n1 = Double.parseDouble(edtNum1.getText().toString());
        double n2 = Double.parseDouble(edtNum2.getText().toString());

        double resultado = 0.0;

        // analisa o Id do gerador do evento para aplicar a operacao necessaria
        switch (v.getId()) {

            case R.id.btnMais:
                resultado = n1 + n2;
                break;
            case R.id.btnMenos:
                resultado = n1 - n2;
                break;
            case R.id.btnMulti:
                resultado = n1 * n2;
                break;
            case R.id.btnDividir:
                resultado = n1 / n2;
        }

        // tem que converter para string o resultado neste caso
        txtResultado.setText(String.valueOf(resultado));

    }


}
