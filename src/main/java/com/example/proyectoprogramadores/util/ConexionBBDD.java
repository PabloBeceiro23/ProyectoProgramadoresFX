package com.example.proyectoprogramadores.util;


import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

public class ConexionBBDD {

    static final String DB_FILE = "programadores.db4o";

    public ObjectContainer abrirConexion(){

        return Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
    }
}
