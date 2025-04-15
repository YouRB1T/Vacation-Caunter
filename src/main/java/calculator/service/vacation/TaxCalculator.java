package calculator.service.vacation;

import calculator.model.entity.tax.Tax;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Service
public class TaxCalculator {

    private final Map<String, Tax> taxCalculators;

    public TaxCalculator(Map<String, Tax> taxCalculators) {
        this.taxCalculators = taxCalculators;
    }

    public BigDecimal calculateTotalTax(BigDecimal grossPay) {
        BigDecimal totalTaxAmount = BigDecimal.ZERO;
        for (Tax tax : taxCalculators.values()) {
            BigDecimal taxAmount = tax.calculate(grossPay);
            totalTaxAmount = totalTaxAmount.add(taxAmount);
        }
        return totalTaxAmount.setScale(2, RoundingMode.HALF_UP);
    }
}
