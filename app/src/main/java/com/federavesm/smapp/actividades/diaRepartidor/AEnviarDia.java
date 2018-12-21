package com.federavesm.smapp.actividades.diaRepartidor;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.diaEnviado.DiaEnviado;
import com.federavesm.smapp.modelo.servidor.Servidor;
import com.federavesm.smapp.modelo.servidor.VerificarConexion;
import com.federavesm.smapp.modelo.servidor.datosXML.EstadoDatoDiaRecibidoXML;
import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by FedeRave on 24/3/2018.
 */




public class AEnviarDia extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aenviardia);


        this.diaRepartidor = Comunicador.getDiaRepartidor();
        this.diaEnviado = diaRepartidor.getDiaEnviado();


        editTextContraseña =  (EditText)findViewById(R.id.aEnviarDiaEditTextContraseña);
        editTextContraseña.addTextChangedListener(new ListenerEditText(this));

        this.buttonRetornar = (Button) findViewById(R.id.aEnviarDiaButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());


        this.linearLayoutContraseña = (LinearLayout) findViewById(R.id.aEnviarDiaLinearLayoutContraseña);


        this.linearLayoutEnviar = (LinearLayout) findViewById(R.id.aEnviarDiaLinearLayoutEnviar);

        this.buttonEnviar = (Button) findViewById(R.id.aEnviarDiaButtonEnviar);
        this.buttonEnviar.setOnClickListener(new ListenerClickButtonEnviar());

        this.textViewRepartosEnviados  = (TextView) findViewById(R.id.aEnviarDiaTextViewRepartosEnviados);
        this.textViewGastosEnviados  = (TextView) findViewById(R.id.aEnviarDiaTextViewGastosEnviados);
        this.textViewCargasEnviadas  = (TextView) findViewById(R.id.aEnviarDiaTextViewCargasEnviadas);
        this.textViewDescargasEnviadas  = (TextView) findViewById(R.id.aEnviarDiaTextViewDescargasEnviadas);

        limpiarDatosInformeEnvio();


        if(Comunicador.getUsuario().getAdministrador())
            {
            this.linearLayoutContraseña.setVisibility(View.GONE);
            }
        else
            {
            this.linearLayoutEnviar.setVisibility(View.GONE);
            }

        cargarTextViews();




    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // EN TEORIA PARA QUE NO SE BLOQUE LA PANTALLA
    }

    private DiaRepartidor diaRepartidor;
    private DiaEnviado diaEnviado;

    private EditText editTextContraseña;

    private LinearLayout linearLayoutContraseña;




    private void cargarTextViews()
    {
    textViewRepartosEnviados.setText("Repartos Enviados: "+diaEnviado.getRepartosEnviados() + " de: " + diaRepartidor.getRepartos().getRepartos().size());
    textViewGastosEnviados.setText("Gastos Enviados: "+diaEnviado.getGastosEnviados() + " de: " + diaRepartidor.getGastos().getGastos().size());
    textViewCargasEnviadas.setText("Cargas Enviadas: "+diaEnviado.getCargasEnviadas() + " de: " + diaRepartidor.getCargamento().getCargas().getCargas().size());
    textViewDescargasEnviadas.setText("Descargas Enviadas: "+diaEnviado.getDescargasEnviadas() + " de: " + diaRepartidor.getCargamento().getDescargas().getDescargas().size());
    }

    ///////////////////


    class ListenerEditText implements TextWatcher
    {

        public ListenerEditText(Context activity)
        {
            this.activity = activity;
        }

        private Context activity;

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        boolean aux=false;


        boolean busqueda = false;

        @Override
        public void afterTextChanged(Editable editable)
        {
        linearLayoutEnviar.setVisibility(View.GONE);
        if(editable.toString().length()>0)
            {
            if(editable.toString().equals(Comunicador.getUsuarioAdministrador().getContraseña()))
                {
                linearLayoutEnviar.setVisibility(View.VISIBLE);
                }
            }

        }

    }



    ///////////////////

    private LinearLayout linearLayoutEnviar;

    private Button buttonEnviar;

    private TextView textViewRepartosEnviados;
    private TextView textViewGastosEnviados;
    private TextView textViewCargasEnviadas;
    private TextView textViewDescargasEnviadas;

    private int repartosEnviados=0;
    private int gastosEnviados=0;
    private int cargasEnviadas=0;
    private int descargasEnviadas=0;

    private void limpiarDatosInformeEnvio()
    {
    repartosEnviados=0;
    gastosEnviados=0;
    cargasEnviadas=0;
    descargasEnviadas=0;
    }




    class ListenerClickButtonEnviar implements View.OnClickListener
    {
    public void onClick(View e)
        {
            iniciarEnviarDia();
        }
    }

    private void iniciarEnviarDia()
    {
    verificarConexionServidor();
    }



    private void verificarConexionServidor()
    {
    if(Comunicador.getConexionInternet(this))
        {
        VerificarConexionServidor verificarConexion = new VerificarConexionServidor(this,Comunicador.getConexionServidor().getIp());
        verificarConexion.execute();
        }
    else
        {
        Toast.makeText(this,"No está conectado a internet",Toast.LENGTH_SHORT).show();
        }
    }



    class VerificarConexionServidor extends VerificarConexion
    {
        public VerificarConexionServidor(Context activity, String ip)
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
            enviarDia(this.verificarConexionXML);
        }

    }




    private void enviarDia(VerificarConexionXML verificarConexionXML)
    {
    if(verificarConexionXML.getEstado())
        {
        limpiarDatosInformeEnvio();
        EnviarDia enviarDia = new EnviarDia(this);
        enviarDia.setCancelar(new Cancelar(enviarDia));
        enviarDia.execute();
        }
    else
        {
        Toast.makeText(this,"La conexion no funciona",Toast.LENGTH_SHORT).show();
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


    public class EnviarDia extends Servidor
    {


        public EnviarDia(Context activity)
        {
        super(activity);
        this.activity = activity;
        this.diaRepartidor = Comunicador.getDiaRepartidor();

        }

        private DiaRepartidor diaRepartidor;
        private Context activity;

        private boolean infoEnvioRepartos=false;
        private boolean infoEnvioGastos=false;
        private boolean infoEnvioCargas=false;
        private boolean infoEnvioDescargas=false;
        private boolean infoEnvioDatos=false;


        private boolean progresoRepartos=false;
        private boolean progresoGastos=false;
        private boolean progresoCargas=false;
        private boolean progresoDescargas=false;




        @Override
        protected void onPreExecute()
        {
        this.dialogoProgreso.setMessage("Enviando Dia");
        this.dialogoProgreso.setIndeterminate(false);
        this.dialogoProgreso.show();
        }


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





        @Override
        protected String doInBackground(String... strings) {

            try
            {


                /////// ENVIANDO REPARTOS

                infoEnvioRepartos = true;
                publishProgress();
                while(infoEnvioRepartos)
                    Thread.sleep(1);
                progresoRepartos = true;

                for(int i=0;i<diaRepartidor.getRepartos().getRepartos().size() && !isCancelled();i++)
                    {
                    if(diaRepartidor.getRepartos().getRepartos().get(i).getEnviado() == false)
                        {
                        this.url = this.urlServidor + "AplicacionSM/servidor/recibirReparto/recibirReparto.php";

                        HttpClient cliente = new DefaultHttpClient();

                        HttpPost requerimiento = new HttpPost(url);
                        //Configuramos los parametos que vaos a enviar con la peticion HTTP POST
                        List<NameValuePair> postParameters = new ArrayList<NameValuePair>(3);
                        postParameters.add(new BasicNameValuePair("idRepartidor", String.valueOf(diaRepartidor.getIdRepartidor())));
                        postParameters.add(new BasicNameValuePair("fecha", String.valueOf(diaRepartidor.getFecha())));
                        postParameters.add(new BasicNameValuePair("reparto", diaRepartidor.getRepartos().getRepartos().get(i).getXMLToSend()));
                        requerimiento.setEntity(new UrlEncodedFormEntity(postParameters));

                        HttpResponse respuesta = cliente.execute(requerimiento);


                        // Get the response
                        BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                        String line = "";
                        String respuestaXML = "";

                        while ((line = rd.readLine()) != null) {
                            respuestaXML += line;
                        }

                        EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXML = new EstadoDatoDiaRecibidoXML(respuestaXML);

                        if (estadoDatoDiaRecibidoXML.getEstado())
                            {
                            diaRepartidor.getRepartos().getRepartos().get(i).setEnviado(true);
                            repartosEnviados++;
                            publishProgress();
                            }

                        }
                    else
                        {
                        repartosEnviados++;
                        publishProgress();
                        }

                    }


                /// Completar Dia
                if(!isCancelled()) {

                    this.url = this.urlServidor + "AplicacionSM/servidor/recibirDatosDia.php";

                    HttpClient cliente = new DefaultHttpClient();

                    HttpPost requerimiento = new HttpPost(url);
                    //Configuramos los parametos que vaos a enviar con la peticion HTTP POST
                    List<NameValuePair> postParameters = new ArrayList<NameValuePair>(2);
                    postParameters.add(new BasicNameValuePair("idRepartidor", String.valueOf(diaRepartidor.getIdRepartidor())));
                    postParameters.add(new BasicNameValuePair("fecha", String.valueOf(diaRepartidor.getFecha())));
                    requerimiento.setEntity(new UrlEncodedFormEntity(postParameters));

                    HttpResponse respuesta = cliente.execute(requerimiento);


                    // Get the response
                    BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                    String line = "";
                    String respuestaXML = "";

                    while ((line = rd.readLine()) != null) {
                        respuestaXML += line;
                    }

                    EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXML = new EstadoDatoDiaRecibidoXML(respuestaXML);

                }


                progresoRepartos = false;


                /////// ENVIANDO GASTOS

                infoEnvioGastos = true;
                publishProgress();
                while(infoEnvioGastos)
                    Thread.sleep(1);
                progresoGastos = true;

                EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXMLEliminarGastos = new EstadoDatoDiaRecibidoXML(eliminarGastos());


                for(int i=0;i<diaRepartidor.getGastos().getGastos().size() && !isCancelled();i++)
                    {

                    this.url = this.urlServidor + "AplicacionSM/servidor/recibirGasto.php";


                    HttpClient cliente = new DefaultHttpClient();

                    HttpPost requerimiento = new HttpPost(url);
                    //Configuramos los parametos que vaos a enviar con la peticion HTTP POST
                    List<NameValuePair> postParameters = new ArrayList<NameValuePair>(3);
                    postParameters.add(new BasicNameValuePair("idRepartidor", String.valueOf(diaRepartidor.getIdRepartidor())));
                    postParameters.add(new BasicNameValuePair("fecha", String.valueOf(diaRepartidor.getFecha())));
                    postParameters.add(new BasicNameValuePair("gasto", diaRepartidor.getGastos().getGastos().get(i).getXMLToSend()));
                    requerimiento.setEntity(new UrlEncodedFormEntity(postParameters));

                    HttpResponse respuesta = cliente.execute(requerimiento);


                    // Get the response
                    BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                    String line = "";
                    String respuestaXML = "";

                    while ((line = rd.readLine()) != null)
                        {
                        respuestaXML += line;
                        }

                    EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXML = new EstadoDatoDiaRecibidoXML(respuestaXML);

                    if(estadoDatoDiaRecibidoXML.getEstado())
                        {
                        gastosEnviados++;
                        publishProgress();
                        }


                    }
                progresoGastos = false;


                /////// ENVIANDO CARGAS

                infoEnvioCargas = true;
                publishProgress();
                while(infoEnvioCargas)
                    Thread.sleep(1);
                progresoCargas = true;


                EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXMLEliminarCargas = new EstadoDatoDiaRecibidoXML(eliminarCargas());


                for(int i=0;i<diaRepartidor.getCargamento().getCargas().getCargas().size() && !isCancelled();i++)
                {

                   this.url = this.urlServidor + "AplicacionSM/servidor/recibirCarga.php";


                    HttpClient cliente = new DefaultHttpClient();

                    HttpPost requerimiento = new HttpPost(url);
                    //Configuramos los parametos que vaos a enviar con la peticion HTTP POST
                    List<NameValuePair> postParameters = new ArrayList<NameValuePair>(3);
                    postParameters.add(new BasicNameValuePair("idRepartidor", String.valueOf(diaRepartidor.getIdRepartidor())));
                    postParameters.add(new BasicNameValuePair("fecha", String.valueOf(diaRepartidor.getFecha())));
                    postParameters.add(new BasicNameValuePair("carga", diaRepartidor.getCargamento().getCargas().getCargas().get(i).getXMLToSend()));
                    requerimiento.setEntity(new UrlEncodedFormEntity(postParameters));

                    HttpResponse respuesta = cliente.execute(requerimiento);


                    // Get the response
                    BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                    String line = "";
                    String respuestaXML = "";

                    while ((line = rd.readLine()) != null)
                        {
                        respuestaXML += line;
                        }

                    EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXML = new EstadoDatoDiaRecibidoXML(respuestaXML);

                    if(estadoDatoDiaRecibidoXML.getEstado())
                        {
                        cargasEnviadas++;
                        publishProgress();
                        }
                }
                progresoCargas = false;


                /////// ENVIANDO DESCARGAS

                infoEnvioDescargas = true;
                publishProgress();
                while(infoEnvioDescargas)
                    Thread.sleep(1);
                progresoDescargas = true;


                EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXMLEliminarDescargas = new EstadoDatoDiaRecibidoXML(eliminarDescargas());


                for(int i=0;i<diaRepartidor.getCargamento().getDescargas().getDescargas().size() && !isCancelled();i++)
                {

                    this.url = this.urlServidor + "AplicacionSM/servidor/recibirDescarga.php";

                    HttpClient cliente = new DefaultHttpClient();

                    HttpPost requerimiento = new HttpPost(url);
                    //Configuramos los parametos que vaos a enviar con la peticion HTTP POST
                    List<NameValuePair> postParameters = new ArrayList<NameValuePair>(3);
                    postParameters.add(new BasicNameValuePair("idRepartidor", String.valueOf(diaRepartidor.getIdRepartidor())));
                    postParameters.add(new BasicNameValuePair("fecha", String.valueOf(diaRepartidor.getFecha())));
                    postParameters.add(new BasicNameValuePair("descarga", diaRepartidor.getCargamento().getDescargas().getDescargas().get(i).getXMLToSend()));
                    requerimiento.setEntity(new UrlEncodedFormEntity(postParameters));

                    HttpResponse respuesta = cliente.execute(requerimiento);


                    // Get the response
                    BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                    String line = "";
                    String respuestaXML = "";

                    while ((line = rd.readLine()) != null)
                        {
                        respuestaXML += line;
                        }

                    EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXML = new EstadoDatoDiaRecibidoXML(respuestaXML);

                    if(estadoDatoDiaRecibidoXML.getEstado())
                    {
                        descargasEnviadas++;
                        publishProgress();
                    }
                }
                progresoDescargas = false;


                /////// ENVIANDO DATOS DIA REPARTIDOR

                /*
                infoEnvioDatos = true;
                publishProgress();




                this.url = "http://" + Comunicador.getConexionServidor().getIp() + "/AplicacionSM/servidor/recibirDatosDia.php";

                HttpClient cliente = new DefaultHttpClient();

                HttpPost requerimiento = new HttpPost(url);
                //Configuramos los parametos que vaos a enviar con la peticion HTTP POST
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>(3);
                postParameters.add(new BasicNameValuePair("idRepartidor", String.valueOf(diaRepartidor.getIdRepartidor())));
                postParameters.add(new BasicNameValuePair("fecha", String.valueOf(diaRepartidor.getFecha())));
                postParameters.add(new BasicNameValuePair("datos", diaRepartidor.getXMLToSend()));
                requerimiento.setEntity(new UrlEncodedFormEntity(postParameters));

                HttpResponse respuesta = cliente.execute(requerimiento);


                // Get the response
                BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

                String line = "";
                String respuestaXML = "";

                while ((line = rd.readLine()) != null)
                {
                    respuestaXML += line;
                }

                EstadoDatoDiaRecibidoXML estadoDatoDiaRecibidoXML = new EstadoDatoDiaRecibidoXML(respuestaXML);

                if(estadoDatoDiaRecibidoXML.getEstado())
                {

                }

                */


            }
        catch (Exception e)
            {
            String x = e.toString();
            }
            return "";
        }




        private String eliminarGastos()throws Exception
        {
            String url = this.urlServidor + "AplicacionSM/servidor/eliminarGastos.php";

            return requerimientoEliminar(url);

        }
        private String eliminarDescargas()throws Exception
        {
            String url = this.urlServidor + "AplicacionSM/servidor/eliminarDescargas.php";

            return requerimientoEliminar(url);

        }

        private String eliminarCargas()throws Exception
        {
            String url = this.urlServidor + "AplicacionSM/servidor/eliminarCargas.php";

            return requerimientoEliminar(url);

        }

        private String  requerimientoEliminar(String url) throws Exception
        {
            HttpClient cliente = new DefaultHttpClient();
            HttpPost requerimiento = new HttpPost(url);
            List<NameValuePair> parametros = new ArrayList<NameValuePair>(2);
            parametros.add(new BasicNameValuePair("idRepartidor", String.valueOf(diaRepartidor.getIdRepartidor())));
            parametros.add(new BasicNameValuePair("fecha", String.valueOf(diaRepartidor.getFecha())));
            requerimiento.setEntity(new UrlEncodedFormEntity(parametros));
            HttpResponse respuesta = cliente.execute(requerimiento);

            // Get the response
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));

            String line = "";
            String respuestaServidor = "";

            while ((line = bufferedReader.readLine()) != null)
            {
                respuestaServidor += line;
            }

            return respuestaServidor;
        }







        @Override
        protected void onProgressUpdate(Integer... values)
        {

        if(infoEnvioRepartos)
            {
            this.dialogoProgreso.setMessage("Enviando Repartos");
            this.dialogoProgreso.setMax(this.diaRepartidor.getRepartos().getRepartos().size());
            this.dialogoProgreso.setProgress(0);
            infoEnvioRepartos = false;
            }

        if(progresoRepartos)
            {
            this.dialogoProgreso.setProgress(repartosEnviados);
            }

        if(infoEnvioGastos)
            {
            infoEnvioGastos = false;
            this.dialogoProgreso.setMessage("Enviando Gastos");
            this.dialogoProgreso.setMax(this.diaRepartidor.getGastos().getGastos().size());
            this.dialogoProgreso.setProgress(0);
            }

        if(progresoGastos)
            {
            this.dialogoProgreso.setProgress(gastosEnviados);
            }

        if(infoEnvioCargas)
            {
            this.dialogoProgreso.setMessage("Enviando Cargas");
            this.dialogoProgreso.setMax(this.diaRepartidor.getCargamento().getCargas().getCargas().size());
            this.dialogoProgreso.setProgress(0);
            infoEnvioCargas = false;
            }

        if(progresoCargas)
            {
            this.dialogoProgreso.setProgress(cargasEnviadas);
            }

        if(infoEnvioDescargas)
            {
            this.dialogoProgreso.setMessage("Enviando Descargas");
            this.dialogoProgreso.setMax(this.diaRepartidor.getCargamento().getDescargas().getDescargas().size());
            this.dialogoProgreso.setProgress(0);
            infoEnvioDescargas = false;
            }

        if(progresoDescargas)
            {
            this.dialogoProgreso.setProgress(descargasEnviadas);
            }

        if(infoEnvioDatos)
            {
            this.dialogoProgreso.setMessage("Enviando Datos del Dia");
            this.dialogoProgreso.setIndeterminate(true);
            }






        }


        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            enviarDiaFinalizado();
        }

    }


    private void enviarDiaFinalizado()
    {
    this.diaRepartidor.getDiaEnviado().setRepartosEnviados(repartosEnviados);
    this.diaRepartidor.getDiaEnviado().setGastosEnviados(gastosEnviados);
    this.diaRepartidor.getDiaEnviado().setCargasEnviadas(cargasEnviadas);
    this.diaRepartidor.getDiaEnviado().setDescargasEnviadas(descargasEnviadas);

    cargarTextViews();

    this.diaRepartidor.getDiaEnviado().modificar();

    Dialogo.setListenerEventoAceptarInterfaz(new ListenerEventoAceptarVacio());
    Dialogo.aceptar("Atención!","El dia se envió correctamente",this);


    }









































}
