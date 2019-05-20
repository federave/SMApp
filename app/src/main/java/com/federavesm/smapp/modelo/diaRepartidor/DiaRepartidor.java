package com.federavesm.smapp.modelo.diaRepartidor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Cargamento;
import com.federavesm.smapp.modelo.diaRepartidor.diaEnviado.DiaEnviado;
import com.federavesm.smapp.modelo.diaRepartidor.gastos.Gasto;
import com.federavesm.smapp.modelo.diaRepartidor.gastos.Gastos;
import com.federavesm.smapp.modelo.diaRepartidor.precios.Precios;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Repartos;


/**
 * Created by Federico on 5/1/2018.
 */

public class DiaRepartidor extends GenericoReparto
{


    public DiaRepartidor(Context context)
    {
    super(context);
    this.repartos = new Repartos(context);
    this.precios = new Precios(context);
    this.cargamento = new Cargamento(context);
    this.gastos = new Gastos(context);
    this.diaEnviado = new DiaEnviado(context);
    }


    protected int idRepartidor;
    protected Fecha fecha;

    private Repartos repartos;
    private Precios precios;
    private boolean enviado;
    private Cargamento cargamento;
    private Gastos gastos;
    private DiaEnviado diaEnviado;


    private float dineroRecaudado;
    private float dineroVentas;
    private float dineroDeudas;
    private float dineroPagoAlquileres;
    private float dineroGastos;

    private float dineroVentaDispensers=0;
    private float dineroVentaVertedores=0;





    private int numeroPagosAlquileres;
    private String remitosPagosAlquileres;


    private int numeroDeudasProductos;
    private String remitosDeudas;


    private int numeroValesPositivos;
    private String valesPositivos;

    private int numeroValesNegativos;
    private String valesNegativos;





    private String boletasGastos;

    private Retornables retornablesRepartidosAlquiler = new Retornables();


    //// Datos Planilla

    private int planillaCantidad=0;
    private int planillaCantidadVisitados=0;
    private int planillaCantidadNoVisitados=0;
    private int planillaCantidadNoEncontrados=0;
    private int planillaCantidadConVenta=0;
    private int planillaCantidadConEntregaAlquiler=0;

    /// Remitos Entrega Alquiler

    private String remitosEntregasAlquileres;

    public String getRemitosEntregasAlquileres() {
        return remitosEntregasAlquileres;
    }

    public int getPlanillaCantidad() {
        return planillaCantidad;
    }

    public int getPlanillaCantidadVisitados() {
        return planillaCantidadVisitados;
    }

    public int getPlanillaCantidadNoVisitados() {
        return planillaCantidadNoVisitados;
    }

    public int getPlanillaCantidadNoEncontrados() {
        return planillaCantidadNoEncontrados;
    }

    public int getPlanillaCantidadConVenta() {
        return planillaCantidadConVenta;
    }

    public int getPlanillaCantidadConEntregaAlquiler() {
        return planillaCantidadConEntregaAlquiler;
    }

    public int getNumeroPagosAlquileres() {
        return numeroPagosAlquileres;
    }

    public String getRemitosPagosAlquileres() {
        return remitosPagosAlquileres;
    }

    public int getNumeroDeudasProductos() {
        return numeroDeudasProductos;
    }

    public String getRemitosDeudas() {
        return remitosDeudas;
    }

    public String getBoletasGastos() {
        return boletasGastos;
    }

    public Retornables getRetornablesRepartidosAlquiler() {
        return retornablesRepartidosAlquiler;
    }

    public float getDineroRecaudado() {
        return dineroRecaudado;
    }

    public float getDineroVentas() {
        return dineroVentas;
    }

    public float getDineroDeudas() {
        return dineroDeudas;
    }

    public float getDineroPagoAlquileres() {
        return dineroPagoAlquileres;
    }

    public float getDineroGastos() {
        return dineroGastos;
    }


    public float getDineroVentaDispensers() {
        return dineroVentaDispensers;
    }

    public float getDineroVentaVertedores() {
        return dineroVentaVertedores;
    }

    public int getNumeroValesPositivos() {
        return numeroValesPositivos;
    }

    public String getValesPositivos() {
        return valesPositivos;
    }

    public int getNumeroValesNegativos() {
        return numeroValesNegativos;
    }

    public String getValesNegativos() {
        return valesNegativos;
    }

