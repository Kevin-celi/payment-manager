package Tarjeta;

import jakarta.persistence.EntityManager;

public class servicioTargeta {
    private EntityManager em;
    public servicioTargeta(EntityManager em) {
        this.em = em;
    }

    // Crear Targeta
    public tarjeta crearTarjeta(tarjeta tarjetaUno) {
        //Inicia una transacción
        this.em.getTransaction().begin();
        // Persiste los datos
        this.em.persist(tarjetaUno);
        //Cierra transaccion
        this.em.getTransaction().commit();
        return tarjetaUno;
    }
}
