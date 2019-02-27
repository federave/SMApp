package com.federavesm.smapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.federavesm.smapp.actividades.*;
import com.federavesm.smapp.actividades.clientes.AClientes;
import com.federavesm.smapp.actividades.configuracion.AConfiguracion;
import com.federavesm.smapp.actividades.diaRepartidor.ADiaRepartidor;
import com.federavesm.smapp.actividades.diaRepartidor.ASeleccionarDiaRepartidor;
import com.federavesm.smapp.actividades.login.ALogin;
import com.federavesm.smapp.actividades.resumenRepartidor.ASeleccionarDatosResumen;
import com.federavesm.smapp.modelo.BaseDeDatos;
import com.federavesm.smapp.modelo.Usuario;
import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.DiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidores;
import com.federavesm.smapp.modelo.servidor.ConexionServidor;




public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creacion BD
        try
            {
            BaseDeDatos baseDeDatos = new BaseDeDatos(this);
            SQLiteDatabase db = baseDeDatos.getWritableDatabase();
            String s = baseDeDatos.getTablas();
            db.close();
            }
        catch (Exception e)
            {
            String x=e.toString();
            }




        buttonSalir = (Button)findViewById(R.id.aButtonSalir);
        buttonSalir.setOnClickListener(new ListenerClickButtonSalir());

        buttonCambiarUsuario = (Button)findViewById(R.id.buttonCambiarUsuario);
        buttonCambiarUsuario.setOnClickListener(new ListenerClickButtonCambiarUsuario());

        buttonConfigurar = (Button)findViewById(R.id.buttonConfigurar);
        buttonConfigurar.setOnClickListener(new ListenerClickButtonConfigurar(this));

        buttonSeleccionarDia = (Button)findViewById(R.id.buttonSeleccionarDia);
        buttonSeleccionarDia.setOnClickListener(new ListenerClickButtonSeleccionarDia());

        buttonClientes = (Button)findViewById(R.id.buttonClientes);
        buttonClientes.setOnClickListener(new ListenerClickButtonClientes());

        buttonResumenRepartidores = (Button)findViewById(R.id.buttonResumen);
        buttonResumenRepartidores.setOnClickListener(new ListenerClickButtonResumenRepartidores());


        Comunicador.setRepartidores(new Repartidores(this));

        Comunicador.setConexionServidor(new ConexionServidor(this));


        Comunicador.setUsuario(new Usuario(this));
        Comunicador.setUsuarioAdministrador(new Usuario(this,1));




        Intent intentLogin = new Intent(this,ALogin.class);
        startActivityForResult(intentLogin,CodigosMA.Login);

    }




    private Button buttonSeleccionarDia;
    private Button buttonConfigurar;

/////////////////////////////////////////////////////////////////////
    /////////////////// RESUMEN REPARTIDOR

    private Button buttonResumenRepartidores;

    class ListenerClickButtonResumenRepartidores implements View.OnClickListener
    {
        public void onClick(View e)
        {
            resumenRepartidores();
        }
    }

    private void resumenRepartidores()
    {
    Intent intentResumenRepartidores = new Intent(this, ASeleccionarDatosResumen.class);
    startActivityForResult(intentResumenRepartidores,CodigosMA.ResumenRepartidores);
    }





    /////////////////////////////////////////////////////////////////////
    /////////////////// CLIENTES

    private Button buttonClientes;

    class ListenerClickButtonClientes implements View.OnClickListener
    {
        public void onClick(View e)
        {
            clientes();
        }
    }

    private void clientes()
    {

        Intent intentClientes = new Intent(this, AClientes.class);
        startActivityForResult(intentClientes,CodigosMA.Clientes);
    }




    /////////////////////////////////////////////////////////////////////
    /////////////////// CAMBIAR USUARIO

    private Button buttonCambiarUsuario;

    class ListenerClickButtonCambiarUsuario implements View.OnClickListener
    {
    public void onClick(View e)
        {
            cambiarUsuario();
        }
    }

    private void cambiarUsuario()
    {

        Intent intentLogin = new Intent(this,ALogin.class);
        startActivityForResult(intentLogin,CodigosMA.Login);
    }


    /////////////////////////////////////////////////////////////////////
    //////////////SELECCIONAR DIA

    class ListenerClickButtonSeleccionarDia implements View.OnClickListener
    {
    public void onClick(View e)
        {
            seleccionarDia();
        }
    }


    private void seleccionarDia()
    {
    Comunicador.setDiaRepartidor(new DiaRepartidor(this));
    Intent intentSeleccionarDiaRepartidor = new Intent(this,ASeleccionarDiaRepartidor.class);
    startActivityForResult(intentSeleccionarDiaRepartidor,CodigosMA.SeleccionarDiaRepartidor);
    }



    class ListenerClickButtonConfigurar implements View.OnClickListener
    {
        Context context;

        public ListenerClickButtonConfigurar(Context context)
        {
        this.context = context;
        }
        public void onClick(View e){configurar();}
    }


    private void configurar()
    {

    Intent intentConfiguracion = new Intent(this,AConfiguracion.class);
    startActivityForResult(intentConfiguracion,CodigosMA.Configuracion);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    if(CodigosMA.Login == requestCode)
        {
        if(CodigosMA.OK == resultCode)
            {
            if(!Comunicador.getUsuario().getAdministrador())
                {
                this.buttonConfigurar.setVisibility(View.GONE);
                }
            else
                {
                this.buttonConfigurar.setVisibility(View.VISIBLE);
                }
            }
            else if(CodigosMA.SALIR == resultCode)
            {
            this.finish();
            }
            else
            {}

        }
    else if(CodigosMA.Configuracion == requestCode)
        {

        if(CodigosMA.OK == resultCode)
            {
            //Toast.makeText(this,"Usuario correcto",Toast.LENGTH_SHORT).show();
            }
        else if(CodigosMA.SALIR == resultCode)
            {
            }
        else
            {}

        }
    else if(CodigosMA.SeleccionarDiaRepartidor == requestCode)
        {

        if(CodigosMA.OK == resultCode)
            {
            Intent intentDiaRepartidor = new Intent(this,ADiaRepartidor.class);
            startActivityForResult(intentDiaRepartidor,CodigosMA.DiaRepartidor);
            }
        else if(CodigosMA.SALIR == resultCode)
            {
            }
        else
            {}

        }
    else if(CodigosMA.DiaRepartidor == requestCode)
    {

        if(CodigosMA.OK == resultCode)
        {
        }
        else if(CodigosMA.SALIR == resultCode)
        {
        }
        else
        {}

    }
    else if(CodigosMA.Clientes == requestCode)
    {

    }
    else
        {

        }




    }

    static class CodigosMA extends CodigosActividades
    {
    public static int Configuracion = 1;
    public static int Login = 2;
    public static int SeleccionarDiaRepartidor = 3;
    public static int DiaRepartidor = 4;
    public static int Clientes = 5;
    public static int ResumenRepartidores = 6;

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



    @Override
    public void onBackPressed() {

    }

















}
