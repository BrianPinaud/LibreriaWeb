package com.example.libreria.controladores;

import com.example.libreria.entidades.Autor;
import com.example.libreria.servicios.ServicioAutor;
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
@RequestMapping("/autor")
public class AutorController {
    
    @Autowired
    public ServicioAutor servicioAutor;
    
    @GetMapping("/lista")
    public String listarAutores(ModelMap nombreDelModelo){
        List<Autor>listaAutores = servicioAutor.listarAutores();
        nombreDelModelo.addAttribute("autores", listaAutores);
        return "lista-autores";
    }
    
    @GetMapping("/guardar")
    public String formulario(){
        return "formulario-autor";
    }
    
    @PostMapping("/guardar")
    public String crearAutor(ModelMap modelo,@RequestParam String nombre){
        try {
            servicioAutor.crear(nombre);
            modelo.put("exito", "se creo exitosamente");
            return "formulario-autor";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "formulario-autor";
        }
        
    }
    
    @GetMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id) throws Exception{
    modelo.addAttribute("modelo", id);
    modelo.addAttribute("autor", servicioAutor.traerPorId(id));
    return "modificar-autor";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String nombre) throws Exception{
        try {
            servicioAutor.modificar(id, nombre);
            return "redirect:/autor/lista";
        } catch (Exception e) {
            modelo.addAttribute("autor", servicioAutor.traerPorId(id));
            modelo.put("error", "hubo un error con los datos");
            return "modificar-autor";
        }
    
    }
      @GetMapping("/baja/{id}")
    public String darBaja(@PathVariable String id){
        
        try {
            servicioAutor.darBaja(id);
            return "redirect:/autor/lista";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
    @GetMapping("/alta/{id}")
    public String darAlta(@PathVariable String id){
        
        try {
            servicioAutor.darAlta(id);
            return "redirect:/autor/lista";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
}
