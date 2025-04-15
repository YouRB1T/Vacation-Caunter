import calculator.model.entity.domain.VacationData;
import calculator.model.entity.domain.VacationPayDaysWorker;
import calculator.service.vacation.strategy.CalculateVacationByDaysService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculateVacationByDaysServiceTest {

    private final CalculateVacationByDaysService service = new CalculateVacationByDaysService();

    @Test
    void calculate_shouldReturnCorrectVacationPay() {
        VacationPayDaysWorker worker = new VacationPayDaysWorker(10, BigDecimal.valueOf(60000));

        VacationData result = service.calculate(worker);

        // Daily rate = 60000 / 21.75 ≈ 2758.62
        // Gross = 2758.62 * 10 = 27586.20
        // Tax = 13% (Ndfl) = 3586.21
        // Vacations = 27586.20 - 3586.21 = 23999.99
        assertNotNull(result);
        assertEquals(0, result.getVacation().compareTo(BigDecimal.valueOf(23999.99)));
    }

    @Test
    void calculate_shouldReturnZeroForZeroVacationDays() {
        VacationPayDaysWorker worker = new VacationPayDaysWorker(0, BigDecimal.valueOf(50000));

        VacationData result = service.calculate(worker);

        assertEquals(BigDecimal.ZERO, result.getVacation());
    }

    @Test
    void calculate_shouldReturnZeroForNegativeVacationDays() {
        VacationPayDaysWorker worker = new VacationPayDaysWorker(-5, BigDecimal.valueOf(50000));

        VacationData result = service.calculate(worker);

        assertEquals(BigDecimal.ZERO, result.getVacation());
    }
}
