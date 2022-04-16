package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import com.example.libreria.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioLibro {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    public Libro crearLibro(String titulo,Integer anio,Integer ejemplares,Integer ejemplaresPrestados,Integer ejemplaresRestantes, Autor autor, Editorial editorial){
    
        Libro l = new Libro();
        l.setTitulo(titulo);
        l.setAnio(anio);
        l.setEjemplares(ejemplares);
        l.setEjemplaresPrestados(ejemplaresPrestados);
        l.setEjemplaresRestantes(ejemplaresRestantes);
        l.setAlta(true);
        l.setAutor(autor);
        l.setEditorial(editorial);      
        return l;
    }
    
    public void darBaja(Libro libro){
     libro.setAlta(Boolean.FALSE);
    }
    
    public void darAlta(Libro libro){
    libro.setAlta(Boolean.TRUE);} 
    
    public void aumentarEjemplar(Libro libro){
    libro.setEjemplares(libro.getEjemplares()+1);
    }
    
    public void disminuirEjemplares(Libro libro){
    libro.setEjemplares(libro.getEjemplares()-1);
    }
    
    public void aumentarEjemplarRestante(Libro libro){
    libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
    }
    
    public void disminuirEjemplarRestante(Libro libro){
    libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
    }
}
