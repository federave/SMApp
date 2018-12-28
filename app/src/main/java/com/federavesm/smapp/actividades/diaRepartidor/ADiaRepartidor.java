package com.federavesm.smapp.actividades.diaRepartidor;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.diaRepartidor.cargas.ACargamento;
import com.federavesm.smapp.actividades.diaRepartidor.gastos.AGastos;
import com.federavesm.smapp.modelo.BaseDeDatos;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.servidor.RequerimientoGET;
import com.federavesm.smapp.modelo.servidor.Servidor;
import com.federavesm.smapp.modelo.servidor.VerificarConexion;
import com.federavesm.smapp.modelo.servidor.datosXML.DatoBasicoRepartoXML;
import com.federavesm.smapp.modelo.servidor.datosXML.DiaRepartidorXML;
import com.federavesm.smapp.modelo.servidor.datosXML.EstadoDiaRepartidorXML;
import com.federavesm.smapp.modelo.servidor.datosXML.InfoClientesFueraDeRecorridoXML;
import com.federavesm.smapp.modelo.servidor.datosXML.InfoDiaRepartidorXML;
import com.federavesm.smapp.modelo.servidor.datosXML.PreciosXML;
import com.federavesm.smapp.modelo.servidor.datosXML.RepartoXML;
import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Federico on 16/5/2017.
 */



