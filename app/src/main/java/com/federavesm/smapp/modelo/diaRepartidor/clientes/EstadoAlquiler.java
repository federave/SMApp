package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Alquileres;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;





public class EstadoAlquiler extends GenericoDiaRepartidor {


    public EstadoAlquiler(Context context)
    {
        super(context);
        this.context = context;
    }



    private int idAlquiler;
    private Alquileres alquileresPagados = new Alquileres();
    private Retornables retornablesEntregados = new Retornables();
    private Fecha fecha;



    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public void copiar(Object object)
    {
        try
        {
            EstadoAlquiler estadoAlquiler = (EstadoAlquiler)object;
            this.id = estadoAlquiler.getId();
            this.alquileresPagados.copiar(estadoAlquiler.getAlquileresPagados());
            this.retornablesEntregados.copiar(estadoAlquiler.getRetornablesEntregados());
        }
        catch (Exception e)
        {

        }
    }




    @Override
    public Object getCopia()
    {
        EstadoAlquiler estadoAlquiler = new EstadoAlquiler(context);
        estadoAlquiler.copiar(this);
        return estadoAlquiler;
    }


    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EstadoAlquiler WHERE idAlquiler="+"'"+this.idAlquiler+"'" + " AND fecha="+"'"+this.fecha.toString()+"'",null);
        boolean aux = false;
        if(cursor.moveToFirst())
            {
            aux = true;

            this.id = cursor.getInt(0);
            this.alquileresPagados.setAlquileres6Bidones(cursor.getInt(2));
            this.alquileresPagados.setAlquileres8Bidones(cursor.getInt(3));
            this.alquileresPagados.setAlquileres10Bidones(cursor.getInt(4));
            this.alquileresPagados.setAlquileres12Bidones(cursor.getInt(5));
            this.retornablesEntregados.setBidones20L(cursor.getInt(6));
            this.retornablesEntregados.setBidones12L(cursor.getInt(7));

            }
            db.close();
            return aux;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean cargar(Fecha fecha)
    {
        this.fecha=fecha;
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM EstadoAlquiler WHERE idAlquiler="+"'"+this.idAlquiler+"'" + " AND fecha<="+"'"+this.fecha.toString()+"'"+" ORDER BY fecha DESC",null);
            boolean aux = false;
            if(cursor.moveToFirst())
            {
                aux = true;

                this.id = cursor.getInt(0);
                this.alquileresPagados.setAlquileres6Bidones(cursor.getInt(2));
                this.alquileresPagados.setAlquileres8Bidones(cursor.getInt(3));
                this.alquileresPagados.setAlquileres10Bidones(cursor.getInt(4));
                this.alquileresPagados.setAlquileres12Bidones(cursor.getInt(5));
                this.retornablesEntregados.setBidones20L(cursor.getInt(6));
                this.retornablesEntregados.setBidones12L(cursor.getInt(7));

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

        SQLiteDatabase db = getWritableDatabase();

        ContentValues estadoAlquiler = new ContentValues();
        estadoAlquiler.put("idAlquiler",this.idAlquiler);
        estadoAlquiler.put("alquileres6BidonesPagados",this.alquileresPagados.getAlquileres6Bidones());
        estadoAlquiler.put("alquileres8BidonesPagados",this.alquileresPagados.getAlquileres8Bidones());
        estadoAlquiler.put("alquileres10BidonesPagados",this.alquileresPagados.getAlquileres10Bidones());
        estadoAlquiler.put("alquileres12BidonesPagados",this.alquileresPagados.getAlquileres12Bidones());
        estadoAlquiler.put("bidones20LEntregados",this.retornablesEntregados.getBidones20L());
        estadoAlquiler.put("bidones12LEntregados",this.retornablesEntregados.getBidones12L());
        estadoAlquiler.put("fecha",this.fecha.toString());


        if(db.insert("EstadoAlquiler",null,estadoAlquiler) > 0)
            {
            this.id = getLastId("EstadoAlquiler");
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

            SQLiteDatabase db = getWritableDatabase();
            if(!(db.delete("EstadoAlquiler", "id=" + "'" + this.id + "'", null)>0))
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
    public boolean actualizar()
    {
        try
        {


            boolean aux = true;

            SQLiteDatabase db = getWritableDatabase();

            ContentValues estadoAlquiler = new ContentValues();
            estadoAlquiler.put("idAlquiler",this.idAlquiler);
            estadoAlquiler.put("alquileres6BidonesPagados",this.alquileresPagados.getAlquileres6Bidones());
            estadoAlquiler.put("alquileres8BidonesPagados",this.alquileresPagados.getAlquileres8Bidones());
            estadoAlquiler.put("alquileres10BidonesPagados",this.alquileresPagados.getAlquileres10Bidones());
            estadoAlquiler.put("alquileres12BidonesPagados",this.alquileresPagados.getAlquileres12Bidones());
            estadoAlquiler.put("bidones20LEntregados",this.retornablesEntregados.getBidones20L());
            estadoAlquiler.put("bidones12LEntregados",this.retornablesEntregados.getBidones12L());
            estadoAlquiler.put("fecha",this.fecha.toString());


            if(db.insert("EstadoAlquiler",null,estadoAlquiler) > 0)
            {
                this.id = getLastId("EstadoAlquiler");
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
    public boolean getEstado()
    {
        return true;
    }




    public boolean restarRetornablesEntregados(Retornables retornables)
    {
    try
        {

        boolean aux = false;

        int bidones20LOld;
        int bidones12LOld;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EstadoAlquiler WHERE id="+"'"+this.id+"'" ,null);
        if(cursor.moveToFirst())
            {
            aux = true;


            bidones20LOld = cursor.getInt(6);
            bidones12LOld = cursor.getInt(7);
            db.close();

            int bidones20LNew = bidones20LOld - retornables.getBidones20L();
            int bidones12LNew = bidones12LOld - retornables.getBidones12L();

            SQLiteDatabase dbW = getWritableDatabase();
            ContentValues estadoAlquiler = new ContentValues();
            estadoAlquiler.put("bidones20LEntregados",bidones20LNew);
            estadoAlquiler.put("bidones12LEntregados",bidones12LNew);
            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(this.id)};
            if (!(dbW.update("EstadoAlquiler", estadoAlquiler, whereClause, whereArgs) > 0))
                {
                aux = false;
                }

            }

        return aux;
        }
    catch (Exception e)
        {
        return false;
        }

    }




    public boolean sumarRetornablesEntregados(Retornables retornables)
    {
        try
        {

            boolean aux = false;

            int bidones20LOld;
            int bidones12LOld;

            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM EstadoAlquiler WHERE id="+"'"+this.id+"'" ,null);
            if(cursor.moveToFirst())
            {
                aux = true;


                bidones20LOld = cursor.getInt(6);
                bidones12LOld = cursor.getInt(7);
                db.close();

                int bidones20LNew = bidones20LOld + retornables.getBidones20L();
                int bidones12LNew = bidones12LOld + retornables.getBidones12L();

                SQLiteDatabase dbW = getWritableDatabase();
                ContentValues estadoAlquiler = new ContentValues();
                estadoAlquiler.put("bidones20LEntregados",bidones20LNew);
                estadoAlquiler.put("bidones12LEntregados",bidones12LNew);
                String whereClause = "id=?";
                String whereArgs[] = {String.valueOf(this.id)};
                if (!(dbW.update("EstadoAlquiler", estadoAlquiler, whereClause, whereArgs) > 0))
                {
                    aux = false;
                }
                dbW.close();

            }

            return aux;
        }
        catch (Exception e)
        {
            return false;
        }
    }



    public void restarRetornablesEntregadosDinamico(Retornables retornables)
    {
    this.retornablesEntregados.setBidones20L(this.retornablesEntregados.getBidones20L()-retornables.getBidones20L());
    this.retornablesEntregados.setBidones12L(this.retornablesEntregados.getBidones12L()-retornables.getBidones12L());
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



    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }




}
