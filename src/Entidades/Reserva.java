package Entidades;

import java.util.Date;

public class Reserva {
    private int idReserva;
    private int idCliente;
    private int idCoche;
    private Date FechaInicio;
    private Date FechaFin;
    private int Estado;

    public Reserva(int idReserva, int idCliente, int idCoche, Date fechaInicio, Date fechaFin, int estado) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idCoche = idCoche;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
        Estado = estado;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCoche() {
        return idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        FechaFin = fechaFin;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }
}
