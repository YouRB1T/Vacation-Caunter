import calculator.model.entity.domain.VacationData;
import calculator.model.entity.domain.VacationPayDatesWorker;
import calculator.service.vacation.strategy.CalculateVacationByDateService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class CalculateVacationByDateServiceTest {

    private final CalculateVacationByDateService service = new CalculateVacationByDateService();

    @Test
    void calculate_shouldReturnCorrectVacationPayForWorkingDays() {
        // 2024-04-08 (Пон) to 2024-04-12 (Пят) — 5 рабочих дней
        VacationPayDatesWorker worker = new VacationPayDatesWorker(
                LocalDate.of(2024, 4, 8),
                LocalDate.of(2024, 4, 12),
                BigDecimal.valueOf(65000)
        );

        VacationData result = service.calculate(worker);

        // Daily rate ≈ 2988.51
        // Gross = 2988.51 * 5 ≈ 14942.55
        // Tax = 13% ≈ 1942.53
        // Vacations ≈ 13000.02
        assertNotNull(result);
        assertEquals(0, result.getVacation().compareTo(BigDecimal.valueOf(13000.02)));
    }

    @Test
    void calculate_shouldReturnZeroForOnlyWeekendDates() {
        // Суббота и воскресенье
        VacationPayDatesWorker worker = new VacationPayDatesWorker(
                LocalDate.of(2024, 4, 6),
                LocalDate.of(2024, 4, 7),
                BigDecimal.valueOf(60000)
        );

        VacationData result = service.calculate(worker);

        assertEquals(BigDecimal.ZERO, result.getVacation());
    }

    @Test
    void calculate_shouldReturnZeroIfDatesAreEqualAndWeekend() {
        VacationPayDatesWorker worker = new VacationPayDatesWorker(
                LocalDate.of(2024, 4, 7),  // Воскресенье
                LocalDate.of(2024, 4, 7),
                BigDecimal.valueOf(60000)
        );

        VacationData result = service.calculate(worker);

        assertEquals(BigDecimal.ZERO, result.getVacation());
    }
}

