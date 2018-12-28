package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo.EstadoInactividad;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo.TipoInactivo;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioAlquileres;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialAlquiler;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialProductos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioNormalProductos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioProductos;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Federico on 4/2/2018.
 */




public class Cliente extends GenericoDiaRepartidor {


    public Cliente(Context context)
    {
    super(context);
    this.direccion = new Direccion(context);
    this.datos = new Datos(context);
    this.estadoBidonesDispenserFC = new EstadoBidonesDispenserFC(context);
    this.estadoInactividad = new EstadoInactividad(context);
    this.datosAlquiler = new DatosAlquiler(context);
    this.context = context;
    }
    private Context context;

    private Direccion direccion;
    private Datos datos;
    private DatosAlquiler datosAlquiler;
    private EstadoInactividad estadoInactividad;
    private EstadoBidonesDispenserFC estadoBidonesDispenserFC;
    private PrecioProductos precioProductos;
    protected int idDiaRepartidor;

    private Fecha fecha;


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
        this.estadoInactividad.copiar(cliente.getEstadoInactividad());
        this.estadoBidonesDispenserFC.copiar(cliente.getEstadoBidonesDispenserFC());
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
            this.datosAlquiler.getEstadoAlquiler().setFecha(this.fecha);

            this.estadoInactividad.setIdCliente(this.datos.getId());
            this.estadoInactividad.setFecha(this.fecha);

            this.estadoBidonesDispenserFC.setFecha(this.fecha);
            this.estadoBidonesDispenserFC.setIdCliente(this.datos.getId());



            aux &= this.direccion.cargar();
            aux &= this.datos.getTipoCliente().cargar();
            aux &= this.precioProductos.cargar();
            aux &= this.datosAlquiler.cargar();
            aux &= this.estadoBidonesDispenserFC.cargar();
            aux &= this.estadoInactividad.cargar();

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

