import java.time.LocalDate;
import java.time.Period;

public class Empleado extends Persona {
    private String idEmpleado;
    private LocalDate fechaContratacion;
    private String departamento;
    private double jornadaHoraria;
    private boolean activo;
    private double salario;
    private double salarioBase;

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public double getJornadaHoraria() {
        return jornadaHoraria;
    }

    public void setJornadaHoraria(double jornadaHoraria) {
        this.jornadaHoraria = jornadaHoraria;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Empleado(String DNI, String Nombre, LocalDate fechaNacimiento, String email, String telefono,
                    String idEmpleado, LocalDate fechaContratacion, String departamento,
                    double jornadaHoraria, double salarioBase) {
        
        super(DNI, Nombre, fechaNacimiento, email, telefono);
        this.Nombre = Nombre;
        this.idEmpleado = idEmpleado;
        this.fechaContratacion = fechaContratacion;
        this.departamento = departamento;
        this.jornadaHoraria = jornadaHoraria;
        this.salarioBase = salarioBase;
        this.activo = true;
    }

    /**
     * Calcula los años transcurridos desde la fecha de contratación.
     */
    public int calcularAntiguedad() {
        if (fechaContratacion == null) return 0;
        return Period.between(fechaContratacion, LocalDate.now()).getYears();
    }

    /**
     * Calcula el salario aplicando un plus del 3% por cada año de antigüedad.
     * Fórmula: SalarioBase + (SalarioBase * 0.03 * Años)
     */
    public double calcularSalario() {
        int años = calcularAntiguedad();
        return salarioBase + (salarioBase * 0.03 * años);
    }



    @Override
    public String toString() {
        return "=== FICHA DE EMPLEADO ===\n" +
               "ID: " + idEmpleado + " | Estado: " + (activo ? "ACTIVO" : "BAJA") + "\n" +
               "Nombre: " + Nombre + "\n" +
               "DNI: " + getDNI() + "\n" +
               "Departamento: " + departamento + "\n" +
               "Antigüedad: " + calcularAntiguedad() + " años (Desde: " + fechaContratacion + ")\n" +
               "Jornada: " + jornadaHoraria + "h/día\n" +
               "Salario Total: " + String.format("%.2f", calcularSalario()) + "€";
    }
}
