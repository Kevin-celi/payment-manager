package ec.edu.uce.managerpay;


import Producto.*;

import EntidadBancaria.*;
import Registros.*;
import Persona.*;
import State.StateBean;

import Tarjeta.servicioTargeta;
import Tarjeta.tarjeta;
import managerPay.MetodoPago;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;

import java.util.ArrayList;
import java.util.List;



@Path("/hello-world")
public class HelloResource {

    private List<Automovil> autosCrear;
    @Inject
    private MetodoPago metodoPago;
    @Inject
    private StateBean stateBean;


    @PostConstruct
    public void init() {
        autosCrear = new ArrayList<>();
        autosCrear.add(new Automovil(1,"Chevrolet", "Spark", "compacto", "blanco",20000));
        autosCrear.add(new Automovil(2,"Chevrolet", "Camaro",  "deportivo", "amarillo",20000));
        autosCrear.add(new Automovil(3,"Chevrolet", "Traverse",  "SUV", "negro",20000));

        autosCrear.add(new Automovil(4,"Hyundai", "Elantra", "sedán", "azul",20000));
        autosCrear.add(new Automovil(5,"Hyundai", "Tucson",  "SUV", "gris",20000));
        autosCrear.add(new Automovil(6,"Hyundai", "Veloster",  "deportivo", "rojo",20000));

        autosCrear.add(new Automovil(7,"Nissan", "Sentra",  "sedán", "plateado",20000));
        autosCrear.add(new Automovil(8,"Nissan", "GT-R", "deportivo", "negro",20000));
        autosCrear.add(new Automovil(9,"Nissan", "X-Trail", "SUV", "verde",20000));
    }


    @GET
    @Produces("text/plain")
    @Path("/Crear-Autos")
    public String crearAutos() {
        // Configurar EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestor_pagos");
        EntityManager em = emf.createEntityManager();
        try {
            // Crear un servicio para manejar las entidades
            servicioAutomovil servicioAuto = new servicioAutomovil(em);
            // Persistir cada Automovil
            for (Automovil automovil : autosCrear) {
                servicioAuto.crearAuto(automovil);
            }
            return "La transacción JPA fue exitosa. lista de automoviles creados.";
        }catch (Exception e){
            return "Ocurrió un error: " + e.getMessage();
        }
    }

