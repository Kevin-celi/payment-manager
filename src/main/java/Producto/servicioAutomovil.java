package Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class servicioAutomovil {

    private EntityManager em;

    public servicioAutomovil(EntityManager em) {
        this.em = em;
    }

    // Crear Usuario
    public void crearAuto(Automovil automovil) {
        this.em.getTransaction().begin();
        this.em.persist(automovil);
        this.em.getTransaction().commit();
    }
    //Leer Automovil
    public Automovil traerAutomovil(int id) {
        return this.em.find(Automovil.class, id);
    }

    // Metodo para traer una lista de autos por el id
    public List<Automovil> traerAutomovilesPorId(int id) {
        String jpql = "SELECT a FROM Automovil a WHERE a.id = :id"; // Consulta para filtrar por id
        TypedQuery<Automovil> query = this.em.createQuery(jpql, Automovil.class);
        query.setParameter("id", id); // Pasar el parámetro id
        return query.getResultList(); // Retorna la lista de automóviles que coincidan
    }

    // Obtener todos los autos
    public List<Automovil> getAutomovil(){
        return this.em.createQuery("SELECT s FROM Automovil s", Automovil.class).getResultList();
    }
}
