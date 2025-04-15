package calculator.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class VacationDTORequest {

    @JsonProperty("salary")
    @NotNull(message = "Зарплата обязательна для указания")
    @DecimalMin(value = "0.0", inclusive = false, message = "Зарплата должна быть положительной")
    private BigDecimal salary;

    @JsonProperty("num_days")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 1, message = "Количество дней должно быть не меньше 1")
    private Integer numDays;

    @JsonProperty("start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate startDate;

    @JsonProperty("end_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate endDate;
}
