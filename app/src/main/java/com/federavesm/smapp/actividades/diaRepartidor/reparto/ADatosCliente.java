package com.federavesm.smapp.actividades.diaRepartidor.reparto;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.ActivityGenerica;
import com.federavesm.smapp.actividades.Dialogo;
import com.federavesm.smapp.actividades.configuracion.AActualizarClientes;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedor;
import com.federavesm.smapp.modelo.servidor.RequerimientoGET;
import com.federavesm.smapp.modelo.servidor.Servidor;
import com.federavesm.smapp.modelo.servidor.VerificarConexion;
import com.federavesm.smapp.modelo.servidor.datosXML.DatosBasicosClienteXML;
import com.federavesm.smapp.modelo.servidor.datosXML.InfoClientesRepartidorXML;
import com.federavesm.smapp.modelo.servidor.datosXML.VerificarConexionXML;

/**
 * Created by Federico on 24/2/2018.
 */



public class ADatosCliente extends ActivityGenerica
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adatoscliente);

        this.buttonRetornar = (Button) findViewById(R.id.aDatosClienteButtonRetornar);
        this.buttonRetornar.setOnClickListener(new ListenerClickButtonRetornar());

        this.buttonActualizar = (Button) findViewById(R.id.aDatosClienteButtonActualizar);
        this.buttonActualizar.setOnClickListener(new ListenerClickButtonActualizarCliente());



        /////Datos Cliente

        textViewIdCliente = (TextView) findViewById(R.id.aDatosClienteIdCliente);

        textViewNombre = (TextView) findViewById(R.id.aDatosClienteNombre);
        textViewApellido = (TextView) findViewById(R.id.aDatosClienteApellido);
        textViewTelefono = (TextView) findViewById(R.id.aDatosClienteTelefono);
        textViewTipo = (TextView) findViewById(R.id.aDatosClienteTipo);
        textViewDireccion = (TextView) findViewById(R.id.aDatosClienteDireccion);


        /////Precio Especial Productos

        linearLayoutPrecioEspecialProductos = (LinearLayout) findViewById(R.id.aDatosClienteLinearLayoutPrecioEspecialProductos);
        textViewPrecioEspecialBidon20L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon20L);
        textViewPrecioEspecialBidon12L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon12L);
        textViewPrecioEspecialBidon10L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon10L);
        textViewPrecioEspecialBidon8L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon8L);
        textViewPrecioEspecialBidon5L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialBidon5L);
        textViewPrecioEspecialPackBotellas2L = (TextView) findViewById(R.id.aDatosClientePrecioEspecialPackBotellas2L);
        textViewPrecioEspecialPackBotellas500mL = (TextView) findViewById(R.id.aDatosClientePrecioEspecialPackBotellas500mL);


        /////Alquiler

        linearLayoutAlquiler = (LinearLayout) findViewById(R.id.aDatosClienteLinearLayoutAlquiler);

        textViewAlquileres6Bidones = (TextView) findViewById(R.id.aDatosClienteAlquileres6Bidones);
        textViewAlquileres6BidonesPagados = (TextView) findViewById(R.id.aDatosClienteAlquileres6BidonesPagados);
        textViewAlquileres8Bidones = (TextView) findViewById(R.id.aDatosClienteAlquileres8Bidones);
        textViewAlquileres8BidonesPagados = (TextView) findViewById(R.id.aDatosClienteAlquileres8BidonesPagados);
        textViewAlquileres10Bidones = (TextView) findViewById(R.id.aDatosClienteAlquileres10Bidones);
        textViewAlquileres10BidonesPagados = (TextView) findViewById(R.id.aDatosClienteAlquileres10BidonesPagados);
        textViewAlquileres12Bidones = (TextView) findViewById(R.id.aDatosClienteAlquileres12Bidones);
        textViewAlquileres12BidonesPagados = (TextView) findViewById(R.id.aDatosClienteAlquileres12BidonesPagados);

        textViewBidones20LEntregados = (TextView) findViewById(R.id.aDatosClienteBidones20LEntregados);
        textViewBidones12LEntregados = (TextView) findViewById(R.id.aDatosClienteBidones12LEntregados);

        linearLayoutPrecioEspecialAlquiler = (LinearLayout) findViewById(R.id.aDatosClienteLinearLayoutPrecioEspecialAlquiler);
        textViewPrecioEspecialAlquiler6Bidones = (TextView) findViewById(R.id.aDatosClientePrecioEspecialAlquiler6Bidones);
        textViewPrecioEspecialAlquiler8Bidones = (TextView) findViewById(R.id.aDatosClientePrecioEspecialAlquiler8Bidones);
        textViewPrecioEspecialAlquiler10Bidones = (TextView) findViewById(R.id.aDatosClientePrecioEspecialAlquiler10Bidones);
        textViewPrecioEspecialAlquiler12Bidones = (TextView) findViewById(R.id.aDatosClientePrecioEspecialAlquiler12Bidones);

        /////INACTIVIDAD

        textViewInactividad = (TextView) findViewById(R.id.aDatosClienteInactividad);
        textViewUltimoConsumo = (TextView) findViewById(R.id.aDatosClienteUltimoConsumo);


        /////BIDONES/DISPENSER FC

        textViewBidones20L = (TextView) findViewById(R.id.aDatosClienteBidones20L);
        textViewBidones12L = (TextView) findViewById(R.id.aDatosClienteBidones12L);
        textViewDispenserFC = (TextView) findViewById(R.id.aDatosClienteDispenserFC);


        if(Comunicador.getClienteSeleccionado()) {
            cliente = Comunicador.getClienteBusqueda();
            this.buttonActualizar.setVisibility(View.VISIBLE);
        }
            else
        {
            cliente = Comunicador.getReparto().getCliente();
            this.buttonActualizar.setVisibility(View.GONE);

        }

        cargarViews();


    }

    private Cliente cliente;

    /////////////////////

    private Button buttonActualizar;


    /////Datos Cliente

    private TextView textViewIdCliente;
    private TextView textViewNombre;
    private TextView textViewApellido;
    private TextView textViewTelefono;
    private TextView textViewTipo;
    private TextView textViewDireccion;


    /////Precio Especial Productos

    private LinearLayout linearLayoutPrecioEspecialProductos;

    private TextView textViewPrecioEspecialBidon20L;
    private TextView textViewPrecioEspecialBidon12L;
    private TextView textViewPrecioEspecialBidon10L;
    private TextView textViewPrecioEspecialBidon8L;
    private TextView textViewPrecioEspecialBidon5L;
    private TextView textViewPrecioEspecialPackBotellas2L;
    private TextView textViewPrecioEspecialPackBotellas500mL;


    /////Alquiler


    private LinearLayout linearLayoutAlquiler;

    private TextView textViewAlquileres6Bidones;
    private TextView textViewAlquileres6BidonesPagados;
    private TextView textViewAlquileres8Bidones;
    private TextView textViewAlquileres8BidonesPagados;
    private TextView textViewAlquileres10Bidones;
    private TextView textViewAlquileres10BidonesPagados;
    private TextView textViewAlquileres12Bidones;
    private TextView textViewAlquileres12BidonesPagados;
    private TextView textViewBidones20LEntregados;
    private TextView textViewBidones12LEntregados;

    private LinearLayout linearLayoutPrecioEspecialAlquiler;

    private TextView textViewPrecioEspecialAlquiler6Bidones;
    private TextView textViewPrecioEspecialAlquiler8Bidones;
    private TextView textViewPrecioEspecialAlquiler10Bidones;
    private TextView textViewPrecioEspecialAlquiler12Bidones;



    /////INACTIVIDAD

    private TextView textViewInactividad;
    private TextView textViewUltimoConsumo;


    /////BIDONES/DISPENSER FC

    private TextView textViewBidones20L;
    private TextView textViewBidones12L;
    private TextView textViewDispenserFC;



    private void cargarViews()
    {
    cargarDatosCliente();
    cargarPrecioEspecialProductos();
    cargarAlquileres();
    cargarInactividad();
    cargarBidonesDispenserFC();
    }


    private void cargarDatosCliente()
    {
    textViewIdCliente.setText("Id: " +  cliente.getDatos().getId());
    textViewNombre.setText("Nombre: " +  cliente.getDatos().getNombre());
    textViewApellido.setText("Apellido: " + cliente.getDatos().getApellido());
    textViewTelefono.setText("Telefono: " + cliente.getDatos().getTelefono());
    textViewTipo.setText("Tipo de Cliente: " + cliente.getDatos().getTipoCliente().getTipoCliente());
    textViewDireccion.setText("Direccion: " + cliente.getDireccion().toString());
    }


    private void cargarPrecioEspecialProductos()
    {
    if(cliente.getPrecioProductos().getEspecial())
        {
        textViewPrecioEspecialBidon20L.setText("Bidon20L: "+cliente.getPrecioProductos().getPrecioRetornables().getBidon20L()+" $");
        textViewPrecioEspecialBidon12L.setText("Bidon12L: "+cliente.getPrecioProductos().getPrecioRetornables().getBidon12L()+" $");
        textViewPrecioEspecialBidon10L.setText("Bidon10L: "+cliente.getPrecioProductos().getPrecioDescartables().getBidon10L()+" $");
        textViewPrecioEspecialBidon8L.setText("Bidon8L: "+cliente.getPrecioProductos().getPrecioDescartables().getBidon8L()+" $");
        textViewPrecioEspecialBidon5L.setText("Bidon5L: "+cliente.getPrecioProductos().getPrecioDescartables().getBidon5L()+" $");
        textViewPrecioEspecialPackBotellas2L.setText("Pack Botellas 2L: "+cliente.getPrecioProductos().getPrecioDescartables().getPackBotellas2L()+" $");
        textViewPrecioEspecialPackBotellas500mL.setText("Pack Botellas 500 mL: "+cliente.getPrecioProductos().getPrecioDescartables().getPackBotellas500mL()+" $");
        }
    else
        {
        linearLayoutPrecioEspecialProductos.setVisibility(View.GONE);
        }
    }



    private void cargarAlquileres()
    {
    if(cliente.getDatosAlquiler().getEstado())
        {

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres6Bidones()>0)
            {
            textViewAlquileres6Bidones.setText("Alquileres 6 Bidones: " + cliente.getDatosAlquiler().getAlquileres().getAlquileres6Bidones());
            textViewAlquileres6BidonesPagados.setText("Alquileres 6 Bidones Pagados: " + cliente.getDatosAlquiler().getEstadoAlquiler().getAlquileresPagados().getAlquileres6Bidones());
            }
        else
            {
            textViewAlquileres6Bidones.setVisibility(View.GONE);
            textViewAlquileres6BidonesPagados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres8Bidones()>0)
            {
            textViewAlquileres8Bidones.setText("Alquileres 8 Bidones: " + cliente.getDatosAlquiler().getAlquileres().getAlquileres8Bidones());
            textViewAlquileres8BidonesPagados.setText("Alquileres 8 Bidones Pagados: " + cliente.getDatosAlquiler().getEstadoAlquiler().getAlquileresPagados().getAlquileres8Bidones());
            }
        else
            {
            textViewAlquileres8Bidones.setVisibility(View.GONE);
            textViewAlquileres8BidonesPagados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres10Bidones()>0)
            {
            textViewAlquileres10Bidones.setText("Alquileres 10 Bidones: " + cliente.getDatosAlquiler().getAlquileres().getAlquileres10Bidones());
            textViewAlquileres10BidonesPagados.setText("Alquileres 10 Bidones Pagados: " + cliente.getDatosAlquiler().getEstadoAlquiler().getAlquileresPagados().getAlquileres10Bidones());
            }
        else
            {
            textViewAlquileres10Bidones.setVisibility(View.GONE);
            textViewAlquileres10BidonesPagados.setVisibility(View.GONE);
            }


        if(cliente.getDatosAlquiler().getAlquileres().getAlquileres12Bidones()>0)
            {
            textViewAlquileres12Bidones.setText("Alquileres 12 Bidones: " + cliente.getDatosAlquiler().getAlquileres().getAlquileres12Bidones());
            textViewAlquileres12BidonesPagados.setText("Alquileres 12 Bidones Pagados: " + cliente.getDatosAlquiler().getEstadoAlquiler().getAlquileresPagados().getAlquileres12Bidones());
            }
        else
            {
            textViewAlquileres12Bidones.setVisibility(View.GONE);
            textViewAlquileres12BidonesPagados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones20L())
            {
            textViewBidones20LEntregados.setText("Bidones 20L Entregados: " + cliente.getDatosAlquiler().getEstadoAlquiler().getRetornablesEntregados().getBidones20L());
            }
        else
            {
            textViewBidones20LEntregados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
            {
            textViewBidones12LEntregados.setText("Bidones 12L Entregados: " + cliente.getDatosAlquiler().getEstadoAlquiler().getRetornablesEntregados().getBidones12L());
            }
        else
            {
            textViewBidones12LEntregados.setVisibility(View.GONE);
            }

        if(cliente.getDatosAlquiler().getPrecioAlquileres().getEspecial())
            {
            textViewPrecioEspecialAlquiler6Bidones.setText("Alquiler 6 Bidones: " + cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler6Bidones()+" $");
            textViewPrecioEspecialAlquiler8Bidones.setText("Alquiler 8 Bidones: " + cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler8Bidones()+" $");
            textViewPrecioEspecialAlquiler10Bidones.setText("Alquiler 10 Bidones: " + cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler10Bidones()+" $");
            textViewPrecioEspecialAlquiler12Bidones.setText("Alquiler 12 Bidones: " + cliente.getDatosAlquiler().getPrecioAlquileres().getAlquiler12Bidones()+" $");
            }
        else
            {
            linearLayoutPrecioEspecialAlquiler.setVisibility(View.GONE);
            }

        }
    else
        {
        linearLayoutAlquiler.setVisibility(View.GONE);
        }
    }


    private void cargarInactividad()
    {
    textViewInactividad.setText("Estado: "+cliente.getEstadoInactividad().getTipoInactivo().getTipoInactivo());
    textViewUltimoConsumo.setText("Ultimo Consumo: "+cliente.getEstadoInactividad().getUltimoConsumo());
    }




    private void cargarBidonesDispenserFC()
    {
    textViewBidones20L.setText("Bidones 20L: "+cliente.getEstadoBidonesDispenserFC().getBidones20L());
    textViewBidones12L.setText("Bidones 12L: "+cliente.getEstadoBidonesDispenserFC().getBidones12L());
    textViewDispenserFC.setText("Dispenser FC: "+cliente.getEstadoBidonesDispenserFC().getDispenserFC());
    }





    //////////////////////////////////////////////////






    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///// -------------   Actualizar Clientes Vendedores         --------------




    class ListenerClickButtonActualizarCliente implements View.OnClickListener
    {
        public void onClick(View e)
        {
            actualizarClienteVerificarConexion();
        }
    }

    private void actualizarClienteVerificarConexion()
    {


    if(Comunicador.getConexionInternet(this))
    {
        VerificarConexionActualizarCliente verificarConexion = new VerificarConexionActualizarCliente(this,Comunicador.getConexionServidor().getIp());
        verificarConexion.execute();
    }
    else
    {
        Dialogo.aceptarVacioError("Atención!","No está conectado a internet",this);
    }




    }








    class VerificarConexionActualizarCliente extends VerificarConexion
    {
        public VerificarConexionActualizarCliente(Context activity, String ip)
        {
            super(activity,ip);
        }
        @Override
        protected void onPostExecute(String respuesta) {
            this.dialogoProgreso.dismiss();
            actualizarCliente(this.verificarConexionXML);
        }

    }


    private void actualizarCliente(VerificarConexionXML verificarConexionXML)
    {
        if(verificarConexionXML.getEstado())
        {
            ActualizarCliente actualizarCliente = new ActualizarCliente(this);
            actualizarCliente.setCancelar(new Cancelar(actualizarCliente));
            actualizarCliente.execute();
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




    public class ActualizarCliente extends Servidor
    {


        public ActualizarCliente(Context activity)
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
            this.dialogoProgreso.setMessage("Actualizando Cliente");
            this.dialogoProgreso.show();
        }




        @Override
        protected void onProgressUpdate(Integer... values) {



        }

        @Override
        protected String doInBackground(String... strings) {

            try
            {

                Fecha fecha = new Fecha();
                RequerimientoGET requerimientoGET = new RequerimientoGET();
                requerimientoGET.setHost(this.urlServidor);
                String respuestaXML = "";

                requerimientoGET.clear();
                requerimientoGET.setRutaScript("AplicacionSM/servidor/getReparto/getCliente.php");
                requerimientoGET.addParametro("fecha",fecha.toString());
                requerimientoGET.addParametro("idCliente",String.valueOf(cliente.getDatos().getId()));
                requerimientoGET.addParametro("idDireccion",String.valueOf(cliente.getDireccion().getIdDireccion()));
                respuestaXML = requerimientoGET.ejecutar();

                this.resultado&=cliente.actualizar(respuestaXML);







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
            actualizarClienteFin(this.resultado);
        }


    }

    private void actualizarClienteFin(boolean resultado)
    {
        if(resultado)
        {
        cargarViews();
        Dialogo.aceptarVacio("Atención!","El cliente se actualizó correctamente",this);


        }
        else
        {
            Dialogo.aceptarVacioError("Atención!","El cliente se actualizó correctamente",this);

        }
    }









}
