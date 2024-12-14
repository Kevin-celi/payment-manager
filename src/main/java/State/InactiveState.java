package State;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualiferTypesState("InactiveState")
public class InactiveState implements IState {
    @Override
    public String getEstado() {
        return "Cancelado";
    }
}
