package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    }




    protected int idDiaRepartidor;
    private Alquileres alquileres = new Alquileres();
    private Alquileres alquileresPagados = new Alquileres();
    private Retornables retornablesEntregados = new Retornables();
    private PrecioAlquileres precioAlquileres;

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
        this.alquileresPagados.copiar(datosAlquiler.getAlquileresPagados());
        this.retornablesEntregados.copiar(datosAlquiler.getRetornablesEntregados());
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
    retornables.setBidones20L(this.alquileres.getAlquileres6Bidones()* 6 + this.alquileres.getAlquileres8Bidones()*8 - this.retornablesEntregados.getBidones20L());
    retornables.setBidones12L(this.alquileres.getAlquileres10Bidones()* 10 + this.alquileres.getAlquileres12Bidones()*12 - this.retornablesEntregados.getBidones12L());
    return retornables;
    }


    public Alquileres getAlquileresAPagar()
    {
    Alquileres alquileres = new Alquileres();
    alquileres.setAlquileres6Bidones(this.alquileres.getAlquileres6Bidones() - this.alquileresPagados.getAlquileres6Bidones());
    alquileres.setAlquileres8Bidones(this.alquileres.getAlquileres8Bidones() - this.alquileresPagados.getAlquileres8Bidones());
    alquileres.setAlquileres10Bidones(this.alquileres.getAlquileres10Bidones() - this.alquileresPagados.getAlquileres10Bidones());
    alquileres.setAlquileres12Bidones(this.alquileres.getAlquileres12Bidones() - this.alquileresPagados.getAlquileres12Bidones());
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
            this.alquileres.setAlquileres8Bidones(cursor.getInt(4));
            this.alquileres.setAlquileres10Bidones(cursor.getInt(6));
            this.alquileres.setAlquileres12Bidones(cursor.getInt(8));
            this.alquileresPagados.setAlquileres6Bidones(cursor.getInt(3));
            this.alquileresPagados.setAlquileres8Bidones(cursor.getInt(5));
            this.alquileresPagados.setAlquileres10Bidones(cursor.getInt(7));
            this.alquileresPagados.setAlquileres12Bidones(cursor.getInt(9));
            this.retornablesEntregados.setBidones20L(cursor.getInt(10));
            this.retornablesEntregados.setBidones12L(cursor.getInt(11));

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
        datosAlquiler.put("alquileres6BidonesPagados",this.alquileresPagados.getAlquileres6Bidones());
        datosAlquiler.put("alquileres8BidonesPagados",this.alquileresPagados.getAlquileres8Bidones());
        datosAlquiler.put("alquileres10BidonesPagados",this.alquileresPagados.getAlquileres10Bidones());
        datosAlquiler.put("alquileres12BidonesPagados",this.alquileresPagados.getAlquileres12Bidones());
        datosAlquiler.put("bidones20LEntregados",this.retornablesEntregados.getBidones20L());
        datosAlquiler.put("bidones12LEntregados",this.retornablesEntregados.getBidones12L());

        if(db.insert("DatosAlquiler",null,datosAlquiler) > 0)
            {
            this.id = getLastId("DatosAlquiler");
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

    public Alquileres getAlquileresPagados() {
        return alquileresPagados;
    }

    public void setAlquileresPagados(Alquileres alquileresPagados) {
        this.alquileresPagados = alquileresPagados;
    }

    public Retornables getRetornablesEntregados() {
        return retornablesEntregados;
    }

    public void setRetornablesEntregados(Retornables retornablesEntregados) {
        this.retornablesEntregados = retornablesEntregados;
    }





    public int getIdDiaRepartidor(){return idDiaRepartidor;}
    public void setIdDiaRepartidor(int idDiaRepartidor){this.idDiaRepartidor = idDiaRepartidor;}




}
