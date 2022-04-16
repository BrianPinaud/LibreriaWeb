package com.example.libreria.servicios;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.repositorios.EditorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEditorial {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    public Editorial crear(String nombre, Boolean alta) throws Exception{
    
        validaciones(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(alta);
        
    return editorialRepositorio.save(editorial);
    }
    
    public void validaciones(String nombre) throws Exception{
    
        if ((nombre == null) || (nombre.trim().isEmpty())) {
            
            throw new Exception("nombre no puede estar vacio o ser null");
        }
    }
}
