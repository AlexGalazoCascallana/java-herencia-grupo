import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    private String idProyecto;
    private String nombre;
    private List<String> desarrolladoresAsignados;
    private double horasEstimadas;
    private double horasReales;
    private String estado;

    public Proyecto(String idProyecto, String nombre, double horasEstimadas) {
        this.idProyecto = idProyecto;
        this.nombre = nombre;
        this.horasEstimadas = horasEstimadas;
        this.horasReales = 0.0;
        this.desarrolladoresAsignados = new ArrayList<>();
        this.estado = "PLANIFICADO";
    }

    /**
     * Añade un desarrollador a la lista de colaboradores del proyecto.
     */
    public void asignarDesarrollador(String idDesarrollador) {
        if (!desarrolladoresAsignados.contains(idDesarrollador)) {
            desarrolladoresAsignados.add(idDesarrollador);
            // Si estaba planificado y asignamos a alguien, suele pasar a curso
            if (this.estado.equals("PLANIFICADO")) {
                this.estado = "EN_CURSO";
            }
        }
    }

    /**
     * Registra el avance de horas de un desarrollador.
     * @param idDesarrollador ID del empleado que imputa horas.
     * @param horas Cantidad de horas a sumar.
     */
    public void registrarHoras(String idDesarrollador, double horas) {
        if (desarrolladoresAsignados.contains(idDesarrollador)) {
            this.horasReales += horas;
        } else {
            System.out.println("Error: El desarrollador " + idDesarrollador + " no está asignado a este proyecto.");
        }
    }

    /**
     * Calcula el porcentaje de eficiencia.
     * > 100%: Se hizo en menos tiempo del estimado (Alta productividad).
     * < 100%: Se han superado las horas previstas.
     */
    public double calcularProductividad() {
        if (horasReales == 0) return 0.0;
        return (horasEstimadas / horasReales) * 100;
    }

    public String getEstado() { return estado; }

    public void setEstado(String estado) { 
        this.estado = estado; 
    }

    public double getHorasReales() { return horasReales; }

    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return "PROYECTO: " + nombre + " [" + idProyecto + "]\n" +
               "Estado: " + estado + "\n" +
               "Horas Estimadas: " + horasEstimadas + "h | Reales: " + horasReales + "h\n" +
               "Productividad: " + String.format("%.2f", calcularProductividad()) + "%\n" +
               "Desarrolladores: " + desarrolladoresAsignados.size();
    }
}
