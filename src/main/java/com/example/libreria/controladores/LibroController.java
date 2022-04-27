package com.example.libreria.controladores;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import com.example.libreria.servicios.ServicioLibro;
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
@RequestMapping("/libro")
public class LibroController {
    
    @Autowired
    private ServicioLibro servicioLibro;
    
    
   @GetMapping("/lista")
   public String listarLibros(ModelMap nombreDelModelo){
   List<Libro>listaLibros = servicioLibro.listarLibros();
   nombreDelModelo.addAttribute("libros", listaLibros);
    return "lista-libros";
   }
   
   @GetMapping("/guardar")
   public String formulario(){
   return "formulario-libro";
   }
   
   @PostMapping("/guardar")
   public String crearLibro(ModelMap modelo,@RequestParam String titulo, @RequestParam Integer anio, @RequestParam Long isbn,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresRestantes, @RequestParam Integer ejemplaresPrestados, @RequestParam Editorial editorial, @RequestParam Autor autor){
       try {
         servicioLibro.crearLibro(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
         modelo.put("exito", "se creo exitosamente");
         return "formulario-libro";
       } catch (Exception e) {
           modelo.put("error", e.getMessage());
           return "formulario-libro";
           
       }
   
       
     
     }
   @GetMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id) throws Exception{
    modelo.addAttribute("modelo",id);
    modelo.addAttribute("libro", servicioLibro.traerPorId(id));
    return "modificar-libro";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String titulo,@RequestParam Long isbn, @RequestParam Integer anio,@RequestParam Integer ejemplares,@RequestParam Integer ejemplaresRestantes, @RequestParam Integer ejemplaresPrestados, @RequestParam Editorial editorial, @RequestParam Autor autor) throws Exception{
        try {
            servicioLibro.modificarLibro(isbn, id, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
            return "redirect:/libro/lista";
        } catch (Exception e) {
            modelo.addAttribute("libro", servicioLibro.traerPorId(id));
            modelo.put("error", "hubo un error con los datos");
            return "modificar-libro";
        }
    
    }
    
    @GetMapping("/buscar-tiulo")
    public String buscarTitulo(ModelMap modelo){
        return "buscar-por-titulo";
    }
    
    @PostMapping("/buscar-titulo")
    public String bucarTitulo(ModelMap modelo,@RequestParam String titulo) throws Exception{
      
        try {
            List<Libro>libros = servicioLibro.buscarPorTitulo(titulo);
            modelo.put("libros", libros);
            return "redirect:/libro/buscar-por-titulo";
        } catch (Exception e) {
            modelo.put("libros", servicioLibro.buscarPorTitulo(titulo));
            modelo.put("error", "no hay un libro con ese titulo");
            return "redirect:/libro/buscar-por-titulo";
            
        }
    
    }
    
       @GetMapping("/baja/{id}")
    public String darBaja(@PathVariable String id){
        try {
            servicioLibro.darBaja(id);
            return "redirect:/libro/lista";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
    
    @GetMapping("/alta/{id}")
    public String darAlta(@PathVariable String id){
        try {
            servicioLibro.darAlta(id);
            return "redirect:/libro/lista";
        } catch (Exception e) {
            return "redirect:/";
        }
    }
}