public class ADiaRepartidor extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adiarepartidor);



        diaRepartidor = Comunicador.getDiaRepartidor();

        aDiaRepartoCargarDia = (LinearLayout)findViewById(R.id.aDiaRepartoCargarDia);
        aDiaRepartoDiaCargado = (ScrollView)findViewById(R.id.aDiaRepartoDiaCargado);



        if(diaRepartidor.getEstado())
            {
            aDiaRepartoDiaCargado.setVisibility(View.VISIBLE);
            aDiaRepartoCargarDia.setVisibility(View.GONE);
            }
        else
            {
            aDiaRepartoDiaCargado.setVisibility(View.GONE);
            aDiaRepartoCargarDia.setVisibility(View.VISIBLE);
            }

        buttonCargarDia = (Button)findViewById(R.id.aDiaRepartoButtonCargarDia);
        buttonCargarDia.setOnClickListener(new ADiaRepartidor.ListenerClickButtonCargarDia());

        buttonRepartos = (Button)findViewById(R.id.aDiaRepartoButtonRepartos);
        buttonRepartos.setOnClickListener(new ADiaRepartidor.ListenerClickButtonRepartos());

        buttonRetornar = (Button)findViewById(R.id.aDiaRepartoButtonRetornar);
        buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());



        buttonGastos = (Button)findViewById(R.id.aDiaRepartoButtonGastos);
        buttonGastos.setOnClickListener(new ADiaRepartidor.ListenerClickButtonGastos());

        buttonCargamento = (Button)findViewById(R.id.aDiaRepartoButtonCargamento);
        buttonCargamento.setOnClickListener(new ADiaRepartidor.ListenerClickButtonCargamento());

        buttonDatosDelDia = (Button)findViewById(R.id.aDiaRepartoButtonDatosDelDia);
        buttonDatosDelDia.setOnClickListener(new ADiaRepartidor.ListenerClickButtonDatosDelDia());

        buttonSalir = (Button)findViewById(R.id.aDiaRepartoButtonSalir);
        buttonSalir.setOnClickListener(new ADiaRepartidor.ListenerClickButtonSalir());

        buttonEnviarDia = (Button)findViewById(R.id.aDiaRepartoButtonEnviarDia);
        buttonEnviarDia.setOnClickListener(new ADiaRepartidor.ListenerClickButtonEnviarDia());

        buttonRecibirClientesFueraDeRecorrido = (Button)findViewById(R.id.aDiaRepartoButtonRecibirClientesFueraDeRecorrido);
        buttonRecibirClientesFueraDeRecorrido.setOnClickListener(new ADiaRepartidor.ListenerClickButtonRecibirClientesFueraDeRecorrido());





    }


    BaseDeDatos baseDeDatos = new BaseDeDatos(this);


    private LinearLayout aDiaRepartoCargarDia;
    private ScrollView aDiaRepartoDiaCargado;

    private DiaRepartidor diaRepartidor;

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // EN TEORIA PARA QUE NO SE BLOQUE LA PANTALLA
    }

    static class CodigosDR extends CodigosActividades
    {
    public static int CargarDia =3;
    public static int Repartos = 4;
    public static int Cargamento = 5;
    public static int Gastos = 6;
    public static int DatosDelDia = 7;
    public static int EnviarDia = 7;

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////// REPARTOS



    private Button buttonRepartos;

    class ListenerClickButtonRepartos implements View.OnClickListener
    {
    public void onClick(View e)
        {
            repartos();
        }
    }


    private void repartos()
    {
    Intent intentRepartos = new Intent(this,ARepartos.class);
    startActivityForResult(intentRepartos, CodigosDR.Repartos);
    }


    /////////////////// GASTOS

    private Button buttonGastos;

    class ListenerClickButtonGastos implements View.OnClickListener
    {
    public void onClick(View e)
        {
            gastos();
        }
    }

    private void gastos()
    {
    Intent intentGastos = new Intent(this, AGastos.class);
    startActivityForResult(intentGastos, CodigosDR.Gastos);
    }


    /////////////////// CARGAS

    private Button buttonCargamento;

    class ListenerClickButtonCargamento implements View.OnClickListener
    {
    public void onClick(View e)
        {
            cargamento();
        }
    }


    private void cargamento()
    {
    Intent intentCargas = new Intent(this,ACargamento.class);
    startActivityForResult(intentCargas, CodigosDR.Cargamento);
    }


    ///////////////////////////////////////
    /////////////////// DATOS DEL DIA

    private Button buttonDatosDelDia;

    class ListenerClickButtonDatosDelDia implements View.OnClickListener
    {
    public void onClick(View e)
        {
            datosDelDia();
        }
    }


    private void datosDelDia()
    {
        Intent intentDatosDelDia = new Intent(this,ADatosDelDia.class);
        startActivityForResult(intentDatosDelDia, CodigosDR.DatosDelDia);

    }


    /////////////////// SALIR

    private Button buttonSalir;

    class ListenerClickButtonSalir implements View.OnClickListener
    {
    public void onClick(View e)
        {
            salir();
        }
    }



    private void salir()
    {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("Atención!")
            .setMessage("Está seguro que desea salir ? ")
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        salirNo();
                    }
                })
            .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        salirSi();
                    }
                });

    builder.create().show();
    }

    private void salirSi()
    {
    setResult(CodigosActividades.SALIR,new Intent());
    this.finish();
    }


    private void salirNo()
    {
    }



    ////// ENVIAR DIA

    private Button buttonEnviarDia;

    class ListenerClickButtonEnviarDia implements View.OnClickListener
    {
    public void onClick(View e)
        {
            enviarDia();
        }
    }

    private void enviarDia()
    {
    Intent intentEnviarDia = new Intent(this,AEnviarDia.class);
    startActivityForResult(intentEnviarDia, CodigosDR.EnviarDia);
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////// RECIBIR CLIENTES FUERA DE RECORRIDO


    private Button buttonRecibirClientesFueraDeRecorrido;

    class ListenerClickButtonRecibirClientesFueraDeRecorrido implements View.OnClickListener
    {
    public void onClick(View e)
        {
            recibirClientesFueraDeRecorrido();
        }
    }

    private void recibirClientesFueraDeRecorrido()
    {
    fueraDeRecorrido_VerificarConexion();
    }




    private void fueraDeRecorrido_VerificarConexion()
    {
        if(Comunicador.getConexionInternet(this))
        {
            ADiaRepartidor.FueraDeRecorrido_VerificarConexion verificarConexion = new ADiaRepartidor.FueraDeRecorrido_VerificarConexion(this,Comunicador.getConexionServidor().getIp());
            verificarConexion.execute();
        }
        else
        {
        Dialogo.aceptarVacioError("Atención!","No esta conectado a internet",this);
        }
    }



    class FueraDeRecorrido_VerificarConexion extends VerificarConexion
    {
        public FueraDeRecorrido_VerificarConexion(Context activity, String ip)
        {
            super(activity,ip);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {


            switch (values[0])
            {
                case 1:
                {
                    break;
                }

                case 2:
                {
                    break;
                }

                case 3:
                {break;}

                default:{break;}

            }


        }

        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            fueraDeRecorrido_Cargar(this.verificarConexionXML);
        }

    }


    private void fueraDeRecorrido_Cargar(VerificarConexionXML verificarConexionXML)
    {
        if(verificarConexionXML.getEstado())
        {
            FueraDeRecorrido_Cargar fueraDeRecorrido_Cargar = new FueraDeRecorrido_Cargar(this,this.diaRepartidor);
            fueraDeRecorrido_Cargar.setCancelar(new Cancelar(fueraDeRecorrido_Cargar));
            fueraDeRecorrido_Cargar.execute();
        }
        else
        {
            Dialogo.aceptarVacioError("Atención!","La conexion no funciona",this);

        }
    }




    public class FueraDeRecorrido_Cargar extends Servidor
    {


        public FueraDeRecorrido_Cargar(Context activity,DiaRepartidor diaRepartidor)
        {
            super(activity);
            this.diaRepartidor = diaRepartidor;
            this.activity = activity;

        }

        private DiaRepartidor diaRepartidor;
        private Context activity;

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            Toast.makeText(activity,"Tarea cancelada",Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(activity,"Tarea cancelada",Toast.LENGTH_SHORT).show();



        }



        protected DiaRepartidorXML diaRepartidorXML = new DiaRepartidorXML();

        @Override
        protected void onPreExecute()
        {
        this.dialogoProgreso.setMessage("Buscando Información de Clientes Fuera de Recorrido");
        this.dialogoProgreso.show();
        }

        boolean buscandoInfo = true;
        boolean cargandoInicio = false;
        boolean cargando = false;
        boolean guardando = false;

        int numeroClientesFueraDeRecorrido=0;
        int numeroCliente=0;


        @Override
        protected String doInBackground(String... strings) {

            try
            {

                ///// Obtencion de numero de clientes fuera de recorrido

                String parametros="?idRepartidor="+String.valueOf(diaRepartidor.getIdRepartidor())+"&fecha="+diaRepartidor.getFecha();
                this.url = this.urlServidor + "AplicacionSM/servidor/getInfoClientesFueraDeRecorrido.php"+parametros;


                HttpClient cliente = new DefaultHttpClient();
                HttpGet requerimiento = new HttpGet(this.url);
                HttpResponse respuesta = cliente.execute(requerimiento);

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                String line = "";
                String respuestaXML = "";
                while ((line = rd.readLine()) != null)
                {
                    respuestaXML += line;
                }

                InfoClientesFueraDeRecorridoXML infoClientesFueraDeRecorridoXML = new InfoClientesFueraDeRecorridoXML(respuestaXML);

                if(infoClientesFueraDeRecorridoXML.getNumeroClientesFueraDeRecorrido()>0)
                {


                    ////////Carga Fuera de Recorrido

                    this.buscandoInfo = false;
                    this.cargandoInicio = true;
                    this.numeroClientesFueraDeRecorrido = infoClientesFueraDeRecorridoXML.getNumeroClientesFueraDeRecorrido();

                    publishProgress();


                    for(int i = 0; i< infoClientesFueraDeRecorridoXML.getNumeroClientesFueraDeRecorrido() && !isCancelled(); i++)
                    {

                        parametros="?idRepartidor="+String.valueOf(diaRepartidor.getIdRepartidor())+"&fecha="+diaRepartidor.getFecha()+"&cliente="+String.valueOf(i);
                        this.url = this.urlServidor + "AplicacionSM/servidor/getReparto/getClienteFueraDeRecorrido.php"+parametros;

                        cliente = new DefaultHttpClient();

                        requerimiento = new HttpGet(this.url);
                        respuesta = cliente.execute(requerimiento);

                        // Get the response
                        rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                        line = "";
                        respuestaXML = "";
                        while ((line = rd.readLine()) != null)
                        {
                            respuestaXML += line;
                        }

                        RepartoXML repartoXML = new RepartoXML(respuestaXML,this.activity);
                        Reparto reparto = repartoXML.getReparto();


                        if(this.diaRepartidor.getRepartos().isReparto(reparto) == false) {
                            this.diaRepartidor.getRepartos().getRepartos().add(reparto);
                            reparto.setIdDiaRepartidor(this.diaRepartidor.getIdDiaRepartidor());
                            this.resultado &= reparto.guardar();
                        }



                        if(this.cargandoInicio)
                            {
                            this.cargando = true;
                            this.cargandoInicio = false;
                            }

                        this.numeroCliente = i+1;
                        publishProgress();

                    }

                    if(!isCancelled())
                    {
                        this.cargando = false;
                        this.guardando = true;
                        this.dialogoProgreso.setCancelable(false);
                        publishProgress();

                    }


                }


            }
            catch (Exception e)
            {
                String x = e.toString();
            }
            return "";
        }


        @Override
        protected void onProgressUpdate(Integer... values)
        {

        if(buscandoInfo)
            {

            }
        else if(cargandoInicio)
            {
            this.dialogoProgreso.setIndeterminate(false);
            this.dialogoProgreso.setMessage("Obteniendo Clientes Fuera de Recorrido");
            this.dialogoProgreso.setMax(this.numeroClientesFueraDeRecorrido);
            this.dialogoProgreso.setProgress(0);
            }
        else if(cargando)
            {
            this.dialogoProgreso.setProgress(this.numeroCliente);
            }
        else if(guardando)
            {
            this.dialogoProgreso.setIndeterminate(true);
            this.dialogoProgreso.setMessage("Guardando Clientes Fuera de Recorrido");
            }
        else
            {
            }

        }

        boolean resultado = true;

        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            fueraDeRecorrido_fin(resultado);
        }




    }


    private void fueraDeRecorrido_fin(boolean resultado)
    {
    if(resultado)
        {
        Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptarVacio());
        Dialogo.aceptar("Atención!","Los clientes fuera de recorrido se cargaron correctamente",this);
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","Los clientes fuera de recorrido no se cargaron correctamente,intente nuevamente",this);
        }
    }






    ////////////////////////////////////////////////////////////////////////////////////////////////////






    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {


        if(ADiaRepartidor.CodigosDR.CargarDia == requestCode)
        {

            if(ADiaRepartidor.CodigosDR.OK == resultCode)
            {

                //  Toast.makeText(this,"OKKKKKK",Toast.LENGTH_LONG).show();


            }
            else if(CodigosDR.Repartos == resultCode)
            {
                //  Toast.makeText(this,"aaaaaaaaaaaaaaa",Toast.LENGTH_LONG).show();

            }
            else if(ADiaRepartidor.CodigosDR.SALIR == resultCode)
            {
                //  Toast.makeText(this,"aaaaaaaaaaaaaaa",Toast.LENGTH_LONG).show();

            }
            else
            {}

        }






    }







    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////// CARGAR DIA
    ///// RETORNAR


    private Button buttonRetornar;

    class ListenerClickButtonRetornar implements View.OnClickListener
    {
        public void onClick(View e)
        {
            retornar();
        }
    }



    private Button buttonCargarDia;

    class ListenerClickButtonCargarDia implements View.OnClickListener
    {
    public void onClick(View e)
        {
            cargarDia_VerificarConexion();
        }
    }


    private void cargarDia_VerificarConexion()
    {
    if(Comunicador.getConexionInternet(this))
        {
        ADiaRepartidor.CargarDia_VerificarConexion verificarConexion = new ADiaRepartidor.CargarDia_VerificarConexion(this,Comunicador.getConexionServidor().getIp());
        verificarConexion.execute();
        }
    else
        {
        Toast.makeText(this,"No esta conectado a internet",Toast.LENGTH_SHORT).show();
        }
    }



    class CargarDia_VerificarConexion extends VerificarConexion
    {
        public CargarDia_VerificarConexion(Context activity, String ip)
        {
            super(activity,ip);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {


            switch (values[0])
            {
                case 1:
                {
                    break;
                }

                case 2:
                {
                    break;
                }

                case 3:
                {break;}

                default:{break;}

            }


        }

        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            cargarDia_verificarDia(this.verificarConexionXML);
        }

    }




    private void cargarDia_verificarDia(VerificarConexionXML verificarConexionXML)
    {
        if(verificarConexionXML.getEstado())
        {
            CargarDia_VerificarDia verificarDia = new CargarDia_VerificarDia(this,this.diaRepartidor.getIdRepartidor(),this.diaRepartidor.getFecha().toString());
            verificarDia.setCancelar(new Cancelar(verificarDia));

            verificarDia.execute();
        }
        else
        {
        Dialogo.aceptarVacioError("Atención!","La conexion no funciona",this);
        }
    }


    public class CargarDia_VerificarDia extends Servidor
    {


        public CargarDia_VerificarDia(Context activity,int idRepartidor,String fecha)
        {
            super(activity);
            String parametros="?idRepartidor="+String.valueOf(idRepartidor)+"&fecha="+fecha;
            this.url = this.urlServidor + "AplicacionSM/servidor/getEstadoDiaRepartidor.php"+parametros;
            this.activity = activity;
        }



        private Context activity;

        protected EstadoDiaRepartidorXML estadoDiaRepartidorXML = new EstadoDiaRepartidorXML();

        @Override
        protected void onPreExecute()
        {
        this.dialogoProgreso.setMessage("Verificando Dia");
        this.dialogoProgreso.show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Dialogo.aceptarVacioError("Atención!","La carga del dia se ha cancelado",this.activity);

        }

        @Override
        protected String doInBackground(String... strings) {

            try
            {
                HttpClient cliente = new DefaultHttpClient();
                HttpGet requerimiento = new HttpGet(this.url);
                HttpResponse respuesta = cliente.execute(requerimiento);

                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                String line = "";
                String respuestaXML = "";
                while ((line = rd.readLine()) != null)
                {
                    respuestaXML += line;
                }

                this.estadoDiaRepartidorXML = new EstadoDiaRepartidorXML(respuestaXML);


            }
            catch (Exception e)
            {
                String x = e.toString();
            }
            return "";
        }


        @Override
        protected void onProgressUpdate(Integer... values) {


            switch (values[0])
            {
                case 1:
                {
                    break;
                }

                case 2:
                {
                    break;
                }

                case 3:
                {break;}

                default:{break;}

            }


        }

        boolean resultado;

        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            cargarDia_cargar(estadoDiaRepartidorXML);
        }

    }





    private void cargarDia_cargar(EstadoDiaRepartidorXML estadoDiaRepartidorXML)
    {
    if(estadoDiaRepartidorXML.getDiaCreado())
        {
        if(!estadoDiaRepartidorXML.getDiaCompletado())
            {

            CargarDia_Cargar cargarDia = new CargarDia_Cargar(this,this.diaRepartidor);
            cargarDia.setCancelar(new Cancelar(cargarDia));
            cargarDia.execute();

            }
        else
            {
            Dialogo.aceptarVacioError("Atención!","El dia seleccionado ya ha sido completado",this);
            }
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","El dia seleccionado no ha sido creado",this);
        }
    }





    public class Cancelar implements DialogInterface.OnClickListener {


        public Cancelar(AsyncTask<String,Integer,String> tarea)
        {
            this.tarea=tarea;
        }

        private AsyncTask<String,Integer,String> tarea;

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            tarea.cancel(true);
        }
    }


    public class CancelarVacio implements DialogInterface.OnClickListener {


        @Override
        public void onClick(DialogInterface dialogInterface, int i) {}
    }






    public class CargarDia_Cargar extends Servidor
    {


    public CargarDia_Cargar(Context activity,DiaRepartidor diaRepartidor)
    {
    super(activity);
    this.diaRepartidor = diaRepartidor;
    this.activity = activity;

    }

    private DiaRepartidor diaRepartidor;
    private Context activity;

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
            this.diaRepartidor.getRepartos().getRepartos().clear();
          //  Dialogo.aceptarVacioError("Atención!","La carga del dia se ha cancelado",this.activity);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            this.diaRepartidor.getRepartos().getRepartos().clear();
            Dialogo.aceptarVacioError("Atención!","La carga del dia se ha cancelado",this.activity);

        }

        protected DiaRepartidorXML diaRepartidorXML = new DiaRepartidorXML();

    @Override
    protected void onPreExecute()
    {
    this.dialogoProgreso.setMessage("Buscando Información del  Dia");
    this.dialogoProgreso.show();
    }

    boolean infoDelDia = true;
    boolean precioDelDia = false;
    boolean repartoInicio = false;
    boolean reparto = false;
    boolean procesandoPlanilla = false;


    int numeroClientesPlanilla;
    int numeroCliente=0;

    @Override
    protected String doInBackground(String... strings) {

        try
            {



            RequerimientoGET requerimientoGET = new RequerimientoGET();
            requerimientoGET.setHost(this.urlServidor);

            String respuestaXML = "";


            ///// Obtencion de numero de clientes en la planilla

            requerimientoGET.setRutaScript("AplicacionSM/servidor/getInfoDiaRepartidor.php");
            requerimientoGET.addParametro("idRepartidor",String.valueOf(diaRepartidor.getIdRepartidor()));
            requerimientoGET.addParametro("fecha",diaRepartidor.getFecha().toString());
            respuestaXML = requerimientoGET.ejecutar();

            InfoDiaRepartidorXML infoDiaRepartidorXML = new InfoDiaRepartidorXML(respuestaXML);


            if(infoDiaRepartidorXML.getNumeroClientesPlanilla()>0)
                {

                ////////Precios del Dia

                this.infoDelDia = false;
                this.precioDelDia = true;
                publishProgress();

                requerimientoGET.clear();
                requerimientoGET.setRutaScript("AplicacionSM/servidor/getPreciosDelDia.php");
                requerimientoGET.addParametro("fecha",diaRepartidor.getFecha().toString());
                respuestaXML = requerimientoGET.ejecutar();

                PreciosXML preciosXML = new PreciosXML(respuestaXML,this.activity);
                this.diaRepartidor.setPrecios(preciosXML.getPrecios());


                this.precioDelDia = false;
                this.repartoInicio = true;
                this.numeroClientesPlanilla = infoDiaRepartidorXML.getNumeroClientesPlanilla();

                publishProgress();



                for(int i = 0; i< infoDiaRepartidorXML.getNumeroClientesPlanilla() && !isCancelled(); i++)
                    {


                    /*
                    Proceso Nueva Carga

                    1) Recibe IdCliente,IdDireccion
                    2) Se fija si el cliente existe en la Base de Datos Local
                    3) Si existe, solo pide datos de inactividad , alquiler en caso de tener y venta productos
                    4) Si no existe, pide el reparto completo
                    5) vuelve a 1)

                    Lo que gano con esto, es no sobrecargar a la tablet con datos redundantes
                    Y la posibilidad de que la carga sea mas rápida

                    */



                    requerimientoGET.setRutaScript("AplicacionSM/servidor/getReparto/getDatoBasico.php");
                    requerimientoGET.addParametro("idRepartidor",String.valueOf(diaRepartidor.getIdRepartidor()));
                    requerimientoGET.addParametro("fecha",String.valueOf(diaRepartidor.getFecha()));
                    requerimientoGET.addParametro("cliente",String.valueOf(i));

                    respuestaXML = requerimientoGET.ejecutar();

                    DatoBasicoRepartoXML datoBasicoRepartoXML = new DatoBasicoRepartoXML(respuestaXML);

                    int idCliente = datoBasicoRepartoXML.getIdCliente();
                    int idDireccion = datoBasicoRepartoXML.getIdDireccion();

                    Reparto reparto = new Reparto(getApplicationContext());

                    reparto.getCliente().setFecha(this.diaRepartidor.getFecha());
                    reparto.getCliente().setPrecioProductos(Comunicador.getDiaRepartidor().getPrecios().getPrecioProductos());
                    reparto.getCliente().getDatosAlquiler().setPrecioAlquileres(Comunicador.getDiaRepartidor().getPrecios().getPrecioAlquileres());


                    if(baseDeDatos.clienteExiste(idCliente,idDireccion))
                        {
                        reparto.getCliente().cargar(datoBasicoRepartoXML.getIdCliente(),datoBasicoRepartoXML.getIdDireccion());

                        requerimientoGET.clear();
                        requerimientoGET.setRutaScript("AplicacionSM/servidor/getReparto/getActualidadCliente.php");
                        requerimientoGET.addParametro("fecha",diaRepartidor.getFecha().toString());
                        requerimientoGET.addParametro("idCliente",String.valueOf(idCliente));
                        requerimientoGET.addParametro("idDireccion",String.valueOf(idDireccion));

                        respuestaXML = requerimientoGET.ejecutar();

                        reparto.getCliente().actualizar(respuestaXML);

                        // atención con actualizar porque podria pasar que recibe un alquiler que no
                        // es el actual que tiene el cliente, o que el cliente tiene alquiler y ahora ya no



                        }
                    else
                        {



                        requerimientoGET.clear();
                        requerimientoGET.setRutaScript("AplicacionSM/servidor/getReparto/getCliente.php");
                        requerimientoGET.addParametro("fecha",diaRepartidor.getFecha().toString());
                        requerimientoGET.addParametro("idCliente",String.valueOf(idCliente));
                        requerimientoGET.addParametro("idDireccion",String.valueOf(idDireccion));

                        respuestaXML = requerimientoGET.ejecutar();

                        reparto.getCliente().guardar(respuestaXML);

                        }




                        requerimientoGET.clear();
                        requerimientoGET.setRutaScript("AplicacionSM/servidor/getReparto/getDatosReparto.php");
                        requerimientoGET.addParametro("idRepartidor",String.valueOf(diaRepartidor.getIdRepartidor()));
                        requerimientoGET.addParametro("fecha",diaRepartidor.getFecha().toString());
                        requerimientoGET.addParametro("cliente",String.valueOf(i));

                        respuestaXML = requerimientoGET.ejecutar();

                        reparto.adicionar(respuestaXML);



                        this.diaRepartidor.getRepartos().getRepartos().add(reparto);








                    if(this.repartoInicio)
                        {
                        this.reparto = true;
                        this.repartoInicio = false;
                        }

                    this.numeroCliente = i+1;
                    publishProgress();

                    }

                if(!isCancelled())
                {
                    this.reparto = false;
                    this.procesandoPlanilla = true;
                    this.removeCancelar();
                    publishProgress();
                    this.resultado = this.diaRepartidor.guardar();
                }


                }


        }
    catch (Exception e)
        {
        String x = e.toString();
        }
    return "";
    }


    @Override
    protected void onProgressUpdate(Integer... values) {

        if(infoDelDia)
            {

            }
        else if(precioDelDia)
            {
            this.dialogoProgreso.setMessage("Obteniendo Precios del  Dia");
            }
        else if(repartoInicio)
            {
            this.dialogoProgreso.setIndeterminate(false);
            this.dialogoProgreso.setMessage("Obteniendo Planilla");
            this.dialogoProgreso.setMax(this.numeroClientesPlanilla);
            this.dialogoProgreso.setProgress(0);
            }
        else if(reparto)
            {
            this.dialogoProgreso.setProgress(this.numeroCliente);
            }
        else if(procesandoPlanilla)
            {
            this.dialogoProgreso.setIndeterminate(true);
            this.dialogoProgreso.setMessage("Procesando Planilla");


                this.dialogoProgreso.setProgressNumberFormat(null);
                this.dialogoProgreso.setProgressPercentFormat(null);

            }
        else
            {

            }

    }

    boolean resultado = false;

    @Override
    protected void onPostExecute(String respuesta) {
        this.dialogoProgreso.dismiss();
        cargarDia_fin(resultado);
    }




}


    private void cargarDia_fin(boolean resultado)
    {
    if(resultado)
        {
        Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptarVacio());
        Dialogo.aceptar("Atención!","El dia se cargo correctamente",this);
        }
    else
        {
        Dialogo.aceptarVacioError("Atención!","El dia se cargo con inconvenientes, pero es posible continuar",this);
        }
    aDiaRepartoDiaCargado.setVisibility(View.VISIBLE);
    aDiaRepartoCargarDia.setVisibility(View.GONE);

    }

    @Override
    public void onBackPressed() {

    }



}




