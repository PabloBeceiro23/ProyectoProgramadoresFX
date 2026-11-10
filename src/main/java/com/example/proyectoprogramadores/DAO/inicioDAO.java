package com.example.proyectoprogramadores.DAO;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.example.proyectoprogramadores.entity.Programador;
import com.example.proyectoprogramadores.util.ConexionBBDD;

import java.util.ArrayList;
import java.util.List;

public class inicioDAO {

    // se busca programadores por su lenguaje de programacion
    public ArrayList<Programador> filtrarPorLenguaje(String lenguaje) {
        ConexionBBDD conexionBBDD = new ConexionBBDD();

        ObjectContainer oc = conexionBBDD.abrirConexion();

        Programador programador = new Programador();
        programador.setLenguaje(lenguaje);

        ArrayList<Programador> listaP =  new ArrayList<>();

        ObjectSet<Programador> listaProgramadores = oc.queryByExample(programador);

        listaP.addAll(listaProgramadores);

        oc.close();

        return listaP;
    }
    // lista todos los programadores de la bbdd
    public ArrayList<Programador> listarProgramadores() {
        ConexionBBDD conexionBBDD = new ConexionBBDD();

        ObjectContainer oc = conexionBBDD.abrirConexion();
        Programador programador = new Programador();
        ArrayList<Programador> listaP =  new ArrayList<>();
        ObjectSet<Programador> listaProgramadores = oc.queryByExample(programador);
        listaP.addAll(listaProgramadores);
        oc.close();
        return listaP;

    }

}