    public void cargarDatosInforme()
    {

    this.planillaCantidad = this.repartos.getRepartos().size();
    this.planillaCantidadVisitados = 0;
    this.planillaCantidadNoVisitados = 0;
    this.planillaCantidadNoEncontrados = 0;
    this.planillaCantidadConVenta = 0;
    this.planillaCantidadConEntregaAlquiler = 0;


    this.remitosEntregasAlquileres = "";



    this.dineroRecaudado=0;
    this.dineroVentas=0;
    this.dineroDeudas=0;
    this.dineroPagoAlquileres=0;
    this.dineroGastos=0;

    this.dineroVentaDispensers=0;
    this.dineroVentaVertedores=0;

    this.numeroValesPositivos=0;
    this.valesPositivos="";
    this.numeroValesNegativos=0;
    this.valesNegativos="";


    this.numeroPagosAlquileres=0;
    this.remitosPagosAlquileres="";

    this.numeroDeudasProductos=0;
    this.remitosDeudas="";

    this.boletasGastos="";

    this.retornablesRepartidosAlquiler = new Retornables();


    for(int i=0;i<this.repartos.getRepartos().size();i++)
        {

        Reparto reparto = this.repartos.getRepartos().get(i);
        Comunicador.setReparto(reparto);


        /// Visita

        switch (reparto.getTipoVisita().getId())
        {
        case 1:
            {
            this.planillaCantidadVisitados++;
            break;
            }
        case 3:
            {
            this.planillaCantidadNoEncontrados++;
            break;
            }
        default:
            {
            this.planillaCantidadNoVisitados++;
            break;
            }
        }


        if(reparto.getVentaProductos().have() || reparto.getDeudaProductos().have() || reparto.getAlquiler().getExcedenteAlquiler().have())
            {
            this.planillaCantidadConVenta++;
            }

        if(reparto.getAlquiler().haveEntrega())
            {
            this.planillaCantidadConEntregaAlquiler++;
            this.remitosEntregasAlquileres += "\n\n" + reparto.getCliente().getDatos().toString();
            }


        /// Vales

        if(reparto.getValePositivo())
            {
            this.numeroValesPositivos++;
            this.valesPositivos += "\n\n" + reparto.getCliente().getDatos().toString() + "\n" + reparto.getValePositivoBidon20L() + "\n" + reparto.getValePositivoBidon12L();
            }

        if(reparto.getValeNegativo())
            {
            this.numeroValesNegativos++;
            this.valesNegativos += "\n\n" + reparto.getCliente().getDatos().toString()+ "\n" + reparto.getValeNegativoBidon20L() + "\n" + reparto.getValeNegativoBidon12L();
            }





            /// Dinero Ventas

        float dineroVentasAux=0;
        float dineroVentasProductos=0;
        float dineroVentasExcedenteAlquiler=0;
        float dineroVentasDispensadores=0;

        if(reparto.getVentaProductos().have())
            {
            dineroVentasProductos = reparto.getVentaProductos().getDinero();
            }

        if(reparto.getAlquiler().getExcedenteAlquiler().getRetornables().have())
            {
            dineroVentasExcedenteAlquiler = reparto.getAlquiler().getExcedenteAlquiler().getDineroVenta();
            }


        this.dineroVentaDispensers += reparto.getVentaDispensers().getDinero();
        this.dineroVentaVertedores += reparto.getVentaVertedores().getDinero();

        dineroVentasDispensadores = reparto.getVentaDispensers().getDinero() + reparto.getVentaVertedores().getDinero();

        dineroVentasAux = dineroVentasDispensadores + dineroVentasProductos + dineroVentasExcedenteAlquiler;

        this.dineroVentas +=  dineroVentasAux;


        /// Pagos

        float dineroPagoAlquilerAux=0;

        if(reparto.getAlquiler().getPagoAlquiler().have())
            {
            this.numeroPagosAlquileres++;
            this.remitosPagosAlquileres += "\n\n" + reparto.getCliente().getDatos().toString();
            dineroPagoAlquilerAux = reparto.getAlquiler().getPagoAlquiler().getDineroPagos();
            this.dineroPagoAlquileres += dineroPagoAlquilerAux;
            }

        // Deudas

        float dineroDeudaProductosAux=0;

        if(reparto.getDeudaProductos().have())
            {
            this.numeroDeudasProductos++;
            this.remitosDeudas += "\n\n" + reparto.getCliente().getDatos().toString();
            dineroDeudaProductosAux = reparto.getDeudaProductos().getDinero();
            this.dineroDeudas += dineroDeudaProductosAux;
            }

      if(reparto.getAlquiler().getExcedenteAlquiler().getRetornablesDeudados().have())
            {
            this.numeroDeudasProductos++;
            this.remitosDeudas += "\n\n" + reparto.getCliente().getDatos().toString();
            this.dineroDeudas += reparto.getAlquiler().getExcedenteAlquiler().getDineroDeuda();
            }

        this.retornablesRepartidosAlquiler.setBidones20L(this.retornablesRepartidosAlquiler.getBidones20L() + reparto.getAlquiler().getRetornablesRepartidos().getBidones20L());
        this.retornablesRepartidosAlquiler.setBidones12L(this.retornablesRepartidosAlquiler.getBidones12L() + reparto.getAlquiler().getRetornablesRepartidos().getBidones12L());

        this.dineroRecaudado += dineroVentasAux +  dineroPagoAlquilerAux;
        }

    for(int i=0;i<this.gastos.getGastos().size();i++)
        {
        Gasto gasto = this.gastos.getGastos().get(i);
        this.dineroGastos += gasto.getDinero();
        this.boletasGastos += "\n\n" + gasto.toString();
        }







    }











