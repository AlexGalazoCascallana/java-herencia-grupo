public class Desarrollador extends Empleado {   // Atributo privado
    private String lenguajePrincipal;
    private String nivel;
    private List<String> tecnologias;
    private double horasExtras;


    // Constructor completo
    public Desarrollador(String nombre, double salarioBase, String lenguajePrincipal, String nivel) {
        super(nombre, salarioBase); // Llama al constructor de la clase padre Empleado
        this.lenguajePrincipal = lenguajePrincipal;
        this.tecnologias = new Arraylist<>();
        this.horasExtras = 0.0;

    }

    // Getter para lenguajePrincipal
    public String getLenguajePrincipal() {
        return lenguajePrincipal;
    }

    // Setter para lenguajePrincipal
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

    // Getter para tecnologías (lista inmutable para seguridad)
    public List<String> getTecnologias() {
        return Collections.unmodifiableList(tecnologias);
    }

    // Método para añadir tecnologías
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

    // Getter para horasExtra
    public double getHorasExtra() {
        return horasExtra;
    }

    // Método para registrar horas extra
    public void registrarHoraExtra(double horas) {
        if (horas < 0) {
            throw new IllegalArgumentException("Las horas extra no pueden ser negativas");
        }
        this.horasExtra += horas;
    }

    // Sobrescritura del método calcularSalario()
    @Override
    public double calcularSalario() {
        double salarioBase = getSalarioBase();

        // Bono por especialización técnica (10%)
        double salarioConBonoEspecializacion = salarioBase * 1.10;

        // Plus por nivel
        double plusNivel = 0.0;
        switch (nivel.toLowerCase()) {
            case "mid":
                plusNivel = salarioBase * 0.15;
                break;
            case "senior":
                plusNivel = salarioBase * 0.30;
                break;
            // Junior: +0%, así que no se hace nada
        }

        // Pago por horas extra: 20€/hora
        double pagoHorasExtra = horasExtra * 20.0;

        return salarioConBonoEspecializacion + plusNivel + pagoHorasExtra;
    }

    // Opcional: toString para depuración o visualización
    @Override
    public String toString() {
        return "Desarrollador{" +
                "nombre='" + getNombre() + '\'' +
                ", salarioBase=" + getSalarioBase() +
                ", lenguajePrincipal='" + lenguajePrincipal + '\'' +
                ", nivel='" + nivel + '\'' +
                ", tecnologias=" + tecnologias +
                ", horasExtra=" + horasExtra +
                ", salarioCalculado=" + calcularSalario() +
                '}';
    }
}
