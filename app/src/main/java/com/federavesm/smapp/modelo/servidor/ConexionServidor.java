package com.federavesm.smapp.modelo.servidor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.federavesm.smapp.modelo.Generico;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;



/**
 * Created by Federico on 5/1/2018.
 */

public class ConexionServidor extends Generico {



    public ConexionServidor(Context context)
    {
    super(context);

    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Configuracion ",null);

        if(cursor.moveToLast())
            {
            this.ip = cursor.getString(1);
            this.puerto = cursor.getString(2);
            }

        }
    catch (Exception e)
        {
        String x=e.toString();
        }




    }

    protected String ip;
    protected String puerto;

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    @Override
    public boolean modificar() {
        return false;
    }


    @Override
    public boolean guardar() {


        SQLiteDatabase db = getWritableDatabase();

        ContentValues configuracion = new ContentValues();
        configuracion.put("ipServidor",this.ip);
        configuracion.put("puerto",this.puerto);

        if(db.insert("Configuracion",null,configuracion) > 0)
            {
            return true;
            }
        else
            {
            return false;
            }

    }

    @Override
    public boolean eliminar() {
        return false;
    }

    @Override
    public boolean actualizar() {
        return false;
    }





    @Override
    public boolean cargar(){return true;}



    @Override
    public Object getCopia() {
        return null;
    }


    @Override
    public String toString() {
        return "";
    }

    @Override
    public void copiar(Object object) {
    }






}
