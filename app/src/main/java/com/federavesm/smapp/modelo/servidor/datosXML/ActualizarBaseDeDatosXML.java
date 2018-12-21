package com.federavesm.smapp.modelo.servidor.datosXML;

import android.content.Context;

import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoCliente.TipoCliente;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo.TipoInactivo;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido.TipoFueraDeRecorrido;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.tipoVisita.TipoVisita;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedor;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Federico on 13/2/2018.
 */


public class ActualizarBaseDeDatosXML
{


    public ActualizarBaseDeDatosXML()
    {}

    public ActualizarBaseDeDatosXML(String xml, Context activity)
    {
        try
        {
            this.activity = activity;
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            this.reader = parser.getXMLReader();
            this.reader.setContentHandler(new DatoXML(this,this.activity));
            this.reader.parse(new InputSource(new StringReader(xml)));
            this.estado = true;
        }
        catch (Exception e)
        {
            String error = e.toString();

        }


    }


    protected boolean estado = false;

    private Context activity;


    private SAXParserFactory factory;
    private SAXParser parser;
    private XMLReader reader;


    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    protected List<Repartidor> repartidores = new ArrayList<Repartidor>();
    public List<Repartidor> getRepartidores() {
        return repartidores;
    }

    protected List<Vendedor> vendedores = new ArrayList<Vendedor>();
    public List<Vendedor> getVendedores() {
        return vendedores;
    }



    protected List<TipoCliente> tipoClientes = new ArrayList<TipoCliente>();
    public List<TipoCliente> getTipoClientes() {
        return tipoClientes;
    }

    protected List<TipoInactivo> tipoInactivos = new ArrayList<TipoInactivo>();
    public List<TipoInactivo> getTipoInactivos() {
        return tipoInactivos;
    }


    protected List<TipoVisita> tipoVisitas = new ArrayList<TipoVisita>();
    public List<TipoVisita> getTipoVisitas() {
        return tipoVisitas;
    }


    protected List<TipoFueraDeRecorrido> tiposFueraDeRecorrido = new ArrayList<TipoFueraDeRecorrido>();
    public List<TipoFueraDeRecorrido> getTiposFueraDeRecorrido() {
        return tiposFueraDeRecorrido;
    }





    class DatoXML extends DefaultHandler
    {

        public DatoXML(ActualizarBaseDeDatosXML actualizarBDXML, Context activity)
        {
            this.actualizarBDXML=actualizarBDXML;
            this.activity=activity;
        }
        private Context activity;


        ActualizarBaseDeDatosXML actualizarBDXML;

        @Override
        public void startDocument()throws SAXException
        {

        }


        private StringBuilder cadena = new StringBuilder();
        private Repartidor repartidor;
        private Vendedor vendedor;
        private TipoCliente tipoCliente;
        private TipoInactivo tipoInactivo;
        private TipoVisita tipoVisita;
        private TipoFueraDeRecorrido tipoFueraDeRecorrido;



        @Override
        public void characters(char ch[], int start, int length)throws SAXException
        {
            cadena.append(ch,start,length);
        }



        @Override
        public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException
        {
        cadena.setLength(0);

        startEstado(localName);

        startRepartidor(localName);
        startVendedor(localName);
        startTipoCliente(localName);
        startTipoInactivo(localName);
        startTipoVisita(localName);
        startTipoFueraDeRecorrido(localName);

        }



        private void startEstado(String localName)
        {
            switch (localName)
            {
                case "ActualizarBaseDeDatos":
                {
                    this.actualizarBDXML.setEstado(true);
                }
                default:{break;}
            }
        }

        private void startRepartidor(String localName)
        {
        switch (localName)
            {
            case "Repartidor":
                {
                repartidor = new Repartidor(this.activity);
                }
            default:{break;}
            }
        }

        private void startVendedor(String localName)
        {
            switch (localName)
            {
                case "Vendedor":
                {
                    vendedor = new Vendedor(this.activity);
                }
                default:{break;}
            }
        }

        private void startTipoCliente(String localName)
        {
            switch (localName)
            {
                case "TipoCliente":
                {
                    tipoCliente = new TipoCliente(this.activity);
                }
                default:{break;}
            }
        }

        private void startTipoInactivo(String localName)
        {
            switch (localName)
            {
                case "TipoInactivo":
                {
                    tipoInactivo = new TipoInactivo(this.activity);
                }
                default:{break;}
            }
        }

