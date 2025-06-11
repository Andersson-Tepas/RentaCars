package Entidades;

public class Usuario {
    private int IdUsuario;
    private String Nombre;
    private String ContrasenaHash;
    private String Correo;
    private int Estado ;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String contrasenaHash, String correo, int estado) {
        IdUsuario = idUsuario;
        Nombre = nombre;
        ContrasenaHash = contrasenaHash;
        Correo = correo;
        Estado = estado;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContrasenaHash() {
        return ContrasenaHash;
    }

    public void setContrasenaHash(String contrasenaHash) {
        ContrasenaHash = contrasenaHash;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }
}
