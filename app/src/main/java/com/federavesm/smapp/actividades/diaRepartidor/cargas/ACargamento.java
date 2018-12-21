package com.federavesm.smapp.actividades.diaRepartidor.cargas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Carga;

/**
 * Created by Federico on 19/2/2018.
 */



public class ACargamento extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acargamento);




        buttonCargas = (Button)findViewById(R.id.aCargamentoButtonCargas);
        buttonCargas.setOnClickListener(new ListenerClickButtonCargas());

        buttonDescargas = (Button)findViewById(R.id.aCargamentoButtonDescargas);
        buttonDescargas.setOnClickListener(new ListenerClickButtonDescargas());

        textViewBidones20L = (TextView)findViewById(R.id.aCargamentoTextViewBidones20L);
        textViewBidones20LVacios = (TextView)findViewById(R.id.aCargamentoTextViewBidones20LVacios);
        textViewBidones12L = (TextView)findViewById(R.id.aCargamentoTextViewBidones12L);
        textViewBidones12LVacios = (TextView)findViewById(R.id.aCargamentoTextViewBidones12LVacios);
        textViewBidones10L = (TextView)findViewById(R.id.aCargamentoTextViewBidones10L);
        textViewBidones8L = (TextView)findViewById(R.id.aCargamentoTextViewBidones8L);
        textViewBidones5L = (TextView)findViewById(R.id.aCargamentoTextViewBidones5L);
        textViewPackBotellas2L = (TextView)findViewById(R.id.aCargamentoTextViewPackBotellas2L);
        textViewPackBotellas500mL = (TextView)findViewById(R.id.aCargamentoTextViewPackBotellas500mL);
        textViewPesoTotal = (TextView)findViewById(R.id.aCargamentoTextViewPesoTotal);

        actualizarCargamento();


        this.buttonRetornar = (Button) findViewById(R.id.aCargamentoButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());




    }


    private Button buttonCargas;
    private Button buttonDescargas;

    private TextView textViewBidones20L;
    private TextView textViewBidones20LVacios;
    private TextView textViewBidones12L;
    private TextView textViewBidones12LVacios;
    private TextView textViewBidones10L;
    private TextView textViewBidones8L;
    private TextView textViewBidones5L;
    private TextView textViewPackBotellas2L;
    private TextView textViewPackBotellas500mL;
    private TextView textViewPesoTotal;



    static class CodigosCargamento extends CodigosActividades
    {
    public static int CargasMenu =3;
    public static int DescargasMenu = 4;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////


    ////// CARGAS

    class ListenerClickButtonCargas implements View.OnClickListener
    {
    public void onClick(View e)
        {
            cargas();
        }
    }

    private void cargas()
    {
    Intent intentCargas = new Intent(this,ACargasMenu.class);
    startActivityForResult(intentCargas, CodigosCargamento.CargasMenu);
    }


    ////// DESCARGAS

    class ListenerClickButtonDescargas implements View.OnClickListener
    {
    public void onClick(View e)
        {
            descargas();
        }
    }

    private void descargas()
    {
    Intent intentDescargas = new Intent(this,ADescargasMenu.class);
    startActivityForResult(intentDescargas, CodigosCargamento.DescargasMenu);
    }



    ///// RETORNAR
    private Button buttonRetornar;

    class ListenerClickButtonRetornar implements View.OnClickListener
    {
        public void onClick(View e)
        {
            retornar();
        }
    }


    private void retornar()
    {
        setResult(CodigosActividades.SALIR,new Intent());
        this.finish();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////

    private void actualizarCargamento()
    {
    this.textViewBidones20L.setText("Bidones 20L: "+ Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones20L());
    this.textViewBidones20LVacios.setText("Bidones 20L Vacios: "+ Comunicador.getDiaRepartidor().getCargamento().getRetornablesVacios().getBidones20L());
    this.textViewBidones12L.setText("Bidones 12L: "+ Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones12L());
    this.textViewBidones12LVacios.setText("Bidones 12L Vacios: "+ Comunicador.getDiaRepartidor().getCargamento().getRetornablesVacios().getBidones12L());
    this.textViewBidones10L.setText("Bidones 10L: "+ Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones10L());
    this.textViewBidones8L.setText("Bidones 8L: "+ Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones8L());
    this.textViewBidones5L.setText("Bidones 5L: "+ Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones5L());
    this.textViewPackBotellas2L.setText("Pack Botellas 2L: "+ Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas2L());
    this.textViewPackBotellas500mL.setText("Pack Botellas 500 mL: "+ Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas500mL());
    int pesoTotal = Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones20L()*20 + Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones12L()*12 + Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones10L()*10 + Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones8L()*8 + Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones5L()*5 +Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas2L() * 16 + Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas500mL()*9;
    this.textViewPesoTotal.setText("Peso Total: "+pesoTotal+ " kilos");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    if(CodigosCargamento.CargasMenu == requestCode)
        {

        }
    else if(CodigosCargamento.DescargasMenu == requestCode)
        {

        }
    else
        {

        }
    actualizarCargamento();
    }

}
