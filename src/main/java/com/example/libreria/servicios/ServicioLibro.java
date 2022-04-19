package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import com.example.libreria.repositorios.LibroRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioLibro {

    @Autowired
    private LibroRepositorio libroRepositorio;

    public Libro crearLibro(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) {

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

    public Libro modificarLibro(Long isbn, Boolean alta,String id, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws Exception {
        validaciones(isbn,titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
        Optional<Libro> objetoTraido = libroRepositorio.findById(id);
        if (objetoTraido.isPresent()) {
            Libro libro = objetoTraido.get();
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setAlta(alta);
            return libroRepositorio.save(libro);
        } else {
            throw new Exception("no existe libro con ese id");
        }

    }

    public void darBaja(Libro libro) {
        libro.setAlta(Boolean.FALSE);
    }

    public void darAlta(Libro libro) {
        libro.setAlta(Boolean.TRUE);
    }

//    public void aumentarEjemplar(Libro libro){
//    libro.setEjemplares(libro.getEjemplares()+1);
//    }
//    
//    public void disminuirEjemplares(Libro libro){
//    libro.setEjemplares(libro.getEjemplares()-1);
//    }
//    
//    public void aumentarEjemplarRestante(Libro libro){
//    libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
//    }
//    
//    public void disminuirEjemplarRestante(Libro libro){
//    libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
//    }
    public void validaciones(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Autor autor, Editorial editorial) throws Exception {

        
        if ((isbn == null)||(isbn <= 0)) {
            throw new Exception("isbn no puede ser null o negativo");
        }
        if ((titulo == null) || (titulo.trim().isEmpty())) {
            throw new Exception("titulo no puede estar vacio o null");
        }
        if ((anio == null) || (anio <= 0)) {
            throw new Exception("anio no puede estar vacio o null");
        }
        if ((ejemplares == null) || (ejemplares <= 0)) {
            throw new Exception("ejemplares no puede estar vacio o null");
        }
        if ((ejemplaresPrestados == null) || (ejemplaresPrestados <= 0)) {
            throw new Exception("ejemplares Prestados no puede estar vacio o null");
        }
        if ((ejemplaresRestantes == null) || (ejemplaresRestantes <= 0)) {
            throw new Exception("ejemplares restantes no puede estar vacio o null");
        }
        if ((editorial == null) || (titulo.trim().isEmpty())) {
            throw new Exception("editorial no puede estar vacio o null");
        }
        if ((autor == null) || (titulo.trim().isEmpty())) {
            throw new Exception("autor no puede estar vacio o null");
        }

    }
}
