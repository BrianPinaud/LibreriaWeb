package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.repositorios.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioAutor {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
            
    public Autor crear(String nombre) throws Exception{
        validaciones(nombre);
         Autor autor = new Autor();
         autor.setNombre(nombre);
         
         return autorRepositorio.save(autor);
    }
    
    public Autor modificar(String id, String nombre) throws Exception{
        validaciones(nombre);
        
        Optional<Autor>objetoTraido = autorRepositorio.findById(id);
        if (objetoTraido.isPresent()) {
            Autor autor = objetoTraido.get();
                    
            return autorRepositorio.save(autor);
        }else{
        throw new Exception("no existe autor con ese id");
        }
    }
    
    public Autor darBaja(String id) throws Exception{
        Autor autor = autorRepositorio.getById(id);
        if(autor != null){
            autor.setAlta(false);
            return autorRepositorio.save(autor);
        }else{
          throw new Exception("no existe autor con ese id");
        }
    }
    
    public Autor darAlta(String id) throws Exception{
      Autor autor = autorRepositorio.getById(id);
        if (autor!=null) {
            autor.setAlta(true);
            return autorRepositorio.save(autor);
        }else{
        throw new Exception("no existe autor con ese id");
        }
    }
    
      public Autor traerPorId(String id) throws Exception{
            Optional<Autor> respuesta = autorRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Autor autor = respuesta.get();
                return autor;
         } else {
                throw new Exception("no existe un autor con ese id");
            }
        }
    
       public List<Autor>listarAutores(){
        return autorRepositorio.findAll();
    }   
       
      public void validaciones(String nombre) throws Exception {

        if ((nombre == null) || (nombre.trim().isEmpty())) {
            throw new Exception("nombre no puede estar vacio o ser null");
        }
    }
    
}
