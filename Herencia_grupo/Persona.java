import java.time.LocalDate;
import java.time.Period;

public class Persona {
    private String DNI;
    protected String Nombre;
    private LocalDate fechaNacimiento;
    private String email;
    private String telefono;

    public Persona(String DNI, String Nombre, LocalDate fechaNacimiento, String email, String telefono) {
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.telefono = telefono;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Calcula la edad basándose en la fecha de nacimiento y la fecha actual.
     * @return int con los años cumplidos.
     */
    
    public int calcularEdad() {
        if (this.fechaNacimiento == null) {
            return 0;
        }
        LocalDate fechaActual = LocalDate.now();
        return Period.between(this.fechaNacimiento, fechaActual).getYears();
    }

    public int getEdad() {
        return calcularEdad();
    }

    public String presentarse() {
        return "Hola, mi nombre es " + Nombre + ", tengo " + calcularEdad() + 
               " años, mi DNI es " + DNI + 
               " y puedes contactarme en " + email + ".";
    }
}
