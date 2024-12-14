package Registros;

import EntidadBancaria.*;
import Producto.Automovil;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class RegistrosPago {
    @Id
    private int idRegistro;
    private String fecha;
    private double monto;
    private String estado;
    private String metodo;

    @ManyToOne
    private cuentaBancaria cuenta;
    @OneToMany
    private List<Automovil> automovil;





    public RegistrosPago() {
    }
    public RegistrosPago(int idRegistro,
                         String fecha,
                         double monto,
                         String estado,
                         String metodo,
                         cuentaBancaria cuenta,
                         List<Automovil> automovil
    ) {
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.metodo=metodo;
        this.cuenta =cuenta;
        this.automovil=automovil;
      //  this.metodoPagoUtilizado=metodoPagoUtilizado;
      //  this.usuario = usuario;
       // this.automovil = automovil;

    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public cuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(cuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    public List<Automovil> getAutomovil() {
        return automovil;
    }

    public void setAutomovil(List<Automovil> automovil) {
        this.automovil = automovil;
    }
}
