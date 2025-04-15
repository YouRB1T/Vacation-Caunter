package calculator.service.vacation.strategy;

import calculator.model.entity.domain.VacationData;
import calculator.model.entity.domain.VacationPayDaysWorker;
import calculator.model.entity.tax.NdflTax;
import calculator.model.entity.tax.Tax;
import calculator.service.vacation.ICalculateService;
import calculator.service.vacation.TaxCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class CalculateVacationByDaysService implements ICalculateService<VacationData, VacationPayDaysWorker> {

    private static final Double AVERAGE_NUMBER_OF_WORKING_DAYS_IN_MONTH = 21.75;

    private final TaxCalculator taxCalculator = new TaxCalculator(new HashMap<>(Map.ofEntries(
            Map.entry("NdflTax", new NdflTax())
    )));

    @Override
    public VacationData calculate(VacationPayDaysWorker vacationPayDaysWorker) {
        BigDecimal salary = vacationPayDaysWorker.getSalary();
        Integer vacationDays = vacationPayDaysWorker.getNumDays();

        if (vacationDays <= 0) {
            return new VacationData(BigDecimal.ZERO);
        }

        BigDecimal dailyRate = salary.divide(
                BigDecimal.valueOf(AVERAGE_NUMBER_OF_WORKING_DAYS_IN_MONTH), 2, RoundingMode.HALF_UP);

        BigDecimal grossVacationPay = dailyRate.multiply(BigDecimal.valueOf(vacationDays))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal taxAmount = taxCalculator.calculateTotalTax(grossVacationPay);

        BigDecimal netVacationPay = grossVacationPay.subtract(taxAmount);

        return new VacationData(netVacationPay);
    }
}
