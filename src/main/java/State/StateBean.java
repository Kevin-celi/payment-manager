package State;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import managerPay.IPay;

import java.util.HashMap;
import java.util.Map;

@Stateless
public class StateBean {
    @Inject
    @QualiferTypesState("ActiveState")
    private ActiveState activeState;
    @Inject
    @QualiferTypesState("InactiveState")
    private InactiveState inactiveState;

    private Map<String, IState> states;

    public StateBean() {
       states = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        states = new HashMap<>();
        states.put("Estado Activo", activeState);
        states.put("Estado Inactivo", inactiveState);

    }

    public String managementIState(String type) {
        IState state = states.get(type);
        if(state != null) {
            return state.getEstado();
        } else {
            return "Errores en el sisrema de pago: ";
        }

    }

}
