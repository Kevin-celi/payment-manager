package EntidadBancaria;

import jakarta.persistence.*;
import Persona.*;

@Entity
public class cuentaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int ID_Banco;
    private String nombreBanco;
    private String numeroCuenta;
    private double saldo;
    private String tipoCuenta;

    @ManyToOne
    private Usuario usuario;

    public cuentaBancaria() {
    }
    public cuentaBancaria( String nombreBanco,
                           String numeroCuenta,
                           double saldo,
                           String tipoCuenta,
                           Usuario usuario) {
        this.nombreBanco = nombreBanco;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
        this.tipoCuenta = tipoCuenta;
        this.usuario = usuario;
    }

    public int getID_Banco() {

        return ID_Banco;
    }

    public void setID_Banco(int ID_Banco) {

        this.ID_Banco = ID_Banco;
    }

    public String getNombreBanco() {

        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {

        this.nombreBanco = nombreBanco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {

        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {

        this.saldo = saldo;
    }

    public String getTipoCuenta() {

        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {

        this.tipoCuenta = tipoCuenta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
