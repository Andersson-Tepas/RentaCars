package Entidades;

public class Coche {
    private int idCoche;
    private String Marca;
    private String Modelo;
    private String Placa;
    private int Año;
    private String Color;

    public Coche(int idCoche, String marca, String modelo, String placa, int año, String color) {
        this.idCoche = idCoche;
        Marca = marca;
        Modelo = modelo;
        Placa = placa;
        Año = año;
        Color = color;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String placa) {
        Placa = placa;
    }

    public int getAño() {
        return Año;
    }

    public void setAño(int año) {
        Año = año;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
