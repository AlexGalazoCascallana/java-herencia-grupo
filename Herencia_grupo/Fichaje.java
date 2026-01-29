import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class Fichaje {

    public enum TipoFichaje {
        ENTRADA, SALIDA, PAUSA
    }

    private long idFichaje;
    private String idEmpleado;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    private TipoFichaje tipo;

    /**
     * Constructor para iniciar un nuevo fichaje.
     * La fecha de salida se inicializa en null porque el empleado acaba de entrar.
     */
    public Fichaje(long idFichaje, String idEmpleado, TipoFichaje tipo) {
        this.idFichaje = idFichaje;
        this.idEmpleado = idEmpleado;
        this.tipo = tipo;
        this.fechaHoraEntrada = LocalDateTime.now();
        this.fechaHoraSalida = null;
    }

    /**
     * Registra la salida con la fecha y hora actuales.
     */
    public void cerrarFichaje() {
        this.fechaHoraSalida = LocalDateTime.now();
    }

    /**
     * Calcula el tiempo transcurrido entre la entrada y la salida.
     * @return String con el tiempo formateado o mensaje si sigue activo.
     */
    public String obtenerDuracion() {
        if (fechaHoraSalida == null) {
            return "Fichaje en curso (no cerrado).";
        }
        
        Duration duracion = Duration.between(fechaHoraEntrada, fechaHoraSalida);
        long horas = duracion.toHours();
        long minutos = duracion.toMinutesPart();
        long segundos = duracion.toSecondsPart();

        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public long getIdFichaje() {
        return idFichaje;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public LocalDateTime getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public void setFechaHoraEntrada(LocalDateTime fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public TipoFichaje getTipo() {
        return tipo;
    }

    public void setTipo(TipoFichaje tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fin = (fechaHoraSalida != null) ? fechaHoraSalida.format(formatter) : "En curso";
        
        return "Fichaje [" + idFichaje + "] | Empleado: " + idEmpleado + 
               " | Tipo: " + tipo + 
               " | Inicio: " + fechaHoraEntrada.format(formatter) + 
               " | Fin: " + fin;
    }
}
