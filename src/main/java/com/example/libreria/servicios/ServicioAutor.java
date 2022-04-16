package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.repositorios.AutorRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioAutor {
    
    @Autowired
    private AutorRepositorio autorRepositorio;
            
    public Autor crear(String nombre, Boolean alta) throws Exception{
        validaciones(nombre);
         Autor autor = new Autor();
         autor.setNombre(nombre);
         autor.setAlta(alta);
         
         return autorRepositorio.save(autor);
    }
    
    public Autor modificar(String id, String nombre, Boolean alta) throws Exception{
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
      public void validaciones(String nombre) throws Exception {

        if ((nombre == null) || (nombre.trim().isEmpty())) {
            throw new Exception("nombre no puede estar vacio o ser null");
        }
    }
    
}
