package com.example.libreria.servicios;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.repositorios.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioEditorial {

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    public Editorial crear(String nombre) throws Exception {

        validaciones(nombre);
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        

        return editorialRepositorio.save(editorial);
    }
    
    public Editorial modificar(String nombre, String id) throws Exception{
    
        validaciones(nombre);
        Optional<Editorial>objetoTraido = editorialRepositorio.findById(id);
        
        if (objetoTraido.isPresent()) {
            Editorial editorial = objetoTraido.get();
            editorial.setNombre(nombre);
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
    
     public Editorial traerPorId(String id) throws Exception{
            Optional<Editorial> respuesta = editorialRepositorio.findById(id);
            if (respuesta.isPresent()) {
                Editorial editorial = respuesta.get();
                return editorial;
         } else {
                throw new Exception("no existe una editorial con ese id");
            }
        }
    
    public List<Editorial>listarEditoriales(){
            return editorialRepositorio.findAll();
        }

    public void validaciones(String nombre) throws Exception {

        if ((nombre == null) || (nombre.trim().isEmpty())) {
            throw new Exception("nombre no puede estar vacio o ser null");
        }
    }
}
