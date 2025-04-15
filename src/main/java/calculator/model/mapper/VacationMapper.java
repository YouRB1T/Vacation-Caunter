package calculator.model.mapper;

import calculator.model.entity.IVacation;
import calculator.model.entity.IWorker;

public interface VacationMapper<Entity, Request,
        Response, Vacation> {
    Entity toEntity(Request request);
    Response toResponse(Vacation entity);
}
