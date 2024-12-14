package Tarjeta;

import EntidadBancaria.cuentaBancaria;
import jakarta.persistence.*;

@Entity
public class tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int numTarjeta;
    private String tipoTarjeta;

   // private String fechaExpiracion;
   // private String nombreTitular;
   // private int codTarjeta;


    @OneToOne
    private cuentaBancaria usuarioTitular;

    public tarjeta() {
    }
    public tarjeta( String tipoTarjeta, cuentaBancaria usuarioTitular) {
        this.tipoTarjeta=tipoTarjeta;
        this.usuarioTitular=usuarioTitular;
    }
    public int getNumTarjeta() {

        return numTarjeta;
    }
    public void setNumTarjeta(int numTarjeta) {

        this.numTarjeta = numTarjeta;
    }

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public cuentaBancaria getUsuarioTitular() {
        return usuarioTitular;
    }

    public void setUsuarioTitular(cuentaBancaria usuarioTitular) {
        this.usuarioTitular = usuarioTitular;
    }
}
