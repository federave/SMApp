package com.federavesm.smapp.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Federico on 12/2/2018.
 */



public class BaseDeDatos extends SQLiteOpenHelper
{

    public BaseDeDatos(Context context)
    {
        super(context,"SMApp",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("CREATE TABLE Usuarios" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT," +
                "password TEXT," +
                "administrador INTEGER" +
                ")");



        ContentValues usuario = new ContentValues();
        usuario.put("nombre","admin");
        usuario.put("password","1351");
        usuario.put("administrador",1);

        long x = db.insert("Usuarios",null,usuario);

        usuario = new ContentValues();
        usuario.put("nombre","reparto");
        usuario.put("password","reparto");
        usuario.put("administrador",0);

        long y = db.insert("Usuarios",null,usuario);



        db.execSQL("CREATE TABLE TipoVisita" +
                "(" +
                "id INTEGER," +
                "tipoVisita TEXT" +
                ")");


        db.execSQL("CREATE TABLE TipoInactivo" +
                "(" +
                "id INTEGER," +
                "tipoInactivo TEXT" +
                ")");


        db.execSQL("CREATE TABLE TipoCliente" +
                "(" +
                "id INTEGER," +
                "tipoCliente TEXT" +
                ")");



        db.execSQL("CREATE TABLE TipoFueraDeRecorrido" +
                "(" +
                "id INTEGER," +
                "tipoFueraDeRecorrido TEXT" +
                ")");





        db.execSQL("CREATE TABLE Repartidores" +
                "(" +
                "id INTEGER," +
                "nombre TEXT," +
                "apellido TEXT," +
                "dni TEXT" +
                ")");


        db.execSQL("CREATE TABLE Vendedores" +
                "(" +
                "id INTEGER," +
                "nombre TEXT," +
                "apellido TEXT," +
                "dni TEXT" +
                ")");


        db.execSQL("CREATE TABLE Configuracion" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ipServidor TEXT," +
                "puerto TEXT" +
                ")");


        ContentValues configuracion = new ContentValues();
        configuracion.put("ipServidor","192.168.1.105");
        configuracion.put("puerto","80");

        long x4 = db.insert("Configuracion",null,configuracion);



        db.execSQL("CREATE TABLE DiaRepartidor" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idRepartidor INTEGER," +
                "enviado INTEGER," +
                "fecha TEXT" +
                ")");


        db.execSQL("CREATE TABLE DiaEnviado" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idDiaRepartidor INTEGER," +
                "repartosEnviados INTEGER," +
                "gastosEnviados INTEGER," +
                "cargasEnviadas INTEGER," +
                "descargasEnviadas INTEGER" +
                ")");


        db.execSQL("CREATE TABLE Gastos" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idDiaRepartidor INTEGER," +
                "combustible INTEGER," +
                "combustibleConTarjeta INTEGER," +
                "descripcion INTEGER," +
                "dinero REAL" +
                ")");


        db.execSQL("CREATE TABLE Cargas" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idDiaRepartidor INTEGER," +
                "bidones20L INTEGER," +
                "bidones12L INTEGER," +
                "bidones10L INTEGER," +
                "bidones8L INTEGER," +
                "bidones5L INTEGER," +
                "packBotellas2L INTEGER," +
                "packBotellas500mL INTEGER," +
                "hora TEXT" +
                ")");

        db.execSQL("CREATE TABLE Descargas" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idDiaRepartidor INTEGER," +
                "bidones20L INTEGER," +
                "bidones20LVacios INTEGER," +
                "bidones12L INTEGER," +
                "bidones12LVacios INTEGER," +
                "bidones10L INTEGER," +
                "bidones8L INTEGER," +
                "bidones5L INTEGER," +
                "packBotellas2L INTEGER," +
                "packBotellas500mL INTEGER," +
                "hora TEXT" +
                ")");




        db.execSQL("CREATE TABLE PrecioDiaRepartidor" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idDiaRepartidor INTEGER," +
                "bidon20L REAL," +
                "bidon12L REAL," +
                "bidon10L REAL," +
                "bidon8L REAL," +
                "bidon5L REAL," +
                "packBotellas2L REAL," +
                "packBotellas500mL REAL," +
                "alquiler6Bidones REAL," +
                "alquiler8Bidones REAL," +
                "alquiler10Bidones REAL," +
                "alquiler12Bidones REAL" +
                ")");



        db.execSQL("CREATE TABLE Repartos" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idDiaRepartidor INTEGER," +
                "idDatosCliente INTEGER," +
                "idVendedor INTEGER," +
                "idVentaProductos INTEGER," +
                "idDeudaProductos INTEGER," +
                "idAlquiler INTEGER," +
                "idVacios INTEGER," +
                "idVisita INTEGER," +
                "idFueraDeRecorrido INTEGER," +
                "enviado INTEGER," +
                "idObservacion INTEGER" +
                ")");




        db.execSQL("CREATE TABLE DatosClientes" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idCliente INTEGER," +
                "idDatosDireccion INTEGER," +
                "nombre TEXT," +
                "apellido TEXT," +
                "telefono TEXT," +
                "idTipoCliente TEXT," +
                "idPrecioEspecial INTEGER," +
                "idDatosAlquiler INTEGER" +
                ")");


        db.execSQL("CREATE TABLE EstadoBidonesDispenserFC" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idCliente INTEGER," +
                "dispenserFC INTEGER," +
                "bidones20L INTEGER," +
                "bidones12L INTEGER," +
                "fecha TEXT" +
                ")");



        db.execSQL("CREATE TABLE EstadoInactividad" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idCliente INTEGER," +
                "idInactividad INTEGER," +
                "ultimoConsumo TEXT," +
                "fecha TEXT" +
                ")");




        db.execSQL("CREATE TABLE DireccionCliente" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idDireccion INTEGER," +
                "calle TEXT," +
                "numero TEXT," +
                "entre1 TEXT," +
                "entre2 TEXT," +
                "departamento TEXT," +
                "piso INTEGER" +
                ")");



        db.execSQL("CREATE TABLE PrecioEspecialProductos" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bidon20L REAL," +
                "bidon12L REAL," +
                "bidon10L REAL," +
                "bidon8L REAL," +
                "bidon5L REAL," +
                "packBotellas2L REAL," +
                "packBotellas500mL REAL" +
                ")");



        db.execSQL("CREATE TABLE DatosAlquiler" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idPrecioEspecial INTEGER," +
                "alquileres6Bidones INTEGER," +
                "alquileres8Bidones INTEGER," +
                "alquileres10Bidones INTEGER," +
                "alquileres12Bidones INTEGER" +
                ")");


        db.execSQL("CREATE TABLE EstadoAlquiler" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idAlquiler INTEGER," +
                "alquileres6BidonesPagados INTEGER," +
                "alquileres8BidonesPagados INTEGER," +
                "alquileres10BidonesPagados INTEGER," +
                "alquileres12BidonesPagados INTEGER," +
                "bidones20LEntregados INTEGER," +
                "bidones12LEntregados INTEGER," +
                "fecha TEXT" +
                ")");




        db.execSQL("CREATE TABLE PrecioEspecialAlquiler" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "alquiler6Bidones REAL," +
                "alquiler8Bidones REAL," +
                "alquiler10Bidones REAL," +
                "alquiler12Bidones REAL" +
                ")");



        db.execSQL("CREATE TABLE Observaciones" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "observacion TEXT" +
                ")");


        db.execSQL("CREATE TABLE VentaProductos" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bidones20L INTEGER," +
                "bidones12L INTEGER," +
                "bidones10L INTEGER," +
                "bidones8L INTEGER," +
                "bidones5L INTEGER," +
                "packBotellas2L INTEGER," +
                "packBotellas500mL INTEGER," +
                "bidones20LBonificados INTEGER," +
                "bidones12LBonificados INTEGER," +
                "bidones10LBonificados INTEGER," +
                "bidones8LBonificados INTEGER," +
                "bidones5LBonificados INTEGER," +
                "packBotellas2LBonificados INTEGER," +
                "packBotellas500mLBonificados INTEGER" +
                ")");




        db.execSQL("CREATE TABLE DeudaProductos" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bidones20L INTEGER," +
                "bidones12L INTEGER," +
                "bidones10L INTEGER," +
                "bidones8L INTEGER," +
                "bidones5L INTEGER," +
                "packBotellas2L INTEGER," +
                "packBotellas500mL INTEGER" +
                ")");


        db.execSQL("CREATE TABLE Alquiler" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idExcedente INTEGER," +
                "idPagoAlquiler INTEGER," +
                "bidones20L INTEGER," +
                "bidones12L INTEGER" +
                ")");



        db.execSQL("CREATE TABLE ExcedenteAlquiler" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bidones20L INTEGER," +
                "bidones20LDeudados INTEGER," +
                "bidones12L INTEGER," +
                "bidones12LDeudados INTEGER" +
                ")");


        db.execSQL("CREATE TABLE PagoAlquiler" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "alquileres6Bidones INTEGER," +
                "alquileres8Bidones INTEGER," +
                "alquileres10Bidones INTEGER," +
                "alquileres12Bidones INTEGER" +
                ")");


        db.execSQL("CREATE TABLE Vacios" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "bidones20L INTEGER," +
                "bidones12L INTEGER" +
                ")");


        db.execSQL("CREATE TABLE FueraDeRecorrido" +
                "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idTipoFueraDeRecorrido INTEGER," +
                "mensaje TEXT" +
                ")");





    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        //   db.execSQL("DROP TABLE PrecioDiaRepartidor");


        try {

/*
            db.execSQL("DROP TABLE Configuracion");

            db.execSQL("CREATE TABLE Configuracion" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ipServidor TEXT," +
                    "puerto TEXT" +
                    ")");


            ContentValues configuracion = new ContentValues();
            configuracion.put("ipServidor","192.168.1.105");
            configuracion.put("puerto","80");

            db.insert("Configuracion",null,configuracion);
*/



        }
        catch (Exception e)
        {
            String n=e.toString();
        }



    }





    public boolean clienteExiste(int idcliente,int iddireccion)
    {
    SQLiteDatabase db = getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM DatosClientes AS DC INNER JOIN DireccionCliente AS D " +
    "ON DC.idDatosDireccion=D.id WHERE DC.idCliente="+"'"+idcliente+"'" + "AND D.idDireccion="+"'"+iddireccion+"'",null);
    boolean aux = false;
    if(cursor.moveToFirst())
        {
        aux=true;
        }
    return aux;
    }








