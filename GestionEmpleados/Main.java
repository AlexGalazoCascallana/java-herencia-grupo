
public class Main {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DE HERENCIA Y ENCAPSULACIÓN ===\n");
        
        System.out.println("1. Creando una Persona:");
        Persona persona = new Persona("12345678A", "Carlos López", 30);
        System.out.println(persona.presentarse());
        System.out.println("DNI (usando getter): " + persona.getDni());
        System.out.println();
        
        System.out.println("2. Creando un Empleado:");
        Empleado empleado = new Empleado("87654321B", "Ana García", 35, 25000, 5);
        System.out.println(empleado.toString());
        System.out.println();
        
        System.out.println("3. Creando un Desarrollador:");
        Desarrollador desarrollador = new Desarrollador("11111111C", "Pedro Martínez", 28, 
                                                        30000, 3, "Java");
        System.out.println(desarrollador.toString());
        System.out.println("Lenguaje principal (usando getter): " + desarrollador.getLenguajePrincipal());
        
        desarrollador.setLenguajePrincipal("Python");
        System.out.println("Nuevo lenguaje principal (después de setter): " + desarrollador.getLenguajePrincipal());
        System.out.println();
        
        System.out.println("4. Creando un Manager:");
        Manager manager = new Manager("22222222D", "Laura Sánchez", 42, 
                                      40000, 10, 5);
        System.out.println(manager.toString());
        System.out.println("Personas a cargo (usando getter): " + manager.getPersonasACargo());
        
        manager.setPersonasACargo(8);
        System.out.println("Nuevas personas a cargo (después de setter): " + manager.getPersonasACargo());
        System.out.println("Nuevo salario: " + String.format("%.2f", manager.calcularSalario()) + "€");
        System.out.println();
        
        System.out.println("5. Demostración de Polimorfismo:");
        System.out.println("Usando presentarse() desde diferentes tipos:");
        
        Persona p1 = new Persona("33333333E", "Persona Genérica", 25);
        Persona p2 = new Empleado("44444444F", "Empleado como Persona", 40, 20000, 2);
        Persona p3 = new Desarrollador("55555555G", "Desarrollador como Persona", 32, 
                                       35000, 4, "JavaScript");
        Persona p4 = new Manager("66666666H", "Manager como Persona", 45, 
                                 45000, 12, 6);
        
        System.out.println(p1.presentarse());
        System.out.println(p2.presentarse());
        System.out.println(p3.presentarse());
        System.out.println(p4.presentarse());
    }
}