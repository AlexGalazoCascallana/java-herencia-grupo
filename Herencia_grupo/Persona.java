public class Persona{
    private Sting DNI;
    protected String Nombre;
    protected int Edad;

    public Persona (Sting DNI, Sting Nombre, int Edad){
        this.DNI = DNI;
        this.Nombre = Nombre;
        this.Edad = Edad;
    }

    public String getDNI(){
        return DNI;
    }

    public String getNombre(){
        return Nombre;
    }

    public int getEdad(){
        return Edad;
    }
    
    public String presentarse(){
        return "Hola, mi nombre es " + Nombre + ", tengo " + Edad + " años y mi DNI es " + DNI + ".";
    }

}   