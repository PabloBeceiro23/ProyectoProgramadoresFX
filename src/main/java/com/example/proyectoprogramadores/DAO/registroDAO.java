package com.example.proyectoprogramadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.proyectoprogramadores.entity.Programador;
import com.example.proyectoprogramadores.util.ConexionBBDD;

public class registroDAO {

    public boolean registrarProgramador(Programador programador) {
        ConexionBBDD conexionBBDD = new ConexionBBDD();

        ObjectContainer oc = conexionBBDD.abrirConexion();

        // primero hay que comprobar que no exista ningun programador con el usuario que se quiere introducir

        Programador programadorPlantilla = new Programador();
        programadorPlantilla.setUsuario(programador.getUsuario());

        ObjectSet<Programador> existente = oc.queryByExample(programadorPlantilla);

        // si existe algun programador con el usuario que se quiere registrar no se registra
        if(existente.hasNext()){
            oc.close();
            return false;
        } else {
            oc.store(programador);
            oc.commit();
            oc.close();
            return true;

        }
    }
}
