package com.example.proyectoprogramadores.util;

import com.example.proyectoprogramadores.entity.Programador;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GenerarExcel {
    public boolean GenerarExcel(ArrayList<Programador> listaProgramadores) throws FileNotFoundException {

        try {
            FileOutputStream fos = new FileOutputStream("programadores.xlsx");

            Workbook libro = new SXSSFWorkbook();
            Sheet hojas = libro.createSheet("programadores");

            Row cabecera = hojas.createRow(0);

            cabecera.createCell(0).setCellValue("Usuario");
            cabecera.createCell(1).setCellValue("Genero");
            cabecera.createCell(2).setCellValue("Lenguaje");

            for(int i = 0; i < listaProgramadores.size(); i++){
                // se crean filas a partir de la segunda fila por que la primera ya se creo antes ( que es la cabecera  del excel)
                Row fila1 = hojas.createRow(i + 1);

                // se accede a cada programador y luego se rellean sus datos en cada fila del excel
                fila1.createCell(0).setCellValue(listaProgramadores.get(i).getUsuario());
                fila1.createCell(1).setCellValue(listaProgramadores.get(i).getGenero());
                fila1.createCell(2).setCellValue(listaProgramadores.get(i).getLenguaje());
            }

            libro.write(fos);
            fos.close();
            libro.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
