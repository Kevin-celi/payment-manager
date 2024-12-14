package managerPay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierTypesPay("Transferencia")
public class Transferencia  implements IPay {
    @Override
    public void pay(double valor) {

    }
}
