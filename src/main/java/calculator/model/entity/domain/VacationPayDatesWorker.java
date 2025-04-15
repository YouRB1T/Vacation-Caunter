package calculator.model.entity.domain;

import calculator.model.entity.IDatesPattern;
import calculator.model.entity.IWorker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VacationPayDatesWorker implements IDatesPattern, IWorker {
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal salary;
    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public BigDecimal getSalary() {
        return salary;
    }
}
