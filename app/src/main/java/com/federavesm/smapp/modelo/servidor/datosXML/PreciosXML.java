package com.federavesm.smapp.modelo.servidor.datosXML;

import android.content.Context;

import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.DatosAlquiler;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioAlquileres;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialAlquiler;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialProductos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioProductos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.Precios;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos.VentaProductos;

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
 * Created by Federico on 20/2/2018.
 */


public class PreciosXML
{

    public PreciosXML()
    {}

    public PreciosXML(String xml,Context activity)
    {
    try
        {
        factory = SAXParserFactory.newInstance();
        parser = factory.newSAXParser();
        this.reader = parser.getXMLReader();
        this.reader.setContentHandler(new DatoXML(this,activity));
        this.reader.parse(new InputSource(new StringReader(xml)));
        this.activity = activity;
        this.estado = true;
        }
    catch (Exception e)
        {
        String x = e.toString();
        this.estado = false;
        }
    }

    private Context activity;

    private SAXParserFactory factory;
    private SAXParser parser;
    private XMLReader reader;



    protected boolean estado = false;

    private Precios precios;

    public Precios getPrecios() {
        return precios;
    }
    public void setPrecios(Precios precios) {
        this.precios = precios;
    }


    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    class DatoXML extends DefaultHandler
    {

        public DatoXML(PreciosXML preciosXML,Context activity)
        {
            this.preciosXML=preciosXML;
            this.activity = activity;
        }

        PreciosXML preciosXML;
        private Context activity;

        @Override
        public void startDocument()throws SAXException
        {
        }

        private StringBuilder cadena = new StringBuilder();

        private Precios precios;


        @Override
        public void characters(char ch[], int start, int length)throws SAXException
        {
        cadena.append(ch,start,length);
        }


        @Override
        public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException
        {
        cadena.setLength(0);
        startPrecios(localName);
        }


        private void startPrecios(String localName)
        {
            switch (localName)
            {
                case "Precios":
                {
                    this.precios = new Precios(this.activity);
                    break;
                }
                default:{break;}
            }
        }




        @Override
        public void endElement(String uri, String localName, String qName)throws SAXException
        {
        endPrecios(localName);
        }



        private void endPrecios(String localName)
        {
            switch (localName)
            {

                ////////////////////////PRECIOS DEL DIA ///////////////////////////////

                ////////////////PRECIO DISPENSADORES

                case "Vertedor_Precio":
                {
                this.precios.getPrecioDispensadores().setVertedor(getFloat(cadena.toString()));
                break;
                }
                case "Dispenser_Precio":
                {
                this.precios.getPrecioDispensadores().setDispenser(getFloat(cadena.toString()));
                break;
                }



                ////////////////PRECIO ALQUILER

                case "Alquiler6Bidones_Precio":
                {
                    this.precios.getPrecioAlquileres().setAlquiler6Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "Alquiler8Bidones_Precio":
                {
                    this.precios.getPrecioAlquileres().setAlquiler8Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "Alquiler10Bidones_Precio":
                {
                    this.precios.getPrecioAlquileres().setAlquiler10Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "Alquiler12Bidones_Precio":
                {
                    this.precios.getPrecioAlquileres().setAlquiler12Bidones(getFloat(cadena.toString()));
                    break;
                }

                ////////////////PRECIO RETORNABLE

                case "Bidon20L_Precio":
                {
                    this.precios.getPrecioProductos().getPrecioRetornables().setBidon20L(getFloat(cadena.toString()));
                    break;
                }

                case "Bidon12L_Precio":
                {
                    this.precios.getPrecioProductos().getPrecioRetornables().setBidon12L(getFloat(cadena.toString()));
                    break;
                }

                ////////////////PRECIO DESCARTABLES

                case "Bidon10L_Precio":
                {
                    this.precios.getPrecioProductos().getPrecioDescartables().setBidon10L(getFloat(cadena.toString()));
                    break;
                }
                case "Bidon8L_Precio":
                {
                    this.precios.getPrecioProductos().getPrecioDescartables().setBidon8L(getFloat(cadena.toString()));
                    break;
                }
                case "Bidon5L_Precio":
                {
                    this.precios.getPrecioProductos().getPrecioDescartables().setBidon5L(getFloat(cadena.toString()));
                    break;
                }
                case "PackBotellas2L_Precio":
                {
                    this.precios.getPrecioProductos().getPrecioDescartables().setPackBotellas2L(getFloat(cadena.toString()));
                    break;
                }
                case "PackBotellas500mL_Precio":
                {
                    this.precios.getPrecioProductos().getPrecioDescartables().setPackBotellas500mL(getFloat(cadena.toString()));
                    break;
                }

                case "Precios":
                {
                    this.preciosXML.setPrecios(this.precios);
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













}

