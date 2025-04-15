package calculator.controller;

import calculator.model.dto.request.VacationDTORequest;
import calculator.model.dto.response.VacationDTOResponse;
import calculator.service.vacation.facade.CalculatorVacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class CalculateController {

    @Autowired
    private CalculatorVacation calculatorVacation;

    @GetMapping("/calculacte")
    public ResponseEntity<VacationDTOResponse> calculate(@Valid @RequestBody VacationDTORequest request) {
        try {
            return ResponseEntity.ok(calculatorVacation.calculate(request));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.valueOf(404));
        }
    }
}
