package transporte.servicio;

import transporte.modelo.*;
import transporte.excepciones.*;

import java.util.*;
import java.io.*;

/**
 * Clase que representa la lógica de negocio para gestionar buses y viajes.
 */
public class Empresa {

    private String nombre;
    private List<Viaje> viajes;
    private Map<String, Bus> buses;
    private Map<String, List<Viaje>> viajesPorDestino;

    /**
     * Constructor para inicializar la empresa con su nombre.
     */
    public Empresa(String nombre) {
        this.nombre = nombre;
        this.viajes = new ArrayList<>();
        this.buses = new HashMap<>();
        this.viajesPorDestino = new HashMap<>();
    }

    // ================= GETTERS =================

    /**
     * @return Nombre de la empresa.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve una lista inmutable de los viajes.
     * @return Lista de viajes.
     */
    public List<Viaje> listarViajes() {
        return Collections.unmodifiableList(viajes);
    }

    /**
     * Devuelve un mapa inmutable de los buses.
     * @return Mapa de buses.
     */
    public Map<String, Bus> listarBuses() {
        return Collections.unmodifiableMap(buses);
    }

    /**
     * Devuelve el número total de buses registrados.
     * @return Cantidad de buses.
     */
    public int cantidadBuses() {
        return buses.size();
    }

    /**
     * Devuelve el número total de viajes registrados.
     * @return Cantidad de viajes.
     */
    public int cantidadViajes() {
        return viajes.size();
    }

    /**
     * Devuelve un mapa inmutable de viajes por destino.
     * @return Mapa de viajes por destino.
     */
    public Map<String, List<Viaje>> listarViajesPorDestino() {
        Map<String, List<Viaje>> destinosInmutables = new HashMap<>();
        for (var entry : viajesPorDestino.entrySet()) {
            destinosInmutables.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        return Collections.unmodifiableMap(destinosInmutables);
    }

    // ================= MÉTODOS PARA BUSES =================

    /**
     * Agrega un nuevo bus al sistema.
     * @param b Bus a agregar.
     * @throws DatosInvalidosException Si el bus ya existe.
     */
    public void agregarBus(Bus b) throws DatosInvalidosException {
        if (buses.containsKey(b.getPatente())) {
            throw new DatosInvalidosException("Ya existe un bus con la patente " + b.getPatente());
        }
        buses.put(b.getPatente(), b);
    }

    public Bus buscarBus(String patente) {
        return buses.get(patente);
    }

    public void eliminarBus(String patente) throws BusNoEncontradoException {
        if (!buses.containsKey(patente)) {
            throw new BusNoEncontradoException("Bus no encontrado");
        }
        buses.remove(patente);
    }

    // ================= MÉTODOS PARA VIAJES =================

    public void agregarViaje(Viaje v) {
        viajes.add(v);
        viajesPorDestino.computeIfAbsent(v.getDestino(), k -> new ArrayList<>()).add(v);
    }

    public Viaje buscarViaje(int id) {
        return viajes.stream().filter(v -> v.getId() == id).findFirst().orElse(null);
    }

    public void eliminarViaje(int id) throws ViajeNoEncontradoException {
        Viaje v = buscarViaje(id);
        if (v == null) {
            throw new ViajeNoEncontradoException("Viaje no encontrado");
        }
        viajes.remove(v);
    }

    public void editarViaje(int id, String destino, double costo, double precio) throws ViajeNoEncontradoException {
        Viaje v = buscarViaje(id);
        if (v == null) {
            throw new ViajeNoEncontradoException("Viaje no encontrado");
        }
        if (destino != null && !destino.trim().isEmpty()) {
            v.setDestino(destino);
        }
        v.setCostoViaje(Math.max(costo, 0));
        v.setPrecioPasaje(Math.max(precio, 0));
    }
}
