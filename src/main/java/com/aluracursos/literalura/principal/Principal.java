package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    private final MenuService menuService;

    @Autowired
    public Principal(MenuService menuService) {
        this.menuService = menuService;
    }
    public class Menu {
        private String menu = """
                1-Buscar libro por título
                2-Listar libros registrados
                3-Listar autores registrados
                4-Listar autores vivos en un determinado año
                5-Listar libros por idioma
                0-Salir
                
                Elija una opción: """;
        private String bienvenida = "Bienvenidos a literAlura";



        public String getMenu() {
            return menu;
        }

        public String getBienvenida() {
            return bienvenida;
        }
    }
    public void EjecutarAplicacion() {
        Menu menu = new Menu();
        Scanner teclado = new Scanner(System.in);
        System.out.println(menu.getBienvenida());
        int opcion;
        do {
            try {
                System.out.print(menu.getMenu() + " ");
                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        menuService.guardarLibro();
                        break;
                    case 2:
                        menuService.listarLibrosRegistrados();
                        break;
                    case 3:
                        menuService.listarAutoresRegistrados();
                        break;
                    case 4:
                        menuService.listarAutoresVivosPorAnio();
                        break;
                    case 5:
                        menuService.listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saliendo de literAlura...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: ingrese un número");
                opcion = -1;
                teclado.nextLine();
            }
        } while (opcion != 0);
        teclado.close();
    }
}
