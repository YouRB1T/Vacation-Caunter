package calculator.model.entity.domain;

import calculator.model.entity.IDaysPattern;
import calculator.model.entity.IWorker;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VacationPayDaysWorker implements IDaysPattern, IWorker {
    private Integer numDays;
    private BigDecimal salary;

    @Override
    public Integer getNumDays() {
        return numDays;
    }

    @Override
    public BigDecimal getSalary() {
        return salary;
    }
}
