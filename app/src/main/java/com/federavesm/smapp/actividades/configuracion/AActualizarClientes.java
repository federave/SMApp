package com.federavesm.smapp.actividades.configuracion;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.diaRepartidor.ADiaRepartidor;
import com.federavesm.smapp.actividades.diaRepartidor.AdaptadorRepartidores;
import com.federavesm.smapp.actividades.diaRepartidor.AdaptadorVendedores;
import com.federavesm.smapp.modelo.BaseDeDatos;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidores;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedores;
import com.federavesm.smapp.modelo.servidor.RequerimientoGET;
import com.federavesm.smapp.modelo.servidor.Servidor;
import com.federavesm.smapp.modelo.servidor.VerificarConexion;
import com.federavesm.smapp.modelo.servidor.datosXML.DatosBasicosClienteXML;
import com.federavesm.smapp.modelo.servidor.datosXML.InfoClientesRepartidorXML;
import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;


public class AActualizarClientes extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aactualizarclientes);




        buttonActualizarClientes = (Button)findViewById(R.id.aActualizarClientesButtonActualizar);
        buttonActualizarClientes.setOnClickListener(new ListenerClickButtonActualizarClientes());



        buttonRetornar = (Button)findViewById(R.id.aActualizarClientesButtonRetornar);
        buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());


        repartidores = Comunicador.getRepartidores();
        spinnerRepartidores = (Spinner) findViewById(R.id.aActualizarClientesSpinnerRepartidores);
        adaptadorRepartidores = new AdaptadorRepartidores(this,repartidores.getRepartidores());
        spinnerRepartidores.setAdapter(adaptadorRepartidores);



        vendedores = new Vendedores(this);
        spinnerVendedores = (Spinner) findViewById(R.id.aActualizarClientesSpinnerVendedores);
        adaptadorVendedores = new AdaptadorVendedores(this,vendedores.getVendedores());
        spinnerVendedores.setAdapter(adaptadorVendedores);



        buttonActualizarClientesVendedores = (Button)findViewById(R.id.aActualizarClientesButtonActualizarVendedores);
        buttonActualizarClientesVendedores.setOnClickListener(new ListenerClickButtonActualizarClientesVendedores());












    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // EN TEORIA PARA QUE NO SE BLOQUE LA PANTALLA
    }

    private Button buttonActualizarClientes;

    private AdaptadorRepartidores adaptadorRepartidores;
    private Spinner spinnerRepartidores;
    private Repartidores repartidores;

    BaseDeDatos baseDeDatos = new BaseDeDatos(this);

    private Button buttonActualizarClientesVendedores;

    private AdaptadorVendedores adaptadorVendedores;
    private Spinner spinnerVendedores;
    private Vendedores vendedores;





    int idEmpleado;



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// -------------   Actualizar Clientes Repartidores         --------------




    class ListenerClickButtonActualizarClientes implements View.OnClickListener
    {
        public void onClick(View e)
        {
            actualizarClientesVerificarConexion();
        }
    }

    private void actualizarClientesVerificarConexion()
    {

    if(spinnerRepartidores.getSelectedItemPosition()>=0)
        {
        this.idEmpleado = ((Repartidor)spinnerRepartidores.getSelectedItem()).getId();

        if(Comunicador.getConexionInternet(this))
            {
            VerificarConexionActualizarClientes verificarConexion = new VerificarConexionActualizarClientes(this,Comunicador.getConexionServidor().getIp());
            verificarConexion.execute();
            }
        else
            {
            Dialogo.aceptarVacioError("Atención!","No está conectado a internet",this);
            }

        }
    else
        {
        Toast.makeText(this,"there is no worker selected",Toast.LENGTH_SHORT).show();
        }


    }





    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// -------------   Actualizar Clientes Vendedores         --------------




    class ListenerClickButtonActualizarClientesVendedores implements View.OnClickListener
    {
        public void onClick(View e)
        {
            actualizarClientesVendedoresVerificarConexion();
        }
    }

    private void actualizarClientesVendedoresVerificarConexion()
    {

        if(spinnerVendedores.getSelectedItemPosition()>=0)
        {
            this.idEmpleado = ((Vendedor)spinnerVendedores.getSelectedItem()).getId();

            if(Comunicador.getConexionInternet(this))
            {
                VerificarConexionActualizarClientes verificarConexion = new VerificarConexionActualizarClientes(this,Comunicador.getConexionServidor().getIp());
                verificarConexion.execute();
            }
            else
            {
                Dialogo.aceptarVacioError("Atención!","No está conectado a internet",this);
            }

        }
        else
        {
            Toast.makeText(this,"there is no worker selected",Toast.LENGTH_SHORT).show();
        }


    }
















    class VerificarConexionActualizarClientes extends VerificarConexion
    {
        public VerificarConexionActualizarClientes(Context activity, String ip)
        {
            super(activity,ip);
        }
        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            actualizarClientes(this.verificarConexionXML);
        }

    }


    private void actualizarClientes(VerificarConexionXML verificarConexionXML)
    {
        if(verificarConexionXML.getEstado())
        {
            ActualizarClientes actualizarClientes = new ActualizarClientes(this);
            actualizarClientes.setCancelar(new Cancelar(actualizarClientes));
            actualizarClientes.execute();
        }
        else
        {
            Dialogo.aceptarVacioError("Atención!","La conexión con el servidor no funciona",this);
        }
    }


    ////////////////Actualizar Base De Datos





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




    public class ActualizarClientes extends Servidor
    {


        public ActualizarClientes(Context activity)
        {
            super(activity);
            this.url = this.urlServidor + "AplicacionSM/servidor/getActualizarBaseDeDatos.php";
            this.activity = activity;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Dialogo.aceptarVacioError("Atención!","La actualizacion de clientes se ha cancelado",this.activity);

        }

        private Context activity;


        @Override
        protected void onPreExecute()
        {
            this.dialogoProgreso.setMessage("Actualizando Clientes");
            this.dialogoProgreso.show();
        }

        boolean infoClientes=true;
        boolean cargandoClientesInicio=false;
        boolean cargandoClientes=false;



        int numeroClientes;
        int numeroCliente=0;


        @Override
        protected void onProgressUpdate(Integer... values) {

            if(infoClientes)
            {

            }
            else if(cargandoClientesInicio)
            {
            this.dialogoProgreso.setIndeterminate(false);
            this.dialogoProgreso.setMessage("Obteniendo Clientes");
            this.dialogoProgreso.setMax(this.numeroClientes);
            this.dialogoProgreso.setProgress(0);
            }
            else if(cargandoClientes)
            {
            this.dialogoProgreso.setProgress(this.numeroCliente);
            }
            else
            {

            }

        }

        @Override
        protected String doInBackground(String... strings) {

            try
            {

                Fecha fecha = new Fecha();


                RequerimientoGET requerimientoGET = new RequerimientoGET();
                requerimientoGET.setHost(this.urlServidor);

                String respuestaXML = "";


                ///// Obtencion de numero de clientes

                requerimientoGET.setRutaScript("AplicacionSM/servidor/clientes/getInfoClientesRepartidor.php");
                requerimientoGET.addParametro("idRepartidor",String.valueOf(idEmpleado));
                respuestaXML = requerimientoGET.ejecutar();

                InfoClientesRepartidorXML infoClientesRepartidorXML = new InfoClientesRepartidorXML(respuestaXML);


                if(infoClientesRepartidorXML.getNumeroClientes()>0) {


                this.infoClientes = false;
                this.cargandoClientesInicio = true;
                this.numeroClientes = infoClientesRepartidorXML.getNumeroClientes();
                publishProgress();


                int idCliente=0;
                for(int i = 0; i< infoClientesRepartidorXML.getNumeroClientes()  && this.resultado && !isCancelled(); i++)
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


                        requerimientoGET.clear();
                        requerimientoGET.setRutaScript("AplicacionSM/servidor/clientes/getDatosBasicos.php");
                        requerimientoGET.addParametro("idRepartidor",String.valueOf(idEmpleado));
                        requerimientoGET.addParametro("idCliente",String.valueOf(idCliente));

                        respuestaXML = requerimientoGET.ejecutar();

                        DatosBasicosClienteXML datosBasicosClienteXML = new DatosBasicosClienteXML(respuestaXML);
                        idCliente = datosBasicosClienteXML.getIdCliente();

                        if(datosBasicosClienteXML.getEstado())
                        {



                        for(int j=0;j<datosBasicosClienteXML.getNumero();j++)
                        {


                        int idDireccion = datosBasicosClienteXML.getIdDireccion(j);


                        Cliente cliente  = new Cliente(getApplicationContext());
                        cliente.setFecha(fecha);



                        if(baseDeDatos.clienteExiste(idCliente,idDireccion))
                            {

                            this.resultado&=cliente.cargar(idCliente,idDireccion);

                            requerimientoGET.clear();
                            requerimientoGET.setRutaScript("AplicacionSM/servidor/getReparto/getActualidadCliente.php");
                            requerimientoGET.addParametro("fecha",fecha.toString());
                            requerimientoGET.addParametro("idCliente",String.valueOf(idCliente));
                            requerimientoGET.addParametro("idDireccion",String.valueOf(idDireccion));

                            respuestaXML = requerimientoGET.ejecutar();

                            this.resultado&=cliente.actualizar(respuestaXML);

                            // atención con actualizar porque podria pasar que recibe un alquiler que no
                            // es el actual que tiene el cliente, o que el cliente tiene alquiler y ahora ya no

                            }
                        else
                            {
                            requerimientoGET.clear();
                            requerimientoGET.setRutaScript("AplicacionSM/servidor/getReparto/getCliente.php");
                            requerimientoGET.addParametro("fecha",fecha.toString());
                            requerimientoGET.addParametro("idCliente",String.valueOf(idCliente));
                            requerimientoGET.addParametro("idDireccion",String.valueOf(idDireccion));
                            respuestaXML = requerimientoGET.ejecutar();
                            this.resultado&=cliente.guardar(respuestaXML);
                            }

                        }


                        }
                        else
                            {
                            this.resultado=false;
                            }




                        if(this.cargandoClientesInicio)
                        {
                            this.cargandoClientes = true;
                            this.cargandoClientesInicio = false;
                        }

                        this.numeroCliente = i+1;
                        publishProgress();

                    }

                    if(!isCancelled())
                    {
                    this.removeCancelar();
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

        boolean resultado=true;


        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            actualizarClientesFin(this.resultado);
        }




    }

    private void actualizarClientesFin(boolean resultado)
    {
        if(resultado)
        {
                Dialogo.aceptarVacio("Atención!","Base de Datos actualizados",this);
        }
        else
        {
            Dialogo.aceptarVacioError("Atención!","La Base de Datos no se vació correctamente",this);

        }
    }










    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onBackPressed() {

        setResult(CodigosActividades.SALIR,new Intent());
        this.finish();
    }



}
