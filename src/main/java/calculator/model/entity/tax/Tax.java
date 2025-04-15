package calculator.model.entity.tax;

import java.math.BigDecimal;

public interface Tax {
    BigDecimal getTax();
    BigDecimal calculate(BigDecimal gross);
}
