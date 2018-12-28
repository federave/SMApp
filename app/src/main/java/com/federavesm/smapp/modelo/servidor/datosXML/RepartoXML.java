package com.federavesm.smapp.modelo.servidor.datosXML;

import android.content.Context;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.DatosAlquiler;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo.TipoInactivo;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioAlquileres;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialAlquiler;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioEspecialProductos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioProductos;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido.TipoFueraDeRecorrido;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos.VentaProductos;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Federico on 20/2/2018.
 */




public class RepartoXML
{


    public RepartoXML()
    {}

    public RepartoXML(String xml,Context activity)
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

    private Reparto reparto;

    public Reparto getReparto() {
        return reparto;
    }

    public void setReparto(Reparto reparto) {
        this.reparto = reparto;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    class DatoXML extends DefaultHandler
    {

        public DatoXML(RepartoXML repartoXML,Context activity)
        {
        this.repartoXML=repartoXML;
        this.activity = activity;
        }

        RepartoXML repartoXML;
        private Context activity;



        @Override
        public void startDocument()throws SAXException
        {
        }

        private StringBuilder cadena = new StringBuilder();


        private Reparto reparto;

        private DatosAlquiler datosAlquiler;
        private PrecioAlquileres precioEspecialAlquiler;

        private VentaProductos ventaProductos;

        private Cliente cliente;

        private PrecioProductos precioEspecialProductos;

        private Vendedor vendedor;


        @Override
        public void characters(char ch[], int start, int length)throws SAXException
        {
        cadena.append(ch,start,length);
        }


        @Override
        public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException
        {

        cadena.setLength(0);


        startVendedor(localName);
        startReparto(localName);
        startDatosCliente(localName);
        startVentaProductos(localName);
        startPrecioEspecialProductos(localName);

        }



        private void startVendedor(String localName)
        {
            switch (localName)
            {
                case "Vendedor":
                {
                    break;
                }
                default:{break;}
            }
        }

        private void startReparto(String localName)
        {
            switch (localName)
            {
                case "Reparto":
                {
                    this.reparto = new Reparto(this.activity);
                    break;
                }
                default:{break;}
            }
        }

        private void startDatosCliente(String localName)
        {
            startCliente(localName);
            startAlquiler(localName);
            startPrecioEspecialAlquiler(localName);
        }

        private void startCliente(String localName)
        {
            switch (localName)
            {
                case "DatosCliente":
                {
                    this.cliente = new Cliente(this.activity);
                    this.cliente.setPrecioProductos(Comunicador.getDiaRepartidor().getPrecios().getPrecioProductos());
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
                this.datosAlquiler = new DatosAlquiler(this.activity);
                this.datosAlquiler.setPrecioAlquileres(Comunicador.getDiaRepartidor().getPrecios().getPrecioAlquileres());
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
                    this.precioEspecialAlquiler = new PrecioEspecialAlquiler(this.activity);
                    break;
                }
                default:{break;}
            }
        }



        private void startVentaProductos(String localName)
        {
            switch (localName)
            {
                case "VentaProductos":
                {
                    this.ventaProductos = new VentaProductos(this.activity);
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
                    this.precioEspecialProductos = new PrecioEspecialProductos(this.activity);
                    break;
                }
                default:{break;}
            }
        }





        @Override
        public void endElement(String uri, String localName, String qName)throws SAXException
        {



        endVendedor(localName);
        endDatosCliente(localName);
        endVentaProductos(localName);
        endPrecioEspecialProductos(localName);
        endReparto(localName);


        }



        private void endVendedor(String localName)
        {

            switch (localName)
            {
                case "IdVendedor":
                {
                    this.vendedor = new Vendedor(this.activity,getInt(cadena.toString()));
                    break;
                }
                case "Vendedor":
                {
                    this.reparto.setVendedor(this.vendedor);
                    break;
                }
                default:{break;}
            }

        }



        private void endDatosCliente(String localName)
        {

            endDireccion(localName);
            endDatos(localName);
            endBidonesDispenserFC(localName);
            endInactividad(localName);
            endAlquiler(localName);
            endCliente(localName);
            endFueraDeRecorrido(localName);
        }


        private void endDireccion(String localName)
        {

            switch (localName)
            {

                ////////////DIRECCION///////////////////////////////


                case "IdDireccion":
                {
                    this.cliente.getDireccion().setIdDireccion(getInt(cadena.toString()));
                    break;
                }

                case "Calle":
                {
                    this.cliente.getDireccion().setCalle(cadena.toString());
                    break;
                }
                case "Entre1":
                {
                    this.cliente.getDireccion().setEntre1(cadena.toString());
                    break;
                }
                case "Entre2":
                {
                    this.cliente.getDireccion().setEntre2(cadena.toString());
                    break;
                }
                case "Numero":
                {
                    this.cliente.getDireccion().setNumero(cadena.toString());
                    break;
                }
                case "Departamento":
                {
                    this.cliente.getDireccion().setDepartamento(cadena.toString());
                    break;
                }
                case "Piso":
                {
                    this.cliente.getDireccion().setPiso(getInt(cadena.toString()));
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
                    this.cliente.getDatos().setId(getInt(cadena.toString()));
                    break;
                }

                case "Nombre":
                {
                    this.cliente.getDatos().setNombre(cadena.toString());
                    break;
                }

                case "Apellido":
                {
                    this.cliente.getDatos().setApellido(cadena.toString());
                    break;
                }

                case "Telefono":
                {
                    this.cliente.getDatos().setTelefono(cadena.toString());
                    break;
                }

                case "IdTipoCliente":
                {
                    this.cliente.getDatos().getTipoCliente().setId(getInt(cadena.toString()));
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
                    this.cliente.getEstadoBidonesDispenserFC().setDispenserFC(getInt(cadena.toString()));
                    break;
                }
                case "Bidones20L":
                {
                    this.cliente.getEstadoBidonesDispenserFC().setBidones20L(getInt(cadena.toString()));
                    break;
                }
                case "Bidones12L":
                {
                    this.cliente.getEstadoBidonesDispenserFC().setBidones12L(getInt(cadena.toString()));
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
                TipoInactivo tipoInactivo = new TipoInactivo(this.activity,id);
                this.cliente.getEstadoInactividad().setTipoInactivo(tipoInactivo);
                break;
                }
                case "UltimoConsumo":
                {
                this.cliente.getEstadoInactividad().setUltimoConsumo(cadena.toString().replace("*","\n"));
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
                    this.datosAlquiler.getAlquileres().setAlquileres6Bidones(getInt(cadena.toString()));
                    break;
                }

                case "Alquileres8Bidones":
                {
                    this.datosAlquiler.getAlquileres().setAlquileres8Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres10Bidones":
                {
                    this.datosAlquiler.getAlquileres().setAlquileres10Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres12Bidones":
                {
                    this.datosAlquiler.getAlquileres().setAlquileres12Bidones(getInt(cadena.toString()));
                    break;
                }

                /*

                case "Alquileres6BidonesPagados":
                {
                    this.datosAlquiler.getAlquileresPagados().setAlquileres6Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres8BidonesPagados":
                {
                    this.datosAlquiler.getAlquileresPagados().setAlquileres8Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres10BidonesPagados":
                {
                    this.datosAlquiler.getAlquileresPagados().setAlquileres10Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Alquileres12BidonesPagados":
                {
                    this.datosAlquiler.getAlquileresPagados().setAlquileres12Bidones(getInt(cadena.toString()));
                    break;
                }
                case "Bidones20LEntregados":
                {
                    this.datosAlquiler.getRetornablesEntregados().setBidones20L(getInt(cadena.toString()));
                    break;
                }
                case "Bidones12LEntregados":
                {
                    this.datosAlquiler.getRetornablesEntregados().setBidones12L(getInt(cadena.toString()));
                    break;
                }


                */


                case "Alquiler6Bidones_PrecioEspecial":
                {
                    this.precioEspecialAlquiler.setAlquiler6Bidones(getFloat(cadena.toString()));
                    break;
                }

                case "Alquiler8Bidones_PrecioEspecial":
                {
                    this.precioEspecialAlquiler.setAlquiler8Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "Alquiler10Bidones_PrecioEspecial":
                {
                    this.precioEspecialAlquiler.setAlquiler10Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "Alquiler12Bidones_PrecioEspecial":
                {
                    this.precioEspecialAlquiler.setAlquiler12Bidones(getFloat(cadena.toString()));
                    break;
                }
                case "PrecioEspecialAlquiler":
                {
                    this.datosAlquiler.setPrecioAlquileres(this.precioEspecialAlquiler);
                    break;
                }

                case "Alquiler":
                {
                    this.cliente.setDatosAlquiler(this.datosAlquiler);
                    break;
                }




                default:{break;}
            }

        }


        private void endCliente(String localName)
        {
            switch (localName)
            {
                case "DatosCliente":
                {
                    this.reparto.setCliente(this.cliente);
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
                    this.reparto.setPrecioEspecial(this.precioEspecialProductos);
                    break;
                }


                default:{break;}
            }

        }


        private void endVentaProductos(String localName)
        {

            switch (localName)
            {


                ////////////////////////VENTA PRODUCTOS///////////////////////////////


                case "Bidones20L_VP":
                {
                    this.ventaProductos.getRetornables().setBidones20L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones12L_VP":
                {
                    this.ventaProductos.getRetornables().setBidones12L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones10L_VP":
                {
                    this.ventaProductos.getDescartables().setBidones10L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones8L_VP":
                {
                    this.ventaProductos.getDescartables().setBidones8L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones5L_VP":
                {
                    this.ventaProductos.getDescartables().setBidones5L(getInt(cadena.toString()));
                    break;
                }

                case "PackBotellas2L_VP":
                {
                    this.ventaProductos.getDescartables().setPackBotellas2L(getInt(cadena.toString()));
                    break;
                }

                case "PackBotellas500mL_VP":
                {
                    this.ventaProductos.getDescartables().setPackBotellas500mL(getInt(cadena.toString()));
                    break;
                }


                case "Bidones20LBonificados_VP":
                {
                    this.ventaProductos.getRetornablesBonificados().setBidones20L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones12LBonificados_VP":
                {
                    this.ventaProductos.getRetornablesBonificados().setBidones12L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones10LBonificados_VP":
                {
                    this.ventaProductos.getDescartablesBonificados().setBidones10L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones8LBonificados_VP":
                {
                    this.ventaProductos.getDescartablesBonificados().setBidones8L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones5LBonificados_VP":
                {
                    this.ventaProductos.getDescartablesBonificados().setBidones5L(getInt(cadena.toString()));
                    break;
                }

                case "PackBotellas2LBonificados_VP":
                {
                    this.ventaProductos.getDescartablesBonificados().setPackBotellas2L(getInt(cadena.toString()));
                    break;
                }

                case "PackBotellas500mLBonificados_VP":
                {
                    this.ventaProductos.getDescartablesBonificados().setPackBotellas500mL(getInt(cadena.toString()));
                    break;
                }

                case "VentaProductos":
                {
                    this.reparto.setVentaProductos(this.ventaProductos);
                    break;
                }

                default:{break;}
            }

        }


        private void endFueraDeRecorrido(String localName)
        {

            switch (localName)
            {

            case "IdTipoFueraDeRecorrido":
                {
                TipoFueraDeRecorrido tipoFueraDeRecorrido = new TipoFueraDeRecorrido(this.activity,getInt(cadena.toString()));
                this.reparto.getFueraDeRecorrido().setTipoFueraDeRecorrido(tipoFueraDeRecorrido);
                break;
                }

            case "Mensaje":
                {
                this.reparto.getFueraDeRecorrido().setMensaje(cadena.toString());
                break;
                }

            case "FueraDeRecorrido":
                {
                break;
                }

            default:{break;}
            }

        }





        private void endReparto(String localName)
        {

            switch (localName)
            {


                case "Reparto":
                {
                    this.repartoXML.setReparto(this.reparto);
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
