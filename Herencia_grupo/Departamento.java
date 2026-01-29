import java.util.ArrayList;
import java.util.List;

public class Departamento {
    private String nombre;
    private String jefeDepartamento;
    private double presupuestoAnual;
    private List<String> empleados;

    public Departamento(String nombre, String jefeDepartamento, double presupuestoAnual) {
        this.nombre = nombre;
        this.jefeDepartamento = jefeDepartamento;
        this.presupuestoAnual = presupuestoAnual;
        this.empleados = new ArrayList<>();
    }

    /**
     * Añade un nuevo empleado al departamento si no está ya en la lista.
     */
    public void agregarEmpleado(String idEmpleado) {
        if (!empleados.contains(idEmpleado)) {
            empleados.add(idEmpleado);
        }
    }

    /**
     * Calcula la suma de todos los salarios de los empleados del departamento.
     * Requiere un Gestor de Empleados para obtener los objetos Empleado por su ID.
     */
    public double calcularNominaDepartamento(GestorEmpleados gestor) {
        double nominaTotal = 0;
        for (String id : empleados) {
            Empleado emp = gestor.obtenerEmpleado(id);
            if (emp != null) {
                nominaTotal += emp.calcularSalario();
            }
        }
        return nominaTotal;
    }

    /**
     * Devuelve la lista de IDs de los empleados.
     */
    public List<String> listarEmpleados() {
        return new ArrayList<>(empleados);
    }


    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getJefeDepartamento() { return jefeDepartamento; }
    public void setJefeDepartamento(String jefeDepartamento) { this.jefeDepartamento = jefeDepartamento; }

    public double getPresupuestoAnual() { return presupuestoAnual; }
    public void setPresupuestoAnual(double presupuestoAnual) { this.presupuestoAnual = presupuestoAnual; }
}
