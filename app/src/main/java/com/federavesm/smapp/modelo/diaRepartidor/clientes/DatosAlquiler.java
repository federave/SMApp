package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.Generico;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioAlquileres;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialAlquiler;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialProductos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioNormalAlquileres;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioNormalProductos;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Alquileres;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.Alquiler;

/**
 * Created by Federico on 4/2/2018.
 */

public class DatosAlquiler extends GenericoDiaRepartidor {


    public DatosAlquiler(Context context)
    {
    super(context);
    this.context = context;
    this.estadoAlquiler = new EstadoAlquiler(context);
    }




    protected int idDiaRepartidor;
    private Alquileres alquileres = new Alquileres();
    private PrecioAlquileres precioAlquileres;

    private EstadoAlquiler estadoAlquiler;


    public EstadoAlquiler getEstadoAlquiler() {
        return estadoAlquiler;
    }

    public void setEstadoAlquiler(EstadoAlquiler estadoAlquiler) {
        this.estadoAlquiler = estadoAlquiler;
    }

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public void copiar(Object object)
    {
    try
        {
        DatosAlquiler datosAlquiler = (DatosAlquiler)object;
        this.id = datosAlquiler.getId();
        this.idDiaRepartidor = datosAlquiler.getIdDiaRepartidor();
        this.alquileres.copiar(datosAlquiler.getAlquileres());
        this.estadoAlquiler.copiar(datosAlquiler.getEstadoAlquiler());
        this.precioAlquileres.copiar(datosAlquiler.getPrecioAlquileres());
        }
    catch (Exception e)
        {

        }
    }




    @Override
    public Object getCopia()
    {
    DatosAlquiler datosAlquiler = new DatosAlquiler(context);
    datosAlquiler.copiar(this);
    return datosAlquiler;
    }




    public Retornables getRetornablesAEntregar()
    {
    Retornables retornables = new Retornables();
    retornables.setBidones20L(this.alquileres.getAlquileres6Bidones()* 6 + this.alquileres.getAlquileres8Bidones()*8 - this.estadoAlquiler.getRetornablesEntregados().getBidones20L());
    retornables.setBidones12L(this.alquileres.getAlquileres10Bidones()* 10 + this.alquileres.getAlquileres12Bidones()*12 - this.estadoAlquiler.getRetornablesEntregados().getBidones12L());
    return retornables;
    }


    public Alquileres getAlquileresAPagar()
    {
    Alquileres alquileres = new Alquileres();
    alquileres.setAlquileres6Bidones(this.alquileres.getAlquileres6Bidones() - this.estadoAlquiler.getAlquileresPagados().getAlquileres6Bidones());
    alquileres.setAlquileres8Bidones(this.alquileres.getAlquileres8Bidones() - this.estadoAlquiler.getAlquileresPagados().getAlquileres8Bidones());
    alquileres.setAlquileres10Bidones(this.alquileres.getAlquileres10Bidones() - this.estadoAlquiler.getAlquileresPagados().getAlquileres10Bidones());
    alquileres.setAlquileres12Bidones(this.alquileres.getAlquileres12Bidones() - this.estadoAlquiler.getAlquileresPagados().getAlquileres12Bidones());
    return alquileres;
    }



