package com.federavesm.smapp.modelo.servidor;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RequerimientoGET {


    public RequerimientoGET()
    {

    }



    private HttpClient cliente = new DefaultHttpClient();
    private String url="";
    private String rutaScript="";
    private String host="";
    private String parametros="";
    private int n=0;


    public void addParametro(String nombre,String valor)
    {
    if(n==0)
        {
        parametros+="?"+nombre+"="+valor;
        }
    else
        {
        parametros+="&"+nombre+"="+valor;
        }
    n++;
    }

    public void clear()
    {
        this.url="";
        this.rutaScript="";
        this.parametros="";
        this.n=0;
    }

    public void setURL(String url)
    {
    this.url=url;
    }

    public void setHost(String host)
    {
    this.host=host;
    }

    public void setRutaScript(String rutaScript)
    {
    this.rutaScript=rutaScript;
    }

    public String ejecutar()
    {
        String respuestaXML = "";

        try {

            this.url = this.host + this.rutaScript + this.parametros;

            HttpGet requerimiento = new HttpGet(this.url);
            HttpResponse respuesta = cliente.execute(requerimiento);
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(respuesta.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null){respuestaXML += line;}
        }
        catch (Exception e)
        {
            respuestaXML = e.toString();

        }


    return respuestaXML;
    }






}
