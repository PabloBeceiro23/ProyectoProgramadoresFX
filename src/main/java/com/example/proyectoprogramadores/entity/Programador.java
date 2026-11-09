package com.example.proyectoprogramadores.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Programador {

    private String usuario;
    private String password;
    private String genero;
    private String lenguaje;

}
