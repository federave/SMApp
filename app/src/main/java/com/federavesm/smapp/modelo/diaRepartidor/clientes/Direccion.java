package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Generico;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;

/**
 * Created by Federico on 4/2/2018.
 */



public class Direccion extends GenericoDiaRepartidor {


    public Direccion(Context context)
    {
        super(context);
    }



    private String calle;
    private String entre1;
    private String entre2;
    private String numero;
    private String departamento;
    private int piso;
    private int idDireccion;

    @Override
    public boolean modificar() {
        return false;
    }
    @Override
    public boolean cargar()
    {
    try
       {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DireccionCliente WHERE id="+"'"+this.id+"'",null);
        boolean aux = false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.idDireccion = cursor.getInt(1);
            this.calle = cursor.getString(2);
            this.numero = cursor.getString(3);
            this.entre1 = cursor.getString(4);
            this.entre2 = cursor.getString(5);
            this.departamento = cursor.getString(6);
            this.piso = cursor.getInt(7);
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }


    @Override
    public void copiar(Object object)
    {
    try
        {
        Direccion direccion = (Direccion)object;
        this.id = direccion.getId();
        this.idDireccion = direccion.getIdDireccion();
        this.calle = direccion.getCalle();
        this.entre1 = direccion.getEntre1();
        this.entre2 = direccion.getEntre2();
        this.numero = direccion.getNumero();
        this.departamento = direccion.getDepartamento();
        this.piso = direccion.getPiso();
        }
    catch (Exception e)
        {

        }

    }


    @Override
    public Object getCopia()
    {
    Direccion direccion = new Direccion(context);
    direccion.copiar(this);
    return direccion;
    }





    @Override
    public boolean guardar()
    {

    try
        {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues direccion = new ContentValues();
        direccion.put("idDireccion",this.idDireccion);
        direccion.put("calle",this.calle);
        direccion.put("entre1",this.entre1);
        direccion.put("entre2",this.entre2);
        direccion.put("numero",this.numero);
        direccion.put("departamento",this.departamento);
        direccion.put("piso",this.piso);

        boolean aux=true;
        if(db.insert("DireccionCliente",null,direccion) > 0)
            {
            this.id = getLastId("DireccionCliente");
            }
        else
            {
            aux = false;
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }



    }



    @Override
    public boolean eliminar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        boolean aux = true;
        if(!(db.delete("DireccionCliente", "id=" + "'" + this.id + "'", null)>0))
            {aux=false;}
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }



    @Override
    public boolean actualizar() {
        return false;
    }


    @Override
    public boolean getEstado() {
        return false;
    }




    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getEntre1() {
        return entre1;
    }

    public void setEntre1(String entre1) {
        this.entre1 = entre1;
    }

    public String getEntre2() {
        return entre2;
    }

    public void setEntre2(String entre2) {
        this.entre2 = entre2;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }



    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }


    @Override
    public String toString() {



        String aux1="";

        if(this.entre1.length()>0 && this.entre2.length()>0)
        {
        aux1+=" "+this.entre1+" y "+this.entre2;
        }
        else if(this.entre1.length()>0)
        {
        aux1+=" "+"esquina: " + this.entre1;
        }
        else if(this.entre2.length()>0)
        {
        aux1+=" "+"esquina: " + this.entre2;
        }
        else
        {

        }

        String aux2="";

        if(this.departamento.length()>0)
        {
        aux2+=" "+"Dep: "+this.departamento;
        if(this.piso>0)
            {
            aux2+=" "+"Piso: " + this.piso;
            }
        }


        String direccion = this.calle + aux1 + " N°: " + this.numero + aux2 ;

        return direccion;






    }

}