    @Override
    public boolean guardar()
    {

    try
        {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues diaRepartidor = new ContentValues();
        diaRepartidor.put("idRepartidor",this.idRepartidor);
        diaRepartidor.put("enviado",0);
        diaRepartidor.put("fecha",this.fecha.toString());

        boolean aux = false;

        if(db.insert("DiaRepartidor",null,diaRepartidor) > 0)
            {
            aux = true;
            this.idDiaRepartidor = getLastId("DiaRepartidor");
            this.id = idDiaRepartidor;

            this.precios.setIdDiaRepartidor(this.idDiaRepartidor);
            aux&=this.precios.guardar();

            this.repartos.setIdDiaRepartidor(this.idDiaRepartidor);
            aux&=this.repartos.guardar();

            this.diaEnviado.setIdDiaRepartidor(this.idDiaRepartidor);
            aux&=this.diaEnviado.guardar();


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
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DiaRepartidor WHERE idRepartidor="+"'"+this.idRepartidor+"'"+"AND fecha="+"'"+this.fecha.toString()+"'",null);
        boolean aux = false;
        if(cursor.moveToFirst())
            {
            aux = true;
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        String x=e.toString();
        return false;
        }
    }



    @Override
    public boolean cargar()
    {
    try {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DiaRepartidor WHERE idRepartidor="+"'"+this.idRepartidor+"'"+"AND fecha="+"'"+this.fecha.toString()+"'",null);
        boolean aux = false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.id = cursor.getInt(0);
            this.idDiaRepartidor = this.id;
            this.enviado = (cursor.getInt(2)==1);
            this.repartos.setIdDiaRepartidor(this.idDiaRepartidor);
            this.precios.setIdDiaRepartidor(this.idDiaRepartidor);
            this.cargamento.setIdDiaRepartidor(this.idDiaRepartidor);
            this.gastos.setIdDiaRepartidor(this.idDiaRepartidor);
            this.diaEnviado.setIdDiaRepartidor(this.idDiaRepartidor);

            aux&=this.repartos.cargar();
            aux&=this.precios.cargar();
            aux&=this.cargamento.cargar();
            aux&=this.gastos.cargar();
            aux&=this.diaEnviado.cargar();


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
        boolean aux=true;
        aux&=this.precios.eliminar();
        aux&=this.repartos.eliminar();

        SQLiteDatabase db = getWritableDatabase();
        if(!(db.delete("DiaRepartidor", "id=" + "'" + this.id + "'", null)>0))
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
    public boolean actualizar() {
        return false;
    }

    @Override
    public boolean evaluar() {
        return false;
    }
    @Override
    public String getEvaluar() {
        return "";
    }

    @Override
    public String getXMLToSend() {
        return "";
    }



    public Repartos getRepartos() {
        return repartos;
    }

    public void setRepartos(Repartos repartos) {
        this.repartos = repartos;
    }

    public Precios getPrecios() {
        return precios;
    }

    public void setPrecios(Precios precios) {
        this.precios = precios;
    }


    public boolean getEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public Gastos getGastos() {
        return gastos;
    }

    public void setGastos(Gastos gastos) {
        this.gastos = gastos;
    }

    public Cargamento getCargamento() {
        return cargamento;
    }

    public void setCargamento(Cargamento cargamento) {
        this.cargamento = cargamento;
    }



    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }


    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public DiaEnviado getDiaEnviado() {
        return diaEnviado;
    }

    public void setDiaEnviado(DiaEnviado diaEnviado) {
        this.diaEnviado = diaEnviado;
    }

    @Override
    public boolean modificar() {
        return false;
    }


    @Override
    public Object getCopia() {
        return null;
    }


    @Override
    public void copiar(Object object) {}

}
