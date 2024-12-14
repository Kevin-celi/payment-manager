package State;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualiferTypesState("ActiveState")
public class ActiveState implements IState {
    @Override
    public String getEstado() {
        return "Pendiente";
    }
}
