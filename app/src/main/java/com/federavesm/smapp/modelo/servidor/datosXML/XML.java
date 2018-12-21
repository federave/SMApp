package com.federavesm.smapp.modelo.servidor.datosXML;

/**
 * Created by FedeRave on 25/3/2018.
 */

public class XML {

    public XML()
    {
    }

    private String xml="";


    public void startTag(String nombreTag)
    {
    this.xml += "<" + nombreTag + ">";
    }

    public void closeTag(String nombreTag)
    {
    this.xml += "</" + nombreTag + ">";
    }


    public void addValue(String valor)
    {
    this.xml += valor;
    }


    public void addTag(String nombreTag,String valor)
    {
    startTag(nombreTag);
    addValue(valor);
    closeTag(nombreTag);
    }

    public String getXML(){return this.xml;}

    public String toString(){return this.xml;}




}
