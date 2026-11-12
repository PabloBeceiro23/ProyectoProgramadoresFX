package com.example.proyectoprogramadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.proyectoprogramadores.entity.Programador;
import com.example.proyectoprogramadores.util.ConexionBBDD;

import java.util.ArrayList;

public class editarPerfilDAO {



    public boolean modificarProgramador(Programador programadorActual, Programador programadorModificado) {
        ConexionBBDD conexionBBDD = new ConexionBBDD();

        ObjectContainer oc = conexionBBDD.abrirConexion();

        ObjectSet<Programador> listaProgramadores = oc.queryByExample(programadorActual);

        if(listaProgramadores.hasNext()){
            programadorActual = listaProgramadores.next();

            programadorActual.setLenguaje(programadorModificado.getLenguaje());
            programadorActual.setPassword(programadorModificado.getPassword());
            programadorActual.setGenero(programadorModificado.getGenero());

            oc.store(programadorActual);
            oc.close();
            return true;
        }
        oc.close();
        return false;
    }
}