    @Override
    public boolean cargar()
    {

    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DatosAlquiler WHERE id="+"'"+this.id+"'",null);
        boolean aux = false;
        if(cursor.moveToFirst())
            {
            aux = true;
            if(cursor.getInt(1)!=-1)
                {
                this.precioAlquileres = new PrecioEspecialAlquiler(this.context);
                }
            else
                {
                this.precioAlquileres = new PrecioNormalAlquileres(this.context);
                }
            this.precioAlquileres.setId(cursor.getInt(1));
            this.precioAlquileres.setIdDiaRepartidor(this.idDiaRepartidor);

            this.alquileres.setAlquileres6Bidones(cursor.getInt(2));
            this.alquileres.setAlquileres8Bidones(cursor.getInt(3));
            this.alquileres.setAlquileres10Bidones(cursor.getInt(4));
            this.alquileres.setAlquileres12Bidones(cursor.getInt(5));

            this.estadoAlquiler.setIdAlquiler(this.id);

            aux &= this.estadoAlquiler.cargar();
            aux &= this.precioAlquileres.cargar();
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }



    private Fecha fecha;

    public boolean cargar(Fecha fecha)
    {
        this.fecha=fecha;

        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM DatosAlquiler WHERE id="+"'"+this.id+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
            {
                aux = true;
                if(cursor.getInt(1)!=-1)
                {
                    this.precioAlquileres = new PrecioEspecialAlquiler(this.context);
                    this.precioAlquileres.setId(cursor.getInt(1));
                    this.precioAlquileres.cargar();

                }
                else
                {
                    this.precioAlquileres = new PrecioNormalAlquileres(this.context);
                    this.precioAlquileres.cargar(fecha);


                }

                this.alquileres.setAlquileres6Bidones(cursor.getInt(2));
                this.alquileres.setAlquileres8Bidones(cursor.getInt(3));
                this.alquileres.setAlquileres10Bidones(cursor.getInt(4));
                this.alquileres.setAlquileres12Bidones(cursor.getInt(5));

                this.estadoAlquiler.setIdAlquiler(this.id);
                aux &= this.estadoAlquiler.cargar(this.fecha);
            }
            db.close();
            return aux;
        }
        catch (Exception e)
        {
            return false;
        }
    }










    public boolean cargarAuxiliar()
    {
    if(this.id>0){
    try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM DatosAlquiler WHERE id="+"'"+this.id+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
            {
                aux = true;

                //En este punto precioAlquileres tiene el precioNormaldelDia

                if(cursor.getInt(1)!=-1)
                {
                this.precioAlquileres = new PrecioEspecialAlquiler(this.context);
                this.precioAlquileres.setId(cursor.getInt(1));
                aux &= this.precioAlquileres.cargar();
                }

                this.alquileres.setAlquileres6Bidones(cursor.getInt(2));
                this.alquileres.setAlquileres8Bidones(cursor.getInt(3));
                this.alquileres.setAlquileres10Bidones(cursor.getInt(4));
                this.alquileres.setAlquileres12Bidones(cursor.getInt(5));
                this.estadoAlquiler.setIdAlquiler(this.id);


            }
            db.close();
            return aux;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    else
        {
            return true;
        }

    }

















    @Override
    public boolean guardar()
    {

    try
        {
        boolean aux = true;
        aux &= this.precioAlquileres.guardar();

        SQLiteDatabase db = getWritableDatabase();

        ContentValues datosAlquiler = new ContentValues();
        datosAlquiler.put("idPrecioEspecial",this.precioAlquileres.getId());
        datosAlquiler.put("alquileres6Bidones",this.alquileres.getAlquileres6Bidones());
        datosAlquiler.put("alquileres8Bidones",this.alquileres.getAlquileres8Bidones());
        datosAlquiler.put("alquileres10Bidones",this.alquileres.getAlquileres10Bidones());
        datosAlquiler.put("alquileres12Bidones",this.alquileres.getAlquileres12Bidones());


        if(db.insert("DatosAlquiler",null,datosAlquiler) > 0)
            {
            this.id = getLastId("DatosAlquiler");
            this.estadoAlquiler.setIdAlquiler(this.id);
            aux &= this.estadoAlquiler.guardar(); // La fecha se asigno previamente

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
        boolean aux = true;
        aux&=this.precioAlquileres.eliminar();

        SQLiteDatabase db = getWritableDatabase();
        if(!(db.delete("DatosAlquiler", "id=" + "'" + this.id + "'", null)>0))
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
    public boolean getEstado()
    {
    return this.alquileres.getEstado();
    }

    public PrecioAlquileres getPrecioAlquileres() {
        return precioAlquileres;
    }

    public void setPrecioAlquileres(PrecioAlquileres precioAlquileres) {
        this.precioAlquileres = precioAlquileres;
    }


    public Alquileres getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(Alquileres alquileres) {
        this.alquileres = alquileres;
    }


    public int getIdDiaRepartidor(){return idDiaRepartidor;}
    public void setIdDiaRepartidor(int idDiaRepartidor){this.idDiaRepartidor = idDiaRepartidor;}




}
