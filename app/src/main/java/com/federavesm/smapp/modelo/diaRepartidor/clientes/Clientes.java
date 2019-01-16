package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.federavesm.smapp.modelo.Conector;
import com.federavesm.smapp.modelo.Fecha;
import java.util.ArrayList;
import java.util.List;

public class Clientes extends Conector {



    public Clientes(Context context)
    {
    super(context);
    this.context = context;
    }

    private Context context;


    public List<ClienteBusqueda> buscar(String dato, Fecha fecha)
    {
    if(dato.length()>0)
        {


        List<ClienteBusqueda> clientesBusqueda = new ArrayList<ClienteBusqueda>();

        try
        {
            SQLiteDatabase db = getReadableDatabase();

            // Busqueda por id o nombre

            Cursor cursor = db.rawQuery("SELECT DC.id,DC.idCliente,DC.nombre,D.calle,D.entre1,D.entre2,D.numero,D.departamento,D.piso,DC.idTipoCliente FROM DatosClientes AS DC INNER JOIN DireccionCliente AS D ON DC.idDatosDireccion = D.id WHERE DC.idCliente LIKE "+"'%"+dato+"%'" + " OR DC.nombre LIKE "+"'%"+dato+"%'" ,null);

            boolean aux = cursor.moveToFirst();
            while(aux)
            {

                int id = cursor.getInt(0);
                int idCliente = cursor.getInt(1);
                String nombre = cursor.getString(2);

                String calle=cursor.getString(3);
                String entre1=cursor.getString(4);
                String entre2=cursor.getString(5);
                String numero=cursor.getString(6);
                String departamento=cursor.getString(7);
                int piso = cursor.getInt(8);
                int idTipoCliente = cursor.getInt(9);

                String auxEntre="";
                if(entre1.length()>0 && entre2.length()>0)
                auxEntre+=" Entre: "+entre1+" y "+entre2;
                else if(entre1.length()>0)
                auxEntre+=" Esquina: "+entre1;
                else if(entre2.length()>0)
                    auxEntre+=" Esquina: "+entre2;
                else
                {}

                String auxDepartamento="";
                if(departamento.length()>0)
                    auxDepartamento+=" Dep: "+auxDepartamento;

                String auxPiso="";
                if(piso>0)
                    auxPiso+=" Piso: "+piso;



                String direccion = "Calle: "+calle+auxEntre+" N°: "+numero+auxDepartamento+auxPiso;

                ClienteBusqueda clienteBusqueda = new ClienteBusqueda(id,idCliente,nombre,direccion,idTipoCliente);
                clientesBusqueda.add(clienteBusqueda);
                aux = cursor.moveToNext();
            }

            // Busqueda por calle o numero

            cursor = db.rawQuery("SELECT DC.id,DC.idCliente,DC.nombre,D.calle,D.entre1,D.entre2,D.numero,D.departamento,D.piso,DC.idTipoCliente FROM DatosClientes AS DC INNER JOIN DireccionCliente AS D ON DC.idDatosDireccion = D.id WHERE D.calle LIKE "+"'%"+dato+"%'" + " OR D.numero LIKE "+"'%"+dato+"%'" +
                    " AND DC.id NOT IN (" + "SELECT DISTINCT DC.id FROM DatosClientes AS DC WHERE DC.idCliente LIKE "+"'%"+dato+"%'" + " OR DC.nombre LIKE "+"'%"+dato+"%'" + " OR DC.apellido LIKE "+"'%"+dato+"%'" + " OR DC.telefono LIKE "+"'%"+dato+"%'" +")" ,null);

            aux = cursor.moveToFirst();
            while(aux)
            {
                int id = cursor.getInt(0);
                int idCliente = cursor.getInt(1);
                String nombre = cursor.getString(2);

                String calle=cursor.getString(3);
                String entre1=cursor.getString(4);
                String entre2=cursor.getString(5);
                String numero=cursor.getString(6);
                String departamento=cursor.getString(7);
                int piso = cursor.getInt(8);
                int idTipoCliente = cursor.getInt(9);

                String auxEntre="";
                if(entre1.length()>0 && entre2.length()>0)
                    auxEntre+=" Entre: "+entre1+" y "+entre2;
                else if(entre1.length()>0)
                    auxEntre+=" Esquina: "+entre1;
                else if(entre2.length()>0)
                    auxEntre+=" Esquina: "+entre2;
                else
                {}

                String auxDepartamento="";
                if(departamento.length()>0)
                    auxDepartamento+=" Dep: "+auxDepartamento;

                String auxPiso="";
                if(piso>0)
                    auxPiso+=" Piso: "+piso;



                String direccion = "Calle: "+calle+auxEntre+" N°: "+numero+auxDepartamento+auxPiso;

                ClienteBusqueda clienteBusqueda = new ClienteBusqueda(id,idCliente,nombre,direccion,idTipoCliente);
                clientesBusqueda.add(clienteBusqueda);
                aux = cursor.moveToNext();
            }



        }
        catch (Exception e)
        {String x=e.toString();}


        return clientesBusqueda;
        }
    else
        return new ArrayList<ClienteBusqueda>();
    }





}
