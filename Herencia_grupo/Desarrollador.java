import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Desarrollador extends Empleado {
    private String lenguajePrincipal;
    private String nivel;
    private ArrayList<String> tecnologias;
    public double horasExtras;


    public Desarrollador(String DNI, String Nombre, LocalDate fechaNacimiento, String email, String telefono,
                         String idEmpleado, LocalDate fechaContratacion, String departamento,
                         double jornadaHoraria, double salarioBase, String lenguajePrincipal, String nivel) {

        super(DNI, Nombre, fechaNacimiento, email, telefono, idEmpleado,
                fechaContratacion, departamento, jornadaHoraria, salarioBase);

        this.lenguajePrincipal = lenguajePrincipal;
        this.tecnologias = new ArrayList<>();
        this.horasExtras = 0.0;
        this.nivel = nivel;
    }

    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }

    public void setLenguajePrincipal(String lenguajePrincipal) {
        this.lenguajePrincipal = lenguajePrincipal;
    }

    public String getNivel(){
        return nivel;
    }

    public void setNivel(String nivel) {
        if ("Junior".equalsIgnoreCase(nivel) || 
            "Mid".equalsIgnoreCase(nivel) || 
            "Senior".equalsIgnoreCase(nivel)) {
            this.nivel = nivel.substring(0, 1).toUpperCase() + nivel.substring(1).toLowerCase();
        } else {
            throw new IllegalArgumentException("Nivel debe ser 'Junior', 'Mid' o 'Senior'");
        }
    }

    public List<String> getTecnologias() {
        return Collections.unmodifiableList(tecnologias);
    }

    public void agregarTecnologia(String tecnologia) {
        if (tecnologia != null && !tecnologia.trim().isEmpty()) {
            tecnologias.add(tecnologia.trim());
        }
    }

    public void agregarTecnologias(List<String> nuevasTecnologias) {
        if (nuevasTecnologias != null) {
            nuevasTecnologias.stream()
                .filter(t -> t != null && !t.trim().isEmpty())
                .forEach(t -> tecnologias.add(t.trim()));
        }
    }

    public double getHorasExtra() {
        return horasExtras;
    }

    public void registrarHoraExtra(double horas) {
        if (horas < 0) {
            throw new IllegalArgumentException("Las horas extra no pueden ser negativas");
        }
        this.horasExtras += horas;
    }

    @Override
    public double calcularSalario() {
        double salarioBase = getSalarioBase();

        double salarioConBonoEspecializacion = salarioBase * 1.10;

        double plusNivel = 0.0;
        switch (nivel.toLowerCase()) {
            case "mid":
                plusNivel = salarioBase * 0.15;
                break;
            case "senior":
                plusNivel = salarioBase * 0.30;
                break;
        }
        double pagoHorasExtra = horasExtras * 20.0;

        return salarioConBonoEspecializacion + plusNivel + pagoHorasExtra;
    }

    @Override
    public String toString() {
        return "Desarrollador{" +
                "nombre='" + getNombre() + '\'' +
                ", salarioBase=" + getSalarioBase() +
                ", lenguajePrincipal='" + lenguajePrincipal + '\'' +
                ", nivel='" + nivel + '\'' +
                ", tecnologias=" + tecnologias +
                ", horasExtra=" + horasExtras +
                ", salarioCalculado=" + calcularSalario() +
                '}';
    }
}
