package managerPay;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@QualifierTypesPay("PayPal")
public class PayPal  implements IPay {
    @Override
    public void pay(double valor) {

    }
}
