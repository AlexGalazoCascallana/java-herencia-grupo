import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static GestorFichajes gestorFichajes = new GestorFichajes();
    private static Map<String, Empleado> empleados = new HashMap<>();
    private static Map<String, Departamento> departamentos = new HashMap<>();
    private static List<Proyecto> proyectos = new ArrayList<>();

    public static void main(String[] args) {
        inicializarDatos();

        int opcionPrincipal = 0;
        do {
            imprimirMenuPrincipal();
            opcionPrincipal = leerEntero();

            switch (opcionPrincipal) {
                case 1 -> menuContratacion();
                case 2 -> menuFichaje();
                case 3 -> menuNominas();
                case 4 -> menuProyectos();
                case 5 -> menuInformes();
                case 6 -> System.out.println("Saliendo del sistema... ¡Adiós!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcionPrincipal != 6);
    }

    private static void menuContratacion() {
        System.out.println("\n1.1. Contratar empleado\n1.2. Asignar a departamento\n2.3. Ver contratos activos");
        double subOp = leerDouble();
        if (subOp == 1.1) {
            System.out.print("DNI: "); String dni = sc.next();
            System.out.print("Nombre: "); String nombre = sc.next();
            System.out.print("Salario Base: "); double salario = leerDouble();
            // Simplificado para el ejemplo: creamos empleado con datos fijos
            Empleado nuevo = new Empleado(dni, nombre, LocalDate.of(1990,1,1), "e@mail.com", "600", 
                                          "EMP-"+dni, LocalDate.now(), "General", 8.0, salario);
            empleados.put(dni, nuevo);
            System.out.println("Empleado contratado.");
        } else if (subOp == 1.2) {
            System.out.print("DNI Empleado: "); String dni = sc.next();
            System.out.print("Nombre Departamento: "); String dep = sc.next();
            departamentos.putIfAbsent(dep, new Departamento(dep, "Jefe", 100000));
            departamentos.get(dep).agregarEmpleado(dni);
            System.out.println("Asignado correctamente.");
        } else if (subOp == 2.3) {
            empleados.values().forEach(e -> System.out.println(e.getNombre() + " [" + e.getDNI() + "] - Activo: " + e.isActivo()));
        }
    }

    private static void menuFichaje() {
        System.out.println("\n2.1. Entrada\n2.2. Salida\n2.3. Consultar fichajes\n2.4. Reporte mensual");
        double subOp = leerDouble();
        System.out.print("Introduce tu DNI: ");
        String dni = sc.next();

        if (subOp == 2.1) gestorFichajes.registrarEntrada(dni);
        else if (subOp == 2.2) gestorFichajes.registrarSalida(dni);
        else if (subOp == 2.3) System.out.println(gestorFichajes.generarReporteAsistencia(dni));
        else if (subOp == 2.4) {
            double horas = gestorFichajes.calcularHorasMensuales(dni, LocalDate.now().getMonthValue(), LocalDate.now().getYear());
            System.out.println("Total horas mes actual: " + String.format("%.2f", horas));
        }
    }

    private static void menuNominas() {
        System.out.println("\n3.1. Calcular salario empleado\n3.2. Calcular nómina total");
        double subOp = leerDouble();
        if (subOp == 3.1) {
            System.out.print("DNI: "); String dni = sc.next();
            if(empleados.containsKey(dni)) System.out.println("Salario Total: " + empleados.get(dni).calcularSalario() + "€");
        } else if (subOp == 3.2) {
            double total = empleados.values().stream().mapToDouble(Empleado::calcularSalario).sum();
            System.out.println("Coste total de nóminas: " + total + "€");
        }
    }

    private static void menuProyectos() {
        System.out.println("\n4.1. Asignar proyecto\n4.2. Registrar horas\n4.3. Ver productividad");
        double subOp = leerDouble();
        if (proyectos.isEmpty()) { System.out.println("No hay proyectos."); return; }
        Proyecto p = proyectos.get(0); // Usamos el primero para el ejemplo
        
        if (subOp == 4.1) {
            System.out.print("DNI: "); p.asignarDesarrollador(sc.next());
        } else if (subOp == 4.2) {
            System.out.print("DNI: "); String id = sc.next();
            System.out.print("Horas: "); p.registrarHoras(id, leerDouble());
        } else if (subOp == 4.3) {
            System.out.println(p.toString());
        }
    }

    private static void menuInformes() {
        System.out.println("\n5.1. Empleados por dep\n5.4. Proyectos activos");
        double subOp = leerDouble();
        if (subOp == 5.1) departamentos.values().forEach(d -> System.out.println(d.getNombre() + ": " + d.listarEmpleados()));
        else if (subOp == 5.4) proyectos.forEach(System.out::println);
    }

    private static void imprimirMenuPrincipal() {
        System.out.println("\n================================");
        System.out.println("  SISTEMA DE GESTIÓN Y FICHAJE  ");
        System.out.println("================================");
        System.out.println("1. CONTRATACIÓN");
        System.out.println("2. FICHAJE");
        System.out.println("3. NÓMINAS");
        System.out.println("4. PROYECTOS");
        System.out.println("5. INFORMES");
        System.out.println("6. SALIR");
        System.out.print("Seleccione una opción: ");
    }

    private static int leerEntero() {
        try { return Integer.parseInt(sc.next()); } 
        catch (Exception e) { return -1; }
    }

    private static double leerDouble() {
        try { return Double.parseDouble(sc.next()); } 
        catch (Exception e) { return -1.0; }
    }

    private static void inicializarDatos() {
        proyectos.add(new Proyecto("PRJ-01", "Web Corporativa", 200.0));
        System.out.println("Sistema inicializado con datos base.");
    }
}
