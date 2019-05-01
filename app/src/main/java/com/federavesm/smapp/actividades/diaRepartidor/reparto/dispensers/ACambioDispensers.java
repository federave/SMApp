package com.federavesm.smapp.actividades.diaRepartidor.reparto.dispensers;



import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;
        import com.federavesm.smapp.R;
        import com.federavesm.smapp.actividades.ActivityGenerica;
        import com.federavesm.smapp.actividades.Dialogo;
        import com.federavesm.smapp.modelo.Comunicador;



public class ACambioDispensers extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avisita);




        this.buttonRetornar = (Button) findViewById(R.id.aVisitaButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonGuardar = (Button) findViewById(R.id.aVisitaButtonGuardar);
        this.buttonGuardar.setOnClickListener(new ListenerClickButtonGuardar());





    }



    ///////GUARDAR

    private Button buttonGuardar;

    class ListenerClickButtonGuardar implements View.OnClickListener
    {
        public void onClick(View e)
        {
            guardar();
        }
    }


    private void guardar()
    {
        try
        {
            if(Comunicador.getReparto().getEstadoClienteAtendido() == false)
            {
                Comunicador.getReparto().modificar();
                Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptar());
                Dialogo.aceptar("Atención!","La visita se guardó correctamente",this);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,"Los datos ingresados no son coherentes",Toast.LENGTH_LONG).show();
        }
    }



}

