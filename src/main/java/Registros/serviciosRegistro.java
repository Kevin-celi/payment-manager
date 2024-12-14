package Registros;

import Persona.Usuario;
import jakarta.persistence.EntityManager;

public class serviciosRegistro {
    private EntityManager em;
    public serviciosRegistro(EntityManager em) {
        this.em = em;
    }

    // Crear Registros del los pagos
    public RegistrosPago crearRegistro(RegistrosPago registros) {
        //Inicia una transacción
        this.em.getTransaction().begin();
        this.em.persist(registros);
        this.em.getTransaction().commit();
        return registros;
    }

    //Leer Cuenta Registro de pago
    public RegistrosPago leerRegistros(int id) {
        return this.em.find(RegistrosPago.class, id);
    }

    // Actualizar Estado
    public void actualizarEstado(RegistrosPago registro) {
        //Inicia una transacción
        this.em.getTransaction().begin();
        // Persiste los cambios
        this.em.merge(registro);
        // Finaliza la transacción
        this.em.getTransaction().commit();
    }

}
