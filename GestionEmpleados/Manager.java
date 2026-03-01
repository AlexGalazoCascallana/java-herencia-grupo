import java.time.LocalDate;

public class Manager extends Empleado {
    private String titulo;
    private double presupuestoGestionado;
    private int reunionesSemanales;
    protected int personasACargo;

    public Manager(String DNI, String Nombre, LocalDate fechaNacimiento, String email, String telefono,
                   String idEmpleado, LocalDate fechaContratacion, String departamento, 
                   double jornadaHoraria, double salarioBase, int personasACargo, 
                   String titulo, double presupuestoGestionado, int reunionesSemanales) {
        
        super(DNI, Nombre, fechaNacimiento, email, telefono, idEmpleado, 
              fechaContratacion, departamento, jornadaHoraria, salarioBase);
        
        this.personasACargo = personasACargo;
        this.titulo = titulo;
        this.presupuestoGestionado = presupuestoGestionado;
        this.reunionesSemanales = reunionesSemanales;
    }

    /**
     * Sobrescribe el cálculo salarial incluyendo:
     * 1. Salario Base + Antigüedad (heredado de Empleado)
     * 2. Plus fijo por personas a cargo: 250€ por persona.
     * 3. Bonus por presupuesto: 0.2% del total gestionado.
     */
    @Override
    public double calcularSalario() {
        double salarioBaseConAntiguedad = super.calcularSalario();
        double plusPersonas = personasACargo * 250.0;
        double bonusPresupuesto = presupuestoGestionado * 0.002; // 0.2%
        
        return salarioBaseConAntiguedad + plusPersonas + bonusPresupuesto;
    }

    /**
     * Simula la aprobación de horas extra para un desarrollador.
     */
    public void aprobarHorasExtra(String idDesarrollador, double horas) {
        System.out.println("El Manager " + getNombre() + " (" + titulo + ") ha aprobado " 
                           + horas + " horas extra para el empleado " + idDesarrollador);
        // Aquí se podría conectar con una lista de "Horas Pendientes" en el futuro
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public double getPresupuestoGestionado() { return presupuestoGestionado; }
    public void setPresupuestoGestionado(double presupuesto) { this.presupuestoGestionado = presupuesto; }

    public int getPersonasACargo() { return personasACargo; }
    public void setPersonasACargo(int personas) { this.personasACargo = personas; }

    @Override
    public String toString() {
        return super.toString() + "\n" +
               "--- ROL: MANAGER (" + titulo + ") ---\n" +
               "Personas a cargo: " + personasACargo + "\n" +
               "Presupuesto bajo gestión: " + String.format("%.2f", presupuestoGestionado) + "€\n" +
               "Reuniones semanales: " + reunionesSemanales + "\n" +
               "Bonus Manager (Personas + Presupuesto): " + 
               String.format("%.2f", (personasACargo * 250.0 + presupuestoGestionado * 0.002)) + "€";
    }
}
