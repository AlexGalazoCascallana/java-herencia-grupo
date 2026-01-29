import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorFichajes {

    private Map<String, List<Fichaje>> fichajes;
    private long contadorIds;
  
    public GestorFichajes() {
        this.fichajes = new HashMap<>();
        this.contadorIds = 1;
    }

    /**
     * Registra una ENTRADA nueva para el empleado.
     */
    public void registrarEntrada(String idEmpleado) {
        fichajes.putIfAbsent(idEmpleado, new ArrayList<>());

        Fichaje nuevoFichaje = new Fichaje(contadorIds++, idEmpleado, Fichaje.TipoFichaje.ENTRADA);
        
        fichajes.get(idEmpleado).add(nuevoFichaje);
        System.out.println("Entrada registrada para: " + idEmpleado);
    }

    /**
     * Registra la SALIDA cerrando el último fichaje abierto.
     */
    public void registrarSalida(String idEmpleado) {
        List<Fichaje> listaEmpleado = fichajes.get(idEmpleado);

        if (listaEmpleado != null && !listaEmpleado.isEmpty()) {
            Fichaje ultimoFichaje = listaEmpleado.get(listaEmpleado.size() - 1);

            if (ultimoFichaje.getTipo() == Fichaje.TipoFichaje.ENTRADA && 
                ultimoFichaje.getFechaHoraSalida() == null) {
                
                ultimoFichaje.cerrarFichaje();
                System.out.println("Salida registrada para: " + idEmpleado);
            } else {
                System.out.println("Error: No hay una entrada abierta para cerrar.");
            }
        } else {
            System.out.println("Error: El empleado no tiene historial de fichajes.");
        }
    }

    /**
     * Devuelve una lista de fichajes de un día específico.
     */
    public List<Fichaje> obtenerFichajesDelDia(String idEmpleado, LocalDate fecha) {
        List<Fichaje> todos = fichajes.getOrDefault(idEmpleado, new ArrayList<>());
        
        // Filtramos usando Streams (Java 8+)
        return todos.stream()
                .filter(f -> f.getFechaHoraEntrada().toLocalDate().equals(fecha))
                .collect(Collectors.toList());
    }

    /**
     * Calcula el total de horas trabajadas en un mes y año específicos.
     * @return double con el total de horas (ej: 8.5 horas).
     */
    public double calcularHorasMensuales(String idEmpleado, int mes, int anio) {
        List<Fichaje> lista = fichajes.get(idEmpleado);
        if (lista == null) return 0.0;

        double totalHoras = 0;

        for (Fichaje f : lista) {
            LocalDateTime entrada = f.getFechaHoraEntrada();
            
            if (entrada.getMonthValue() == mes && entrada.getYear() == anio) {
                if (f.getFechaHoraSalida() != null) {
                    Duration duracion = Duration.between(entrada, f.getFechaHoraSalida());
                    totalHoras += duracion.toMinutes() / 60.0;
                }
            }
        }
        return totalHoras;
    }

    /**
     * Genera un reporte en texto.
     */
    public String generarReporteAsistencia(String idEmpleado) {
        List<Fichaje> lista = fichajes.get(idEmpleado);
        if (lista == null || lista.isEmpty()) {
            return "El empleado " + idEmpleado + " no tiene registros.";
        }

        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE ASISTENCIA: ").append(idEmpleado).append(" ===\n");
        
        for (Fichaje f : lista) {
            reporte.append(f.toString()).append("\n");
        }
        
        reporte.append("==========================================");
        return reporte.toString();
    }
}
