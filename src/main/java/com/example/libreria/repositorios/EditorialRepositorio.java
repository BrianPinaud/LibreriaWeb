package com.example.libreria.repositorios;

import com.example.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

    @Query("SELECT e FROM Editorial e WHERE e.id = :id")
    public Editorial buscarPorId(@Param("id") Integer id);

    @Query("SELECT n FROM Editorial n WHERE n.nombre = :nombre")
    public Editorial buscarPorNombreEditorial(@Param("nombre") String nombre);
}