    @GET
    @Produces("text/json")
    @Path("/lista-autos")
    public List<Automovil> getAllStudents() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestor_pagos");
        EntityManager em = emf.createEntityManager();
        // Crear un servicio para manejar las entidades
        servicioAutomovil servicioAuto = new servicioAutomovil(em);
        return servicioAuto.getAutomovil();
    }

    @GET
    @Produces("text/json")
    @Path("/lista-Datos")
    public String crarDatos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestor_pagos");
        EntityManager em = emf.createEntityManager();

        try {
            servicioUsuario servicioUsuario = new servicioUsuario(em);
            serviciosBancos serviciosBancos = new serviciosBancos(em);
            servicioTargeta servicioTargeta = new servicioTargeta(em);

            Usuario persona = servicioUsuario.crearUsuario(new Usuario(1,"kevin","celi","chillogallo","0968685229","usuario1@gmail.com"));
            Usuario persona2 = servicioUsuario.crearUsuario(new Usuario(2,"julian","arcos","forestal","0968685672","usuario2@gmail.com"));
            Usuario persona3 = servicioUsuario.crearUsuario(new Usuario(3,"Ana","Andrade","Cumbaya","0965856722","usuario3@gmail.com"));

            cuentaBancaria cuenta = serviciosBancos.CrearBanco(new cuentaBancaria("Pichincha", "1435623456", 50000, "Ahorros", persona));
            cuentaBancaria cuenta2 = serviciosBancos.CrearBanco(new cuentaBancaria("Pichincha", "1435623457", 50000, "Ahorros", persona2));
            cuentaBancaria cuenta3 = serviciosBancos.CrearBanco(new cuentaBancaria("Pichincha", "1435623458", 50000, "Ahorros", persona3));
            cuentaBancaria cuenta4 = serviciosBancos.CrearBanco(new cuentaBancaria("Pichincha", "1435623278", 1000000, "Corriente", persona3));

            tarjeta tarjetaCred = servicioTargeta.crearTarjeta(new tarjeta("Credito",cuenta));
            tarjeta tarjetaCred1 = servicioTargeta.crearTarjeta(new tarjeta("Debito",cuenta2));
            tarjeta tarjetaCred2 = servicioTargeta.crearTarjeta(new tarjeta("Debito",cuenta4));

            return "Datos Creados ¡Exitosamente";
        }catch (Exception e) {
            return "Ocurrió un error: " + e.getMessage();
        }
    }

    @GET
    @Produces("text/json")
    @Path("/ID-usuario/{usuarioID}")
    public Usuario leerUsuario(@PathParam("usuarioID") int usuarioID) {
        // Configurar EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestor_pagos");
        EntityManager em = emf.createEntityManager();

        servicioUsuario servicioUsuario = new servicioUsuario(em);
        return servicioUsuario.leertUsuario(usuarioID);
    }

    @GET
    @Produces("text/json")
    @Path("/ID-Cuenta/{cuentaID}")
    public cuentaBancaria leerCuenta(@PathParam("cuentaID") int cuentaID) {
        // Configurar EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestor_pagos");
        EntityManager em = emf.createEntityManager();

        serviciosBancos serviciosBancos = new serviciosBancos(em);
        return serviciosBancos.leerCuenta(2);
    }

    @GET
    @Produces("text/json")
    @Path("/id-registro/{idRegistro}/id-auto/{IdAuto}/tipo de Pago/{tipo}/monto-pagar/{monto}/numero-cuenta/{cuenta}")
    public String registroCompra(
            @PathParam("idRegistro") int idRegistro,
            @PathParam("IdAuto") int IdAuto,
            @PathParam("tipo") String tipo,
            @PathParam("monto") int monto,
            @PathParam("cuenta") String cuenta
    ){

        // Configurar EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestor_pagos");
        EntityManager em = emf.createEntityManager();
        serviciosBancos serviciosBancos = new serviciosBancos(em);

        if (metodoPago.managementIpay(tipo,monto)==true &&
                serviciosBancos.validarCuenta(cuenta)== true){
            try {
                serviciosRegistro serviciosRegistro = new serviciosRegistro(em);
                servicioAutomovil servicioAutomovil = new servicioAutomovil(em);

                RegistrosPago registroUno = serviciosRegistro.crearRegistro(new RegistrosPago(
                        idRegistro,
                        "14-12-4",
                        monto,
                        stateBean.managementIState("Estado Activo"),
                        tipo,
                        serviciosBancos.leerCuentaUno(cuenta),
                        servicioAutomovil.traerAutomovilesPorId(IdAuto)

                ));
                return " ¡Registro de compra registrada";
            }catch (Exception e){
                return "Ocurrió un error al crear la entidad: " + e.getMessage();
            }
        }
        return "algo salio mal";
    }

    /**
     *
     * Esta funcion conmfirma el pago y actualiza el estado de pendiente a cancelado
     * pasando como parametros el numero de cuenta de la persona  y el ID de registro
     */
    @GET
    @Produces("text/json")
    @Path("/State/{numCuenta}/registro-pago/{IdRegistro}")
    public String updateState(
            @PathParam("numCuenta") String numCuenta,
            @PathParam("IdRegistro") int registroId
    ){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestor_pagos");
        EntityManager em = emf.createEntityManager();

        serviciosBancos serviciosBancos = new serviciosBancos(em);
        serviciosRegistro serviciosRegistro = new serviciosRegistro(em);
        if( serviciosBancos.validarCuenta(numCuenta)== true){

            RegistrosPago updateState = serviciosRegistro.leerRegistros(registroId);
            updateState.setEstado(
                    stateBean.managementIState("Estado Inactivo")
            );
            serviciosRegistro.actualizarEstado(updateState);
            return "registro actualizado";
        }else {
            return "algo salio mal";
        }
    }

    /**
     * Esta funcion me imprimira el registro de pago a traves del id del registro
     *
     */
    @GET
    @Produces("text/json")
    @Path("/mostrar-registro/{registro}")
    public RegistrosPago leerRegistro(@PathParam("registro") int registroId){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestor_pagos");
        EntityManager em = emf.createEntityManager();
        serviciosRegistro serviciosRegistro = new serviciosRegistro(em);
        return serviciosRegistro.leerRegistros(registroId);
    }
}