        private void startTipoVisita(String localName)
        {
            switch (localName)
            {
                case "TipoVisita":
                {
                    tipoVisita = new TipoVisita(this.activity);
                }
                default:{break;}
            }
        }


        private void startTipoFueraDeRecorrido(String localName)
        {
            switch (localName)
            {
                case "TipoFueraDeRecorrido":
                {
                    tipoFueraDeRecorrido = new TipoFueraDeRecorrido(this.activity);
                }
                default:{break;}
            }
        }




        @Override
        public void endElement(String uri, String localName, String qName)throws SAXException
        {
        endRepartidor(localName);
        endVendedor(localName);
        endTipoCliente(localName);
        endTipoInactivo(localName);
        endTipoVisita(localName);
        endTipoFueraDeRecorrido(localName);

        }


        private void endRepartidor(String localName)
        {
            switch (localName)
            {
                case "IdRepartidor":
                {
                    this.repartidor.setId(Integer.valueOf(cadena.toString()));
                    break;
                }
                case "NombreRepartidor":
                {
                    this.repartidor.setNombre(cadena.toString());
                    break;
                }
                case "ApellidoRepartidor":
                {
                    this.repartidor.setApellido(cadena.toString());
                    break;
                }
                case "DniRepartidor":
                {
                    this.repartidor.setDni(cadena.toString());
                    break;
                }
                case "Repartidor":
                {
                    this.actualizarBDXML.getRepartidores().add(repartidor);
                    break;
                }
                default:{break;}
            }
        }

        private void endVendedor(String localName)
        {
            switch (localName)
            {
                case "IdVendedor":
                {
                    this.vendedor.setId(Integer.valueOf(cadena.toString()));
                    break;
                }
                case "NombreVendedor":
                {
                    this.vendedor.setNombre(cadena.toString());
                    break;
                }
                case "ApellidoVendedor":
                {
                    this.vendedor.setApellido(cadena.toString());
                    break;
                }
                case "DniVendedor":
                {
                    this.vendedor.setDni(cadena.toString());
                    break;
                }
                case "Vendedor":
                {
                    this.actualizarBDXML.getVendedores().add(vendedor);
                    break;
                }
                default:{break;}
            }
        }


        private void endTipoCliente(String localName)
        {
            switch (localName)
            {
                case "IdTipoCliente":
                {
                    this.tipoCliente.setId(Integer.valueOf(cadena.toString()));
                    break;
                }
                case "tipoCliente":
                {
                    this.tipoCliente.setTipoCliente(cadena.toString());
                    break;
                }
                case "TipoCliente":
                {
                    this.actualizarBDXML.getTipoClientes().add(tipoCliente);
                    break;
                }
                default:{break;}
            }
        }


        private void endTipoInactivo(String localName)
        {
            switch (localName)
            {
                case "IdTipoInactivo":
                {
                    this.tipoInactivo.setId(Integer.valueOf(cadena.toString()));
                    break;
                }
                case "tipoInactivo":
                {
                    this.tipoInactivo.setTipoInactivo(cadena.toString());
                    break;
                }
                case "TipoInactivo":
                {
                    this.actualizarBDXML.getTipoInactivos().add(tipoInactivo);
                    break;
                }
                default:{break;}
            }
        }


        private void endTipoVisita(String localName)
        {
            switch (localName)
            {
                case "IdTipoVisita":
                {
                    this.tipoVisita.setId(Integer.valueOf(cadena.toString()));
                    break;
                }
                case "tipoVisita":
                {
                    this.tipoVisita.setTipoVisita(cadena.toString());
                    break;
                }
                case "TipoVisita":
                {
                    this.actualizarBDXML.getTipoVisitas().add(tipoVisita);
                    break;
                }
                default:{break;}
            }
        }




        private void endTipoFueraDeRecorrido(String localName)
        {
            switch (localName)
            {
                case "IdTipoFueraDeRecorrido":
                {
                    this.tipoFueraDeRecorrido.setId(Integer.valueOf(cadena.toString()));
                    break;
                }
                case "tipoFueraDeRecorrido":
                {
                    this.tipoFueraDeRecorrido.setTipoFueraDeRecorrido(cadena.toString());
                    break;
                }
                case "TipoFueraDeRecorrido":
                {
                    this.actualizarBDXML.getTiposFueraDeRecorrido().add(tipoFueraDeRecorrido);
                    break;
                }
                default:{break;}
            }
        }







    }













}
