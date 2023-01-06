package com.distribuida.servicios;

import com.distribuida.dto.Libro;

import java.util.List;

public interface ServicioLibro {

    Libro obtenerLibroPorId(Integer id);
    List<Libro> obtenerLibros();
    boolean crearLibro(Libro libro);
    boolean actualizarLibro(Libro libro, Integer id);
    boolean eliminarLibro(Integer id);

}
