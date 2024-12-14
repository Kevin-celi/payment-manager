package managerPay;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.Map;
@ApplicationScoped
public class MetodoPago {

    @Inject
    @QualifierTypesPay("PayPal")
    private PayPal payPal;
    @Inject
    @QualifierTypesPay("Transferencia")
     private Transferencia transferencia;
    @Inject
    @QualifierTypesPay("TargetaCredito")
     private TargetaCredito targetaCredito;

    private Map<String, IPay> pay;

    public MetodoPago() {
        pay = new HashMap<>();
    }
    @PostConstruct
    public void init() {
        pay = new HashMap<>();
        pay.put("PayPal", payPal);
        pay.put("transferencia", transferencia);
        pay.put("targetaCredito", targetaCredito);
    }

    public boolean managementIpay(String type,int payTotal) {
        IPay payUno = pay.get(type);
        if(payUno != null) {
            payUno.pay(payTotal);
            return true;
        } else {
            System.out.println("Tipo de pago no soportado: ");
        }
        return false;
    }

}
