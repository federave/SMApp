package com.federavesm.smapp.modelo.diaRepartidor.reparto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Convertidor;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.Cliente;
import com.federavesm.smapp.modelo.diaRepartidor.clientes.tipoInactivo.TipoInactivo;
import com.federavesm.smapp.modelo.diaRepartidor.otros.Dispensers;
import com.federavesm.smapp.modelo.diaRepartidor.otros.Vertedores;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.dispenser.CambioDispensers;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.dispenser.EntregaDispensers;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.dispenser.RetiroDispensers;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.dispenser.VentaDispensers;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.vertedores.CambioVertedores;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.vertedores.EntregaVertedores;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.vertedores.VentaVertedores;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido.FueraDeRecorrido;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioProductos;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler.Alquiler;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.deudaProductos.DeudaProductos;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.fueraDeRecorrido.TipoFueraDeRecorrido;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.otros.Observacion;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.otros.Vacios;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.tipoVisita.TipoVisita;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.vendedor.Vendedor;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos.VentaProductos;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

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

public  class Reparto extends GenericoReparto {



    public Reparto(Context context)
    {
    super(context);
    this.ventaProductos = new VentaProductos(context);
    this.alquiler = new Alquiler(context);
    this.deudaProductos = new DeudaProductos(context);
    this.vacios = new Vacios(context);
    this.observacion = new Observacion(context);
    this.vendedor = new Vendedor(context);
    this.tipoVisita = new TipoVisita(context);
    this.fueraDeRecorrido = new FueraDeRecorrido(context);
    this.cliente = new Cliente(context);
    this.ventaDispensers = new VentaDispensers(context);
    this.cambioDispensers = new CambioDispensers(context);
    this.entregaDispensers = new EntregaDispensers(context);
    this.retiroDispensers = new RetiroDispensers(context);
    this.ventaVertedores = new VentaVertedores(context);
    this.cambioVertedores = new CambioVertedores(context);
    this.entregaVertedores = new EntregaVertedores(context);

    }





    private Cliente cliente;
    private VentaProductos ventaProductos;
    private DeudaProductos deudaProductos;
    private Alquiler alquiler;
    private Vacios vacios;
    private TipoVisita tipoVisita;
    private Observacion observacion;
    private Vendedor vendedor;
    private FueraDeRecorrido fueraDeRecorrido;


    private VentaDispensers ventaDispensers;
    private CambioDispensers cambioDispensers;
    private EntregaDispensers entregaDispensers;
    private RetiroDispensers retiroDispensers;
    private VentaVertedores ventaVertedores;
    private CambioVertedores cambioVertedores;
    private EntregaVertedores entregaVertedores;



    private Retornables retornablesRepartidos = new Retornables();
    private Descartables descartablesRepartidos = new Descartables();

    private Vertedores vertedoresRepartidos = new Vertedores();
    private Dispensers dispensersRepartidos = new Dispensers();


    private Vertedores vertedoresCambiados = new Vertedores();
    private Dispensers dispensersCambiados = new Dispensers();

    private Dispensers dispensersRetirados = new Dispensers();


    public boolean getValePositivo()
    {
    if((this.retornablesRepartidos.getBidones20L() - this.vacios.getRetornables().getBidones20L() > 0) || (this.retornablesRepartidos.getBidones12L() - this.vacios.getRetornables().getBidones12L() > 0) )
        return true;
    else
        return false;
    }

    public String getValePositivoBidon20L()
    {
    int n = this.retornablesRepartidos.getBidones20L() - this.vacios.getRetornables().getBidones20L();
    if(n > 0)
        return "Bidones 20L: "+n;
    else
        return "";
    }

    public String getValePositivoBidon12L()
    {
    int n = this.retornablesRepartidos.getBidones12L() - this.vacios.getRetornables().getBidones12L();
    if(n > 0)
        return "Bidones 12L: "+n;
    else
        return "";
    }

    public String getValeNegativoBidon20L()
    {
        int n = this.retornablesRepartidos.getBidones20L() - this.vacios.getRetornables().getBidones20L();
        if(n < 0)
            return "Bidones 20L: "+(-1*n);
        else
            return "";
    }

    public String getValeNegativoBidon12L()
    {
        int n = this.retornablesRepartidos.getBidones12L() - this.vacios.getRetornables().getBidones12L();
        if(n < 0)
            return "Bidones 12L: "+(-1*n);
        else
            return "";
    }



