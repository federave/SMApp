package com.federavesm.smapp.actividades.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.federavesm.smapp.R;
import com.federavesm.smapp.actividades.CodigosActividades;
import com.federavesm.smapp.modelo.Usuario;
import com.federavesm.smapp.modelo.Comunicador;

/**
 * Created by Federico on 27/4/2017.
 */

public class ALogin extends Activity
{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alogin);


        buttonIngresar = (Button)findViewById(R.id.aLoginButtonIngresar);
        buttonIngresar.setOnClickListener(new ListenerClickButtonIngresar());


        buttonIngresar = (Button)findViewById(R.id.aLoginButtonIngresar);
        buttonIngresar.setOnClickListener(new ListenerClickButtonIngresar());

        editTextNombre = (EditText)findViewById(R.id.aLoginEditTextNombre);
        editTextContraseña = (EditText)findViewById(R.id.aLoginEditTextContraseña);

        this.usuario = Comunicador.getUsuario();


    }


    private Usuario usuario;

    private Button buttonIngresar;

    private EditText editTextNombre;
    private EditText editTextContraseña;



    class ListenerClickButtonIngresar implements View.OnClickListener
    {
        public void onClick(View e)
        {
            ingresar();
        }

    }


    @Override
    public void onBackPressed() {

        setResult(CodigosActividades.SALIR,new Intent());
        this.finish();
    }


    private void ingresar()
    {

        try
        {

            if(!(editTextNombre.getText().toString().equals("") || editTextContraseña.getText().toString().equals("")))
            {
                this.usuario.setNombre(editTextNombre.getText().toString());
                this.usuario.setContraseña(editTextContraseña.getText().toString());

                if(this.usuario.chequear())
                {

                    setResult(CodigosActividades.OK,new Intent());
                    this.finish();

                }
                else
                {
                    Toast.makeText(this,"El usuario ingresado no es correcto", Toast.LENGTH_SHORT).show();
                }

            }
            else
            {
                Toast.makeText(this,"Debe completar los campos", Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e)
        {

            Toast.makeText(this,e.toString(), Toast.LENGTH_LONG).show();

        }




    }








}





