package com.fiap.igorcosta.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private ImageView imgDadoAndroid;
    private ImageView imgDadoVoce;
    private ImageView imgDadoAndroid2;
    private ImageView imgDadoVoce2;
    private TextView txtResultado;

    private int[] dados = new int[]{
            R.drawable.dado1,
            R.drawable.dado2,
            R.drawable.dado3,
            R.drawable.dado4,
            R.drawable.dado5,
            R.drawable.dado6,
            };

    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgDadoAndroid = (ImageView) findViewById(R.id.imgDadoAndroid);
        imgDadoVoce = (ImageView) findViewById(R.id.imgAndroidVoce);
        imgDadoAndroid2 = (ImageView) findViewById(R.id.imgDadoAndroid2);
        imgDadoVoce2 = (ImageView) findViewById(R.id.imgAndroidVoce2);
        txtResultado = (TextView) findViewById(R.id.txtResultado);
        animation = AnimationUtils.loadAnimation(this, R.anim.rotacao);

    }

    public void jogar (View v){

        String fraseVencedor = getResources().getString(R.string.strResultado);

        Random r = new Random ();

        int jogarAndroid = r.nextInt(5);
        int jogarVoce = r.nextInt(5);

        imgDadoAndroid.startAnimation(animation);
        imgDadoVoce.startAnimation(animation);

        imgDadoAndroid2.startAnimation(animation);
        imgDadoVoce2.startAnimation(animation);

        for (int i=0; i<animation.getRepeatCount(); i++) {

            jogarAndroid = r.nextInt(5);
            jogarVoce = r.nextInt(5);

            imgDadoAndroid.setImageResource(dados[jogarAndroid]);
            imgDadoVoce.setImageResource(dados[jogarVoce]);

            imgDadoAndroid2.setImageResource(dados[jogarAndroid]);
            imgDadoVoce2.setImageResource(dados[jogarVoce]);

        }

        if (jogarAndroid > jogarVoce)

            fraseVencedor +=getResources().getString(R.string.strAndroid);
            else if(jogarAndroid < jogarVoce)

                fraseVencedor +=getResources().getString(R.string.strVoce);
                else
            fraseVencedor += getResources().getString(R.string.strEmpate);

        txtResultado.setText(fraseVencedor);

            }
        }


