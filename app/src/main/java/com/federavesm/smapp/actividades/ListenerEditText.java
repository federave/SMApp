package com.federavesm.smapp.actividades;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by Federico on 24/2/2018.
 */


public class ListenerEditText implements TextWatcher
{

    public ListenerEditText()
    {
    }

    protected Context activity;

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable)
    {
    }

}