    public String getTablas()
    {
        SQLiteDatabase db = getReadableDatabase();
        String tablas="";

        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {

                tablas+=c.getString(0) + "  ";
                c.moveToNext();
            }
        }



        return tablas;
    }







    private boolean eliminarTablasDinamica()
    {

    try
        {

        SQLiteDatabase db = getWritableDatabase();

            db.execSQL("DROP TABLE DiaRepartidor");
            db.execSQL("DROP TABLE DiaEnviado");
            db.execSQL("DROP TABLE Gastos");
            db.execSQL("DROP TABLE Cargas");
            db.execSQL("DROP TABLE Descargas");
            db.execSQL("DROP TABLE PrecioDiaRepartidor");
            db.execSQL("DROP TABLE Repartos");
            db.execSQL("DROP TABLE DatosClientes");
            db.execSQL("DROP TABLE EstadoBidonesDispenserFC");
            db.execSQL("DROP TABLE EstadoInactividad");
            db.execSQL("DROP TABLE DireccionCliente");
            db.execSQL("DROP TABLE PrecioEspecialProductos");
            db.execSQL("DROP TABLE DatosAlquiler");
            db.execSQL("DROP TABLE EstadoAlquiler");
            db.execSQL("DROP TABLE PrecioEspecialAlquiler");
            db.execSQL("DROP TABLE Observaciones");
            db.execSQL("DROP TABLE VentaProductos");
            db.execSQL("DROP TABLE DeudaProductos");
            db.execSQL("DROP TABLE Alquiler");
            db.execSQL("DROP TABLE ExcedenteAlquiler");
            db.execSQL("DROP TABLE PagoAlquiler");
            db.execSQL("DROP TABLE Vacios");
            db.execSQL("DROP TABLE FueraDeRecorrido");

        return true;
        }
    catch (Exception e)
        {
        String n=e.toString();
        return false;
        }


    }

    private boolean crearTablasDinamica()
    {
        try
        {

        SQLiteDatabase db = getWritableDatabase();


            db.execSQL("CREATE TABLE DiaRepartidor" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idRepartidor INTEGER," +
                    "enviado INTEGER," +
                    "fecha TEXT" +
                    ")");


            db.execSQL("CREATE TABLE DiaEnviado" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idDiaRepartidor INTEGER," +
                    "repartosEnviados INTEGER," +
                    "gastosEnviados INTEGER," +
                    "cargasEnviadas INTEGER," +
                    "descargasEnviadas INTEGER" +
                    ")");


            db.execSQL("CREATE TABLE Gastos" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idDiaRepartidor INTEGER," +
                    "combustible INTEGER," +
                    "combustibleConTarjeta INTEGER," +
                    "descripcion INTEGER," +
                    "dinero REAL" +
                    ")");


            db.execSQL("CREATE TABLE Cargas" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idDiaRepartidor INTEGER," +
                    "bidones20L INTEGER," +
                    "bidones12L INTEGER," +
                    "bidones10L INTEGER," +
                    "bidones8L INTEGER," +
                    "bidones5L INTEGER," +
                    "packBotellas2L INTEGER," +
                    "packBotellas500mL INTEGER," +
                    "hora TEXT" +
                    ")");

            db.execSQL("CREATE TABLE Descargas" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idDiaRepartidor INTEGER," +
                    "bidones20L INTEGER," +
                    "bidones20LVacios INTEGER," +
                    "bidones12L INTEGER," +
                    "bidones12LVacios INTEGER," +
                    "bidones10L INTEGER," +
                    "bidones8L INTEGER," +
                    "bidones5L INTEGER," +
                    "packBotellas2L INTEGER," +
                    "packBotellas500mL INTEGER," +
                    "hora TEXT" +
                    ")");




            db.execSQL("CREATE TABLE PrecioDiaRepartidor" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idDiaRepartidor INTEGER," +
                    "bidon20L REAL," +
                    "bidon12L REAL," +
                    "bidon10L REAL," +
                    "bidon8L REAL," +
                    "bidon5L REAL," +
                    "packBotellas2L REAL," +
                    "packBotellas500mL REAL," +
                    "alquiler6Bidones REAL," +
                    "alquiler8Bidones REAL," +
                    "alquiler10Bidones REAL," +
                    "alquiler12Bidones REAL" +
                    ")");



            db.execSQL("CREATE TABLE Repartos" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idDiaRepartidor INTEGER," +
                    "idDatosCliente INTEGER," +
                    "idVendedor INTEGER," +
                    "idVentaProductos INTEGER," +
                    "idDeudaProductos INTEGER," +
                    "idAlquiler INTEGER," +
                    "idVacios INTEGER," +
                    "idVisita INTEGER," +
                    "idFueraDeRecorrido INTEGER," +
                    "enviado INTEGER," +
                    "idObservacion INTEGER" +
                    ")");







            db.execSQL("CREATE TABLE DatosClientes" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idCliente INTEGER," +
                    "idDatosDireccion INTEGER," +
                    "nombre TEXT," +
                    "apellido TEXT," +
                    "telefono TEXT," +
                    "idTipoCliente TEXT," +
                    "idPrecioEspecial INTEGER," +
                    "idDatosAlquiler INTEGER" +
                    ")");




            db.execSQL("CREATE TABLE EstadoBidonesDispenserFC" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idCliente INTEGER," +
                    "dispenserFC INTEGER," +
                    "bidones20L INTEGER," +
                    "bidones12L INTEGER," +
                    "fecha TEXT" +
                    ")");



            db.execSQL("CREATE TABLE EstadoInactividad" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idCliente INTEGER," +
                    "idInactividad INTEGER," +
                    "ultimoConsumo TEXT," +
                    "fecha TEXT" +
                    ")");






            db.execSQL("CREATE TABLE DireccionCliente" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idDireccion INTEGER," +
                    "calle TEXT," +
                    "numero TEXT," +
                    "entre1 TEXT," +
                    "entre2 TEXT," +
                    "departamento TEXT," +
                    "piso INTEGER" +
                    ")");



            db.execSQL("CREATE TABLE PrecioEspecialProductos" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bidon20L REAL," +
                    "bidon12L REAL," +
                    "bidon10L REAL," +
                    "bidon8L REAL," +
                    "bidon5L REAL," +
                    "packBotellas2L REAL," +
                    "packBotellas500mL REAL" +
                    ")");




            db.execSQL("CREATE TABLE DatosAlquiler" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idPrecioEspecial INTEGER," +
                    "alquileres6Bidones INTEGER," +
                    "alquileres8Bidones INTEGER," +
                    "alquileres10Bidones INTEGER," +
                    "alquileres12Bidones INTEGER" +
                    ")");


            db.execSQL("CREATE TABLE EstadoAlquiler" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idAlquiler INTEGER," +
                    "alquileres6BidonesPagados INTEGER," +
                    "alquileres8BidonesPagados INTEGER," +
                    "alquileres10BidonesPagados INTEGER," +
                    "alquileres12BidonesPagados INTEGER," +
                    "bidones20LEntregados INTEGER," +
                    "bidones12LEntregados INTEGER," +
                    "fecha TEXT" +
                    ")");




            db.execSQL("CREATE TABLE PrecioEspecialAlquiler" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "alquiler6Bidones REAL," +
                    "alquiler8Bidones REAL," +
                    "alquiler10Bidones REAL," +
                    "alquiler12Bidones REAL" +
                    ")");



            db.execSQL("CREATE TABLE Observaciones" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "observacion TEXT" +
                    ")");


            db.execSQL("CREATE TABLE VentaProductos" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bidones20L INTEGER," +
                    "bidones12L INTEGER," +
                    "bidones10L INTEGER," +
                    "bidones8L INTEGER," +
                    "bidones5L INTEGER," +
                    "packBotellas2L INTEGER," +
                    "packBotellas500mL INTEGER," +
                    "bidones20LBonificados INTEGER," +
                    "bidones12LBonificados INTEGER," +
                    "bidones10LBonificados INTEGER," +
                    "bidones8LBonificados INTEGER," +
                    "bidones5LBonificados INTEGER," +
                    "packBotellas2LBonificados INTEGER," +
                    "packBotellas500mLBonificados INTEGER" +
                    ")");




            db.execSQL("CREATE TABLE DeudaProductos" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bidones20L INTEGER," +
                    "bidones12L INTEGER," +
                    "bidones10L INTEGER," +
                    "bidones8L INTEGER," +
                    "bidones5L INTEGER," +
                    "packBotellas2L INTEGER," +
                    "packBotellas500mL INTEGER" +
                    ")");


            db.execSQL("CREATE TABLE Alquiler" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idExcedente INTEGER," +
                    "idPagoAlquiler INTEGER," +
                    "bidones20L INTEGER," +
                    "bidones12L INTEGER" +
                    ")");



            db.execSQL("CREATE TABLE ExcedenteAlquiler" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bidones20L INTEGER," +
                    "bidones20LDeudados INTEGER," +
                    "bidones12L INTEGER," +
                    "bidones12LDeudados INTEGER" +
                    ")");


            db.execSQL("CREATE TABLE PagoAlquiler" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "alquileres6Bidones INTEGER," +
                    "alquileres8Bidones INTEGER," +
                    "alquileres10Bidones INTEGER," +
                    "alquileres12Bidones INTEGER" +
                    ")");


            db.execSQL("CREATE TABLE Vacios" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bidones20L INTEGER," +
                    "bidones12L INTEGER" +
                    ")");


            db.execSQL("CREATE TABLE FueraDeRecorrido" +
                    "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idTipoFueraDeRecorrido INTEGER," +
                    "mensaje TEXT" +
                    ")");



            return true;
        }
        catch (Exception e)
        {
            String n=e.toString();
            return false;
        }
    }

    public boolean vaciarBaseDeDatosDinamica()
    {

    boolean aux=true;

    aux&=eliminarTablasDinamica();
    aux&=crearTablasDinamica();

    return aux;

    }




    public int getNumeroDias()
    {
    int x=0;
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DiaRepartidor",null);
        x =  cursor.getCount();
        }
    catch (Exception e)
        {
        }
    return x;
    }















}
