package com.distribuida.servicios;

import com.distribuida.dto.Libro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ServicioLibroImpl implements ServicioLibro{

    @Inject
    DataSource ds;

    @Override
    public Libro obtenerLibroPorId(Integer id) {
        Jdbi jdbi = null;
        try {
            jdbi = Jdbi.create(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Libro> lista = jdbi.withHandle(handle ->
            handle.createQuery("select * from libro where id = :id")
                    .bind("id", id)
                    .mapToBean(Libro.class)
                    .list()
        );

        return lista.isEmpty() ? new Libro():lista.get(0);
    }

    @Override
    public List<Libro> obtenerLibros() {
        Jdbi jdbi = null;
        try {
            jdbi = Jdbi.create(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Libro> listaLibros = jdbi.withHandle(handle ->
            handle.createQuery("select * from libro")
                  .mapToBean(Libro.class)
                  .list());
        return listaLibros;
    }

    @Override
    public boolean crearLibro(Libro libro) {

        Jdbi jdbi = null;
        try {
            jdbi = Jdbi.create(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int contador = jdbi.withHandle(handle ->
            handle.createUpdate("insert into libro (isbn,title,author,price) values(:isbn,:title,:author,:price)")
                .bind("isbn", libro.getIsbn())
                .bind("title", libro.getTitle())
                .bind("author", libro.getAuthor())
                .bind("price", libro.getPrice())
                .execute()
        );

        return contador > 0;
    }

    @Override
    public boolean actualizarLibro(Libro libro, Integer id) {
        Jdbi jdbi = null;
        int contador = 0;
        try {
            jdbi = Jdbi.create(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        contador = jdbi.withHandle(handle ->
                handle.createUpdate("update libro set isbn = :isbn, title = :title, author = :author, price = :price where id = :id")
                        .bind("isbn", libro.getIsbn())
                        .bind("title", libro.getTitle())
                        .bind("author", libro.getAuthor())
                        .bind("price", libro.getPrice())
                        .bind("id", id)
                        .execute()
        );

        return contador > 0;
    }

    @Override
    public boolean eliminarLibro(Integer id) {
        Jdbi jdbi = null;
        try {
            jdbi = Jdbi.create(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int contador = jdbi.withHandle(handle ->
                handle.createUpdate("delete from libro where id = :id")
                        .bind("id", id)
                        .execute()
        );

        return contador > 0;
    }
}
