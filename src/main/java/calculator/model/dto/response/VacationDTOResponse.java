package calculator.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class VacationDTOResponse {
    @JsonProperty("pay_vacation")
    private BigDecimal payVacation;
}
