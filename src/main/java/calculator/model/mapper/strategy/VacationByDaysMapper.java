package calculator.model.mapper.strategy;

import calculator.model.dto.request.VacationDTORequest;
import calculator.model.dto.response.VacationDTOResponse;
import calculator.model.entity.domain.VacationData;
import calculator.model.entity.domain.VacationPayDaysWorker;
import calculator.model.mapper.VacationMapper;
import org.springframework.stereotype.Component;

@Component
public class VacationByDaysMapper
        implements VacationMapper<VacationPayDaysWorker, VacationDTORequest, VacationDTOResponse, VacationData> {
    public VacationByDaysMapper() {
    }

    @Override
    public VacationPayDaysWorker toEntity(VacationDTORequest request) {
        return new VacationPayDaysWorker(
                request.getNumDays(),
                request.getSalary()
        );
    }

    @Override
    public VacationDTOResponse toResponse(VacationData entity) {
        return new VacationDTOResponse(
                entity.getVacation()
        );
    }
}
