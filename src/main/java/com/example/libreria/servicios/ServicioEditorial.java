package com.example.libreria.servicios;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.repositorios.EditorialRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEditorial {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    public Editorial crear(String nombre, Boolean alta) throws Exception {

        validaciones(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(alta);

        return editorialRepositorio.save(editorial);
    }
    
    public Editorial modificar(String nombre, Boolean alta, String id) throws Exception{
    
        validaciones(nombre);
        Optional<Editorial>objetoTraido = editorialRepositorio.findById(id);
        
        if (objetoTraido.isPresent()) {
            Editorial editorial = objetoTraido.get();
            editorial.setNombre(nombre);
            editorial.setAlta(alta);
            
             return editorialRepositorio.save(editorial);
        }else {
        throw new Exception ("no existe editorial con ese id");
        }
    }
    
    public Editorial darBaja(String id)throws Exception{
        
        Editorial editorial = editorialRepositorio.getById(id);
        if (editorial != null) {
            editorial.setAlta(false);
            return editorialRepositorio.save(editorial);
        }else {
        throw new Exception("no existe editorial con ese id");
        }
    
    }
    
    public Editorial darAlta(String id) throws Exception{
    
        Editorial editorial = editorialRepositorio.getById(id);
        if(editorial!=null) {
        editorial.setAlta(true);
        return editorialRepositorio.save(editorial);
        
        }else{
        throw new Exception("no existe editorial con ese id");
        }
    
    }

    public void validaciones(String nombre) throws Exception {

        if ((nombre == null) || (nombre.trim().isEmpty())) {
            throw new Exception("nombre no puede estar vacio o ser null");
        }
    }
}