    public boolean getValeNegativo()
    {
    if((this.retornablesRepartidos.getBidones20L() - this.vacios.getRetornables().getBidones20L() < 0) || (this.retornablesRepartidos.getBidones12L() - this.vacios.getRetornables().getBidones12L() < 0) )
        return true;
    else
        return false;
    }



    private boolean enviado = false;

    public boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado)
    {
    this.enviado = enviado;
    try
        {
        boolean aux=true;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues reparto = new ContentValues();
        reparto.put("enviado",Convertidor.toInteger(this.enviado));
        String whereClause = "id=?";
        String whereArgs[] = {String.valueOf(this.id)};
        if (!(db.update("Repartos", reparto, whereClause, whereArgs) > 0))
            {
            aux = false;
            }
        db.close();
        }
    catch (Exception e)
        {
        }
    }

    @Override
    public String getXMLToSend()
    {
    XML xml = new XML();

    xml.startTag("Reparto");

        xml.addTag("IdCliente",String.valueOf(this.cliente.getDatos().getId()));
        xml.addTag("IdDireccion",String.valueOf(this.cliente.getDireccion().getIdDireccion()));

        xml.addValue(ventaProductos.getXMLToSend());
        xml.addValue(deudaProductos.getXMLToSend());
        xml.addValue(alquiler.getXMLToSend());
        xml.addValue(vacios.getXMLToSend());
        xml.addValue(tipoVisita.getXMLToSend());
        xml.addValue(observacion.getXMLToSend());
        xml.addValue(ventaDispensers.getXMLToSend());
        xml.addValue(cambioDispensers.getXMLToSend());
        xml.addValue(entregaDispensers.getXMLToSend());
        xml.addValue(retiroDispensers.getXMLToSend());
        xml.addValue(ventaVertedores.getXMLToSend());
        xml.addValue(cambioVertedores.getXMLToSend());
        xml.addValue(entregaVertedores.getXMLToSend());


    xml.closeTag("Reparto");

    return xml.getXML();
    }


    @Override
    public void copiar(Object object)
    {
    try
        {
        Reparto reparto = (Reparto)object;
        this.id = reparto.getId();
        this.idDiaRepartidor = reparto.getIdDiaRepartidor();
        this.cliente.copiar(reparto.getCliente());
        this.ventaProductos.copiar(reparto.getVentaProductos());
        this.deudaProductos.copiar(reparto.getDeudaProductos());
        this.alquiler.copiar(reparto.getAlquiler());
        this.vacios.copiar(reparto.getVacios());
        this.tipoVisita.copiar(reparto.getTipoVisita());
        this.observacion.copiar(reparto.getObservacion());
        this.vendedor.copiar(reparto.getVendedor());
        this.fueraDeRecorrido.copiar(reparto.getFueraDeRecorrido());
        this.retornablesRepartidos.copiar(reparto.getRetornablesRepartidos());
        this.descartablesRepartidos.copiar(reparto.getDescartablesRepartidos());
        this.enviado = reparto.getEnviado();

        this.ventaDispensers.copiar(reparto.getVentaDispensers());
        this.cambioDispensers.copiar(reparto.getCambioDispensers());
        this.entregaDispensers.copiar(reparto.getEntregaDispensers());
        this.retiroDispensers.copiar(reparto.getRetiroDispensers());
        this.ventaVertedores.copiar(reparto.getVentaVertedores());
        this.entregaVertedores.copiar(reparto.getEntregaVertedores());
        this.cambioVertedores.copiar(reparto.getCambioVertedores());



        }
    catch (Exception e)
        {

        }
    }

    @Override
    public Object getCopia()
    {
    Reparto reparto = new Reparto(context);
    reparto.copiar(this);
    return reparto;
    }





    public boolean getCoincidencia(String cadena)
    {
    if((this.cliente.getDatos().toString().indexOf(cadena) != -1) || (this.cliente.getDireccion().toString().indexOf(cadena) != -1))
        {
        return true;
        }
    else
        {
        return false;
        }
    }



    public void setPrecioEspecial(PrecioProductos precioProductos)
    {
    this.cliente.setPrecioProductos(precioProductos);
    }


    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Repartos WHERE id="+"'"+this.id+"'",null);
        boolean aux=false;
        if(cursor.moveToFirst())
            {
            aux=true;
            this.idDiaRepartidor = cursor.getInt(1);
            this.cliente.setId(cursor.getInt(2));
            this.cliente.setIdDiaRepartidor(this.idDiaRepartidor);
            this.cliente.setFecha(Comunicador.getDiaRepartidor().getFecha());

            this.vendedor.setId(cursor.getInt(3));
            this.ventaProductos.setId(cursor.getInt(4));
            this.deudaProductos.setId(cursor.getInt(5));
            this.alquiler.setId(cursor.getInt(6));
            this.vacios.setId(cursor.getInt(7));
            this.tipoVisita.setId(cursor.getInt(8));
            this.fueraDeRecorrido.setId(cursor.getInt(9));
            this.enviado = Convertidor.toBoolean(cursor.getInt(10));
            this.observacion.setId(cursor.getInt(11));

            this.ventaVertedores.setId(cursor.getInt(12));
            this.entregaVertedores.setId(cursor.getInt(13));
            this.cambioVertedores.setId(cursor.getInt(14));
            this.ventaDispensers.setId(cursor.getInt(15));
            this.entregaDispensers.setId(cursor.getInt(16));
            this.cambioDispensers.setId(cursor.getInt(17));
            this.retiroDispensers.setId(cursor.getInt(18));




            aux&=this.cliente.cargar();
            Comunicador.setReparto(this);
            aux&=this.ventaProductos.cargar();
            aux&=this.deudaProductos.cargar();
            aux&=this.alquiler.cargar();
            aux&=this.vacios.cargar();
            aux&=this.observacion.cargar();
            aux&=this.fueraDeRecorrido.cargar();
            aux&=this.tipoVisita.cargar();
            aux&=this.ventaVertedores.cargar();
            aux&=this.entregaVertedores.cargar();
            aux&=this.cambioVertedores.cargar();
            aux&=this.ventaDispensers.cargar();
            aux&=this.entregaDispensers.cargar();
            aux&=this.cambioDispensers.cargar();
            aux&=this.retiroDispensers.cargar();

            this.auxVisita = (TipoVisita) this.tipoVisita.getCopia();
            actualizarInterno();
            setEstadosTipoVisita();


            this.cliente.getDatosAlquiler().getEstadoAlquiler().restarRetornablesEntregadosDinamico(this.alquiler.getRetornables());



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
        boolean aux=true;



        if(this.ventaProductos.have())
            {
            aux&=this.ventaProductos.guardar();
            }

        if(this.fueraDeRecorrido.getEstado())
            {
            aux&=this.fueraDeRecorrido.guardar();
            }

        SQLiteDatabase db = getWritableDatabase();

        ContentValues reparto = new ContentValues();
        reparto.put("idDiaRepartidor",this.idDiaRepartidor);
        reparto.put("idDatosCliente",this.cliente.getId());
        reparto.put("idVendedor",this.vendedor.getId());
        reparto.put("idVentaProductos",this.ventaProductos.getId());
        reparto.put("idDeudaProductos",this.deudaProductos.getId());
        reparto.put("idAlquiler",this.alquiler.getId());
        reparto.put("idVacios",this.vacios.getId());
        reparto.put("idVisita",this.tipoVisita.getId());
        reparto.put("idFueraDeRecorrido",this.fueraDeRecorrido.getId());
        reparto.put("enviado",0);
        reparto.put("idObservacion",this.observacion.getId());

        reparto.put("idVentaVertedores",this.ventaVertedores.getId());
        reparto.put("idEntregaVertedores",this.entregaVertedores.getId());
        reparto.put("idCambioVertedores",this.cambioVertedores.getId());
        reparto.put("idVentaDispensers",this.ventaDispensers.getId());
        reparto.put("idEntregaDispensers",this.entregaDispensers.getId());
        reparto.put("idCambioDispensers",this.cambioDispensers.getId());
        reparto.put("idRetiroDispensers",this.retiroDispensers.getId());


        if(db.insert("Repartos",null,reparto) > 0)
            {
            this.id=getLastId("Repartos");
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

    boolean aux = true;

    aux&=this.cliente.eliminar();
    aux&=this.ventaProductos.eliminar();
    aux&=this.deudaProductos.eliminar();
    aux&=this.alquiler.eliminar();
    aux&=this.vacios.eliminar();
    aux&=this.observacion.eliminar();
    aux&=this.fueraDeRecorrido.eliminar();
    aux&=this.ventaVertedores.eliminar();
    aux&=this.entregaVertedores.eliminar();
    aux&=this.cambioVertedores.eliminar();
    aux&=this.ventaDispensers.eliminar();
    aux&=this.entregaDispensers.eliminar();
    aux&=this.cambioDispensers.eliminar();
    aux&=this.retiroDispensers.eliminar();

    try {
        SQLiteDatabase db = getWritableDatabase();
        if(db.delete("Repartos", "id=" + "'" + this.id + "'", null)>0)
            {db.close();
            return aux;}
        else
            {db.close();
            return false;}
    }
    catch (Exception e)
        {
        return false;
        }

    }


    private void actualizarInterno()
    {
    int bidones20LVentaProductos = this.ventaProductos.getRetornables().getBidones20L() + this.ventaProductos.getRetornablesBonificados().getBidones20L();
    int bidones20LDeudaProductos = this.deudaProductos.getRetornables().getBidones20L();
    int bidones20LAlquiler = this.alquiler.getRetornables().getBidones20L() + this.alquiler.getExcedenteAlquiler().getRetornables().getBidones20L() + this.alquiler.getExcedenteAlquiler().getRetornablesDeudados().getBidones20L();
    int bidones20LRepartidos = bidones20LVentaProductos + bidones20LDeudaProductos + bidones20LAlquiler;

    int bidones12LVentaProductos = this.ventaProductos.getRetornables().getBidones12L() + this.ventaProductos.getRetornablesBonificados().getBidones12L();
    int bidones12LDeudaProductos = this.deudaProductos.getRetornables().getBidones12L();
    int bidones12LAlquiler = this.alquiler.getRetornables().getBidones12L() + this.alquiler.getExcedenteAlquiler().getRetornables().getBidones12L() + this.alquiler.getExcedenteAlquiler().getRetornablesDeudados().getBidones12L();
    int bidones12LRepartidos = bidones12LVentaProductos + bidones12LDeudaProductos + bidones12LAlquiler;

    this.retornablesRepartidos.setBidones20L(bidones20LRepartidos);
    this.retornablesRepartidos.setBidones12L(bidones12LRepartidos);

    this.descartablesRepartidos.setBidones10L(this.ventaProductos.getDescartables().getBidones10L()+this.ventaProductos.getDescartablesBonificados().getBidones10L()+this.deudaProductos.getDescartables().getBidones10L());
    this.descartablesRepartidos.setBidones8L(this.ventaProductos.getDescartables().getBidones8L()+this.ventaProductos.getDescartablesBonificados().getBidones8L()+this.deudaProductos.getDescartables().getBidones8L());
    this.descartablesRepartidos.setBidones5L(this.ventaProductos.getDescartables().getBidones5L()+this.ventaProductos.getDescartablesBonificados().getBidones5L()+this.deudaProductos.getDescartables().getBidones5L());
    this.descartablesRepartidos.setPackBotellas2L(this.ventaProductos.getDescartables().getPackBotellas2L()+this.ventaProductos.getDescartablesBonificados().getPackBotellas2L()+this.deudaProductos.getDescartables().getPackBotellas2L());
    this.descartablesRepartidos.setPackBotellas500mL(this.ventaProductos.getDescartables().getPackBotellas500mL()+this.ventaProductos.getDescartablesBonificados().getPackBotellas500mL()+this.deudaProductos.getDescartables().getPackBotellas500mL());




    int vertedoresRep = this.ventaVertedores.getCantidad()+this.entregaVertedores.getCantidad();
    int dispensersRep = this.ventaDispensers.getCantidad()+this.entregaDispensers.getCantidad();

    this.vertedoresRepartidos.setCantidad(vertedoresRep);
    this.dispensersRepartidos.setCantidad(dispensersRep);


    int vertedoresCamb = this.cambioVertedores.getCantidad();
    int dispensersCamb = this.cambioDispensers.getCantidad();

    this.vertedoresCambiados.setCantidad(vertedoresCamb);
    this.dispensersCambiados.setCantidad(dispensersCamb);

    this.dispensersRetirados.setCantidad(this.retiroDispensers.getCantidad());




    }

    @Override
    public boolean actualizar()
    {
    actualizarVisita();
    actualizarInterno();
    setEstadosTipoVisita();
    Comunicador.getDiaRepartidor().getRepartos().actualizar();
    return true;
    }


    public boolean getEstadoClienteAtendido()
    {
    return this.ventaProductos.have() || this.deudaProductos.have() || this.vacios.have() || this.alquiler.have() || this.alquiler.getPagoAlquiler().have() || this.ventaVertedores.have() || this.entregaVertedores.have() || this.cambioVertedores.have() || this.ventaDispensers.have() || this.entregaDispensers.have() || this.cambioDispensers.have() || this.retiroDispensers.have();
    }

    public void setEstadosTipoVisita()
    {

    if(this.alquiler.getPagoAlquiler().have())
        {
        this.tipoVisita.setPagoAlquiler(true);
        }
    else
        {
        this.tipoVisita.setPagoAlquiler(false);
        }


    if(this.ventaProductos.have() || this.deudaProductos.have() || this.alquiler.have())
        {
        this.tipoVisita.setEntrega(true);
        }
    else
        {
        this.tipoVisita.setEntrega(false);
        }


    if(this.vacios.have())
        {
        this.tipoVisita.setVacios(true);
        }
    else
        {
        this.tipoVisita.setVacios(false);
        }


    if(this.ventaVertedores.have() || this.entregaVertedores.have() || this.cambioVertedores.have())
        {
        this.tipoVisita.setVertedores(true);
        }
    else
        {
        this.tipoVisita.setVertedores(false);
        }


    if(this.ventaDispensers.have() || this.entregaDispensers.have() || this.cambioDispensers.have() || this.retiroDispensers.have())
        {
        this.tipoVisita.setDispensers(true);
        }
    else
        {
        this.tipoVisita.setDispensers(false);
        }



    }




    private TipoVisita auxVisita;
    private boolean visitaCambio=false;

    private void actualizarVisita()
    {
    if(getEstadoClienteAtendido())
        {
        this.tipoVisita.setId(1);
        }
    else
        {
        if(this.tipoVisita.getId() == 1)
            {
            this.tipoVisita.setId(2);
            }
        }
    verificarVisitaCambio();
    }

    private void verificarVisitaCambio()
    {
    if(!this.tipoVisita.equals(auxVisita))
        {
        this.visitaCambio=true;
        }
    else
        {
        this.visitaCambio=false;
        }
    }

    public boolean getVisitaCambio()
    {
        return this.visitaCambio;
    }


    public void limpiarVisitaCambio() {
        this.visitaCambio = false;
        this.auxVisita=(TipoVisita)this.tipoVisita.getCopia();
    }





    @Override
    public boolean modificar()
    {
    try
        {

        if(getEstadoClienteAtendido())
            {
            this.tipoVisita.setId(1);
            }

        setEstadosTipoVisita();



        boolean aux=true;

        SQLiteDatabase db = getWritableDatabase();
        ContentValues reparto = new ContentValues();
        reparto.put("idDiaRepartidor",this.idDiaRepartidor);
        reparto.put("idDatosCliente",this.cliente.getId());
        reparto.put("idVendedor",this.vendedor.getId());
        reparto.put("idVentaProductos",this.ventaProductos.getId());
        reparto.put("idDeudaProductos",this.deudaProductos.getId());
        reparto.put("idAlquiler",this.alquiler.getId());
        reparto.put("idVacios",this.vacios.getId());
        reparto.put("idVisita",this.tipoVisita.getId());
        reparto.put("idFueraDeRecorrido",this.fueraDeRecorrido.getId());
        reparto.put("enviado",0);//nuevo
        this.enviado = false;
        reparto.put("idObservacion",this.observacion.getId());
        reparto.put("idVentaVertedores",this.ventaVertedores.getId());
        reparto.put("idEntregaVertedores",this.entregaVertedores.getId());
        reparto.put("idCambioVertedores",this.cambioVertedores.getId());
        reparto.put("idVentaDispensers",this.ventaDispensers.getId());
        reparto.put("idEntregaDispensers",this.entregaDispensers.getId());
        reparto.put("idCambioDispensers",this.cambioDispensers.getId());
        reparto.put("idRetiroDispensers",this.retiroDispensers.getId());




        String whereClause = "id=?";
        String whereArgs[] = {String.valueOf(this.id)};

        if (!(db.update("Repartos", reparto, whereClause, whereArgs) > 0))
            {
            aux = false;
            }

        db.close();
        verificarVisitaCambio();
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
    boolean aux = this.ventaProductos.getEstado() || this.deudaProductos.getEstado() || this.alquiler.getEstado() || this.ventaVertedores.getEstado() || this.entregaVertedores.getEstado() || this.cambioVertedores.getEstado() || this.ventaDispensers.getEstado() || this.entregaDispensers.getEstado() || this.cambioDispensers.getEstado() || this.retiroDispensers.getEstado();
    return aux;
    }


    @Override
    public boolean evaluar() {
        return false;
    }

    @Override
    public String getEvaluar() {
        return "";
    }






    ////////////////////////////////////////////////////////////////////////////////////
    /////////////ADICIONAR



    public void adicionar(String xml)
    {
        try
        {
            factory = SAXParserFactory.newInstance();
            parser = factory.newSAXParser();
            this.reader = parser.getXMLReader();
            this.reader.setContentHandler(new DatosRepartoXML());
            this.reader.parse(new InputSource(new StringReader(xml)));

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




    class DatosRepartoXML extends DefaultHandler
    {

        public DatosRepartoXML()
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


            startVendedor(localName);
            startVentaProductos(localName);

        }

        private void startVentaProductos(String localName)
        {
            switch (localName)
            {
                case "VentaProductos":
                {
                    break;
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


            endVendedor(localName);
            endVentaProductos(localName);
            endFueraDeRecorrido(localName);


        }






        private void endVentaProductos(String localName)
        {

            switch (localName)
            {


                ////////////////////////VENTA PRODUCTOS///////////////////////////////


                case "Bidones20L_VP":
                {
                    ventaProductos.getRetornables().setBidones20L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones12L_VP":
                {
                    ventaProductos.getRetornables().setBidones12L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones10L_VP":
                {
                    ventaProductos.getDescartables().setBidones10L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones8L_VP":
                {
                    ventaProductos.getDescartables().setBidones8L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones5L_VP":
                {
                    ventaProductos.getDescartables().setBidones5L(getInt(cadena.toString()));
                    break;
                }

                case "PackBotellas2L_VP":
                {
                    ventaProductos.getDescartables().setPackBotellas2L(getInt(cadena.toString()));
                    break;
                }

                case "PackBotellas500mL_VP":
                {
                    ventaProductos.getDescartables().setPackBotellas500mL(getInt(cadena.toString()));
                    break;
                }


                case "Bidones20LBonificados_VP":
                {
                    ventaProductos.getRetornablesBonificados().setBidones20L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones12LBonificados_VP":
                {
                    ventaProductos.getRetornablesBonificados().setBidones12L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones10LBonificados_VP":
                {
                    ventaProductos.getDescartablesBonificados().setBidones10L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones8LBonificados_VP":
                {
                    ventaProductos.getDescartablesBonificados().setBidones8L(getInt(cadena.toString()));
                    break;
                }

                case "Bidones5LBonificados_VP":
                {
                    ventaProductos.getDescartablesBonificados().setBidones5L(getInt(cadena.toString()));
                    break;
                }

                case "PackBotellas2LBonificados_VP":
                {
                    ventaProductos.getDescartablesBonificados().setPackBotellas2L(getInt(cadena.toString()));
                    break;
                }

                case "PackBotellas500mLBonificados_VP":
                {
                    ventaProductos.getDescartablesBonificados().setPackBotellas500mL(getInt(cadena.toString()));
                    break;
                }

                case "VentaProductos":
                {
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
                    TipoFueraDeRecorrido tipoFueraDeRecorrido = new TipoFueraDeRecorrido(activity,getInt(cadena.toString()));
                    fueraDeRecorrido.setTipoFueraDeRecorrido(tipoFueraDeRecorrido);
                    break;
                }

                case "Mensaje":
                {
                    fueraDeRecorrido.setMensaje(cadena.toString());
                    break;
                }

                case "FueraDeRecorrido":
                {
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
                    vendedor = new Vendedor(activity,getInt(cadena.toString()));
                    break;
                }
                case "Vendedor":
                {
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



























    public Vacios getVacios() {
        return vacios;
    }

    public void setVacios(Vacios vacios) {
        this.vacios = vacios;
    }

    public Observacion getObservacion() {
        return observacion;
    }

    public void setObservacion(Observacion observacion) {
        this.observacion = observacion;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }


    public TipoVisita getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(TipoVisita tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    public FueraDeRecorrido getFueraDeRecorrido() {
        return fueraDeRecorrido;
    }

    public void setFueraDeRecorrido(FueraDeRecorrido fueraDeRecorrido) {
        this.fueraDeRecorrido = fueraDeRecorrido;
    }



    public Cliente getCliente() {return cliente;}
    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public VentaProductos getVentaProductos(){return ventaProductos;}
    public void setVentaProductos(VentaProductos ventaProductos){this.ventaProductos = ventaProductos;}

    public Alquiler getAlquiler() {return alquiler;}

    public void setAlquiler(Alquiler alquiler) {this.alquiler = alquiler;}

    public DeudaProductos getDeudaProductos() {
        return deudaProductos;
    }

    public void setDeudaProductos(DeudaProductos deudaProductos) {
        this.deudaProductos = deudaProductos;
    }

    public Retornables getRetornablesRepartidos() {return retornablesRepartidos;}

    public void setRetornablesRepartidos(Retornables retornablesRepartidos) {this.retornablesRepartidos = retornablesRepartidos;}

    public Descartables getDescartablesRepartidos() {return descartablesRepartidos; }

    public void setDescartablesRepartidos(Descartables descartablesRepartidos) {this.descartablesRepartidos = descartablesRepartidos;}

    public VentaDispensers getVentaDispensers() {
        return ventaDispensers;
    }

    public void setVentaDispensers(VentaDispensers ventaDispensers) {
        this.ventaDispensers = ventaDispensers;
    }

    public CambioDispensers getCambioDispensers() {
        return cambioDispensers;
    }

    public void setCambioDispensers(CambioDispensers cambioDispensers) {
        this.cambioDispensers = cambioDispensers;
    }

    public EntregaDispensers getEntregaDispensers() {
        return entregaDispensers;
    }

    public void setEntregaDispensers(EntregaDispensers entregaDispensers) {
        this.entregaDispensers = entregaDispensers;
    }

    public RetiroDispensers getRetiroDispensers() {
        return retiroDispensers;
    }

    public void setRetiroDispensers(RetiroDispensers retiroDispensers) {
        this.retiroDispensers = retiroDispensers;
    }

    public VentaVertedores getVentaVertedores() {
        return ventaVertedores;
    }

    public void setVentaVertedores(VentaVertedores ventaVertedores) {
        this.ventaVertedores = ventaVertedores;
    }

    public CambioVertedores getCambioVertedores() {
        return cambioVertedores;
    }

    public void setCambioVertedores(CambioVertedores cambioVertedores) {
        this.cambioVertedores = cambioVertedores;
    }

    public EntregaVertedores getEntregaVertedores() {
        return entregaVertedores;
    }

    public void setEntregaVertedores(EntregaVertedores entregaVertedores) {
        this.entregaVertedores = entregaVertedores;
    }


    public Vertedores getVertedoresRepartidos() {
        return vertedoresRepartidos;
    }

    public void setVertedoresRepartidos(Vertedores vertedoresRepartidos) {
        this.vertedoresRepartidos = vertedoresRepartidos;
    }

    public Dispensers getDispensersRepartidos() {
        return dispensersRepartidos;
    }

    public void setDispensersRepartidos(Dispensers dispensersRepartidos) {
        this.dispensersRepartidos = dispensersRepartidos;
    }


    public Vertedores getVertedoresCambiados() {
        return vertedoresCambiados;
    }

    public void setVertedoresCambiados(Vertedores vertedoresCambiados) {
        this.vertedoresCambiados = vertedoresCambiados;
    }

    public Dispensers getDispensersCambiados() {
        return dispensersCambiados;
    }

    public void setDispensersCambiados(Dispensers dispensersCambiados) {
        this.dispensersCambiados = dispensersCambiados;
    }


    public Dispensers getDispensersRetirados() {
        return dispensersRetirados;
    }

    public void setDispensersRetirados(Dispensers dispensersRetirados) {
        this.dispensersRetirados = dispensersRetirados;
    }
}
