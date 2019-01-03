package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Conector;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoCliente.TipoCliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes extends Conector {



    public Clientes(Context context)
    {
        super(context);
    }


    public List<Cliente> buscar(String dato, Fecha fecha)
    {
    if(dato.length()>0)
        {

        List<Cliente> clientes = new ArrayList<Cliente>();

        List<ClienteBusqueda> clientesBusqueda = new ArrayList<ClienteBusqueda>();


        SQLiteDatabase db = getReadableDatabase();

        // Busqueda por id o nombre

        Cursor cursor = db.rawQuery("SELECT Distinct DC.id FROM DatosClientes WHERE DC.idCliente LIKE "+"%'"+dato+"'%" + " OR DC.nombre LIKE "+"%'"+dato+"'%" + " OR DC.apellido LIKE "+"%'"+dato+"'%" + " OR DC.telefono LIKE "+"%'"+dato+"'%",null);

        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            ClienteBusqueda clienteBusqueda = new ClienteBusqueda(cursor.getInt(0));
            clientesBusqueda.add(clienteBusqueda);
            aux = cursor.moveToNext();
            }

        // Busqueda por calle o numero




        for(int i=0;i<clientesBusqueda.size();i++)
        {
            // Cliente cliente = new Cliente()
        }

        return clientes;
        }
    else
        return new ArrayList<Cliente>();
    }


    private class ClienteBusqueda
    {

    public ClienteBusqueda(int id)
    {
    this.id = id;
    }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        private int id;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClienteBusqueda that = (ClienteBusqueda) o;
            return id == that.id;
        }

    }






}
