package Entidades;

public class Cliente {
    private int idCliente;
    private String Nombre;
    private String Correo;
    private String Telefono;
    private String DUI;
    private String Direccion;
    private int idUsuario; // Asociación directa por ID

    public Cliente(int idCliente, String nombre, String correo, String telefono, String DUI, String direccion, int idUsuario) {
        this.idCliente = idCliente;
        Nombre = nombre;
        Correo = correo;
        Telefono = telefono;
        this.DUI = DUI;
        Direccion = direccion;
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDUI() {
        return DUI;
    }

    public void setDUI(String DUI) {
        this.DUI = DUI;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
