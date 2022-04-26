package com.example.libreria.controladores;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.servicios.ServicioEditorial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialController {
    
    @Autowired
    private ServicioEditorial servicioEditorial;
    //desde controladores accedo a servicios
    //desde servicios accedo a los repositorios
    @GetMapping("/lista")
    public String listarEditoriales(ModelMap nombreDelModelo){
        List<Editorial>listaEditoriales = servicioEditorial.listarEditoriales();
        nombreDelModelo.addAttribute("editoriales" , listaEditoriales);
        return "lista-editoriales";
    }
    
    @GetMapping("/guardar")
    public String formulario(){
    return "formulario-editorial";
    }
    
    @PostMapping("/guardar")
    public String crearEditorial(ModelMap modelo,@RequestParam String nombre){
        try {
           servicioEditorial.crear(nombre);
           modelo.put("exito", "se creo exitosamente");
           return "formulario-editorial";
          
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "formulario-editorial";
        }
        
    }
    // esto hace que busque por id el objeto a modificar 
    @GetMapping("/modificar/{id}") // lo que muestra en la pag es una variable el path
    public String modificar(ModelMap modelo, @PathVariable String id) throws Exception{
        modelo.addAttribute("modelo", id);
         modelo.addAttribute("editorial", servicioEditorial.traerPorId(id));  
      return "modificar-editorial";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre) throws Exception{
        try {
            servicioEditorial.modificar(nombre,id);
            return "redirect:/editorial/lista";
        } catch (Exception e) {
            modelo.addAttribute("editorial", servicioEditorial.traerPorId(id));  
           modelo.put("error","hubo un error con los datos");
           return "modificar-editorial";
        }
    }
    
}
