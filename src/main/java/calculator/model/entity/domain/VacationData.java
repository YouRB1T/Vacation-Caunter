package calculator.model.entity.domain;

import calculator.model.entity.IVacation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VacationData implements IVacation {
    private BigDecimal vacation;

    @Override
    public BigDecimal getVacation() {
        return vacation;
    }
}
