package calculator.service.vacation.facade;

import calculator.model.dto.request.VacationDTORequest;
import calculator.model.dto.response.VacationDTOResponse;
import calculator.model.mapper.strategy.VacationByDatesMapper;
import calculator.model.mapper.strategy.VacationByDaysMapper;
import calculator.service.vacation.ICalculateService;
import calculator.service.vacation.strategy.CalculateVacationByDateService;
import calculator.service.vacation.strategy.CalculateVacationByDaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CalculatorVacation implements ICalculateService<VacationDTOResponse, VacationDTORequest> {
    @Autowired
    private VacationByDaysMapper vacationByDaysMapper;
    @Autowired
    private VacationByDatesMapper vacationByDatesMapper;

    @Autowired
    private CalculateVacationByDaysService calculateVacationByDaysService;
    @Autowired
    private CalculateVacationByDateService calculateVacationByDateService;

    @Override
    public VacationDTOResponse calculate(VacationDTORequest request) {
        Integer numDays = request.getNumDays();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();

        boolean hasNumDays = numDays != null;
        boolean hasDates = startDate != null && endDate != null;

        if (!hasNumDays && !hasDates) {
            return new VacationDTOResponse(BigDecimal.ZERO);
        }

        if (hasDates) {
            return vacationByDatesMapper.toResponse(
                    calculateVacationByDateService.calculate(
                            vacationByDatesMapper.toEntity(request)
                    )
            );
        }

        return vacationByDaysMapper.toResponse(
                calculateVacationByDaysService.calculate(
                        vacationByDaysMapper.toEntity(request)
                )
        );
    }
}
