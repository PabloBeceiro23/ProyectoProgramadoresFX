package com.example.proyectoprogramadores.util;

import com.example.proyectoprogramadores.entity.Programador;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.scene.control.Alert;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class GenerarPDF {

    // genera un pdf con una tabla con la lista de los programadores
    public boolean generarPDF(ArrayList <Programador> listaProgramadores)  {
        try {
            Document doc = new Document();

            FileOutputStream fos = new FileOutputStream("empleados.pdf");

            PdfWriter.getInstance(doc, fos);
            doc.open();

            doc.add(new Paragraph("Listado programadores"));
            doc.add(new Paragraph("\n\n"));

            PdfPTable tabla = new PdfPTable(3);

            //cabeceras de la tabla del pdf
            tabla.addCell("Usuario");
            tabla.addCell("Genero");
            tabla.addCell("Lenguaje");

            // se recorre la lista de programadores y se añaden los datos a la tabla del pdf
            for(Programador programador : listaProgramadores){
                tabla.addCell(programador.getUsuario());
                tabla.addCell(programador.getGenero());
                tabla.addCell(programador.getLenguaje());
            }
            doc.add(tabla);
            doc.close();
            return true;
        } catch (FileNotFoundException e) {
           return false;
        } catch (DocumentException e) {
            return false;
        }
    }


}
