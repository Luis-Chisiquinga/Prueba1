package com.distribuida.rest;

import com.distribuida.dto.Libro;
import com.distribuida.servicios.ServicioLibro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@ApplicationScoped
@Path("books")
public class ServicioLibroRest {

    @Inject
    ServicioLibro servicio;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Libro> obtenerLibros(){
        return servicio.obtenerLibros();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Libro obtenerLibroPorId(@PathParam("id") Integer id){
        return servicio.obtenerLibroPorId(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean crearLibro(Libro libro){
        return servicio.crearLibro(libro);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean actualizarLibro(@PathParam("id") Integer id, Libro libro){
        return servicio.actualizarLibro(libro, id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean eliminarLibro(@PathParam("id") Integer id){
        return servicio.eliminarLibro(id);
    }

}
