package com.aluracursos.literalura.service;

import com.aluracursos.literalura.api.PeticionAPI;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Idioma;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.model.LibroRecord;
import com.aluracursos.literalura.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {
    private PeticionAPI peticionAPI;
    private Scanner sc;
    private LibroService libroService;
    private AutorService autorService;
    private JsonParser jsonParser;

    @Autowired
    public MenuService(LibroService libroService, AutorService autorService, JsonParser jsonParser) {
        this.peticionAPI = new PeticionAPI();
        this.sc = new Scanner(System.in);
        this.libroService = libroService;
        this.autorService = autorService;
        this.jsonParser = jsonParser;
    }

    public void guardarLibro() {
        List<LibroRecord> librosObtenidos = obtenerLibrosApi();

        if (librosObtenidos.isEmpty()) {
            System.out.println("No se encontró ningun libro");
            return;
        }

        System.out.println("Escoja un libro para guardar[0-Cancelar]");
        for (int i = 0; i < librosObtenidos.size(); i++) {
            LibroRecord libro = librosObtenidos.get(i);

            String idioma = libro.idiomas().isEmpty() ? "Desconocido" : libro.idiomas().get(0);
            String autor = libro.autores().isEmpty() ? "Autor desconocido" : libro.autores().get(0).nombre();

            System.out.println((i + 1) + " - " + libro.titulo() + " - " + idioma + " - " + autor);
        }


        int opcion = sc.nextInt();
        sc.nextLine();
        if (opcion == 0) {
            return;
        }
        if (opcion < 1 || opcion > librosObtenidos.size()) {
            System.out.println("Error: número erroneo");
            return;
        }

        LibroRecord libroRecord = librosObtenidos.get(opcion - 1);
        Optional<Libro> libroDelRepositorio = libroService.obtenerLibroPorNombre(libroRecord.titulo());
        Optional<Autor> autorDelRepositorio = autorService.obtenerAutorPorNombre(libroRecord.autores().get(0).nombre());

        if (libroDelRepositorio.isPresent()) {
            System.out.println("Error: no se puede registrar dos veces el mismo libro");
            return;
        }

        Libro libro = new Libro(libroRecord);

        if (!autorDelRepositorio.isPresent()) {
            Autor autorNuevo = libro.getAutor();
            autorService.guardarAutor(autorNuevo);
        }

        libroService.guardarLibro(libro);
    }

    public List<LibroRecord> obtenerLibrosApi() {
        System.out.print("Ingrese el título del libro [0-Cancelar]: ");
        String titulo = sc.nextLine();
        if (titulo.equals("0")) {
            return Collections.emptyList();
        }
        List<LibroRecord> librosObtenidos;
        librosObtenidos = jsonParser.parsearLibros(peticionAPI.obtenerDatos(titulo));
        return librosObtenidos;
    }


    public void listarLibrosRegistrados() {
        List<Libro> libros = libroService.obtenerTodosLosLibros();
        libros.forEach(libro -> libro.imprimirInformacion());
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorService.obtenerTodosLosAutores();
        autores.forEach(autor -> autor.imprimirInformacion());
    }

    public void listarAutoresVivosPorAnio() {
        try {
            System.out.print("Ingrese año: ");
            int anio = sc.nextInt();
            sc.nextLine();
            List<Autor> autores = autorService.obtenerAutoresVivosPorAnio(anio);
            autores.forEach(autor -> autor.imprimirInformacion());
        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un número");
        }

    }

    public void listarLibrosPorIdioma() {
        Idioma.listarIdiomas();
        System.out.print("Ingrese el codigo del idioma [0-Cancelar]: ");
        String idiomaBuscado = sc.nextLine();
        if (idiomaBuscado.equals("0")) {
            return;
        }
        List<Libro> libros = libroService.obtenerLibrosPorIdioma(Idioma.stringToEnum(idiomaBuscado));
        libros.forEach(libro -> libro.imprimirInformacion());
    }

}