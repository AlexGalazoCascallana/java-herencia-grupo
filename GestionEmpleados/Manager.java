public class Manager extends Empleado {
    protected int personasACargo;
    

    public Manager(String dni, String nombre, int edad, 
                  double salarioBase, int antiguedadAnios, 
                  int personasACargo) {
        super(dni, nombre, edad, salarioBase, antiguedadAnios);
        this.personasACargo = personasACargo;
    }
    
    public int getPersonasACargo() {
        return personasACargo;
    }
    
    public void setPersonasACargo(int personasACargo) {
        this.personasACargo = personasACargo;
    }
    
    @Override
    public double calcularSalario() {
        // Los managers tienen un bono por personas a cargo (5% por persona)
        double salarioEmpleado = super.calcularSalario(); // Llama al método de Empleado
        double bonoPorPersonas = salarioBase * 0.05 * personasACargo;
        return salarioEmpleado + bonoPorPersonas;
    }
    
    @Override
    public String toString() {
        return "Manager: " + nombre + 
               "\nDNI: " + getDni() + 
               "\nEdad: " + edad + 
               "\nPersonas a cargo: " + personasACargo +
               "\nAntigüedad: " + antiguedadAnios + " años" +
               "\nSalario base: " + salarioBase + "€" +
               "\nSalario total: " + String.format("%.2f", calcularSalario()) + "€";
    }
}