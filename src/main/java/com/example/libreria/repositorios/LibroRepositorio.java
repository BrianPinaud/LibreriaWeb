package com.example.libreria.repositorios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String> {

    @Query("SELECT l FROM Libro l WHERE l.id = :id")
    public Libro buscarPorId(@Param("id") Integer id);

    @Query("SELECT i FROM Libro i WHERE i.ISBN = :isbn")
    public Libro buscarPorISBN(@Param("isbn") Long isbn);

    @Query("SELECT t FROM Libro t WHERE t.titulo = :titulo")
    public Libro buscarPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT a FROM Libro a WHERE a.anio = :anio")
    public Libro buscarPorAnio(@Param("anio") Integer anio);
    
    @Query("SELECT e FROM Libro e WHERE e.ejemplares = :ejemplares")
    public Libro buscarPorEjemplares(@Param("ejemplares") Integer ejemplares);
    
    @Query("SELECT ep FROM Libro ep WHERE ep.ejemplaresPrestados = :ejemplaresPrestados")
    public Libro buscarPorEjemplaresPrestados(@Param("ejemplaresPrestados") Integer ejemplaresPrestados);

    @Query("SELECT er FROM Libro er WHERE er.ejemplaresRestantes = :ejemplaresRestantes")
    public Libro buscarPorEjemplaresRestantes(@Param("ejemplaresRestantes") Integer ejemplaresRestantes);
    
    @Query("SELECT au FROM Libro au WHERE au.autor = :autor")
    public Libro buscarPorAutor(@Param("autor") Autor autor);
    
    @Query("SELECT ed FROM Libro ed WHERE ed.editorial = :editorial")
    public Libro buscarPorEditorial(@Param("editorial") Editorial editorial);
}
