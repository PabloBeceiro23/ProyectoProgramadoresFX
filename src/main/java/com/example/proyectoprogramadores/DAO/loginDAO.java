package com.example.proyectoprogramadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.proyectoprogramadores.entity.Programador;
import com.example.proyectoprogramadores.util.ConexionBBDD;

public class loginDAO {


// comprueba que existe el programador en la base de datos
    public boolean login(Programador programador) {
        ConexionBBDD conexionBBDD = new ConexionBBDD();

        ObjectContainer oc = conexionBBDD.abrirConexion();

        ObjectSet<Programador> existente = oc.queryByExample(programador);

        if(existente.hasNext()){
            oc.close();
            return true;
        } else {
            oc.close();
            return false;
        }
    }
}
