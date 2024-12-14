package EntidadBancaria;

import EntidadBancaria.cuentaBancaria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class serviciosBancos {
    private EntityManager em;
    public serviciosBancos(EntityManager em) {

        this.em = em;
    }

    // Crear Cuentas Bancarias
    public cuentaBancaria CrearBanco(cuentaBancaria banco) {
        em.getTransaction().begin();
        em.persist(banco);
        em.getTransaction().commit();
        return banco;
    }

    // Leer Cuenta por id
    public cuentaBancaria leerCuenta(int id) {
        return this.em.find(cuentaBancaria.class, id);
    }

    // Leer Cuenta por numero de cuenta
    public cuentaBancaria leerCuentaUno(String numeroCuenta) {
        try {
            // Consulta segura usando parámetros
            String query = "SELECT c FROM cuentaBancaria c WHERE c.numeroCuenta = :numCuenta";
            TypedQuery<cuentaBancaria> typedQuery = em.createQuery(query, cuentaBancaria.class);
            typedQuery.setParameter("numCuenta", numeroCuenta);

            return typedQuery.getSingleResult(); // Devuelve la cuenta encontrada
        } catch (NoResultException e) {
            // Si no encuentra resultados, devuelve null
            return null;
        } catch (Exception e) {
            // Maneja errores inesperados
            e.printStackTrace();
            return null;
        }
    }
    // Validar cuenta bancaria
    public boolean validarCuenta(String numCuenta) {
        try {
            // Consulta segura usando parámetros nombrados
            String query = "SELECT COUNT(c) FROM cuentaBancaria c WHERE c.numeroCuenta = :numCuenta";
            TypedQuery<Long> typedQuery = em.createQuery(query, Long.class);
            typedQuery.setParameter("numCuenta", numCuenta);

            Long count = typedQuery.getSingleResult();
            return count > 0; // Si existe al menos un resultado, la cuenta es válida
        } catch (Exception e) {
            // Manejar errores genéricos si ocurren
            e.printStackTrace();
            return false;
        }
    }


}
