// Empleado.java
public class Empleado extends Persona {
    protected double salarioBase;
    protected int antiguedadAnios;
    

    public Empleado(String dni, String nombre, int edad, double salarioBase, int antiguedadAnios) {
        super(dni, nombre, edad); 
        this.salarioBase = salarioBase;
        this.antiguedadAnios = antiguedadAnios;
    }

    public double calcularSalario() {
        return salarioBase + (salarioBase * 0.02 * antiguedadAnios);
    }
    
    @Override
    public String toString() {
        return "Empleado: " + nombre + 
               "\nDNI: " + getDni() + 
               "\nEdad: " + edad + 
               "\nAntigüedad: " + antiguedadAnios + " años" +
               "\nSalario base: " + salarioBase + "€" +
               "\nSalario total: " + String.format("%.2f", calcularSalario()) + "€";
    }
}