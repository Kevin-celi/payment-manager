package Persona;

import jakarta.persistence.EntityManager;

import java.util.List;

public class servicioUsuario {

    private EntityManager em;

    public servicioUsuario(EntityManager em) {
        this.em = em;
    }

    // Crear Usuario
    public Usuario crearUsuario(Usuario usuario) {
        //Inicia una transacción
        this.em.getTransaction().begin();
        this.em.persist(usuario);
        this.em.getTransaction().commit();
        return usuario;
    }

    //Leer Usuario
    public Usuario leertUsuario(int id) {
        return this.em.find(Usuario.class, id);
    }

    //Leer los datos de un unico estudiante
    public List<Usuario> leerUsuarioUno(int minId) {
        return this.em.createQuery("SELECT s FROM Usuario s WHERE s.id = :minId", Usuario.class)
                .setParameter("minId", minId)
                .getResultList();
    }

    // Actualizar Usuario
    public void actualizarUsuario(Usuario usuario) {
        //Inicia una transacción
        this.em.getTransaction().begin();
        // Persiste los cambios
        this.em.merge(usuario);
        // Finaliza la transacción
        this.em.getTransaction().commit();
    }
}
