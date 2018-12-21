package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo.Inactividad;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialProductos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioNormalProductos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioProductos;

import org.apache.http.impl.io.ContentLengthInputStream;

/**
 * Created by Federico on 4/2/2018.
 */




public class Cliente extends GenericoDiaRepartidor {


    public Cliente(Context context)
    {
    super(context);
    this.direccion = new Direccion(context);
    this.datos = new Datos(context);
    this.bidonesDispenserFC = new BidonesDispenserFC(context);
    this.inactividad = new Inactividad(context);
    this.datosAlquiler = new DatosAlquiler(context);
    this.context = context;
    }
    private Context context;

    private Direccion direccion;
    private Datos datos;
    private DatosAlquiler datosAlquiler;
    private Inactividad inactividad;
    private BidonesDispenserFC bidonesDispenserFC;
    private PrecioProductos precioProductos;
    protected int idDiaRepartidor;


    @Override
    public boolean equals(Object obj) {

        Cliente cliente = (Cliente)obj;

        if(this.datos.getId() == cliente.datos.getId() && this.direccion.getIdDireccion() == cliente.getDireccion().getIdDireccion())
        {
        return true;
        }
        else
            {
                return false;
            }


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
        Cliente cliente = (Cliente)object;
        this.id = cliente.getId();
        this.idDiaRepartidor = cliente.getIdDiaRepartidor();
        this.direccion.copiar(cliente.getDireccion());
        this.datos.copiar(cliente.getDatos());
        this.datosAlquiler.copiar(cliente.getDatosAlquiler());
        this.inactividad.copiar(cliente.getInactividad());
        this.bidonesDispenserFC.copiar(cliente.getBidonesDispenserFC());
        this.precioProductos.copiar(cliente.getPrecioProductos());
        }
    catch (Exception e)
        {

        }
    }




    @Override
    public Object getCopia()
    {
    Cliente cliente = new Cliente(context);
    cliente.copiar(this);
    return cliente;
    }



    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DatosClientes WHERE id="+"'"+this.id+"'",null);
        boolean aux = false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.datos.setId(cursor.getInt(1));
            this.direccion.setId(cursor.getInt(2));
            this.datos.setNombre(cursor.getString(3));
            this.datos.setApellido(cursor.getString(4));
            this.datos.setTelefono(cursor.getString(5));
            this.datos.getTipoCliente().setId(cursor.getInt(6));


            if(cursor.getInt(7)!=-1)
                {
                this.precioProductos = new PrecioEspecialProductos(this.context);
                }
            else
                {
                this.precioProductos = new PrecioNormalProductos(this.context);
                }

            this.precioProductos.setId(cursor.getInt(7));
            this.precioProductos.setIdDiaRepartidor(this.idDiaRepartidor);


            this.datosAlquiler.setId(cursor.getInt(8));
            this.datosAlquiler.setIdDiaRepartidor(this.idDiaRepartidor);


            this.inactividad.setId(cursor.getInt(9));
            this.bidonesDispenserFC.setDispenserFC(cursor.getInt(10));
            this.bidonesDispenserFC.setBidones20L(cursor.getInt(11));
            this.bidonesDispenserFC.setBidones12L(cursor.getInt(12));

            aux &= this.direccion.cargar();
            aux &= this.datos.getTipoCliente().cargar();
            aux &= this.precioProductos.cargar();
            aux &= this.datosAlquiler.cargar();
            aux &= this.inactividad.cargar();
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        String x = e.toString();
        return false;
        }
    }




    @Override
    public boolean guardar()
    {
    try
        {
        boolean aux = true;

        aux&=this.direccion.guardar();
        aux&=this.precioProductos.guardar();
        aux&=this.inactividad.guardar();
        aux &= this.datos.getTipoCliente().cargar();

        if(this.datosAlquiler.getEstado()){aux&=this.datosAlquiler.guardar();}

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cliente = new ContentValues();
        cliente.put("idCliente",this.datos.getId());
        cliente.put("idDatosDireccion",this.direccion.getId());
        cliente.put("nombre",this.datos.getNombre());
        cliente.put("apellido",this.datos.getApellido());
        cliente.put("telefono",this.datos.getTelefono());
        cliente.put("idTipoCliente",this.datos.getTipoCliente().getId());
        cliente.put("idPrecioEspecial",this.precioProductos.getId());
        cliente.put("idDatosAlquiler",this.datosAlquiler.getId());
        cliente.put("idInactividad",this.inactividad.getId());
        cliente.put("dispenserFC",this.bidonesDispenserFC.getDispenserFC());
        cliente.put("bidones20L",this.bidonesDispenserFC.getBidones20L());
        cliente.put("bidones12L",this.bidonesDispenserFC.getBidones12L());

        if(db.insert("DatosClientes",null,cliente) > 0)
            {
            this.id = getLastId("DatosClientes");
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
        aux&=this.direccion.eliminar();
        aux&=this.precioProductos.eliminar();
        aux&=this.inactividad.eliminar();
        aux&=this.datosAlquiler.eliminar();
        SQLiteDatabase db = getWritableDatabase();
        if(!(db.delete("DatosClientes", "id=" + "'" + this.id + "'", null)>0))
            {
            aux=false;
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
    public boolean actualizar() {
        return false;
    }


    @Override
    public boolean getEstado() {
        return false;
    }


    public PrecioProductos getPrecioProductos() {
        return precioProductos;
    }

    public void setPrecioProductos(PrecioProductos precioProductos) {
        this.precioProductos = precioProductos;
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos datos) {
        this.datos = datos;
    }

    public BidonesDispenserFC getBidonesDispenserFC() {
        return bidonesDispenserFC;
    }

    public void setBidonesDispenserFC(BidonesDispenserFC bidonesDispenserFC) {
        this.bidonesDispenserFC = bidonesDispenserFC;
    }


    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Inactividad getInactividad() {
        return inactividad;
    }

    public void setInactividad(Inactividad inactividad) {
        this.inactividad = inactividad;
    }



    public DatosAlquiler getDatosAlquiler() {
        return datosAlquiler;
    }

    public void setDatosAlquiler(DatosAlquiler datosAlquiler) {
        this.datosAlquiler = datosAlquiler;
    }

    public int getIdDiaRepartidor(){return idDiaRepartidor;}
    public void setIdDiaRepartidor(int idDiaRepartidor){this.idDiaRepartidor = idDiaRepartidor;}



}