package calculator.service.vacation.strategy;

import calculator.model.entity.domain.VacationData;
import calculator.model.entity.domain.VacationPayDatesWorker;
import calculator.model.entity.tax.NdflTax;
import calculator.service.vacation.ICalculateService;
import calculator.service.vacation.TaxCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalculateVacationByDateService implements ICalculateService<VacationData, VacationPayDatesWorker> {

    private final Double AVERAGE_NUMBER_OF_WORKING_DAYS_IN_MONTH = 21.75;

    private final TaxCalculator taxCalculator = new TaxCalculator(new HashMap<>(Map.ofEntries(
            Map.entry("NdflTax", new NdflTax())
    ))
    );

    @Override
    public VacationData calculate(VacationPayDatesWorker vacationByDatesWorker) {
        BigDecimal monthRate = vacationByDatesWorker.getSalary();
        List<LocalDate> vacationDates = generateDatesBetween(
                vacationByDatesWorker.getStartDate(),
                vacationByDatesWorker.getEndDate()
        );

        Integer workingDays = calculateWorkingDays(vacationDates);

        if (workingDays == 0) {
            return new VacationData(BigDecimal.ZERO);
        }

        BigDecimal dailyRate = monthRate.divide(BigDecimal.valueOf(AVERAGE_NUMBER_OF_WORKING_DAYS_IN_MONTH), 2, RoundingMode.HALF_UP);

        BigDecimal grossVacationPay = dailyRate.multiply(BigDecimal.valueOf(workingDays)).setScale(2, RoundingMode.HALF_UP);

        BigDecimal taxAmount = taxCalculator.calculateTotalTax(grossVacationPay);
        BigDecimal netVacationPay = grossVacationPay.subtract(taxAmount);

        return new VacationData(netVacationPay);
    }

    private Integer calculateWorkingDays(List<LocalDate> datesOff) {
        Integer workingDays = 0;
        for (LocalDate date : datesOff) {
            if (isWorkingDay(date)) {
                workingDays++;
            }
        }
        return workingDays;
    }

    private boolean isWorkingDay(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    private List<LocalDate> generateDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }

}
