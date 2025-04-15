package calculator.model.entity.tax;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class NdflTax implements Tax{
    private final BigDecimal tax = new BigDecimal("0.13");
    @Override
    public BigDecimal getTax() {
        return tax;
    }

    @Override
    public BigDecimal calculate(BigDecimal gross) {
        return gross.multiply(tax);
    }
}
