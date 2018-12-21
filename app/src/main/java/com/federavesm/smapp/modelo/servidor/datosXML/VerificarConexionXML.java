package com.federavesm.smapp.modelo.servidor.datosXML;

import com.federavesm.smapp.modelo.servidor.ConexionServidor;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Federico on 10/1/2018.
 */

public class VerificarConexionXML
{


    public VerificarConexionXML()
    {}

    public VerificarConexionXML(String xml)
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


    protected boolean estado = false;


    private SAXParserFactory factory;
    private SAXParser parser;
    private XMLReader reader;


    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }




    class DatoXML extends DefaultHandler
    {

        public DatoXML(VerificarConexionXML verificarConexionXML)
        {
        this.verificarConexionXML=verificarConexionXML;
        }

        VerificarConexionXML verificarConexionXML;

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
                case "Estado":
                {
                this.verificarConexionXML.setEstado(Boolean.valueOf(cadena.toString()));
                break;
                }
                default:{break;}
            }
        }



    }













}
