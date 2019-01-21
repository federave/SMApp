package com.federavesm.smapp.modelo.servidor.datosXML;

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
 * Created by FedeRave on 24/3/2018.
 */


public class DatosBasicosClienteXML
{


    public DatosBasicosClienteXML()
    {}

    public DatosBasicosClienteXML(String xml)
    {
        try
        {
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            this.reader = parser.getXMLReader();
            this.reader.setContentHandler(new DatoXML(this));
            this.reader.parse(new InputSource(new StringReader(xml)));
        }
        catch (Exception e)
        {

        }


    }



    private SAXParserFactory factory;
    private SAXParser parser;
    private XMLReader reader;


    protected int idCliente = -1;

    protected List<Integer> direcciones = new ArrayList<Integer>();

    protected int numero=0;

    public int getIdDireccion(int k)
    {
    return direcciones.get(k);
    }


    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<Integer> getDirecciones() {
        return direcciones;
    }

    public int getNumero() {
        return numero;
    }

    public void setDirecciones(List<Integer> direcciones) {
        this.direcciones = direcciones;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    protected boolean estado = false;

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }



    class DatoXML extends DefaultHandler
    {

        public DatoXML(DatosBasicosClienteXML datosBasicosClienteXML)
        {
            this.datosBasicosClienteXML = datosBasicosClienteXML;
        }

        DatosBasicosClienteXML datosBasicosClienteXML;

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
            case "IdCliente":
                {
                this.datosBasicosClienteXML.setIdCliente(Integer.valueOf(cadena.toString()));
                break;
                }
                case "IdDireccion":
                {
                this.datosBasicosClienteXML.getDirecciones().add(Integer.valueOf(cadena.toString()));
                break;
                }
                case "DatoBasico":
                {
                this.datosBasicosClienteXML.setNumero(this.datosBasicosClienteXML.getNumero()+1);
                    this.datosBasicosClienteXML.setEstado(true);
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













}
