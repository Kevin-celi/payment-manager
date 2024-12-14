package managerPay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierTypesPay("TargetaCredito")
public class TargetaCredito implements IPay {
    @Override
    public void pay(double valor) {

    }
}
