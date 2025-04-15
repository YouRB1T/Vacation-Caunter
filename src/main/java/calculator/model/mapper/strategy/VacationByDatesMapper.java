package calculator.model.mapper.strategy;

import calculator.model.dto.request.VacationDTORequest;
import calculator.model.dto.response.VacationDTOResponse;
import calculator.model.entity.domain.VacationData;
import calculator.model.entity.domain.VacationPayDatesWorker;
import calculator.model.mapper.VacationMapper;
import org.springframework.stereotype.Component;

@Component
public class VacationByDatesMapper
        implements VacationMapper<VacationPayDatesWorker, VacationDTORequest, VacationDTOResponse, VacationData> {

    public VacationByDatesMapper() {
    }

    @Override
    public VacationPayDatesWorker toEntity(VacationDTORequest request) {
        return new VacationPayDatesWorker(
                request.getStartDate(),
                request.getEndDate(),
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
