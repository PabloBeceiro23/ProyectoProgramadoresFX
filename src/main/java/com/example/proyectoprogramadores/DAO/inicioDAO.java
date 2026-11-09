package com.example.proyectoprogramadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.proyectoprogramadores.entity.Programador;
import com.example.proyectoprogramadores.util.ConexionBBDD;

public class inicioDAO {

    // se busca programadores por su lenguaje de programacion
    public ObjectSet filtrarPorLenguaje(String lenguaje) {
        ConexionBBDD conexionBBDD = new ConexionBBDD();

        ObjectContainer oc = conexionBBDD.abrirConexion();

        Programador programador = new Programador();
        programador.setLenguaje(lenguaje);

        ObjectSet<Programador> listaProgramadores = oc.queryByExample(programador);

        return listaProgramadores;
    }
}