    if(!(this.id>0))
    {
        try
        {
            boolean aux = true;

            aux&=this.direccion.guardar();
            aux&=this.precioProductos.guardar();
            aux &= this.datos.getTipoCliente().cargar();

            if(this.datosAlquiler.getEstado())
            {
            this.datosAlquiler.getEstadoAlquiler().setFecha(this.fecha);
            aux&=this.datosAlquiler.guardar();
            }

            this.estadoBidonesDispenserFC.setIdCliente(this.datos.getId()); // Atencion es el id de la base de datos de la oficina
            this.estadoBidonesDispenserFC.setFecha(this.fecha);
            aux &= this.estadoBidonesDispenserFC.guardar();

            this.estadoInactividad.setIdCliente(this.datos.getId()); // Atencion es el id de la base de datos de la oficina
            this.estadoInactividad.setFecha(this.fecha);
            aux&=this.estadoInactividad.guardar();




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
    else
        {
            return true;
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
        aux&=this.estadoInactividad.eliminar();
        aux&=this.datosAlquiler.eliminar();
        aux&=this.estadoBidonesDispenserFC.eliminar();

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



    public void cargar(int idCliente,int idDireccion)
    {
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT DC.id FROM DatosClientes AS DC INNER JOIN DireccionCliente AS D " +
    "ON DC.idDatosDireccion=D.id WHERE DC.idCliente="+"'"+idCliente+"'" + "AND D.idDireccion="+"'"+idDireccion+"'",null);
    boolean aux = false;
    if(cursor.moveToFirst())
        {
        this.id = cursor.getInt(0);

        cursor = db.rawQuery("SELECT * FROM DatosClientes WHERE id="+"'"+this.id+"'",null);

        if(cursor.moveToFirst())
            {
            aux = true;
            this.datos.setId(cursor.getInt(1));
            this.direccion.setId(cursor.getInt(2));
            this.datos.setNombre(cursor.getString(3));
            this.datos.setApellido(cursor.getString(4));
            this.datos.setTelefono(cursor.getString(5));
            this.datos.getTipoCliente().setId(cursor.getInt(6));


            // En este pungo precioProductos tiene el precioNormaldelDia
            if(cursor.getInt(7)!=-1)
                {
                this.precioProductos = new PrecioEspecialProductos(this.context);
                this.precioProductos.setId(cursor.getInt(7));
                aux &= this.precioProductos.cargar();
                }


            this.datosAlquiler.setId(cursor.getInt(8));
            this.datosAlquiler.getEstadoAlquiler().setFecha(this.fecha);

            aux &= this.datosAlquiler.cargarAuxiliar();

            this.estadoBidonesDispenserFC.setFecha(this.fecha);
            this.estadoBidonesDispenserFC.setIdCliente(this.datos.getId());

            this.estadoInactividad.setFecha(this.fecha);
            this.estadoInactividad.setIdCliente(this.datos.getId());


            aux &= this.direccion.cargar();
            aux &= this.datos.getTipoCliente().cargar();


            }

        db.close();

        }
    }


    protected boolean existe = false;

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }










    ////////////////////////////////////////////////////////////////////////////////////
    /////////////ACTUALIZAR



    public void actualizar(String xml)
    {
        try
        {
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            this.reader = parser.getXMLReader();
            this.reader.setContentHandler(new DatosActualidadXML());
            this.reader.parse(new InputSource(new StringReader(xml)));

            // Falta hacer estos actualizar



            this.estadoInactividad.actualizar();
            this.estadoBidonesDispenserFC.actualizar();
            this.datosAlquiler.getEstadoAlquiler().actualizar();



        }
        catch (Exception e)
        {
            String x = e.toString();

        }


    }

    private Context activity;

    private SAXParserFactory factory;
    private SAXParser parser;
    private XMLReader reader;




    class DatosActualidadXML extends DefaultHandler
    {

        public DatosActualidadXML()
        {

        }


        @Override
        public void startDocument()throws SAXException
        {
        }

        private StringBuilder cadena = new StringBuilder();

        @Override
        public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException
        {
            cadena.setLength(0);

        }

        @Override
        public void characters(char ch[], int start, int length)throws SAXException
        {
            cadena.append(ch,start,length);
        }

        @Override
        public void endElement(String uri, String localName, String qName)throws SAXException
        {
            switch (localName)
            {


                ////////////ESTADO ALQUILER///////////////////////////////

                case "Alquileres6BidonesPagados":
                {
                    datosAlquiler.getEstadoAlquiler().getAlquileresPagados().setAlquileres6Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres8BidonesPagados":
                {
                    datosAlquiler.getEstadoAlquiler().getAlquileresPagados().setAlquileres8Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres10BidonesPagados":
                {
                    datosAlquiler.getEstadoAlquiler().getAlquileresPagados().setAlquileres10Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres12BidonesPagados":
                {
                    datosAlquiler.getEstadoAlquiler().getAlquileresPagados().setAlquileres12Bidones(getInt(cadena.toString()));
                    break;
                }

                case "Bidones20LEntregados":
                {
                    datosAlquiler.getEstadoAlquiler().getRetornablesEntregados().setBidones20L(getInt(cadena.toString()));
                    break;
                }
                case "Bidones12LEntregados":
                {
                    datosAlquiler.getEstadoAlquiler().getRetornablesEntregados().setBidones12L(getInt(cadena.toString()));
                    break;
                }


                ////////////BIDONES DISPENSER FC///////////////////////////////

                case "DispenserFC":
                {
                    estadoBidonesDispenserFC.setDispenserFC(getInt(cadena.toString()));
                    break;
                }
                case "Bidones20L":
                {
                    estadoBidonesDispenserFC.setBidones20L(getInt(cadena.toString()));
                    break;
                }
                case "Bidones12L":
                {
                    estadoBidonesDispenserFC.setBidones12L(getInt(cadena.toString()));
                    break;
                }

                ///////////INACTIVIDAD///////////////////////////////

                case "IdInactividad":
                {
                    int id = getInt(cadena.toString());
                    TipoInactivo tipoInactivo = new TipoInactivo(activity,id);
                    estadoInactividad.setTipoInactivo(tipoInactivo);
                    break;
                }
                case "UltimoConsumo":
                {
                    estadoInactividad.setUltimoConsumo(cadena.toString().replace("*","\n"));
                    break;
                }



                default:{break;}
            }
        }


        private int getInt(String cadena)
        {
            try
            {
                return Integer.valueOf(cadena.toString());
            }
            catch (Exception e)
            {
                return -1;
            }
        }

    }






    ////////////////////////////////////////////////////////////////////////////////////
    /////////////GUARDAR





    public void guardar(String xml)
    {
    try
        {
        factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
        this.reader = parser.getXMLReader();
        this.reader.setContentHandler(new DatosClienteXML());
        this.reader.parse(new InputSource(new StringReader(xml)));
        this.guardar();
        }
    catch (Exception e)
        {
        String x = e.toString();
        }


    }


    class DatosClienteXML extends DefaultHandler
    {

        public DatosClienteXML()
        {

        }

        private PrecioAlquileres precioEspecialAlquiler;
        private PrecioProductos precioEspecialProductos;


        @Override
        public void startDocument()throws SAXException
        {
        }

        private StringBuilder cadena = new StringBuilder();

        @Override
        public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException
        {
            cadena.setLength(0);
            startDatosCliente(localName);

        }



        private void startDatosCliente(String localName)
        {
            startCliente(localName);
            startAlquiler(localName);
            startPrecioEspecialAlquiler(localName);
            startPrecioEspecialProductos(localName);
        }


        private void startCliente(String localName)
        {
            switch (localName)
            {
                case "DatosCliente":
                {
                    break;
                }
                default:{break;}
            }
        }

        private void startAlquiler(String localName)
        {
            switch (localName)
            {
                case "Alquiler":
                {
                    break;
                }
                default:{break;}
            }
        }


        private void startPrecioEspecialAlquiler(String localName)
        {
            switch (localName)
            {
                case "PrecioEspecialAlquiler":
                {
                    this.precioEspecialAlquiler = new PrecioEspecialAlquiler(activity);
                    break;
                }
                default:{break;}
            }
        }


        private void startPrecioEspecialProductos(String localName)
        {
            switch (localName)
            {
                case "PrecioEspecialProductos":
                {
                    this.precioEspecialProductos = new PrecioEspecialProductos(activity);
                    break;
                }
                default:{break;}
            }
        }







        @Override
        public void characters(char ch[], int start, int length)throws SAXException
        {
            cadena.append(ch,start,length);
        }

        @Override
        public void endElement(String uri, String localName, String qName)throws SAXException
        {
            endDatosCliente(localName);

        }



        private void endDatosCliente(String localName)
        {

            endDireccion(localName);
            endDatos(localName);
            endBidonesDispenserFC(localName);
            endInactividad(localName);
            endAlquiler(localName);

        }


        private void endDireccion(String localName)
        {

            switch (localName)
            {

                ////////////DIRECCION///////////////////////////////


                case "IdDireccion":
                {
                    direccion.setIdDireccion(getInt(cadena.toString()));
                    break;
                }

                case "Calle":
                {
                    direccion.setCalle(cadena.toString());
                    break;
                }
                case "Entre1":
                {
                    direccion.setEntre1(cadena.toString());
                    break;
                }
                case "Entre2":
                {
                    direccion.setEntre2(cadena.toString());
                    break;
                }
                case "Numero":
                {
                    direccion.setNumero(cadena.toString());
                    break;
                }
                case "Departamento":
                {
                    direccion.setDepartamento(cadena.toString());
                    break;
                }
                case "Piso":
                {
                    direccion.setPiso(getInt(cadena.toString()));
                    break;
                }

                default:{break;}
            }

        }


        private void endDatos(String localName)
        {

            switch (localName)
            {

                ////////////DATOS///////////////////////////////

                case "IdCliente":
                {
                    datos.setId(getInt(cadena.toString()));
                    break;
                }

                case "Nombre":
                {
                    datos.setNombre(cadena.toString());
                    break;
                }

                case "Apellido":
                {
                    datos.setApellido(cadena.toString());
                    break;
                }

                case "Telefono":
                {
                    datos.setTelefono(cadena.toString());
                    break;
                }

                case "IdTipoCliente":
                {
                    datos.getTipoCliente().setId(getInt(cadena.toString()));
                    break;
                }
                case "Datos":
                {
                    break;
                }
                default:{break;}
            }

        }

        private void endBidonesDispenserFC(String localName)
        {

            switch (localName)
            {

                ////////////BIDONES DISPENSER FC///////////////////////////////

                case "DispenserFC":
                {
                    estadoBidonesDispenserFC.setDispenserFC(getInt(cadena.toString()));
                    break;
                }
                case "Bidones20L":
                {
                    estadoBidonesDispenserFC.setBidones20L(getInt(cadena.toString()));
                    break;
                }
                case "Bidones12L":
                {
                    estadoBidonesDispenserFC.setBidones12L(getInt(cadena.toString()));
                    break;
                }

                default:{break;}
            }

        }

        private void endInactividad(String localName)
        {

            switch (localName)
            {

                ///////////INACTIVIDAD///////////////////////////////

                case "IdInactividad":
                {
                    int id = getInt(cadena.toString());
                    TipoInactivo tipoInactivo = new TipoInactivo(context,id);
                    estadoInactividad.setTipoInactivo(tipoInactivo);
                    break;
                }
                case "UltimoConsumo":
                {
                    estadoInactividad.setUltimoConsumo(cadena.toString().replace("*","\n"));
                    break;
                }
                default:{break;}
            }

        }


        private void endAlquiler(String localName)
        {

            switch (localName)
            {


                ///////////ALQUILER///////////////////////////////


                case "Alquileres6Bidones":
                {
                    datosAlquiler.getAlquileres().setAlquileres6Bidones(getInt(cadena.toString()));
                    break;
                }

                case "Alquileres8Bidones":
                {
                    datosAlquiler.getAlquileres().setAlquileres8Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres10Bidones":
                {
                    datosAlquiler.getAlquileres().setAlquileres10Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres12Bidones":
                {
                    datosAlquiler.getAlquileres().setAlquileres12Bidones(getInt(cadena.toString()));
                    break;
                }

                case "Alquileres6BidonesPagados":
                {
                    datosAlquiler.getEstadoAlquiler().getAlquileresPagados().setAlquileres6Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres8BidonesPagados":
                {
                    datosAlquiler.getEstadoAlquiler().getAlquileresPagados().setAlquileres8Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres10BidonesPagados":
                {
                    datosAlquiler.getEstadoAlquiler().getAlquileresPagados().setAlquileres10Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres12BidonesPagados":
                {
                    datosAlquiler.getEstadoAlquiler().getAlquileresPagados().setAlquileres12Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Bidones20LEntregados":
                {
                    datosAlquiler.getEstadoAlquiler().getRetornablesEntregados().setBidones20L(getInt(cadena.toString()));
                    break;
                }
                case "Bidones12LEntregados":
                {
                    datosAlquiler.getEstadoAlquiler().getRetornablesEntregados().setBidones12L(getInt(cadena.toString()));
                    break;
                }

                case "Alquiler6Bidones_PrecioEspecial":
                {
                    precioEspecialAlquiler.setAlquiler6Bidones(getFloat(cadena.toString()));
                    break;
                }

                case "Alquiler8Bidones_PrecioEspecial":
                {
                    precioEspecialAlquiler.setAlquiler8Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "Alquiler10Bidones_PrecioEspecial":
                {
                    precioEspecialAlquiler.setAlquiler10Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "Alquiler12Bidones_PrecioEspecial":
                {
                    precioEspecialAlquiler.setAlquiler12Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "PrecioEspecialAlquiler":
                {
                    datosAlquiler.setPrecioAlquileres(this.precioEspecialAlquiler);
                    break;
                }

                case "Alquiler":
                {
                    break;
                }


                default:{break;}
            }

        }



        private void endPrecioEspecialProductos(String localName)
        {

            switch (localName)
            {


                ////////////////////////VENTA PRODUCTOS///////////////////////////////



                case "Bidon20L_PrecioEspecial":
                {
                    this.precioEspecialProductos.getPrecioRetornables().setBidon20L(getFloat(cadena.toString()));
                    break;
                }

                case "Bidon12L_PrecioEspecial":
                {
                    this.precioEspecialProductos.getPrecioRetornables().setBidon12L(getFloat(cadena.toString()));
                    break;
                }

                case "Bidon10L_PrecioEspecial":
                {
                    this.precioEspecialProductos.getPrecioDescartables().setBidon10L(getFloat(cadena.toString()));
                    break;
                }

                case "Bidon8L_PrecioEspecial":
                {
                    this.precioEspecialProductos.getPrecioDescartables().setBidon8L(getFloat(cadena.toString()));
                    break;
                }

                case "Bidon5L_PrecioEspecial":
                {
                    this.precioEspecialProductos.getPrecioDescartables().setBidon5L(getFloat(cadena.toString()));
                    break;
                }

                case "PackBotellas2L_PrecioEspecial":
                {
                    this.precioEspecialProductos.getPrecioDescartables().setPackBotellas2L(getFloat(cadena.toString()));
                    break;
                }

                case "PackBotellas500mL_PrecioEspecial":
                {
                    this.precioEspecialProductos.getPrecioDescartables().setPackBotellas500mL(getFloat(cadena.toString()));
                    break;
                }
                case "PrecioEspecialProductos":
                {
                    precioProductos = this.precioEspecialProductos;
                    break;
                }


                default:{break;}
            }

        }





        private int getInt(String cadena)
        {
            try
            {
                return Integer.valueOf(cadena.toString());
            }
            catch (Exception e)
            {
                return -1;
            }
        }

        private float getFloat(String cadena)
        {
            try
            {
                return Float.valueOf(cadena.toString());
            }
            catch (Exception e)
            {
                return -1;
            }
        }





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

    public EstadoBidonesDispenserFC getEstadoBidonesDispenserFC() {
        return estadoBidonesDispenserFC;
    }

    public void setEstadoBidonesDispenserFC(EstadoBidonesDispenserFC estadoBidonesDispenserFC) {
        this.estadoBidonesDispenserFC = estadoBidonesDispenserFC;
    }




    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public EstadoInactividad getEstadoInactividad() {
        return estadoInactividad;
    }

    public void setEstadoInactividad(EstadoInactividad estadoInactividad) {
        this.estadoInactividad = estadoInactividad;
    }



    public DatosAlquiler getDatosAlquiler() {
        return datosAlquiler;
    }

    public void setDatosAlquiler(DatosAlquiler datosAlquiler) {
        this.datosAlquiler = datosAlquiler;
    }

    public int getIdDiaRepartidor(){return idDiaRepartidor;}
    public void setIdDiaRepartidor(int idDiaRepartidor){this.idDiaRepartidor = idDiaRepartidor;}


    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }








}