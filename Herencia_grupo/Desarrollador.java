public class Desarrollador extends Empleado {   // Atributo privado
    private String lenguajePrincipal;

    // Constructor completo
    public Desarrollador(String nombre, double salarioBase, String lenguajePrincipal) {
        super(nombre, salarioBase); // Llama al constructor de la clase padre Empleado
        this.lenguajePrincipal = lenguajePrincipal;
    }

    // Getter para lenguajePrincipal
    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }

    // Setter para lenguajePrincipal
    public void setLenguajePrincipal(String lenguajePrincipal) {
        this.lenguajePrincipal = lenguajePrincipal;
    }

    // Sobrescritura del método calcularSalario()
    @Override
    public double calcularSalario() {
        // Ejemplo: se añade un bono del 10% por especialización técnica
        return getSalarioBase() * 1.10;
    }
}
