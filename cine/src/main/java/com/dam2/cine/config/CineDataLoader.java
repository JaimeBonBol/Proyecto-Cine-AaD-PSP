package com.dam2.cine.config;

import com.dam2.cine.model.*;
import com.dam2.cine.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CineDataLoader implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final SalaRepository salaRepository;
    private final PeliculaRepository peliculaRepository;
    private final FuncionRepository funcionRepository;
    private final VentaRepository ventaRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Evitamos duplicar datos si ya existen
        if (rolRepository.count() > 0) {
            System.out.println("Ya existen datos, omitiendo DataLoader...");
            return;
        }

        System.out.println("🌱 Iniciando carga de datos de prueba...");

        // 1. ROLES
        Rol roleAdmin = rolRepository.save(new Rol(null, "ROLE_ADMIN", new ArrayList<>()));
        Rol roleUser = rolRepository.save(new Rol(null, "ROLE_USER", new ArrayList<>()));

        // 2. USUARIOS
        crearUsuario("admin@cine.com", "admin123", roleAdmin);
        Usuario user1 = crearUsuario("jaime@cine.com", "1234", roleUser);
        Usuario user2 = crearUsuario("maria@cine.com", "1234", roleUser);
        Usuario user3 = crearUsuario("pepe@cine.com", "1234", roleUser);

        // 3. DIRECTORES (5)
        Director nolan = directorRepository.save(new Director(null, "Christopher Nolan", new ArrayList<>()));
        Director tarantino = directorRepository.save(new Director(null, "Quentin Tarantino", new ArrayList<>()));
        Director spielberg = directorRepository.save(new Director(null, "Steven Spielberg", new ArrayList<>()));
        Director scorsese = directorRepository.save(new Director(null, "Martin Scorsese", new ArrayList<>()));
        Director villeneuve = directorRepository.save(new Director(null, "Denis Villeneuve", new ArrayList<>()));

        // 4. ACTORES (10)
        Actor dicaprio = actorRepository.save(new Actor(null, "Leonardo DiCaprio", new ArrayList<>()));
        Actor murphy = actorRepository.save(new Actor(null, "Cillian Murphy", new ArrayList<>()));
        Actor pitt = actorRepository.save(new Actor(null, "Brad Pitt", new ArrayList<>()));
        Actor robbie = actorRepository.save(new Actor(null, "Margot Robbie", new ArrayList<>()));
        Actor deniro = actorRepository.save(new Actor(null, "Robert De Niro", new ArrayList<>()));
        Actor pacino = actorRepository.save(new Actor(null, "Al Pacino", new ArrayList<>()));
        Actor holland = actorRepository.save(new Actor(null, "Tom Holland", new ArrayList<>()));
        Actor zendaya = actorRepository.save(new Actor(null, "Zendaya", new ArrayList<>()));
        Actor chalamet = actorRepository.save(new Actor(null, "Timothée Chalamet", new ArrayList<>()));
        Actor stone = actorRepository.save(new Actor(null, "Emma Stone", new ArrayList<>()));

        // 5. SALAS (3)
        Sala sala1 = salaRepository.save(new Sala(null, "Sala 1 - IMAX", 200, new ArrayList<>()));
        Sala sala2 = salaRepository.save(new Sala(null, "Sala 2 - 3D", 150, new ArrayList<>()));
        Sala sala3 = salaRepository.save(new Sala(null, "Sala 3 - VIP", 50, new ArrayList<>()));

        // 6. PELICULAS (5) - Usamos helper para simplificar listas
        Pelicula inception = crearPelicula("Inception", 148, 12, nolan, List.of(dicaprio, murphy));
        Pelicula oppenheimer = crearPelicula("Oppenheimer", 180, 16, nolan, List.of(murphy, damonStub(actorRepository))); 
        Pelicula pulpFiction = crearPelicula("Pulp Fiction", 154, 18, tarantino, List.of(travoltaStub(actorRepository), jacksonStub(actorRepository)));
        Pelicula dune = crearPelicula("Dune: Part Two", 166, 12, villeneuve, List.of(chalamet, zendaya));
        Pelicula taxiDriver = crearPelicula("Taxi Driver", 114, 18, scorsese, List.of(deniro));
        
        // 7. FUNCIONES (Simulamos cartelera)
        Funcion f1 = crearFuncion(inception, sala1, LocalDateTime.now().plusDays(1).withHour(18).withMinute(0), 12.50);
        Funcion f2 = crearFuncion(inception, sala1, LocalDateTime.now().plusDays(1).withHour(21).withMinute(30), 12.50);
        Funcion f3 = crearFuncion(dune, sala2, LocalDateTime.now().plusDays(2).withHour(17).withMinute(0), 10.00);
        Funcion f4 = crearFuncion(pulpFiction, sala3, LocalDateTime.now().plusDays(3).withHour(22).withMinute(0), 15.00); // VIP
        Funcion f5 = crearFuncion(oppenheimer, sala1, LocalDateTime.now().plusDays(4).withHour(19).withMinute(0), 11.00);

        // 8. VENTAS Y ENTRADAS (Simulamos compras)
        // Venta 1: Jaime compra 2 entradas para Inception
        crearVentaCompleta(user1, f1, List.of(
                crearEntradaStub(5, 10),
                crearEntradaStub(5, 11)
        ));

        // Venta 2: Maria compra 1 entrada VIP para Pulp Fiction
        crearVentaCompleta(user2, f4, List.of(
                crearEntradaStub(3, 1)
        ));

        // Venta 3: Pepe compra 3 entradas para Dune
        crearVentaCompleta(user3, f3, List.of(
                crearEntradaStub(7, 15),
                crearEntradaStub(7, 16),
                crearEntradaStub(7, 17)
        ));

        System.out.println("✅ Carga de datos completada con éxito.");
    }

    // --- MÉTODOS HELPER (Para limpiar el código principal) ---

    private Usuario crearUsuario(String email, String password, Rol rol) {
        return usuarioRepository.save(new Usuario(null, email, password, rol, new ArrayList<>()));
    }

    private Pelicula crearPelicula(String titulo, int duracion, int edad, Director director, List<Actor> actores) {
        Pelicula p = new Pelicula(null, titulo, duracion, edad, director, new ArrayList<>(actores), new ArrayList<>());
        return peliculaRepository.save(p);
    }

    private Funcion crearFuncion(Pelicula peli, Sala sala, LocalDateTime fecha, Double precio) {
        return funcionRepository.save(new Funcion(null, fecha, precio, peli, sala, new ArrayList<>()));
    }

    private void crearVentaCompleta(Usuario user, Funcion funcion, List<Entrada> entradasBase) {
        Venta venta = new Venta();
        venta.setUsuario(user);
        venta.setFecha(LocalDateTime.now());
        venta.setMetodoPago("TARJETA");
        venta.setEstado("PAGADA");
        
        // Calculamos total
        double total = funcion.getPrecio() * entradasBase.size();
        venta.setTotal(total);

        // Asignamos las entradas a la venta y función
        List<Entrada> entradasReales = new ArrayList<>();
        for (Entrada eBase : entradasBase) {
            eBase.setVenta(venta);
            eBase.setFuncion(funcion);
            eBase.setCodigo(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            eBase.setEstado(EstadoEntrada.PAGADA); // Asumiendo que tienes este valor en el Enum
            entradasReales.add(eBase);
        }
        venta.setEntradas(entradasReales);

        // Al guardar la venta, cascade guardará las entradas
        ventaRepository.save(venta);
    }

    // Helpers rápidos para entradas sin relaciones (DTO style interno)
    private Entrada crearEntradaStub(int fila, int asiento) {
        Entrada e = new Entrada();
        e.setFila(fila);
        e.setAsiento(asiento);
        return e;
    }

    // Stubs para actores extra si no queremos crear variables arriba
    private Actor damonStub(ActorRepository repo) {
        return repo.findByNombre("Matt Damon")
                .orElseGet(() -> repo.save(new Actor(null, "Matt Damon", new ArrayList<>())));
    }
    
    private Actor travoltaStub(ActorRepository repo) {
         return repo.save(new Actor(null, "John Travolta", new ArrayList<>()));
    }
    
    private Actor jacksonStub(ActorRepository repo) {
         return repo.save(new Actor(null, "Samuel L. Jackson", new ArrayList<>()));
    }
}